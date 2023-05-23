package com.jesusfc.springboot3java17.controller.rest;

import com.jesusfc.springboot3java17.database.entity.CollectionEntity;
import com.jesusfc.springboot3java17.database.repository.CollectionRepository;
import com.jesusfc.springboot3java17.openApi.v1.api.ICollectionDocumentation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

/**
 * @author jesusfc
 * Created on may 2023
 */
@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/rest/")
public class CollectionRestController  implements ICollectionDocumentation {

    private final CollectionRepository collectionRepository;

    @Override
    public ResponseEntity<List<CollectionEntity>> getCollectionList(Locale locale) {
        List<CollectionEntity> all = collectionRepository.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }
}
