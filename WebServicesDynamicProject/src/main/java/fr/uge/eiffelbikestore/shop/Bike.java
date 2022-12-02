/**
 * Bike.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package fr.uge.eiffelbikestore.shop;

import java.util.Objects;

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
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Bike bike = (Bike) o;
        return id == bike.id && price == bike.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, price);
    }
}
