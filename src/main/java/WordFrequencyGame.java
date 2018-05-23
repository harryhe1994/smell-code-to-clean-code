import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

/**
 * Created by jxzhong on 2018/5/22.
 */
public class WordFrequencyGame {
    public String play(String inputStr) {

        String[] wordStrList = inputStr.split("\\s+");

        List<Word> wordList = buildWordList(wordStrList);

        wordList = caculateWordsCount(wordList);

        sortWords(wordList);

        String wordsReport = formatWordReport(wordList);

        return wordsReport;

    }

    private String formatWordReport(List<Word> wordList) {

        return wordList.stream()
                .map( word -> word.getValue() + " " + word.getWordCount()).collect(joining("\n"));
    }

    private void sortWords(List<Word> wordList) {
        wordList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());
    }

    private List<Word> caculateWordsCount(List<Word> wordList) {

        return wordList.stream()
                .collect(Collectors.groupingBy(Word::getValue))
                .entrySet().stream()
                .map(entry -> new Word(entry.getKey(), entry.getValue().size()))
                .collect(Collectors.toList());
    }

    private List<Word> buildWordList(String[] wordStrList) {
        return Arrays.stream(wordStrList).map(s -> new Word(s, 1)).collect(Collectors.toList());
    }

}
