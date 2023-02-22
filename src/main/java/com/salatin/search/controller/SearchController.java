package com.salatin.search.controller;

import com.salatin.search.model.Sentence;
import com.salatin.search.service.TextScanner;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class SearchController {
    private final TextScanner textScanner;

    @PostMapping(
        path = "/sentences",
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Sentence>> findSentences(
        @RequestPart("file") MultipartFile file
    ) throws IOException {
        return ResponseEntity.ok(textScanner.findAllSentences(file));
    }
}
