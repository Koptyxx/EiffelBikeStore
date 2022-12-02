/**
 * Bike.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package fr.uge.eiffelbikestore.shopWebService;

public class Bike  implements java.io.Serializable {
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


    /**
     * Gets the id value for this Bike.
     * 
     * @return id
     */
    public long getId() {
        return id;
    }


    /**
     * Sets the id value for this Bike.
     * 
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }


    /**
     * Gets the price value for this Bike.
     * 
     * @return price
     */
    public long getPrice() {
        return price;
    }


    /**
     * Sets the price value for this Bike.
     * 
     * @param price
     */
    public void setPrice(long price) {
        this.price = price;
    }


}
