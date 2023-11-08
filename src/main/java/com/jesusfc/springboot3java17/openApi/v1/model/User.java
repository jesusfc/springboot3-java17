package com.jesusfc.springboot3java17.openApi.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.List;

/**
 * A customer
 */
@Schema(description = "A user")
@Data
@Validated
public class User {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("familyName")
    private String familyName;

    @JsonProperty("email")
    @Schema(nullable = true)
    private String email;

    @JsonProperty("enable")
    private boolean enable;

    @JsonProperty("createAt")
    private LocalDate createAt;

    @JsonProperty("address")
    private Address address;

    @JsonProperty("videoClub")
    private List<VideoClub> videoClub;

    public User id(Long id) {
        this.id = id;
        return this;
    }

}
