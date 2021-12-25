package sample;
import javafx.beans.property.SimpleStringProperty;

import javax.swing.table.TableModel;

public class AddItmForClient {
    private SimpleStringProperty name;
    private SimpleStringProperty itemQuantity;
    private SimpleStringProperty price;
    private SimpleStringProperty weight;
    private SimpleStringProperty expDate;

    @Override
    public String toString() {
        return "AddItmForClient{" +
                "name=" + name +
                ", itemQuantity=" + itemQuantity +
                ", price=" + price +
                ", weight=" + weight +
                ", expDate=" + expDate +
                ", mfgDate=" + mfgDate +
                ", serial=" + serial +
                ", buyPrice=" + buyPrice +
                '}';
    }

    private SimpleStringProperty mfgDate;
    private SimpleStringProperty serial;
    private SimpleStringProperty buyPrice;

    public AddItmForClient(String name, String itemQuantity, String price, String weight, String expDate, String mfgDate, String serial, String buyPrice )
    {
        this.name = new SimpleStringProperty(name);
        String p = String.valueOf(price);
        this.itemQuantity = new SimpleStringProperty(itemQuantity);
        this.price = new SimpleStringProperty(p);
        this.weight = new SimpleStringProperty(weight);
        this.expDate = new SimpleStringProperty(expDate);
        this.mfgDate = new SimpleStringProperty(mfgDate);
        this.serial = new SimpleStringProperty(serial);
        this.buyPrice = new SimpleStringProperty(buyPrice);
    }

    public String getExpDate() {
        return expDate.get();
    }

    public SimpleStringProperty expDateProperty() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate.set(expDate);
    }

    public String getMfgDate() {
        return mfgDate.get();
    }

    public SimpleStringProperty mfgDateProperty() {
        return mfgDate;
    }

    public void setMfgDate(String mfgDate) {
        this.mfgDate.set(mfgDate);
    }

    public String getSerial() {
        return serial.get();
    }

    public SimpleStringProperty serialProperty() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial.set(serial);
    }

    public String getBuyPrice() {
        return buyPrice.get();
    }

    public SimpleStringProperty buyPriceProperty() {
        return buyPrice;
    }

    public void setBuyPrice(String buyPrice) {
        this.buyPrice.set(buyPrice);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getItemQuantity() {
        return itemQuantity.get();
    }

    public SimpleStringProperty itemQuantityProperty() {
        return itemQuantity;
    }

    public void setItemQuantity(String itemQuantity) {
        this.itemQuantity.set(itemQuantity);
    }



    public String getPrice() {
        return price.get();
    }

    public SimpleStringProperty priceProperty() {
        return price;
    }

    public void setPrice(String price) {
        this.price.set(price);
    }

    public String getWeight() {
        return weight.get();
    }

    public SimpleStringProperty weightProperty() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight.set(weight);
    }




}
