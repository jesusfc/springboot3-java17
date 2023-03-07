package com.jesusfc.springboot3java17.openApi.v1.api;

import com.jesusfc.springboot3java17.openApi.v1.model.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Locale;

@Validated
public interface IProduct {
    @Operation(
            summary = "Get a list",
            description = "Get a list of a **Product list**",
            security = {
                    @SecurityRequirement(name = "BasicAuth"),
                    @SecurityRequirement(name = "JwtAutoToken")
            },
            tags = {"Product"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List of products",
                            content =
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Product.class)))),

                    @ApiResponse(responseCode = "404", description = "Not Found")}
    )
    @RequestMapping(value = "/v1/product/list", produces = {"application/json"}, method = RequestMethod.GET)
    ResponseEntity<List<Product>> getProductList(Locale locale);
}
