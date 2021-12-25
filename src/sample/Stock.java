package sample;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class Stock extends Application
{
    String FILENAME = "D:\\Items.txt";

    private Label lblMainTitle;
    private Label lblItemName;
    private Label lblItemQuantity;
    private Label lblItemBarcodeNum;
    private Label lblItemPrice;
    private Label lblItemWeight;
    private Label lblItemExpDate;
    private Label lblItemMfgDate;
    private Label lblItemBuyPrice;
    private Label lblItemNameErrorMsg;
    private Label lblItemQuantityErrorMsg;
    private Label lblItemBarcodeNumErrorMsg;
    private Label lblItemPriceErrorMsg;
    private Label lblItemWeightErrorMsg;
    private Label lblItemExpDateErrorMsg;
    private Label lblItemMfgDateErrorMsg;
    private Label lblItemBuyPriceErrorMsg;
    private TextField fieldItemName;
    private TextField fieldItemQuantity;
    private TextField fieldItemPrice;
    private TextField fieldItemWeight;
    private TextField fieldItemExpDate;
    private TextField fieldItemMfgDate;
    private TextField fieldItemBarcodeNum;
    private TextField fieldItemBuyPrice;
    private TextField fieldItemFind;
    private Button btnAddItem;
    private Button btnFindItem;
    private AnchorPane ap;
    private Scene s;
    private Scene s1;
    private BorderPane bp;
    private MenuItem menuItemClose;
    private TableView<Item> tableItem;
    private DatePicker dpExpDate;
    private DatePicker dpMfgDate;
    private TableColumn c1;
    private TableColumn c2;
    private TableColumn c3;
    private TableColumn c4;
    private TableColumn c5;
    private TableColumn c6;
    private TableColumn c7;
    private TableColumn c8;
    private LocalDate i;

    private ItemClass itemClass;

    @Override
    public void start(Stage primaryStage)
    {
        itemClass = new ItemClass();
        primaryStage.setTitle("General Store");
        primaryStage.setHeight(680);
        ap = new AnchorPane();
        components();
        menuBar();
        menuItemClose.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });

        s1 = new Scene(bp);
        primaryStage.setWidth(1200);
        primaryStage.show();
        addItemInTable();
        primaryStage.setScene(s1);
    }
    public void components()
    {
        lblMainTitle=new Label("STOCK IN STORE");
        lblMainTitle.setLayoutX(400);
        lblMainTitle.setLayoutY(10);
        lblMainTitle.setFont(new Font("Arial",30));
        ap.getChildren().add(lblMainTitle);

        lblItemName=new Label("Item Name ");
        lblItemName.setLayoutX(10);
        lblItemName.setLayoutY(70);
        lblItemName.setFont(new Font("Arial",12));
        ap.getChildren().add(lblItemName);

        lblItemPrice=new Label("Item Price ");
        lblItemPrice.setLayoutX(260);
        lblItemPrice.setLayoutY(70);
        lblItemPrice.setFont(new Font("Arial",12));
        ap.getChildren().add(lblItemPrice);

        lblItemQuantity=new Label("Item Quantity ");
        lblItemQuantity.setLayoutX(510);
        lblItemQuantity.setLayoutY(70);
        lblItemQuantity.setFont(new Font("Arial",12));
        ap.getChildren().add(lblItemQuantity);

        lblItemWeight= new Label("Item Weight ");
        lblItemWeight.setLayoutX(780);
        lblItemWeight.setLayoutY(70);
        lblItemWeight.setFont(new Font("Arial",12));
        ap.getChildren().add(lblItemWeight);

        lblItemExpDate=new Label("Item Expiry Date ");
        lblItemExpDate.setLayoutX(10);
        lblItemExpDate.setLayoutY(120);
        lblItemExpDate.setFont(new Font("Arial",12));
        ap.getChildren().add(lblItemExpDate);

        lblItemMfgDate=new Label("Item Mfg Date ");
        lblItemMfgDate.setLayoutX(285);
        lblItemMfgDate.setLayoutY(120);
        lblItemMfgDate.setFont(new Font("Arial",12));
        ap.getChildren().add(lblItemMfgDate);

        lblItemBarcodeNum=new Label("Item Barcode No ");
        lblItemBarcodeNum.setLayoutX(560);
        lblItemBarcodeNum.setLayoutY(120);
        lblItemBarcodeNum.setFont(new Font("Arial",12));
        ap.getChildren().add(lblItemBarcodeNum);

        lblItemBuyPrice=new Label("Item Buy Price ");
        lblItemBuyPrice.setLayoutX(835);
        lblItemBuyPrice.setLayoutY(120);
        lblItemBuyPrice.setFont(new Font("Arial",12));
        ap.getChildren().add(lblItemBuyPrice);

        fieldItemName=new TextField();
        fieldItemName.setLayoutX(80);
        fieldItemName.setLayoutY(70);
        fieldItemName.setStyle("-fx-background-radius: 20px");
        ap.getChildren().add(fieldItemName);

        fieldItemPrice=new TextField();
        fieldItemPrice.setLayoutX(325);
        fieldItemPrice.setLayoutY(70);
        fieldItemPrice.setStyle("-fx-background-radius: 20px");
        ap.getChildren().add(fieldItemPrice);

        fieldItemQuantity=new TextField();
        fieldItemQuantity.setLayoutX(590);
        fieldItemQuantity.setLayoutY(70);
        fieldItemQuantity.setStyle("-fx-background-radius: 20px");
        ap.getChildren().add(fieldItemQuantity);

        fieldItemWeight=new TextField();
        fieldItemWeight.setLayoutX(855);
        fieldItemWeight.setLayoutY(70);
        fieldItemWeight.setStyle("-fx-background-radius: 20px");
        ap.getChildren().add(fieldItemWeight);

        dpExpDate= new DatePicker();
        dpExpDate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(dpExpDate.getValue());
            }
        });
        dpExpDate.setLayoutX(105);
        dpExpDate.setLayoutY(120);
        ap.getChildren().add(dpExpDate);

        dpMfgDate= new DatePicker();
        dpMfgDate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(dpMfgDate.getValue());
            }
        });
        dpMfgDate.setLayoutX(370);
        dpMfgDate.setLayoutY(120);
        ap.getChildren().add(dpMfgDate);

        fieldItemBarcodeNum=new TextField();
        fieldItemBarcodeNum.setLayoutX(655);
        fieldItemBarcodeNum.setLayoutY(120);
        fieldItemBarcodeNum.setStyle("-fx-background-radius: 20px");
        ap.getChildren().add(fieldItemBarcodeNum);

        fieldItemBuyPrice=new TextField();
        fieldItemBuyPrice.setLayoutX(920);
        fieldItemBuyPrice.setLayoutY(120);
        fieldItemBuyPrice.setStyle("-fx-background-radius: 20px");
        ap.getChildren().add(fieldItemBuyPrice);

        btnAddItem=new Button("Add Item");
        btnAddItem.setLayoutX(400);
        btnAddItem.setLayoutY(180);
        btnAddItem.setStyle("-fx-background-radius: 20px");
        ap.getChildren().add(btnAddItem);


        tableItem= new TableView<>();
        tableItem.setLayoutX(30);
        tableItem.setLayoutY(240);
        tableItem.setPrefHeight(350);
        tableItem.setPrefWidth(800);
        c1=new TableColumn("Name");
        c1.setPrefWidth(100);
        c2=new TableColumn("Quantity");
        c2.setPrefWidth(100);
        c3=new TableColumn("Price");
        c4=new TableColumn("Weight");
        c5=new TableColumn("Barcode No");
        c6=new TableColumn("Exp Date");
        c7=new TableColumn("Mfg Date");
        c8=new TableColumn("Buy Price");
        c3.setPrefWidth(100);
        c4.setPrefWidth(100);
        c5.setPrefWidth(100);
        c6.setPrefWidth(100);
        c7.setPrefWidth(100);
        c8.setPrefWidth(100);
        btnAddItem.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent e)
            {
                if(e.getSource()==btnAddItem)
                {
                    String expDateLine = String.valueOf(dpExpDate.getValue());
                    String tokenExp[] = (expDateLine.split("-"));
                    String mfgDateLine = String.valueOf(dpMfgDate.getValue());
                    String tokenMfg[] = mfgDateLine.split("-");
                    Boolean flag = true;

                    if(fieldItemName.getText().isEmpty() || fieldItemQuantity.getText().isEmpty() || fieldItemWeight.getText().isEmpty() ||
                            fieldItemPrice.getText().isEmpty() || fieldItemBuyPrice.getText().isEmpty() || fieldItemBarcodeNum.getText().isEmpty()
                    )
                    {
                        Alert a=new Alert(Alert.AlertType.WARNING, "One or more Fields are empty.\n Re-check Please",ButtonType.OK);
                        a.showAndWait();
                    }
                    if(Integer.parseInt(tokenExp[1]) == Integer.parseInt(tokenMfg[1])){
                        if ((Integer.parseInt(tokenExp[2]) < Integer.parseInt(tokenMfg[2])) || (Integer.parseInt(tokenExp[0]) < Integer.parseInt(tokenMfg[0])))
                        {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION, "you entered invalid mfg date");
                            alert.showAndWait();
                            flag = false;

                        }
                        else
                            flag = true;
                    }
                    else if(!flag && ((Integer.parseInt(tokenExp[0]) < Integer.parseInt(tokenMfg[0]))||(Integer.parseInt(tokenExp[1])<Integer.parseInt(tokenMfg[1])) || (Integer.parseInt(tokenExp[2]) < Integer.parseInt(tokenMfg[2]))))
                    {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION,"you entered invalid mfg date");
                        alert.showAndWait();
                        flag = false;
                    }


                    if (flag){
                        String expString = String.valueOf(dpExpDate.getValue());

                        boolean add = true;
                        for (int i = 0;i<tableItem.getItems().size();i++)
                        {
                            Item temp = tableItem.getItems().get(i);
                            if (temp.getItemBarCode().equals(fieldItemBarcodeNum.getText()))
                            {
                                int quantity = Integer.parseInt(fieldItemQuantity.getText());
                                int quantityInTable = Integer.parseInt(temp.getItemQuantity());
                                quantityInTable += quantity;
                                add = false;
                                temp.setItemQuantity(String.valueOf(quantityInTable));
                            }
                        }
                        if (add) {
                            Item temp = new Item(fieldItemName.getText(), fieldItemQuantity.getText(), fieldItemPrice.getText(), fieldItemWeight.getText(), String.valueOf(expDateLine)
                                    , String.valueOf(dpMfgDate.getValue()), fieldItemBarcodeNum.getText(), fieldItemBuyPrice.getText());
                            tableItem.getItems().add(temp);
                        }
                        flag = true;

                        add = true;
                        c1.setCellValueFactory(new PropertyValueFactory<Item, SimpleStringProperty>("itemName"));
                        c3.setCellValueFactory(new PropertyValueFactory<Item, SimpleStringProperty>("itemRetailPrice"));
                        c2.setCellValueFactory(new PropertyValueFactory<Item, SimpleStringProperty>("itemQuantity"));
                        c4.setCellValueFactory(new PropertyValueFactory<Item, SimpleStringProperty>("itemWeight"));
                        c5.setCellValueFactory(new PropertyValueFactory<Item, SimpleStringProperty>("itemBarCode"));
                        c6.setCellValueFactory(new PropertyValueFactory<Item, SimpleStringProperty>("itemExpDate"));
                        c7.setCellValueFactory(new PropertyValueFactory<Item, SimpleStringProperty>("itemMfgDate"));
                        c8.setCellValueFactory(new PropertyValueFactory<Item, SimpleStringProperty>("itemBuyPrice"));

                        addItemInFile();




                    }
                    if(fieldItemName.getText().isEmpty())
                    {
                        lblItemNameErrorMsg = new Label("Please Fill this empty field");
                        lblItemNameErrorMsg.setLayoutX(100);
                        lblItemNameErrorMsg.setLayoutY(100);
                        lblItemNameErrorMsg.setFont(new Font("Serif",9));
                        ap.getChildren().add(lblItemNameErrorMsg);
                    }
                    if(fieldItemPrice.getText().isEmpty())
                    {
                        lblItemPriceErrorMsg = new Label("Please Fill this empty field");
                        lblItemPriceErrorMsg.setLayoutX(350);
                        lblItemPriceErrorMsg.setLayoutY(100);
                        lblItemPriceErrorMsg.setFont(new Font("Serif",9));
                        ap.getChildren().add(lblItemPriceErrorMsg);
                    }
                    if(fieldItemQuantity.getText().isEmpty())
                    {
                        lblItemQuantityErrorMsg = new Label("Please Fill this empty field");
                        lblItemQuantityErrorMsg.setLayoutX(610);
                        lblItemQuantityErrorMsg.setLayoutY(100);
                        lblItemQuantityErrorMsg.setFont(new Font("Serif",9));
                        ap.getChildren().add(lblItemQuantityErrorMsg);
                    }
                    if(fieldItemWeight.getText().isEmpty())
                    {
                        lblItemWeightErrorMsg = new Label("Please Fill this empty field");
                        lblItemWeightErrorMsg.setLayoutX(870);
                        lblItemWeightErrorMsg.setLayoutY(100);
                        lblItemWeightErrorMsg.setFont(new Font("Serif",9));
                        ap.getChildren().add(lblItemWeightErrorMsg);
                    }

                    if(fieldItemBarcodeNum.getText().isEmpty())
                    {
                        lblItemBarcodeNumErrorMsg = new Label("Please Fill this empty field");
                        lblItemBarcodeNumErrorMsg.setLayoutX(665);
                        lblItemBarcodeNumErrorMsg.setLayoutY(150);
                        lblItemBarcodeNumErrorMsg.setFont(new Font("Serif",9));
                        ap.getChildren().add(lblItemBarcodeNumErrorMsg);
                    }
                    if(fieldItemBuyPrice.getText().isEmpty())
                    {
                        lblItemBuyPriceErrorMsg = new Label("Please Fill this empty field");
                        lblItemBuyPriceErrorMsg.setLayoutX(940);
                        lblItemBuyPriceErrorMsg.setLayoutY(150);
                        lblItemBuyPriceErrorMsg.setFont(new Font("Serif",9));
                        ap.getChildren().add(lblItemBuyPriceErrorMsg);
                    }
                    if((!(fieldItemName.getText().isEmpty())) && (!(fieldItemPrice.getText().isEmpty())) && (!(fieldItemWeight.getText().isEmpty())) && (!(fieldItemQuantity.getText().isEmpty()))
                            && (!(fieldItemBuyPrice.getText().isEmpty())) && (!(fieldItemBarcodeNum.getText().isEmpty())) )
                    {
                        lblItemNameErrorMsg = new Label("");
                        lblItemQuantityErrorMsg = new Label("");
                    }

                    fieldItemName.clear();
                    fieldItemQuantity.clear();
                    fieldItemBuyPrice.clear();
                    fieldItemPrice.clear();
                    fieldItemWeight.clear();
                    fieldItemBarcodeNum.clear();
                }
            }
        });
        tableItem.getColumns().addAll(c1,c2,c3,c4,c5,c6,c7,c8);
        ap.getChildren().add(tableItem);
    }

    public void addItemInFile()
    {
            SimpleDateFormat date= new SimpleDateFormat("dd/MM/yyyy");
            Date d= new Date();

                    Item item = new Item(fieldItemName.getText(),fieldItemQuantity.getText(),
                            fieldItemPrice.getText(),fieldItemWeight.getText(),
                            dpExpDate.getValue()+"",dpMfgDate.getValue()+""
                            ,fieldItemBarcodeNum.getText(),fieldItemBuyPrice.getText());

                    itemClass.insert(item);
                    itemClass.saveItem(item);


    }
    public void addItemInTable()
    {

        Item item[] = itemClass.getAllItem();
        if (item != null)
        for (int i = 0;i<item.length;i++)
        {
            tableItem.getItems().add(item[i]);
            c1.setCellValueFactory(new PropertyValueFactory<Item, SimpleStringProperty>("itemName"));
            c3.setCellValueFactory(new PropertyValueFactory<Item, SimpleStringProperty>("itemRetailPrice"));
            c2.setCellValueFactory(new PropertyValueFactory<Item, SimpleStringProperty>("itemQuantity"));
            c4.setCellValueFactory(new PropertyValueFactory<Item, SimpleStringProperty>("itemWeight"));
            c5.setCellValueFactory(new PropertyValueFactory<Item, SimpleStringProperty>("itemBarCode"));
            c6.setCellValueFactory(new PropertyValueFactory<Item, SimpleStringProperty>("itemExpDate"));
            c7.setCellValueFactory(new PropertyValueFactory<Item, SimpleStringProperty>("itemMfgDate"));
            c8.setCellValueFactory(new PropertyValueFactory<Item, SimpleStringProperty>("itemBuyPrice"));

        }


    }

    public void menuBar()
    {
        Menu menuFile=new Menu("File");
        MenuItem menuItemNewWindow=new MenuItem("New Window");
        menuItemNewWindow.setAccelerator(new KeyCodeCombination(KeyCode.N,KeyCombination.CONTROL_DOWN));
        menuItemNewWindow.setOnAction(e->new Stock().start(new Stage()));
        MenuItem menuItemClientWindow=new MenuItem("Client Window");
        menuItemClientWindow.setAccelerator(new KeyCodeCombination(KeyCode.K,KeyCombination.CONTROL_DOWN));
        menuItemClientWindow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {

                    new Main().start(new Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        menuItemClose=new MenuItem("Close");
        menuItemClose.setAccelerator(new KeyCodeCombination(KeyCode.E,KeyCombination.CONTROL_DOWN));

        menuFile.getItems().addAll(menuItemNewWindow, menuItemClientWindow,new SeparatorMenuItem(),menuItemClose);

        Menu menuEdit=new Menu("Edit");
        MenuItem menuItemFind=new MenuItem("Find");
        menuItemFind.setAccelerator(new KeyCodeCombination(KeyCode.F,KeyCombination.CONTROL_DOWN));
        menuItemFind.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                fieldItemFind=new TextField();
                fieldItemFind.setLayoutX(30);
                fieldItemFind.setLayoutY(200);
                fieldItemFind.setStyle("-fx-background-radius: 20px");
                ap.getChildren().add(fieldItemFind);

                btnFindItem=new Button("Find Item");
                btnFindItem.setLayoutX(190);
                btnFindItem.setLayoutY(200);
                btnFindItem.setStyle("-fx-background-radius: 20px");
                ap.getChildren().add(btnFindItem);
                int tableItemSize = tableItem.getItems().size();
                btnFindItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if(fieldItemFind.getText().isEmpty())
                        {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION,"we have not any stuff to find");
                            alert.showAndWait();
                        }
                        else{
                            for (int i = 0; i< tableItemSize; i++)
                            {
                                System.out.println("table rows"+tableItemSize);
                                Item item = new Item(tableItem.getItems().get(i).getItemName(),tableItem.getItems().get(i).getItemQuantity(),tableItem.getItems().get(i).getItemRetailPrice(),
                                        tableItem.getItems().get(i).getItemWeight(),tableItem.getItems().get(i).getItemExpDate(),tableItem.getItems().get(i).getItemMfgDate(),tableItem.getItems().get(i).getItemBarCode(),
                                        tableItem.getItems().get(i).getItemBuyPrice());
                                System.out.println(item.getItemBarCode());
                                if(fieldItemFind.getText().contains(item.getItemBarCode()) || fieldItemFind.getText().contains(item.getItemName()) || fieldItemFind.getText().contains(item.getItemRetailPrice()) || fieldItemFind.getText().contains(item.getItemWeight()) || fieldItemFind.getText().contains(item.getItemQuantity())
                                        || fieldItemFind.getText().contains(item.getItemMfgDate()) || fieldItemFind.getText().contains(item.getItemBuyPrice()) || fieldItemFind.getText().contains(item.getItemExpDate()))
                                {
                                    tableItem.getItems().remove(i);
                                    tableItem.getItems().add(0,item);
                                }
                            }
                            fieldItemFind.clear();
                        }
                    }
                });
            }
        });
        menuEdit.getItems().addAll(menuItemFind);

        Menu menuView=new Menu("View");
        MenuItem menuItemViewHistory=new MenuItem("View History");
        menuItemViewHistory.setAccelerator(new KeyCodeCombination(KeyCode.V,KeyCombination.CONTROL_DOWN));
        menuItemViewHistory.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new StockHistory();
            }
        });
        menuView.getItems().addAll(menuItemViewHistory);

        Menu menuHelp=new Menu("Help");
        MenuItem menuItemUpdate=new MenuItem("Update");
        menuItemUpdate.setAccelerator(new KeyCodeCombination(KeyCode.U,KeyCombination.CONTROL_DOWN));
        menuItemUpdate.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"you run the latest version of the spftware\n" +
                        "if you want to add some new functionalities in you software\n" +
                        "the contact to the developer team  of you software");
                alert.showAndWait();
            }
        });
        menuItemUpdate.setAccelerator(new KeyCodeCombination(KeyCode.U,KeyCombination.CONTROL_DOWN));
        MenuItem menuItemViewHelp=new MenuItem("View Help");
        menuItemViewHelp.setAccelerator(new KeyCodeCombination(KeyCode.H,KeyCombination.CONTROL_DOWN));
        menuItemViewHelp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Dear User!\n" +
                        "\t\tIf you need some help to use this software you may contact our" +
                        " team to remove the problem");
                alert.showAndWait();

            }
        });
        MenuItem menuItemAbout=new MenuItem("About");
        menuItemAbout.setAccelerator(new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN));
        menuItemAbout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"\n" +
                        "Build #IU-201.6668.121, built on April 8, 2020\n" +
                        "Licensed to https://zhile.io\n" +
                        "You have a perpetual fallback license for this version\n" +
                        "Subscription is active until July 8, 2089\n" +
                        "Runtime version: 11.0.6+8-b765.25 amd64\n" +
                        "Windows 10 10.0\n" +
                        "GC: ParNew, ConcurrentMarkSweep\n" +
                        "Memory: 976M\n" +
                        "Cores: 4\n" +
                        "Non-Bundled Plugins: mobi.hsz.idea.latex");
                alert.setHeaderText("Utility Store");
                alert.showAndWait();
            }
        });
        menuHelp.getItems().addAll(menuItemUpdate, menuItemViewHelp, new SeparatorMenuItem(), menuItemAbout);

        MenuBar menuBarFile= new MenuBar();
        menuBarFile.getMenus().addAll(menuFile, menuEdit, menuView, menuHelp);

        bp=new BorderPane();
        bp.setTop(menuBarFile);
        bp.setLeft(ap);
    }

}
