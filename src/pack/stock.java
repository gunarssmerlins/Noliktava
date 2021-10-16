package pack;

import java.sql.Time;
import java.util.Date;

public class stock {
    private int ID;
    private String customer;
    private String item;
    private int itemsPerBox;
    private int pcs;
    private String stockOperator;
    private Date dateOfOperations;
    private Time timeOfOperations;

    // Konstruktors
    public stock(int id, String customer, String item, int itemsPerBox, int pcs, String stockOperator,
                 Date dateOfOperations, Time timeOfOperations) {
        ID = id;
        this.customer = customer;
        this.item = item;
        this.itemsPerBox = itemsPerBox;
        this.pcs = pcs;
        this.stockOperator = stockOperator;
        this.dateOfOperations = dateOfOperations;
        this.timeOfOperations = timeOfOperations;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setItemsPerBox(int itemsPerBox) {
        this.itemsPerBox = itemsPerBox;
    }

    public void setPcs(int pcs) {
        this.pcs = pcs;
    }

    public void setStockOperator(String stockOperator) {
        this.stockOperator = stockOperator;
    }

    public void setDateOfOperations(Date dateOfOperations) {
        this.dateOfOperations = dateOfOperations;
    }

    public void setTimeOfOperations(Time timeOfOperations) {
        this.timeOfOperations = timeOfOperations;
    }

    public int getID() {
        return ID;
    }

    public String getCustomer() {
        return customer;
    }

    public String getItem() {
        return item;
    }

    public int getItemsPerBox() {
        return itemsPerBox;
    }

    public int getPcs() {
        return pcs;
    }

    public String getStockOperator() {
        return stockOperator;
    }

    public Date getDateOfOperations() {
        return dateOfOperations;
    }

    public Time getTimeOfOperations() {
        return timeOfOperations;
    }
}
