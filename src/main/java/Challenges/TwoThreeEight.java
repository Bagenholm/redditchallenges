package Challenges;

import Utils.Handler;

import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;

// https://www.reddit.com/r/dailyprogrammer/comments/3qjnil/20151028_challenge_238_intermediate_fallout/

public class TwoThreeEight implements Challenge {

    Handler handler;

    List<String> words;
    List<String> guesses = new ArrayList<>();
    List<Integer> wordMatches = new ArrayList<>();

    public TwoThreeEight(Handler handler) {
        this.handler = handler;
    }

    public void game() {
        int guesses = 0;
        boolean answer = false;
        words = randomWords();
        String correctWord = words.get((int) (Math.random() * ((words.size() - 1))));

        while (guesses < 5 && !answer) {
            words.stream().forEach(System.out::println);
            answer = checkWord(handler.getInput().verifyString().toUpperCase(), correctWord);
            guesses++;
            System.out.println(" Guesses made: " + guesses + "\n");
        }
        System.out.println("\n You failed. Correct word: " + correctWord);
    }

    private boolean checkWord(String verifyString, String correctWord) {
        int match = 0;

        if (verifyString.equals(correctWord)) {
            System.out.println("YOU WON!");
            System.exit(0);
        }


        for (int i = 0; i < verifyString.length(); i++) {
            if(correctWord.charAt(i) == verifyString.charAt(i)) {
                match++;
            }
        }

        System.out.println("\n Correct letters: " + match);
        printPreviousGuesses(verifyString, match);
        return false;
    }

    private List<String> randomWords() {
        int length = (int) (Math.random() * ((9 - 4) + 1) + 4);
        int amountOfWords = 12;

        List<String> rightLength =
        handler.getDictionary().getWordlist().stream().filter(string -> string.length() == length )
                .collect(Collectors.toList());

        List<String> randomWords = new ArrayList<>();

        for (int i = 0; i < amountOfWords; i++) {
            randomWords.add( rightLength.get( (int) (Math.random() * ((rightLength.size()) ))).toUpperCase());
        }
        return randomWords;
    }

    private void printPreviousGuesses(String newGuess, int matches) {
        guesses.add(newGuess);
        wordMatches.add(matches);

        for (int i = 0; i < guesses.size(); i++) {
            System.out.println(guesses.get(i) + ": " + wordMatches.get(i));
        }
    }

    @Override
    public void initialize() {
        handler.getDictionary().loadWordlist();
        game();
    }
}
