package sample;
public class ItemHistory {
    private String itemDateOfPurchase;
    private String itemName;
    private String itemQuantity;
    private String itemWeight;
    private String itemBuyPrice;
    private String itemRetailPrice;

    public ItemHistory(String itemDateOfPurchase, String itemName, String itemQuantity, String itemWeight, String itemBuyPrice, String itemRetailPrice) {
        this.itemDateOfPurchase = itemDateOfPurchase;
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
        this.itemWeight = itemWeight;
        this.itemBuyPrice = itemBuyPrice;
        this.itemRetailPrice = itemRetailPrice;
    }

    public String getItemDateOfPurchase() {
        return itemDateOfPurchase;
    }

    public void setItemDateOfPurchase(String itemDateOfPurchase) {
        this.itemDateOfPurchase = itemDateOfPurchase;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(String itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public String getItemWeight() {
        return itemWeight;
    }

    public void setItemWeight(String itemWeight) {
        this.itemWeight = itemWeight;
    }

    public String getItemBuyPrice() {
        return itemBuyPrice;
    }

    public void setItemBuyPrice(String itemBuyPrice) {
        this.itemBuyPrice = itemBuyPrice;
    }

    public String getItemRetailPrice() {
        return itemRetailPrice;
    }

    public void setItemRetailPrice(String itemRetailPrice) {
        this.itemRetailPrice = itemRetailPrice;
    }
}
