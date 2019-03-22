package Menu;

import Utils.Handler;

public class MenuInitializer {

    Handler handler;

    public MenuInitializer(Handler handler){
        this.handler = handler;
    }

    public void initializeMenus() {
        Menu mainMenu = new Menu(handler);

        mainMenu.add("Reddit challenge 238, Fallout Hack Game", () -> handler.getChallenges().get(0).initialize());
        mainMenu.add("Quit", () -> System.exit(0));

        mainMenu.run();
    }
}
