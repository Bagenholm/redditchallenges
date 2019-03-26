package Scrape;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PrisjaktScraper {

    static Document doc = null;

    static ArrayList<Phone> phones = new ArrayList<>();

    public static ArrayList<Phone> scrapePhones() {
        try {
            doc = Jsoup.connect("https://classic.prisjakt.nu/kategori.php?l=103").get();
            scrapePrice();
            scrapeProductName();
            scrapeRatings();
            scrapeScreenSize();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return phones;
    }

    private static void scrapeScreenSize() {
        Element produktlista = doc.getElementById("div_produktlista");
        ArrayList<Float> screenSizes = new ArrayList<>();
        doc.getElementsByClass("cell-bar")
                .forEach( element -> screenSizes.add( Float.parseFloat(element.text().substring(0, element.text().indexOf(" ")).replace(",", ".") )));

        for (int i = 0; i < phones.size(); i++) {
            phones.get(i).setScreenSize(screenSizes.get(i));
        }
    }

    private static void scrapePrice() {
        ArrayList<String> linkList = new ArrayList<>();
        ArrayList<String> priceList = new ArrayList<>();

        doc.getElementsByClass("price").stream()
                .forEach(element -> {
                    linkList.add(element.attr("href"));
                    priceList.add(element.text());
                });

        for (int i = 0; i < linkList.size(); i++) {
            String priceWithSpace = priceList.get(i).substring(0, priceList.get(i).indexOf(":") );
            String priceWithoutSpace = priceWithSpace.replace(" ", "");
            phones.add(new Phone(linkList.get(i), Integer.parseInt(priceWithoutSpace)));
        }
    }

    private static void scrapeRatings() {
        Element produktlista = doc.getElementById("div_produktlista");
        ArrayList<String> elementsToParse = new ArrayList<>();
        Map<String, String> amountRatingsMap = new HashMap<>();
        Map<String, String> ratingsMap = new HashMap<>();

        produktlista.getElementsByAttribute("href").stream().forEach(element -> element.getElementsByAttributeValueContaining("title", "recension")
                .forEach(element1 -> elementsToParse.add(element1.getElementsByAttribute("href").toString())));

        //Irrelevant table header on first index.
        elementsToParse.remove(0);
        elementsToParse.stream().filter(s -> s.length() > 0).forEach(s -> {
            String link = s.substring(s.indexOf("\"")+1, s.indexOf("\" "));
            link = link.replace("o=", "p=");
            ratingsMap.put(link, s.substring(s.indexOf("lt=\"")+4, s.indexOf("/10")));
            amountRatingsMap.put(link, s.substring(s.indexOf("le=\"")+4, s.indexOf(" rec")));
        });

        phones.stream().filter(phone -> ratingsMap.containsKey(phone.getLink())).forEach(phone -> {
            phone.setAmountRatings(Integer.parseInt(amountRatingsMap.get(phone.getLink())));
            phone.setRating(Float.parseFloat(ratingsMap.get(phone.getLink())));
        });
    }

    private static void scrapeProductName() {
        Map<String, String> modelsMap = new HashMap<>();

        Element produktlista = doc.getElementById("div_produktlista");
        produktlista.getElementsByAttribute("href").stream().filter(element -> !element.hasAttr("rel"))
                .forEach(element -> modelsMap.put(element.attr("href"), element.text()));

        phones.stream().forEach( phone -> phone.setProductName(modelsMap.get(phone.getLink())) );
    }
}