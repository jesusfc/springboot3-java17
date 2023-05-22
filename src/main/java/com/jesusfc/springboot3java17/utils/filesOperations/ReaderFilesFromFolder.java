package com.jesusfc.springboot3java17.utils.filesOperations;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.util.CollectionUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


//@SpringBootApplication
public class ReaderFilesFromFolder {

    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final String SEPARATOR_CSV = ";";
    private static final String FOLDER_LOCATION = "/home/jesusfc/Escritorio/metadata_22_06/";


    static final String DB_URL = "jdbc:mysql://157.230.102.76:3306/eshitv_test?autoReconnect=true&useSSL=false";
    static final String USER = "root";
    static final String PASS = "64xUP5Tv";


    public static void main(String[] args) throws IOException {

        createInsertProducts();
        //loadSimpleProductList();
        //loadCollectionProductList();
    }

    private static void createInsertProducts() {
        String vimeo_categoria = "ZUMBA® y Baile deportivo";
        Map<String, List<Product>> productsByCollection = getProductsByCollectionListMap();
        List<Product> productsByCurrentCollections = productsByCollection.get(vimeo_categoria);
        // System.out.println(productsByCurrentCollections.get(0));
        Product product = productsByCurrentCollections.get(0);

        String QUERY = "select id, code, title from product where code = 'V1234'";
        String INSERT_QUERY = "insert into image (data) values ('IMAGE_VALUE')";
        String SQL_INSERT_QUERY = INSERT_QUERY.replace("IMAGE_VALUE", product.getThumbnail_b64());
        System.out.println(SQL_INSERT_QUERY);

        // Open a connection
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(QUERY);
        ) {


            // Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                System.out.print("ID: " + rs.getInt("id"));
                System.out.print(", CODE: " + rs.getString("code"));
                System.out.print(", TITLE: " + rs.getString("title"));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


/*

       String insertSql = "insert into product (code, title, subtitle, description, duration, completed_time, fk_video, fk_trailer,\n" +
                "                                 premiere, premiere_end_date, published, publication_date, publication_end_date,\n" +
                "                                 fk_image, fk_image_portrait, fk_thumbnail, fk_thumbnail_portrait, producer,\n" +
                "                                 producer_type, subtitle_idx, fk_club, level, material, mental_level, technical_level,\n" +
                "                                 physical_level)\n" +
                "values ();");


        System.out.println(

 */
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
