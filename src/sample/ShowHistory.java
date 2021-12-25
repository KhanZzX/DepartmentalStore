package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ShowHistory extends Application {
    private Stage stage;
    private AnchorPane anchorPane;

    private String FILETHATSTOREHISTORYOFCLIENT = "Client.txt";

    //declear the table view
    private TableView<History> table;
    //declear column of table
    private TableColumn column1;
    private TableColumn column2;
    private TableColumn column3;
    private TableColumn column4;
    private TableColumn column5;
    private TableColumn column6;

    public ShowHistory() throws IOException {
        stage = new Stage();
        stage.setHeight(700);
        stage.setMaxWidth(700);
        stage.setWidth(700);


        anchorPane = new AnchorPane();

            init();

        Scene scene  = new Scene(anchorPane);
        stage.setScene(scene);
        stage.showAndWait();
    }


    public void init() throws IOException {

        Label findLabel = new Label("Find");
        findLabel.setStyle("-fx-background-size: cover");
        findLabel.setLayoutX(550);
        findLabel.setLayoutY(10);

        TextField findField = new TextField();
        findField.setLayoutX(550);
        findField.setLayoutY(25);
        findField.setPrefWidth(100);
        findField.setStyle("-fx-background-radius: 20px");

        Button btnFind = new Button("Find");
        btnFind.setLayoutX(550);
        btnFind.setLayoutY(70);
        btnFind.setStyle("-fx-background-radius: 20px");
        btnFind.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Label empty = null;
                if (table.getItems().isEmpty())
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION,"we have no history to view");
                    alert.showAndWait();
                }
                else if (findField.getText().isEmpty())
                {
                    empty = new Label("Field is required");
                    empty.setLayoutX(550);
                    empty.setLayoutY(50);
                    anchorPane.getChildren().add(empty);
                }
                else {
                    
                    for (int i = 0;i<table.getItems().size();i++)
                    {
                        History history = table.getItems().get(i);
                        if (history.getId().contains(findField.getText()) || history.getDate().contains(findField.getText())
                        ||history.getTime().contains(findField.getText()) || history.getItemName().contains(findField.getText())
                        || history.getItemQuantity().contains(findField.getText()) || history.getItemPrice().contains(findField.getText()))
                        {
                            table.getItems().remove(i);
                            table.getItems().add(0,history);
                        }
                    }
                }
            }
        });


        anchorPane.getChildren().addAll(findLabel,findField,btnFind);
        table = new TableView<>();
        column1 = new TableColumn("Client ID");
        column2 = new TableColumn("date");
        column3 = new TableColumn("time");

        TableColumn itemColumn = new TableColumn("Item Information");
        column4 = new TableColumn("item Name");
        column5 = new TableColumn("Item Quantity");
        column6 = new TableColumn("item Price");

        itemColumn.getColumns().addAll(column4,column5,column6);

        column1.setPrefWidth(100);
        column2.setPrefWidth(100);
        column3.setPrefWidth(100);
        column4.setPrefWidth(100);
        column5.setPrefWidth(100);
        column6.setPrefWidth(100);

        table.setStyle("-fx-background-radius: 20px");

        table.getColumns().addAll(column1,column2,column3,itemColumn);

        FileReader reader = new FileReader(FILETHATSTOREHISTORYOFCLIENT);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = null;
        String token[] = {};
        ArrayList<History> clientHistory = null;
        while ((line = bufferedReader.readLine()) != null)
        {
            token = line.split(",");
            int size = token.length;
            clientHistory = new ArrayList<>();
            clientHistory.add(new History(token[0],token[1],token[2],token[3],token[4],token[5]));
            table.getItems().add(clientHistory.get(0));


            if (size > 6)
            {
                size -= 6;
                int index = 6;
                for (int i = 1;i<= size / 3;i++ )
                {
                    clientHistory.add(new History(token[0],token[1],token[2],token[index++],token[index++],token[index++]));

                    table.getItems().add(clientHistory.get(i));
                }

            }

        }
        bufferedReader.close();
        reader.close();

        column1.setCellValueFactory(new PropertyValueFactory<String,String>("id"));
        column2.setCellValueFactory(new PropertyValueFactory<String,String>("date"));
        column3.setCellValueFactory(new PropertyValueFactory<String,String>("time"));
        column4.setCellValueFactory(new PropertyValueFactory<String,String>("itemName"));
        column5.setCellValueFactory(new PropertyValueFactory<String,String>("itemQuantity"));
        column6.setCellValueFactory(new PropertyValueFactory<String,String>("itemPrice"));


        table.setLayoutX(10);
        table.setLayoutY(100);
        table.setPrefHeight(500);
        table.setPrefWidth(600);

        anchorPane.getChildren().add(table);


    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}
