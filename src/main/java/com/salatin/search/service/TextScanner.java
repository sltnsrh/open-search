package com.salatin.search.service;

import com.salatin.search.model.Sentence;
import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface TextScanner {
    List<Sentence> findAllSentences(MultipartFile source) throws IOException;
}
