package Scrape;

public class Phone {

    public Phone(String link, int price) {
        this.link = link;
        this.price = price;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public void setAmountRatings(int amountRatings) {
        this.amountRatings = amountRatings;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    private String productName, link;
    private Float rating = 1F;
    private Float screenSize;
    private int amountRatings, price;

    public Float getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(Float screenSize) {
        this.screenSize = screenSize;
    }

    public float getPricePerRating() {
        return price / rating;
    }

    public float getPricePerInch() {
        return price / screenSize;
    }

    public String getProductName() {
        return productName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Float getRating() {
        return rating;
    }

    public int getAmountRatings() {
        return amountRatings;
    }

    public int getPrice() {
        return price;
    }

    public float getPricePerRatingPerInch() {
        return getPricePerRating() / screenSize;
    }

//    @Override
//    public String toString() {
//        return "[ " + productName + ": kr/rating="+getPricePerRating() + " kr/inch="+getPricePerInch() + " kr/rating/inch=" + getPricePerRatingPerInch() + " ]";
//    }

        @Override
    public String toString() {
        return "[ " + productName + ", Screen size: " + screenSize + ", Price: " + price + ", Rating: " + rating + ", #Ratings: " + amountRatings + " Price per rating: " + getPricePerRating() + ", Price per inch: " + getPricePerInch() + ", Price per rating per pinch: " + getPricePerRatingPerInch() +  " ]";
    }
}
