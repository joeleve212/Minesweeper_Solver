package sample;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.Arrays;

public class Main extends Application {
    private int BOARD_WIDTH = 12;
    private int BOARD_HEIGHT = 12;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Board mineBoard = new Board(BOARD_WIDTH, BOARD_HEIGHT); //TODO: change to non-default size
        ObservableList<String[]> data = FXCollections.observableArrayList();
        data.addAll(Arrays.asList(mineBoard.mines));
        TableView<String[]> mineTable = new TableView<>();
        mineTable.setEditable(true);

        for (int i = 0; i < mineBoard.mines[0].length; i++) {
            TableColumn tc = new TableColumn(mineBoard.mines[0][i]);
            final int colNo = i;
            tc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
                    return new SimpleStringProperty((p.getValue()[colNo]));
                }
            });
            tc.setPrefWidth(90);
            mineTable.getColumns().add(tc);
        }
        mineTable.setItems(data);

        Solver solver = new Solver(BOARD_WIDTH,BOARD_HEIGHT);
        TableView<String[]> solveTable = displaySolve(solver);




        VBox vertBox = new VBox(mineTable,solveTable);
        Group gr = new Group(vertBox);
        BorderPane bordPane = new BorderPane(); //Using borderPane to easily place things on screen edge
        bordPane.setCenter(gr);

//        String[][] staffArray = {{"nice to ", "have", "titles"},
//                {"a", "b", "c"},
//                {"d", "e", "f"}};

    //mineTable.getColumns().addAll(test);
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(bordPane);
        primaryStage.setTitle("Minesweeper Solver");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public TableView<String[]> displaySolve(Solver currSolve){
        // TODO: use these symbols for covered & flagged: "☐""☒"
        String[][] grid = new String[BOARD_WIDTH][BOARD_HEIGHT];
        for(int i = 0;i<BOARD_WIDTH;i++){
            for(int j = 0;j<BOARD_HEIGHT;j++){
                Pixel thisPix = currSolve.gameBoard[i][j];
                if(thisPix.num>=0){
                    grid[i][j] = Integer.toString(thisPix.num);
                } else if(thisPix.flag) {
                    grid[i][j] = "☒";
                } else{
                    grid[i][j] = "☐";
                }
            }
        }

        ObservableList<String[]> data = FXCollections.observableArrayList();
        data.addAll(Arrays.asList(grid));
        TableView<String[]> table = new TableView<>();

        table.setEditable(true);
        for (int i = 0; i < grid[0].length; i++) {
            TableColumn tc = new TableColumn(grid[0][i]);
            final int colNo = i;
            tc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
                    return new SimpleStringProperty((p.getValue()[colNo]));
                }
            });
            tc.setPrefWidth(90);
            table.getColumns().add(tc);
        }
        table.setItems(data);
        return table;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
