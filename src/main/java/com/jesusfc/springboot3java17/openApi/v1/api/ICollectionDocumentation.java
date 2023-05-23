package com.jesusfc.springboot3java17.openApi.v1.api;

import com.jesusfc.springboot3java17.database.entity.CollectionEntity;
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

/**
 * @author jesusfc
 * Created on may 2023
 */
@Validated
public interface ICollectionDocumentation {
    @Operation(
            summary = "Get a list of collection",
            description = "Get a list of a **Collection list**",
            security = {
                    @SecurityRequirement(name = "BasicAuth"),
                    @SecurityRequirement(name = "JwtAutoToken")
            },
            tags = {"Film"}
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List of collection",
                            content =
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Product.class)))),

                    @ApiResponse(responseCode = "404", description = "Not Found")}
    )
    @RequestMapping(value = "/v1/collection/list", produces = {"application/json"}, method = RequestMethod.GET)
    ResponseEntity<List<CollectionEntity>> getCollectionList(Locale locale);
}
