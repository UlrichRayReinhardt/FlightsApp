import FlightApp.Flight;
import Jedis_db.JedisController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;

public class LauncherMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        //new RedisThreat().runRedis();
        JedisPool pool = new JedisPool("localhost");
        JedisController controller = new JedisController(pool);

        /*controller.clear();
        new BackUpData(pool);*/

        Group flightsGroup = new Group();

        HBox two = new HBox();
        VBox suitesVBox = new VBox();
        suitesVBox.setPadding(new Insets(10, 0, 0, 0));

        TableView<String> suitNames = new TableView<>();

        TableColumn suiteNameColumn = new TableColumn("Suite");
        suiteNameColumn.setMinWidth(30);
        List<Flight> tmp = controller.getFlightList();
        List<String> tmpsuits = new ArrayList<>();
        tmpsuits.add("--All---");
        tmpsuits.add("my suite1");

        Label suitesLabel = new Label("Suites");
        suiteNameColumn.setCellValueFactory(new PropertyValueFactory<String, String>("id"));


        suitNames.getItems().add("---All---");
        suitNames.getItems().add("---asd---");
        suitNames.getItems().add("---asdw---");
        suitNames.getItems().add("---gggg---");
        suitesVBox.getChildren().addAll(suitesLabel, suitNames);


        VBox flightsVBox = new VBox();
        suitesVBox.setPadding(new Insets(10, 0, 0, 0));

        Label flightsLabel = new Label("Flights in selected");

        TableView<Flight> tableFlights = new TableView<>();
       /* List<Flight> tmp = controller.getFlightList();*/
        ObservableList<Flight> flightsAll = FXCollections.observableArrayList(tmp);

        flightsLabel.setFont(new Font("Arial", 20));
        tableFlights.setEditable(false);

        TableColumn flightId = new TableColumn("ID");
        flightId.setMinWidth(60);
        flightId.setCellValueFactory(new PropertyValueFactory<Flight, String>("id"));

        TableColumn cityFrom = new TableColumn("Departure");
        cityFrom.setMinWidth(100);
        cityFrom.setCellValueFactory(new PropertyValueFactory<Flight, String>("departure"));

        TableColumn cityTo = new TableColumn("Destination");
        cityTo.setMinWidth(100);
        cityTo.setCellValueFactory(new PropertyValueFactory<Flight, String>("destination"));
        tableFlights.setItems(flightsAll);
        tableFlights.getColumns().addAll(flightId, cityFrom, cityTo);
        flightsVBox.getChildren().addAll(flightsLabel, tableFlights);
        two.getChildren().addAll(suitesVBox /*flightsVBox*/);


        flightsGroup.getChildren().add(two);
        Label debugLabel = new Label("");
        //elements.getChildren().addAll(showAllButton,flightSuits);
        // hBox.getChildren().addAll(elements, debugLabel);

        primaryStage.setTitle("Flights app");
        primaryStage.setScene(new Scene(flightsGroup, 520, 275));
        primaryStage.show();
        /*Button showAllButton = new Button("Show all flights in new window");
        showAllButton.setOnAction((addFlightEvent) -> {
            new AllFlightsWindow(controller);
        });*/
    }

    public static void main(String[] args) {
        launch(args);
    }
}
