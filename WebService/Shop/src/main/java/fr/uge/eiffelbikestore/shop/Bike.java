package fr.uge.eiffelbikestore.shop;

public class Bike {
    private long id;

    private long price;

    public Bike() {
    }

    public Bike(
           long id,
           long price) {
           this.id = id;
           this.price = price;
    }


    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }


    public long getPrice() {
        return price;
    }


    public void setPrice(long price) {
        this.price = price;
    }


}
