package com.jesusfc.springboot3java17.openApi.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * A customer
 */
@Schema(description = "A customer")
@Validated
public class Customer {
    @JsonProperty("id")
    private UUID id = null;

    @JsonProperty("name")
    private String name = null;

    @JsonProperty("familyName")
    private String familyName = null;

    @JsonProperty("email")
    private String email = null;

    @JsonProperty("enable")
    private Boolean enable = false;

    @JsonProperty("createAt")
    private LocalDate createAt = null;

    @JsonProperty("address")
    private Address address = null;

    public Customer id(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Get id
     *
     * @return id
     **/
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "")

    @Valid
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Customer name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get name
     *
     * @return name
     **/
    @Schema(example = "Jesus", description = "")

    @Size(min = 3, max = 30)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Customer familyName(String familyName) {
        this.familyName = familyName;
        return this;
    }

    /**
     * Get familyName
     *
     * @return familyName
     **/
    @Schema(example = "Fdez.", description = "")

    @Size(min = 3)
    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public Customer email(String email) {
        this.email = email;
        return this;
    }

    /**
     * Get email
     *
     * @return email
     **/
    @Schema(description = "")

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Customer enable(Boolean enable) {
        this.enable = enable;
        return this;
    }

    /**
     * Get enable
     *
     * @return enable
     **/
    @Schema(description = "")

    public Boolean isEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Customer createAt(LocalDate createAt) {
        this.createAt = createAt;
        return this;
    }

    /**
     * Get createAt
     *
     * @return createAt
     **/
    @Schema(description = "")

    @Valid
    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    public Customer address(Address address) {
        this.address = address;
        return this;
    }

    /**
     * Get address
     *
     * @return address
     **/
    @Schema(description = "")

    @Valid
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Customer customer = (Customer) o;
        return Objects.equals(this.id, customer.id) &&
                Objects.equals(this.name, customer.name) &&
                Objects.equals(this.familyName, customer.familyName) &&
                Objects.equals(this.email, customer.email) &&
                Objects.equals(this.enable, customer.enable) &&
                Objects.equals(this.createAt, customer.createAt) &&
                Objects.equals(this.address, customer.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, familyName, email, enable, createAt, address);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Customer {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    familyName: ").append(toIndentedString(familyName)).append("\n");
        sb.append("    email: ").append(toIndentedString(email)).append("\n");
        sb.append("    enable: ").append(toIndentedString(enable)).append("\n");
        sb.append("    createAt: ").append(toIndentedString(createAt)).append("\n");
        sb.append("    address: ").append(toIndentedString(address)).append("\n");
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
