package Huffman;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.scene.text.*;

import javax.xml.soap.Node;
import java.awt.*;


/**
 * Created by Jman on 12/12/13.
 */
public class HuffmanGUI {




    public static VBox makeMain(){
        VBox mainPage = new VBox();
        mainPage.setStyle("-fx-background-color: #ADADAD;");

        Text splash = new Text("Final Project CS4450\n\n" +
                "Huffman Coding\n\n" +
                "Project by Matt Brown and Johnathan Elson\n\n" +
                "Select an option above to begin");
        splash.setFont(javafx.scene.text.Font.font("Arial", FontWeight.BOLD, 30));

        mainPage.setAlignment(Pos.CENTER);
        mainPage.getChildren().add(splash);



        return mainPage;
    }








    public static HBox makeTop(){
        HBox header = new HBox();

        header.setPadding(new Insets(5, 12, 5, 12));
        header.setSpacing(5);
        header.setStyle("-fx-background-color: #282828;");

        final ToggleGroup group = new ToggleGroup();

        ToggleButton tb1 = new ToggleButton("Read Data: Keyboard");
        tb1.setToggleGroup(group);
        tb1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                ((BorderPane)(((ToggleButton)(event.getSource())).getParent().getParent())).setCenter(makeReadK());
            }
        });



        ToggleButton tb2 = new ToggleButton("Read Data: File");
        tb2.setToggleGroup(group);
        tb2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                ((BorderPane)(((ToggleButton)(event.getSource())).getParent().getParent())).setCenter(makeReadF());
            }
        });



        ToggleButton tb3 = new ToggleButton("View Frequencies");
        tb3.setToggleGroup(group);
        tb3.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                ((BorderPane)(((ToggleButton)(event.getSource())).getParent().getParent())).setCenter(makeViewF());
            }
        });


        ToggleButton tb4 = new ToggleButton("Encode Message: Keyboard");
        tb4.setToggleGroup(group);
        tb4.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                ((BorderPane)(((ToggleButton)(event.getSource())).getParent().getParent())).setCenter(encodeK());
            }
        });



        ToggleButton tb5 = new ToggleButton("Encode Message: File");
        tb5.setToggleGroup(group);
        tb5.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                ((BorderPane)(((ToggleButton)(event.getSource())).getParent().getParent())).setCenter(encodeF());
            }
        });



        ToggleButton tb6 = new ToggleButton("Decode From File");
        tb6.setToggleGroup(group);
        tb6.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                ((BorderPane)(((ToggleButton)(event.getSource())).getParent().getParent())).setCenter(decode());
            }
        });



        header.getChildren().addAll(tb1, tb2, tb3, tb4, tb5, tb6);

        header.setAlignment(Pos.CENTER);


        return header;
    }

    public static GridPane makeReadK(){
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(5, 10, 0, 10));
        //grid.setStyle("-fx-background-color: #556699;"); //old color
        grid.setStyle("-fx-background-color: #ADADAD;");
        grid.setAlignment(Pos.TOP_CENTER);


        ColumnConstraints col = new ColumnConstraints();
        col.setFillWidth(true);
        col.setHalignment(HPos.CENTER);
        grid.getColumnConstraints().add(col);

        RowConstraints row = new RowConstraints();
        row.setFillHeight(true);
        row.setValignment(VPos.TOP);
        grid.getRowConstraints().add(row);


        // Instructions in column 0, row 0
        FlowPane flow = new FlowPane();


        Text title = new Text("Enter Text Below to Generate or Modify Frequencies");
        title.setFont(javafx.scene.text.Font.font("Arial", FontWeight.BOLD, 20));
        flow.setAlignment(Pos.CENTER);
        flow.getChildren().add(title);
        grid.add(flow, 0, 0);

        //Text Area in column 0 row 1
        TextArea input = new TextArea();
        input.setPrefSize(5000,450);

        grid.add(input, 0 , 1);


        //Submit Button column 0 row 2
        FlowPane bottom = new FlowPane();
        bottom.setAlignment(Pos.BOTTOM_RIGHT);
        Button submit = new Button("Submit");
        submit.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                //INSERT CODE HERE FOR SUBMIT Button -> call
            }
        });

        submit.setPrefSize(70, 25);
        bottom.getChildren().add(submit);
        grid.add(bottom, 0, 2);

        return grid;
    }

    public static GridPane makeReadF(){
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(5, 10, 0, 10)); //feel free to remove these if you think it looks better.
        grid.setStyle("-fx-background-color: #ADADAD;");

        return grid;
    }

    public static HBox makeViewF(){
        HBox box = new HBox();
        box.setStyle("-fx-background-color: #ADADAD;");
        box.setPadding(new Insets(5, 10, 0, 10)); //feel free to remove these if you think it looks better.



        return box;
    }

    public static GridPane encodeK(){
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(5, 10, 0, 10)); //feel free to remove these if you think it looks better.
        grid.setStyle("-fx-background-color: #ADADAD;");

        return grid;
    }

    public static GridPane encodeF(){
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(5, 10, 0, 10)); //feel free to remove these if you think it looks better.
        grid.setStyle("-fx-background-color: #ADADAD;");

        return grid;
    }

    public static HBox decode(){
        HBox box = new HBox();
        box.setStyle("-fx-background-color: #ADADAD;");
        box.setPadding(new Insets(5, 10, 0, 10)); //feel free to remove these if you think i looks better.

        return box;
    }

}
