package Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dictionary {

    private List<String> wordlist = new ArrayList<String>();
    private Scanner scanner;

    public void loadWordlist() {

        File file = new File("src\\main\\resources\\wordlist.txt");

        try {
            scanner = new Scanner(file);

            while(scanner.hasNext()) {
                wordlist.add(scanner.next());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }

    }

    public List<String> getWordlist() {
        return wordlist;
    }
}
