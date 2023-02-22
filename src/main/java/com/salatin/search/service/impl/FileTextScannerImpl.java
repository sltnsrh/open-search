package com.salatin.search.service.impl;

import com.salatin.search.model.Sentence;
import com.salatin.search.service.TextScanner;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileTextScannerImpl implements TextScanner {

    private static final String RECOGNISE_SENTENCE_RESOURCE = "src/main/resources/en-sent.bin";

    @Override
    public List<Sentence> findAllSentences(MultipartFile source) throws IOException {
        InputStream inputStream = new FileInputStream(RECOGNISE_SENTENCE_RESOURCE);

        SentenceModel model = new SentenceModel(inputStream);
        SentenceDetectorME detector = new SentenceDetectorME(model);

        String text = new String(source.getBytes());
        String[] sentences = detector.sentDetect(text);

        return getListOfSentences(sentences);
    }

    private List<Sentence> getListOfSentences(String[] sentences) {
        AtomicInteger position = new AtomicInteger(0);
        return Arrays.stream(sentences)
            .map(sentence -> {
                sentence = sentence
                    .replaceAll("[\\n?\\r]", " ")
                    .replaceAll("\\t", "")
                    .replaceAll("\\s+", " ");
                int sentenceLength = sentence.length();
                return new Sentence(sentence, position.getAndAdd(sentenceLength), sentenceLength);
            })
            .toList();
    }
}
