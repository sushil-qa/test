package com.longestwords;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by longestwords on 5/6/18.
 */
@RunWith(Parameterized.class)
public class LengthyWordCounterTest extends TestCase{

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                //blank input
                {"", 0, null},
                //null input
                {null, 0, null},
                //valid input
                {"Hello, who are you hello", 5, Stream.of("hello").collect(Collectors.toSet())},
                {"Hello, who are you", 5, Stream.of("hello").collect(Collectors.toSet())},
                {"Hi,: there's b3       mother-in-law's", 15, Stream.of("mother-in-law's").collect(Collectors.toSet())},
                {"                      hello        , who are you?", 5, Stream.of("hello").collect(Collectors.toSet())},
                {"The cow jumped over the moon.", 6, Stream.of("jumped").collect(Collectors.toSet())},
                {"The cow jumped over the yellow moon.", 6, Stream.of("yellow", "jumped").collect(Collectors.toSet())}
        });
    }

    private String input;
    private int expectedLength;
    private Set expectedWordSet;

    public LengthyWordCounterTest(String sentence, int length, Set wordSet) {
        this.input = sentence;
        this.expectedLength = length;
        this.expectedWordSet = wordSet;
    }

    @Test
    public void testMaxLengthWordCounterDInput() throws Exception {
        LengthyWordCounter.MyResult result = LengthyWordCounter.maxLengthWordCounter(input);
        Assert.assertEquals("Result set does not match expected", expectedWordSet, result.getLengthyWordsSet());
        Assert.assertEquals("Result length does not match expected", expectedLength, result.getMaxLength());
    }
}