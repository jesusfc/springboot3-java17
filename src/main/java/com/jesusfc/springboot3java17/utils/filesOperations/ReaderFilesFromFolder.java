package com.jesusfc.springboot3java17.utils.filesOperations;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
//@SpringBootApplication
public class ReaderFilesFromFolder {

    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final String SEPARATOR_CSV = ";";
    private static final String FOLDER_LOCATION = "/home/jesusfc/Escritorio/metadata_22_06/";

    private static final String FOLDER_PRODUCT_CODE_CSV_FILE = "/home/jesusfc/Escritorio/productId_code_ProductsVimeo.csv";

    private static final String FOLDER_PRODUCT_CODE_TXT_FILE = "/home/jesusfc/Escritorio/trainer_product_10-10-2023.txt";

    static final String DB_URL = "jdbc:mysql://157.230.102.76:3306/eshitv_test?autoReconnect=true&useSSL=false";
    static final String USER = "root";
    static final String PASS = "64xUP5Tv";


    public static void main(String[] args) {
        //createInsertForTrainers();
        //loadSimpleProductList();
        //loadCollectionProductList();
        openSingleTXTFile();
    }

    private static void createInsertForTrainers() {

        /*
        -- 219,MARÍATOSH
        -- 220,FRANCO VALICENTI
        -- 221,ANDRÉS BRAGANZA
        -- 222,ALBA TRIÑANES
        -- 223,LAURA LAKSHMI
        224,CLARA ROSELL
        */

        String trainerId = "224";
        String trainerName = "CLARA ROSELL";

        Set<String> resultIds = new TreeSet<>();

        // Vimeo Id, clubSystem Id
        Map<Long, String> vimeoIdClubSystemId = openSingleCSVFile();
        List<Product> productList = loadProductList();
        productList.forEach(product -> {
            if (product.getTitle().toLowerCase().contains(trainerName.toLowerCase())) {
                Integer productId = product.getProductId();
                System.out.println(productId);
                String clubSystemProductId = vimeoIdClubSystemId.get(productId.longValue());
                if (clubSystemProductId != null) {
                    resultIds.add(clubSystemProductId);
                }
            }
        });

        // Pinta resultado
        resultIds.forEach(prodId -> {
            String INSERT_QUERY = "insert into trainer_product (fk_trainer, fk_product) values (#TRAINER_ID#, #PRODUCT_ID#);";
            String insert = INSERT_QUERY.replace("#TRAINER_ID#", trainerId).replace("#PRODUCT_ID#", prodId);
            System.out.println(insert);
        });


    }


    private static void loadSimpleProductList() throws IOException {
        StringBuilder productCSVList = getProductCSVList();

        ByteArrayInputStream byteArrayOutputStream = new ByteArrayInputStream(String.valueOf(productCSVList).getBytes(StandardCharsets.UTF_8));

        // File name
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH:mm");
        String formattedDateTime = LocalDateTime.now().format(dateTimeFormatter);
        String csvFileName = "Products-List.csv";

        File source = new File("/home/jesusfc/Escritorio/" + csvFileName);
        try (FileOutputStream out = new FileOutputStream(source)) {
            IOUtils.copy(byteArrayOutputStream, out);
        }
    }

    private static void loadCollectionProductList() throws IOException {
        StringBuilder productCSVList = getProductByCollectionCSVList();

        ByteArrayInputStream byteArrayOutputStream = new ByteArrayInputStream(String.valueOf(productCSVList).getBytes(StandardCharsets.UTF_8));

        // File name
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH:mm");
        String formattedDateTime = LocalDateTime.now().format(dateTimeFormatter);
        String csvFileName = "Collection-Products-List.csv";

        File source = new File("/home/jesusfc/Escritorio/" + csvFileName);
        try (FileOutputStream out = new FileOutputStream(source)) {
            IOUtils.copy(byteArrayOutputStream, out);
        }
    }

    private static StringBuilder getProductByCollectionCSVList() {
        Map<String, List<Product>> productsByCollection = getProductsByCollectionListMap();

        StringBuilder sb = new StringBuilder();
        getCSVCollectionHeader(sb);

        productsByCollection.forEach((collectionTitle, collectionProductList) -> {

            for (Product product : collectionProductList) {

                // Escape rich text
                String shortDescription = StringUtils.isBlank(product.getShortDescription()) ? "" : product.getShortDescription()
                        .replaceAll("\\<.*?\\>", "").replaceAll("\\n", "");
                shortDescription = StringEscapeUtils.unescapeHtml4(shortDescription);

                // Escape rich text
                String prodDescription = StringUtils.isBlank(product.getDescription()) ? "" : product.getDescription()
                        .replaceAll("\\<.*?\\>", "").replaceAll("\\n", "");
                prodDescription = StringEscapeUtils.unescapeHtml4(prodDescription);

                String[] productFile = {
                        collectionTitle,
                        product.getProductId().toString(),
                        StringUtils.isBlank(product.getTitle()) ? "" : product.getTitle(),
                        shortDescription,
                        prodDescription,
                        CollectionUtils.isEmpty(product.getPrimaryCollections()) ? "" : converListToString(product.getPrimaryCollections()),
                        CollectionUtils.isEmpty(product.getCollections()) ? "" : converListToString(product.getCollections()),
                        CollectionUtils.isEmpty(product.getCategories()) ? "" : converListToString(product.getCategories()),
                        StringUtils.isBlank(product.getThumbnailDiv()) ? "" : product.getThumbnailDiv()
                };
                sb.append(NEW_LINE_SEPARATOR);
                fillCsvLine(sb, productFile);
            }
        });

        return sb;
    }

    private static Map<String, List<Product>> getProductsByCollectionListMap() {
        List<Product> productList = loadProductList();
        Set<String> collect = productList.stream().flatMap(product -> product.getCollections().stream()).collect(Collectors.toSet());
        Map<String, List<Product>> productsByCollection = new HashMap<>();
        collect.forEach(title -> {
            productList.forEach(product -> {
                product.getCollections().forEach(collection -> {
                    if (collection.equalsIgnoreCase(title)) {
                        if (productsByCollection.containsKey(title)) {
                            List<Product> productList1 = productsByCollection.get(title);
                            productList1.add(product);
                            productsByCollection.put(title, productList1);
                        } else {
                            List<Product> productListNew = new ArrayList<>();
                            productListNew.add(product);
                            productsByCollection.put(title, productListNew);
                        }
                    }
                });
            });
        });
        return productsByCollection;
    }

    private static StringBuilder getProductCSVList() {
        List<Product> productList = loadProductList();
        StringBuilder sb = new StringBuilder();
        getCSVHeader(sb);
        for (Product product : productList) {

            // Escape rich text
            String shortDescription = StringUtils.isBlank(product.getShortDescription()) ? "" : product.getShortDescription()
                    .replaceAll("\\<.*?\\>", "").replaceAll("\\n", "");
            shortDescription = StringEscapeUtils.unescapeHtml4(shortDescription);

            // Escape rich text
            String prodDescription = StringUtils.isBlank(product.getDescription()) ? "" : product.getDescription()
                    .replaceAll("\\<.*?\\>", "").replaceAll("\\n", "");
            prodDescription = StringEscapeUtils.unescapeHtml4(prodDescription);

            String[] productFile = {
                    product.getProductId().toString(),
                    StringUtils.isBlank(product.getTitle()) ? "" : product.getTitle(),
                    shortDescription,
                    prodDescription,
                    CollectionUtils.isEmpty(product.getPrimaryCollections()) ? "" : converListToString(product.getPrimaryCollections()),
                    CollectionUtils.isEmpty(product.getCollections()) ? "" : converListToString(product.getCollections()),
                    CollectionUtils.isEmpty(product.getCategories()) ? "" : converListToString(product.getCategories()),
                    StringUtils.isBlank(product.getThumbnailDiv()) ? "" : product.getThumbnailDiv()
            };
            sb.append(NEW_LINE_SEPARATOR);
            fillCsvLine(sb, productFile);
        }
        return sb;
    }


    private static List<Product> loadProductList() {
        List<String> fileNameList = readFolderFileName();
        List<Product> productList = new ArrayList<>();
        fileNameList.forEach(fileName -> {
            Integer productId = Integer.valueOf(fileName.split("\\.")[0]);
            productList.add(readFile(productId, FOLDER_LOCATION + fileName));
        });
        return productList;
    }

    private static Map<Long, String> openSingleCSVFile() {

        Map<Long, String> result = new HashMap<>();

        // Create an object of filereader
        // class with CSV file as a parameter.
        try {
            FileReader filereader = new FileReader(FOLDER_PRODUCT_CODE_CSV_FILE);
            // create csvReader object passing
            // file reader as a parameter
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;


            // we are going to read data line by line
            while ((nextRecord = csvReader.readNext()) != null) {
                result.put(Long.parseLong(nextRecord[1]), nextRecord[0]);
            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private static Map<Long, Long> openSingleTXTFile() {

        Map<Long, Long> result = new HashMap<>();

        File archivo;
        FileReader fr = null;
        BufferedReader br;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File(FOLDER_PRODUCT_CODE_TXT_FILE);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            while ((linea = br.readLine()) != null) {
                String s = StringUtils.substringBetween(linea, "(", ")");
                String[] split = s.split(",");
                result.put(Long.parseLong(split[0]), Long.parseLong(split[1]));
            }
        } catch (Exception e) {
            log.debug(e.getMessage());
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta
            // una excepcion.
            try {
                if (null != fr) fr.close();
            } catch (Exception e2) {
                log.debug(e2.getMessage());
            }
        }
        return result;
    }

    private static Product readFile(Integer productId, String urlFile) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Product product = objectMapper.readValue(new File(urlFile), Product.class);
            product.setProductId(productId);
            return product;
        } catch (Exception ex) {
            System.out.println("Error urlFile: " + urlFile + " - " + ex);
        }
        return null;
    }

    private static List<String> readFolderFileName() {
        return Stream.of(Objects.requireNonNull(new File(ReaderFilesFromFolder.FOLDER_LOCATION).listFiles()))
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toList());
    }

    private static void getCSVHeader(StringBuilder sb) {
        sb.append("productId").append(ReaderFilesFromFolder.SEPARATOR_CSV).append("title").append(ReaderFilesFromFolder.SEPARATOR_CSV).append("shortDescription").append(ReaderFilesFromFolder.SEPARATOR_CSV).append("description").append(ReaderFilesFromFolder.SEPARATOR_CSV).append("primaryCollections").append(ReaderFilesFromFolder.SEPARATOR_CSV).append("collections").append(ReaderFilesFromFolder.SEPARATOR_CSV).append("categories").append(ReaderFilesFromFolder.SEPARATOR_CSV).append("thumbnailDiv");
    }

    private static void getCSVCollectionHeader(StringBuilder sb) {
        sb.append("collection").append(ReaderFilesFromFolder.SEPARATOR_CSV).append("productId").append(ReaderFilesFromFolder.SEPARATOR_CSV).append("title").append(ReaderFilesFromFolder.SEPARATOR_CSV).append("shortDescription").append(ReaderFilesFromFolder.SEPARATOR_CSV).append("description").append(ReaderFilesFromFolder.SEPARATOR_CSV).append("primaryCollections").append(ReaderFilesFromFolder.SEPARATOR_CSV).append("collections").append(ReaderFilesFromFolder.SEPARATOR_CSV).append("categories").append(ReaderFilesFromFolder.SEPARATOR_CSV).append("thumbnailDiv");
    }

    private static void fillCsvLine(StringBuilder sb, String[] productFile) {
        for (String columnCSV : productFile) {
            sb.append(getStringWithoutDoubleComa(columnCSV.trim())).append(ReaderFilesFromFolder.SEPARATOR_CSV);
        }
    }

    private static String getStringWithoutDoubleComa(String value) {
        return value.replace("'", "\\'").replace("\"", "\'");
    }

    private static String converListToString(List<String> stringList) {
        return String.join(" | ", stringList);
    }
}
