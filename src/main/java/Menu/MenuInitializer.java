package Menu;

import Scrape.PrisjaktScraper;
import Utils.Handler;

public class MenuInitializer {

    Handler handler;

    public MenuInitializer(Handler handler){
        this.handler = handler;
    }

    public void initializeMenus() {
        Menu mainMenu = new Menu(handler);

        mainMenu.add("Scrape the most popular phones from Prisjakt.nu", () -> handler.setPhones(PrisjaktScraper.scrapePhones()));
        mainMenu.add("Print scraped phones", () -> handler.getPhones().forEach(System.out::println));
        mainMenu.add("Sort phones by kr/rating", () -> handler.getPhones().sort((p1, p2) -> (int)p1.getPricePerRating() - (int)p2.getPricePerRating()));
        mainMenu.add("Sort phones by kr/inch", () -> handler.getPhones().sort((p1, p2) -> (int) p1.getPricePerInch() - (int)p2.getPricePerInch() ));
        mainMenu.add("Sort phones by kr/rating/inch", () -> handler.getPhones().sort((p1, p2) -> (int) p1.getPricePerRatingPerInch() - (int)p2.getPricePerRatingPerInch()));
        mainMenu.add("Reddit challenge 238, Fallout Hack Game", () -> handler.getChallenges().get(0).initialize());
        mainMenu.add("Print image to ascii", () -> System.out.println(handler.asciiImage()));
        mainMenu.add("Quit", () -> System.exit(0));

        mainMenu.run();
    }
}
