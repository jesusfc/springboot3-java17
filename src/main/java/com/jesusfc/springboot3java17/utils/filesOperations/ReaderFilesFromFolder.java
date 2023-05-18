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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


//@SpringBootApplication
public class ReaderFilesFromFolder {

    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final String SEPARATOR_CSV = ";";
    private static final String FOLDER_LOCATION = "/home/jesusfc/Descargas/vimeo_meta_data/metadata/";

    public static void main(String[] args) throws IOException {
        loadSimpleProductList();
        loadCollectionProductList();
    }

    public static void loadSimpleProductList() throws IOException {
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

    public static void loadCollectionProductList() throws IOException {
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

    public static StringBuilder getProductByCollectionCSVList() {
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

    public static StringBuilder getProductCSVList() {
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
