package com.jesusfc.springboot3java17.openApi.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

/**
 * @author jesusfc
 * Created on may 2023
 */
@Validated
@Data
@Schema(description = "A Film")
public class Film {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("filmCode")
    private String filmCode;
    @JsonProperty("title")
    private String title;
    @JsonProperty("videoClub")
    private VideoClub videoClub;

}
