package com.jesusfc.springboot3java17.utils.filesOperations;

import lombok.Data;

import java.util.List;

@Data
public class Product {
    private Integer productId;
    private String title;
    private String shortDescription;
    private String description;
    private List<String> primaryCollections;
    private List<String> collections;
    private List<String> categories;
    private String thumbnailDiv;
    private String thumbnail_b64;
}
