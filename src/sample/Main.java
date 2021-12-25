package sample;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Alert.*;


import java.io.File;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Main extends Application {
    private String FILENAME ;
    private String FILETHATSTORECLIENTID = "ID.txt";
    private String TEMP = "Temp.txt";
    private String FILETHATSTOREHISTORYOFCLIENT = "Client.txt";

    private ItemClass itemClass;
    
    private  MenuBar menuBar;

        private AnchorPane anchorPane;

    private Label remaining;
    private Label totalPrice;

    private Button btnDeleteRow;

    private TextField castomerBalance;
    private TextField itemNameField;
    private TextField itemQuantityField;
    private TextField weightField;
    private TextField serialNumberField;
    private TextField serialNumberQuantity;

    private Button btnAdd;
    private Button btnSerialNumber;

    private Button btnAddItemWithName;
    private MenuItem closeMenu;


    protected TableColumn column1;
    protected TableColumn column2;
    protected TableColumn column3;
    protected TableColumn column4;



    public TableView<AddItmForClient> table;


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Data Enter");


        itemClass = new ItemClass();

        FILENAME = "Items.txt";

        makeMenus();//it make the menu on the top of the stage
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menuBar);

        anchorPane = new AnchorPane();



        enterData();//it make all the fields, labels and btns on the screen

        makeTable();//it make the table in which item will show

        borderPane.setLeft(anchorPane);
        Scene scene = new Scene(borderPane,1000,700);



        primaryStage.setScene(scene);
        closeMenu.setOnAction(e->primaryStage.close());

        primaryStage.show();
    }

    public void enterData ()throws Exception
    {


        Label  itemNameLabel = new Label("Item Name  : ");
        itemNameLabel.setLayoutX(10);
        itemNameLabel.setLayoutY(50);

        itemNameField = new TextField();
        itemNameField.setLayoutX(100);
        itemNameField.setLayoutY(45);
        itemNameField.setStyle("-fx-background-radius: 20px");
        itemNameField.setPrefWidth(100);

        Label itemQuantityLabel = new Label("Item Quantity : ");
        itemQuantityLabel.setLayoutY(50);
        itemQuantityLabel.setLayoutX(230);

        itemQuantityField = new TextField();
        itemQuantityField.setLayoutY(45);
        itemQuantityField.setLayoutX(320);
        itemQuantityField.setPrefWidth(100);
        itemQuantityField.setStyle("-fx-background-radius: 20px");

        Label weightLabel = new Label("Weight : ");
        weightLabel.setLayoutX(450);
        weightLabel.setLayoutY(50);

        weightField = new TextField();
        weightField.setLayoutY(45);
        weightField.setLayoutX(520);
        weightField.setPrefWidth(100);
        weightField.setStyle("-fx-background-radius: 20px");

        Button btnAddItemWithName = new Button("Add Item");
        //this button will add item on the table
        btnAddItemWithName.setLayoutY(45);
        btnAddItemWithName.setLayoutX(650);
        btnAddItemWithName.setStyle("-fx-background-radius: 30px");
        //following is the action that perform in time of click
        btnAddItemWithName.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                boolean emptyChecker = true;
                //this following condition check that the all fields is empty or not.
                if (itemNameField.getText().isEmpty() || itemQuantityField.getText().isEmpty() || weightField.getText().isEmpty())
                {

                    Alert alert = new Alert(AlertType.ERROR,"All fields are rquireds");
                    alert.setHeaderText("enter carefully");
                    alert.showAndWait();

                }
                else//this else is execute when the user enter all requierd fields.
                {
                    boolean flag = true;//it check that the item is present or not
                    Item item =new Item(itemNameField.getText(),itemQuantityField.getText(),"", weightField.getText(), "","","","");
                           if (itemClass.chkItem(item))//this condition check that item name that user enter is avilable in our file or not
                            {

                                if (itemClass.chkQuantityInFile(item))//this condition check that the quantity tht user enter is available or not
                                {
                                    Alert alert = new Alert(AlertType.ERROR,"you enter large amount of quantity which we have not ");
                                    alert.showAndWait();
                                }
                                else if ((itemClass.chkWeight(item)))//this condition check that the weight that user enter is valid or not
                                {
                                    Alert alert = new Alert(AlertType.ERROR,"we have no any such item that have the weight that you entered");
                                    alert.showAndWait();
                                }
                                else//this else is use to add item in table
                                {

                                    double price =itemClass.getPrice(item);//it store the price that store in file
                                    int quantity = Integer.parseInt(itemQuantityField.getText());//it store the quantity that the user entered

                                    double total = Double.parseDouble(totalPrice.getText());//it take the total price that store in the label of totalPrice
                                    total =  (total + (price * quantity));//it calculate the new price
                                    totalPrice.setText(String.valueOf(total));//set the new value to total price label

                                    boolean isPresent = true;
                                    for (int i = 0;i<table.getItems().size();i++)//this loop will add quantity on the table if the item is already enter in table
                                    {
                                        AddItmForClient addItmForClient = table.getItems().get(i);//it get the data that present in table
                                        if (addItmForClient.getName().equals(itemNameField.getText()) && addItmForClient.getWeight().equals(weightField.getText()))//this condition check the name and waight of the item
                                        {
                                            double priceInTable = Double.parseDouble(addItmForClient.getPrice());

                                            priceInTable = priceInTable + price;//it get the price after add the other itens

                                            addItmForClient.setPrice(""+(price + priceInTable));//it set the price after adding the new items

                                            addItmForClient.setItemQuantity(""+(Integer.parseInt(addItmForClient.getItemQuantity()) + quantity));

                                            isPresent = false;//it use to enter a seperate item
                                        }
                                        if (!isPresent)
                                            break;//if the item quantity is increases then the loop will break.
                                    }
                                    if (isPresent == true)//when the variable is true the the item will add the item in the seperate item.
                                    {
                                        Item item1  = itemClass.getItem(item);
                                        item1.setItemRetailPrice("" + (price * quantity));
//                                        enterItem([4],token[5],token[6],token[7]));
//


                                        enterItem(new AddItmForClient(item1.getItemName(), itemQuantityField.getText(), "" + price,
                                                item1.getItemWeight(), item1.getItemExpDate(),item1.getItemMfgDate(),item1.getItemBarCode(),item1.getItemBuyPrice()));
                                    }
                                    isPresent = true;

                                    //decrement in qunatity that store in file


                                    item = itemClass.getItem(item);
                                    itemClass.decreaseFromFile(item,Integer.parseInt(itemQuantityField.getText()));


                                    itemNameField.clear();
                                    itemQuantityField.clear();
                                    weightField.clear();

                                }
                            }



                }
            }
        });

        Label orLabel = new Label("OR");
        orLabel.setLayoutX(10);
        orLabel.setLayoutY(100);
        orLabel.setFont(new Font(18));

        Label serialLabel = new Label("Serial number : ");
        serialLabel.setLayoutY(150);
        serialLabel.setLayoutX(10);

        serialNumberField = new TextField();
        serialNumberField.setLayoutX(100);
        serialNumberField.setLayoutY(145);
        serialNumberField.setStyle("-fx-background-radius: 20px");

        Label serialNumberQuantityLabel = new Label("Quantity : ");
        serialNumberQuantityLabel.setLayoutX(270);
        serialNumberQuantityLabel.setLayoutY(150);

        serialNumberQuantity = new TextField();
        serialNumberQuantity.setLayoutX(330);
        serialNumberQuantity.setLayoutY(145);
        serialNumberQuantity.setPrefWidth(100);
        serialNumberQuantity.setStyle("-fx-background-radius: 20px");

        Button btnAddItemWithSerial = new Button("Add Item");
        btnAddItemWithSerial.setLayoutX(470);
        btnAddItemWithSerial.setLayoutY(145);
        btnAddItemWithSerial.setStyle("-fx-background-radius: 30px");
        //add action on this button
        btnAddItemWithSerial.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //when the fields is empty then the error message will appear
                if (serialNumberField.getText().isEmpty() || serialNumberQuantity.getText().isEmpty())
                {//this condition return true of the user click the button without any input
                    Alert alert = new Alert(AlertType.ERROR,"all fields are required");
                    alert.showAndWait();
                }
                else {
                        //the following line is use to check the item from the file.

                        Item item =new Item("",serialNumberQuantity.getText(),"", "", "","",serialNumberField.getText(),"");

                            int quantity = Integer.parseInt(serialNumberQuantity.getText());
                        boolean isPresent = true;

                            if (itemClass.chkBar(item))//it check that the serialnumber is present in file or not
                            {
                                if (itemClass.chkQuantityInFile(item))//this condition check that the qunatity that we enter in the field is valid or not
                                {
                                    Alert alert = new Alert(AlertType.ERROR, "you enter invalid quantity which we have not ");//it show when the quantity is not valid
                                    alert.showAndWait();
                                } else {

                                    double price = itemClass.getPrice(item) * quantity;//it return the total price of the item

                                    double total = Double.parseDouble(totalPrice.getText());//it take the price that already store in a label
                                    total = total + price;//it add the total price with the price of label
                                    totalPrice.setText(String.valueOf(total));//it set the  new total value to the label
                                    String amount = null;
                                    for (int i = 0; i < table.getItems().size(); i++)//this loop will change the quantity of the item when it exist in table.
                                    {
                                        AddItmForClient addItmForClient = table.getItems().get(i);//it take the selected row item
                                        if (addItmForClient.getSerial().equals(serialNumberField.getText()))//this condition check that the value is present in table or not.
                                        {

                                            int quantity1 = Integer.parseInt(serialNumberQuantity.getText());//it take the quantity that user enter
                                            int quantityInTable = Integer.parseInt(addItmForClient.getItemQuantity());//it take the quantity that already store in table

                                            double priceInTable = Double.parseDouble(addItmForClient.getPrice());//it take the price that already store in table
                                            addItmForClient.setPrice("" + (price + priceInTable));//it add the the total value after add
                                            addItmForClient.setItemQuantity(String.valueOf((quantity1 + quantityInTable)));//it set new number of qquantity to table

                                            amount = (price + priceInTable)+"";

                                            isPresent = false;//it tell us that the value is added to the table

                                        }
                                        if (!isPresent)//it check that value is present in table
                                            break;

                                    }
                                    amount = price+"";
                                    if (isPresent)//it check that the value is not present in table right know
                                    {
                                        Item item1 = itemClass.getItemFromSerial(item);

                                        enterItem(new AddItmForClient(item1.getItemName(),serialNumberQuantity.getText(),amount
                                                ,item1.getItemWeight(),"","",""
                                                ,""));



                                    }

                                }
                            }

                        //decrease the quantity from the file
                    itemClass.decreaseFromFile(item,Integer.parseInt(serialNumberQuantity.getText()));
                        serialNumberQuantity.clear();//it clear the field
                        serialNumberField.clear();//it also clear the field



                }}});

        totalPrice = new Label("0.0");
        totalPrice.setLayoutY(320);
        totalPrice.setLayoutX(750);
        totalPrice.setFont(new Font(20));

        Label totalPriceLabel = new Label("Total price : ");
        totalPriceLabel.setLayoutY(320);
        totalPriceLabel.setLayoutX(630);
        totalPriceLabel.setFont(new Font(20));

        Label castomer = new Label("Castomer Enter : ");
        castomer.setLayoutX(630);
        castomer.setLayoutY(370);

        castomerBalance = new TextField();
        castomerBalance.setLayoutX(750);
        castomerBalance.setLayoutY(370);
        castomerBalance.setPrefWidth(100);
        castomerBalance.setStyle("-fx-background-radius: 20px");



        Label line = new Label("_________________________________________________");
        line.setLayoutY(400);
        line.setLayoutX(630);

        Label remainingBalance = new Label("Remaining : ");
        remainingBalance.setLayoutX(630);
        remainingBalance.setLayoutY(420);

        remaining = new Label("0.0");
        remaining.setLayoutX(750);
        remaining.setLayoutY(420);

        Button btnRemaining = new Button("Remaining");
        btnRemaining.setLayoutX(650);
        btnRemaining.setLayoutY(450);
        btnRemaining.setStyle("-fx-background-radius: 20px");
        btnRemaining.setPrefWidth(100);
        btnRemaining.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (totalPrice.getText().equals("0.0"))
                {
                    Alert alert = new Alert(AlertType.WARNING,"You have not enter any Item so" +
                            "\n we can't make the recipt");
                    alert.showAndWait();
                }
                else if (castomerBalance.getText().isEmpty())
                {
                    Alert alert = new Alert(AlertType.WARNING,"you have not enter any amount from user/client");
                    alert.showAndWait();
                }
                else
                {
                    double totalaAmount = Double.parseDouble(totalPrice.getText());
                    double userAmount = Double.parseDouble(castomerBalance.getText());
                    if (totalaAmount > userAmount)
                    {
                        Alert alert = new Alert(AlertType.WARNING,"you enter the clent price less then required");
                        alert.showAndWait();
                    }
                    else {

                        remaining.setText(String.valueOf((userAmount - totalaAmount)));
                        try {
                            FileReader reader = new FileReader(FILETHATSTORECLIENTID);//it helps us to find the last client id
                            BufferedReader bufferedReader = new BufferedReader(reader);

                            int id = Integer.parseInt(bufferedReader.readLine());//it take the last client id
                            id += 1;//increment in id

                            bufferedReader.close();//close the file
                            reader.close();//close the file

                            FileWriter writer = new FileWriter(TEMP,true);
                            BufferedWriter bufferedWriter = new BufferedWriter(writer);

                            bufferedWriter.write(String.valueOf(id) + "\n");

                            bufferedWriter.close();
                            writer.close();

                            Files.delete(Paths.get(FILETHATSTORECLIENTID));
                           File forRename = new File(TEMP);
                           forRename.renameTo(new File(FILETHATSTORECLIENTID));


                           FileWriter writer1 = new FileWriter(FILETHATSTOREHISTORYOFCLIENT,true);
                           BufferedWriter bufferedWriter1 = new BufferedWriter(writer1);
                           String storeHistory = String.valueOf(id);
                           Date date = new Date();
                           SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy,hh:mm:ss");
                           storeHistory = storeHistory+"," + dateFormat.format(date);
                           //the history will store in this formate
                            //client id , total price , date , time , item that purchase , item quantity , item price , ....
                           for (int i = 0;i<table.getItems().size();i++)
                           {
                               AddItmForClient addItmForClient = table.getItems().get(i);
                               storeHistory = storeHistory + ","+addItmForClient.getSerial()+","+addItmForClient.getItemQuantity()+","+addItmForClient.getPrice();
                           }
                           storeHistory = storeHistory + "\n";
                           bufferedWriter1.write(storeHistory);

                           bufferedWriter1.close();
                           writer1.close();

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Button btnPrint = new Button("Generate Racipt");
        btnPrint.setLayoutY(450);
        btnPrint.setLayoutX(760);
        btnPrint.setStyle("-fx-background-radius: 20px");

        btnPrint.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                String reciptString = "Utility Store\n--------------------------------------\n\n";
                reciptString = reciptString + "itemName   item Qty   Price\n--------------------------------------\n";
                for (int i = 0;i<table.getItems().size();i++)
                {
                    AddItmForClient addItmForClient = table.getItems().get(i);
                    int size = addItmForClient.getName().length();
                    reciptString = reciptString + addItmForClient.getName();
                    for (int space = size;space <= 16;space++)
                        reciptString += " ";
                    reciptString += addItmForClient.getItemQuantity();
                    size = addItmForClient.getItemQuantity().length();
                    for (int space = size;space<=22;space++)
                        reciptString += " ";
                    reciptString += addItmForClient.getPrice()+"\n";
                }
                reciptString = reciptString + "\n--------------------------------------\n";
                reciptString = reciptString + "\t\tTotal Amount : \t"+totalPrice.getText()+"\n";
                reciptString = reciptString + "\t\tUser Balance Enter :\t"+castomerBalance.getText()+"\n";
                reciptString = reciptString + "--------------------------------------\n";
                reciptString = reciptString + "\t\t\tRemaining : \t"+remaining.getText()+"\n";

                TextArea textArea = new TextArea(reciptString);


                Printer printers = Printer.getDefaultPrinter();
                if (printers != null)
                {
                    textArea.appendText(printers.getName()+"\n");
                    PrinterJob job = PrinterJob.createPrinterJob();
                    textArea.textProperty().bind(job.jobStatusProperty().asString());

                    if (job.printPage(textArea))
                    {
                        job.endJob();
                    }


                }
                else
                {
                    Alert alert = new Alert(AlertType.WARNING,"you have no any print to print the recipt");
                    alert.showAndWait();
                }

            }
        });

        anchorPane.getChildren().addAll(itemNameLabel,itemNameField);
        anchorPane.getChildren().addAll(itemQuantityLabel,itemQuantityField);
        anchorPane.getChildren().addAll(serialLabel,serialNumberField);
        anchorPane.getChildren().addAll(weightLabel,weightField,orLabel);
        anchorPane.getChildren().addAll(serialNumberQuantityLabel,serialNumberQuantity);
        anchorPane.getChildren().addAll(btnAddItemWithName,btnAddItemWithSerial);
        anchorPane.getChildren().addAll(totalPrice,totalPriceLabel);
        anchorPane.getChildren().addAll(castomer,castomerBalance);
        anchorPane.getChildren().addAll(remainingBalance,remaining,line);
        anchorPane.getChildren().addAll(btnRemaining,btnPrint);
    }
    public void makeMenus()
    {
        menuBar = new MenuBar();

        //make menu for file
        Menu fileMenu = new Menu("File");

        MenuItem addStoke = new MenuItem("Add Stoke...");
        addStoke.setAccelerator(new KeyCodeCombination(KeyCode.S,KeyCombination.CONTROL_DOWN));
        addStoke.setOnAction(e->new Stock().start(new Stage()));

        MenuItem newWindow = new MenuItem("New Window...");
        newWindow.setAccelerator(new KeyCodeCombination(KeyCode.N,KeyCombination.CONTROL_DOWN));
        newWindow.setOnAction(e-> {
            try {
                new Main().start(new Stage());
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        closeMenu = new MenuItem("Close");
        closeMenu.setAccelerator(new KeyCodeCombination(KeyCode.E,KeyCombination.CONTROL_DOWN));


        //add menuitem in fileManu
        fileMenu.getItems().add(addStoke);
        fileMenu.getItems().add(newWindow);
        fileMenu.getItems().add(new SeparatorMenuItem());
        fileMenu.getItems().add(closeMenu);

        //make menu for edit
        Menu editMenu = new Menu("_Edit");
        MenuItem findMenuItem = new MenuItem("Find");
        //add shortcut key
        findMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.F,KeyCombination.CONTROL_DOWN));
        //make action on find menuitem
        findMenuItem.setOnAction(e-> {
            try {

                int sizeOfTeble = table.getItems().size();

                Label findLabel = new Label("Enter Item to find");
                findLabel.setLayoutY(10);
                findLabel.setLayoutX(800);

                TextField findField = new TextField();
                findField.setLayoutX(800);
                findField.setLayoutY(30);
                findField.setStyle("-fx-background-radius: 20px");


                Button btnFind = new Button("Find");
                btnFind.setLayoutX(850);
                btnFind.setLayoutY(60);
                btnFind.setStyle("-fx-background-radius: 20px");
                btnFind.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if (findField.getText().isEmpty())
                        {
                            Alert alert = new Alert(AlertType.INFORMATION,"field is required to find Items from Table");
                            alert.showAndWait();
                        }
                        else if (table.getItems().isEmpty())
                        {
                            Alert alert = new Alert(AlertType.INFORMATION,"table is empty so we can't find any thing");
                            alert.showAndWait();
                        }
                        else
                        {
                            for (int i = 0; i < sizeOfTeble; i++) {
                                AddItmForClient temp = table.getItems().get(i);
                                if (findField.getText().equals(temp.getName()) || findField.getText().equals(temp.getItemQuantity()) ||
                                        findField.getText().equals(temp.getPrice()) || findField.getText().equals(temp.getWeight())
                                        || findField.getText().equals(temp.getSerial())) {

                                    table.getItems().remove(i);
                                    table.getItems().add(0, temp);

                                }
                            }

                            findField.clear();
                        }
                    }
                });


                Button btnClose = new Button("X");
                btnClose.setLayoutX(950);
                btnClose.setLayoutY(5);
                btnClose.setStyle("-fx-background-radius: 20px");
                btnClose.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {

                        anchorPane.getChildren().remove(btnFind);
                        anchorPane.getChildren().remove(findField);
                        anchorPane.getChildren().remove(findLabel);
                        anchorPane.getChildren().remove(btnClose);


                    }
                });


                anchorPane.getChildren().addAll(findField,btnClose,btnFind);
                anchorPane.getChildren().add(findLabel);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        //add menu item to edit menu
        
        editMenu.getItems().add(findMenuItem);

        //make view  menu
        Menu viewMenu = new Menu("_View");
        MenuItem viewHistory = new MenuItem("View history");
        viewMenu.getItems().addAll(viewHistory);
        viewHistory.setAccelerator(new KeyCodeCombination(KeyCode.H,KeyCombination.CONTROL_DOWN));
        viewHistory.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    new ShowHistory();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        //make help menu
        Menu helpMenu = new Menu("Help");
        MenuItem help = new MenuItem("Help");
        help.setAccelerator(new KeyCodeCombination(KeyCode.H,KeyCombination.CONTROL_DOWN));
        help.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(AlertType.INFORMATION,"Dear User!\n" +
                        "\t\tIf you need some help to use this software you may contact our" +
                        " team to remove the problem");
                alert.showAndWait();

            }
        });
        MenuItem about = new MenuItem("About");
        about.setAccelerator(new KeyCodeCombination(KeyCode.A,KeyCombination.CONTROL_DOWN));
        about.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(AlertType.INFORMATION,"\n" +
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
        MenuItem update = new MenuItem("Update");
        update.setAccelerator(new KeyCodeCombination(KeyCode.U,KeyCombination.CONTROL_DOWN));
        update.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(AlertType.INFORMATION,"you run the latest version of the spftware\n" +
                        "if you want to add some new functionalities in you software\n" +
                        "the contact to the developer team  of you software");
                alert.showAndWait();
            }
        });
        helpMenu.getItems().addAll(help,about,update);

        menuBar.getMenus().addAll(fileMenu,editMenu,viewMenu,helpMenu);


    }

    public void makeTable()
    {
        table = new TableView<>();
        column1 = new TableColumn("Item Name");
        column1.setPrefWidth(150);
        column2 = new TableColumn("Item Quantity");
        column2.setPrefWidth(150);
        column3 = new TableColumn("Item weight");
        column3.setPrefWidth(150);
        column4 = new TableColumn("Price");
        column4.setPrefWidth(150);
        column1.setCellValueFactory(new PropertyValueFactory<AddItmForClient, SimpleStringProperty>("name"));
        column2.setCellValueFactory(new PropertyValueFactory<AddItmForClient, SimpleStringProperty>("itemQuantity"));
        column4.setCellValueFactory(new PropertyValueFactory<AddItmForClient, SimpleStringProperty>("price"));
        column3.setCellValueFactory(new PropertyValueFactory<AddItmForClient, SimpleStringProperty>("weight"));



        table.setLayoutX(20);
        table.setLayoutY(250);
        table.setPrefWidth(600);
        table.setPrefHeight(300);
        table.setStyle("-fx-background-radius: 50px");

       btnDeleteRow = new Button("Remove");
        btnDeleteRow.setLayoutX(20);
        btnDeleteRow.setLayoutY(560);
        btnDeleteRow.setPrefWidth(70);
        btnDeleteRow.setStyle("-fx-background-radius: 40px");
        btnDeleteRow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (table.getItems().isEmpty())
                {
                    Alert alert = new Alert(AlertType.WARNING,"The table is empty");
                    alert.setHeaderText("we can't do that");
                    alert.showAndWait();
                }
                else if (table.getSelectionModel().getSelectedIndex() == -1)
                {
                    Alert alert = new Alert(AlertType.WARNING,"you must select the row to remove");
                    alert.setHeaderText("we can't do that");
                    alert.showAndWait();
                }
                else {
                    AddItmForClient addItmForClient = table.getSelectionModel().getSelectedItem();
                    Item item = itemClass.getItem(new Item(addItmForClient.getName(),addItmForClient.getSerial(),
                            "",addItmForClient.getWeight(),"","","",""));
                    table.getItems().remove(table.getSelectionModel().getSelectedIndex());
                    item.setItemQuantity(addItmForClient.getItemQuantity());
                    itemClass.increaseInFile(item);
                }
            }
        });

        anchorPane.getChildren().add(btnDeleteRow);

        table.getColumns().addAll(column1,column2,column3,column4);


        anchorPane.getChildren().addAll(table);


    }

    public void enterItem(AddItmForClient addItmForClient)
    {
        System.out.println("to enter "+addItmForClient);
        table.getItems().add(addItmForClient);

        column1.setCellValueFactory(new PropertyValueFactory<AddItmForClient, SimpleStringProperty>("name"));
        column2.setCellValueFactory(new PropertyValueFactory<AddItmForClient, SimpleStringProperty>("itemQuantity"));
        column4.setCellValueFactory(new PropertyValueFactory<AddItmForClient, SimpleStringProperty>("price"));
        column3.setCellValueFactory(new PropertyValueFactory<AddItmForClient, SimpleStringProperty>("weight"));

    }
    public static void main(String[] args) {

        launch();
    }
}
