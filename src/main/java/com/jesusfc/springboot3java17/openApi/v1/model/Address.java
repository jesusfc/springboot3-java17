package com.jesusfc.springboot3java17.openApi.v1.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * Address
 */
@Validated
public class Address {
    @JsonProperty("line1")
    private String line1 = null;

    @JsonProperty("city")
    private String city = null;

    /**
     * 2 Letter State Code
     */
    public enum StateCodeEnum {
        AL("AL"),

        AK("AK"),

        AZ("AZ"),

        AR("AR"),

        CA("CA");

        private String value;

        StateCodeEnum(String value) {
            this.value = value;
        }

        @Override
        @JsonValue
        public String toString() {
            return String.valueOf(value);
        }

        @JsonCreator
        public static StateCodeEnum fromValue(String text) {
            for (StateCodeEnum b : StateCodeEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }
    }

    @JsonProperty("stateCode")
    private StateCodeEnum stateCode = null;

    @JsonProperty("zipCode")
    private String zipCode = null;

    public Address line1(String line1) {
        this.line1 = line1;
        return this;
    }

    /**
     * Get line1
     *
     * @return line1
     **/
    @Schema(example = "123 main", description = "")

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public Address city(String city) {
        this.city = city;
        return this;
    }

    /**
     * Get city
     *
     * @return city
     **/
    @Schema(example = "St Pete", description = "")

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Address stateCode(StateCodeEnum stateCode) {
        this.stateCode = stateCode;
        return this;
    }

    /**
     * 2 Letter State Code
     *
     * @return stateCode
     **/
    @Schema(description = "2 Letter State Code")

    @Size(min = 2, max = 2)
    public StateCodeEnum getStateCode() {
        return stateCode;
    }

    public void setStateCode(StateCodeEnum stateCode) {
        this.stateCode = stateCode;
    }

    public Address zipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    /**
     * Get zipCode
     *
     * @return zipCode
     **/
    @Schema(example = "33701", description = "")

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Address address = (Address) o;
        return Objects.equals(this.line1, address.line1) &&
                Objects.equals(this.city, address.city) &&
                Objects.equals(this.stateCode, address.stateCode) &&
                Objects.equals(this.zipCode, address.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(line1, city, stateCode, zipCode);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Address {\n");

        sb.append("    line1: ").append(toIndentedString(line1)).append("\n");
        sb.append("    city: ").append(toIndentedString(city)).append("\n");
        sb.append("    stateCode: ").append(toIndentedString(stateCode)).append("\n");
        sb.append("    zipCode: ").append(toIndentedString(zipCode)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
