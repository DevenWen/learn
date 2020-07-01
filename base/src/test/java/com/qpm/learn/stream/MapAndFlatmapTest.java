package com.qpm.learn.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class MapAndFlatmapTest {


    @Test
    public void testMap() {
        List<String> words = new ArrayList<>();
        words.add("Hello");
        words.add("World");

        List<String[]> collect = words.stream().map(word -> {
            return word.split("");
        }).collect(Collectors.toList());

        assertEquals("H", collect.get(0)[0]);
    }


    /**
     * flatMap 把不同的 Stream 合并为一个 Stream，并传到下游
     */
    @Test
    public void testFlatMap() {
        List<String> words = new ArrayList<>();
        words.add("Hello");
        words.add("World");
        List<String> collect = words.stream().map(word -> word.split("")).flatMap(wordArray -> Arrays.stream(wordArray)).collect(Collectors.toList());
        assertEquals("H", collect.get(0));
    }


}