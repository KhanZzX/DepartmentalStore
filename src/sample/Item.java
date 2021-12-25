package sample;
import javafx.beans.property.SimpleStringProperty;

public class Item {
    SimpleStringProperty itemName;
    SimpleStringProperty itemQuantity;
    SimpleStringProperty itemBuyPrice;
    SimpleStringProperty itemRetailPrice;
    SimpleStringProperty itemWeight;

    @Override
    public String toString() {
        return "Item{" +
                "itemName=" + itemName +
                ", itemQuantity=" + itemQuantity +
                ", itemBuyPrice=" + itemBuyPrice +
                ", itemRetailPrice=" + itemRetailPrice +
                ", itemWeight=" + itemWeight +
                ", itemExpDate=" + itemExpDate +
                ", itemMfgDate=" + itemMfgDate +
                ", itemBarCode=" + itemBarCode +
                '}';
    }

    SimpleStringProperty itemExpDate;
    SimpleStringProperty itemMfgDate;
    SimpleStringProperty itemBarCode;

    public Item(String itemName, String itemQuantity, String itemRetailPrice, String itemWeight, String itemExpDate, String itemMfgDate, String itemBarCode, String itemBuyPrice) {
        this.itemName = new SimpleStringProperty(itemName);
        this.itemQuantity = new SimpleStringProperty(itemQuantity);
        this.itemBuyPrice = new SimpleStringProperty(itemBuyPrice);
        this.itemRetailPrice = new SimpleStringProperty(itemRetailPrice);
        this.itemWeight = new SimpleStringProperty(itemWeight);
        this.itemExpDate = new SimpleStringProperty(itemExpDate);
        this.itemMfgDate = new SimpleStringProperty(itemMfgDate);
        this.itemBarCode = new SimpleStringProperty(itemBarCode);
    }

    public String getItemName() {
        return itemName.get();
    }

    public SimpleStringProperty itemNameProperty() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName.set(itemName);
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

    public String getItemBuyPrice() {
        return itemBuyPrice.get();
    }

    public SimpleStringProperty itemBuyPriceProperty() {
        return itemBuyPrice;
    }

    public void setItemBuyPrice(String itemBuyPrice) {
        this.itemBuyPrice.set(itemBuyPrice);
    }

    public String getItemRetailPrice() {
        return itemRetailPrice.get();
    }

    public SimpleStringProperty itemRetailPriceProperty() {
        return itemRetailPrice;
    }

    public void setItemRetailPrice(String itemRetailPrice) {
        this.itemRetailPrice.set(itemRetailPrice);
    }

    public String getItemWeight() {
        return itemWeight.get();
    }

    public SimpleStringProperty itemWeightProperty() {
        return itemWeight;
    }

    public void setItemWeight(String itemWeight) {
        this.itemWeight.set(itemWeight);
    }

    public String getItemExpDate() {
        return itemExpDate.get();
    }

    public SimpleStringProperty itemExpDateProperty() {
        return itemExpDate;
    }

    public void setItemExpDate(String itemExpDate) {
        this.itemExpDate.set(itemExpDate);
    }

    public String getItemMfgDate() {
        return itemMfgDate.get();
    }

    public SimpleStringProperty itemMfgDateProperty() {
        return itemMfgDate;
    }

    public void setItemMfgDate(String itemMfgDate) {
        this.itemMfgDate.set(itemMfgDate);
    }

    public String getItemBarCode() {
        return itemBarCode.get();
    }

    public SimpleStringProperty itemBarCodeProperty() {
        return itemBarCode;
    }

    public void setItemBarCode(String itemBarCode) {
        this.itemBarCode.set(itemBarCode);
    }
}
