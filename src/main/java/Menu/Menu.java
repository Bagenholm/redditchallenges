package Menu;

import Utils.Handler;

import java.util.ArrayList;

public class Menu {

    Handler handler;

    private ArrayList<Selectable> menuOptions = new ArrayList<>();
    private ArrayList<String> menuChoice = new ArrayList<>();

    public Menu(Handler handler) {
        this.handler = handler;
    }

    public void add(String choice, Selectable option) {
        menuOptions.add(option);
        menuChoice.add(choice);
    }

    public void show() {
        for (int i = 0; i < menuChoice.size(); i++) {
            System.out.println(i + 1 + ": " + menuChoice.get(i));
        }
    }

    public int choose() {
        return handler.getInput().verifyInt(1, menuChoice.size());
    }

    public void run() {
        while(true) {
            show();
            execute(choose());
        }
    }

    private void execute(int choice) {
        menuOptions.get(choice - 1).select();
    }
}
