package com.salatin.search.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Sentence {
    private String sentence;
    private int position;
    private int length;
}
