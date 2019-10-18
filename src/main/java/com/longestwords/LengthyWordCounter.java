package com.longestwords;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

/**
 * The LengthyWordCounter program determines the largest word(s) in a sentence and the length of the word(s).
 *
 * @author  Binu
 * @version 1.0
 * @since   1.0
 */
public class LengthyWordCounter {

    private static final Logger LOGGER = LogManager.getLogger("LengthyWordCounter");

    //creating a new result class
    public static class MyResult{
        public Set getLengthyWordsSet() {
            return lengthyWordsSet;
        }

        public int getMaxLength() {
            return maxLength;
        }

        //this variable will hold the set of lengthy words
        private Set lengthyWordsSet;
        //this variable will hold the length of longest word(s)
        private int maxLength;

        public MyResult(Set wordsSet, int length){
            lengthyWordsSet=wordsSet;
            maxLength=length;
        }
    }

    /**
     *
     * This method is used to determine the longest word(s) in a sentence and return the word(s)
     * as well as the length of the words
     * @param inputString This is the input string to be analyzed
     * @return MyResult This is an object consisting of a list of the longest words and the length
     *                  of the word(s)
     * */
    public static MyResult maxLengthWordCounter(String inputString) {
        
        //if input string is "" return a MyResult object with values null and 0. Also log a message
        if("".equals(inputString)) {
            LOGGER.info("Black input. Please enter a valid sentence input");
            return new MyResult(null, 0);
        }
        //if input string is null return a MyResult object with values null and 0. Also log a message
        if(inputString==null) {
            LOGGER.info("Null input. Please enter a valid sentence input");
            return new MyResult(null, 0);
        }

        int maxLength = 0;

        //Convert input sentence to a string array
        String[] words = sentenceToArrayConverter(inputString);

        //Using a set to make sure only unique words are returned as answer
        Set<String> longWords = new HashSet<>();
        longWords.clear();

        //this loop will calculate length of each word and create a set of longest word(s)
        for(String word: words) {
            if(word.length() > maxLength) {
                longWords.clear();
                longWords.add(word);
                maxLength = word.length();
            } else if(word.length() == maxLength) {
                longWords.add(word);
            }
        }

        LOGGER.info("Maxlength: "+maxLength+" Long word(s): "+longWords);
        return new MyResult(longWords, maxLength);
    }

    /**
     *
     * This method is used to split an input string into words. The split is
     * based on a regular expression. A word consists of alphabets, digits
     * hyphens and paranthesis.
     * @param inputString This is the input string to be analyzed
     * @return String[] This is an array of all the words
     * */
    private static String[] sentenceToArrayConverter(String inputString) {
        //trim sentence an convert to lowercase
        String trimmedInput = inputString.trim().toLowerCase();

        //convert string in to array of words containing all alphabets, digits, hyphens and paranthesis
        String[] inputWords = trimmedInput.split("[^a-zA-Z'0-9-()]+");
        LOGGER.info("Total Words: "+inputWords.length);

        //Log all the words of string extracted above
        for(String s: inputWords) {
            LOGGER.info(s);
        }

        return inputWords;
    }
}
