package Utils;

import Challenges.Challenge;
import Challenges.TwoFourThree;
import Challenges.TwoThreeEight;
import Menu.MenuInitializer;
import Scrape.Phone;

import java.util.ArrayList;

public class Handler {

    private InputManager input = new InputManager();
    private Dictionary dictionary = new Dictionary();
    private ArrayList<Challenge> challenges = new ArrayList();

    public ArrayList<Phone> getPhones() {
        return phones;
    }

    public void setPhones(ArrayList<Phone> phones) {
        this.phones = phones;
    }

    MenuInitializer menuInitializer = new MenuInitializer(this);
    private ArrayList<Phone> phones = new ArrayList<>();

    public void init() {
        initChallenges();
        menuInitializer.initializeMenus();
    }

    private void initChallenges() {
        challenges.add(new TwoThreeEight(this));
        challenges.add(new TwoFourThree(this));
    }

    public String asciiImage() {
        AsciiImage image = new AsciiImage();
        return image.newImage();
    }

    public ArrayList<Challenge> getChallenges() {
        return challenges;
    }

    public InputManager getInput() {
        return input;
    }

    public Dictionary getDictionary() {
        return dictionary;
    }
}
