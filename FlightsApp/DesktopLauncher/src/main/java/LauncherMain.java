import FileRead.BackUpData;
import FlightApp.Flight;
import Jedis_db.JedisController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
        InitServer.run();
        JedisPool pool = new JedisPool("localhost");
        JedisController controller = new JedisController(pool);
        //controller.clear();
        new BackUpData(pool);


        TableView<Flight> table = new TableView<>();
        List<Flight> tmp = new ArrayList<>();
        for (String flight : controller.getFlightStringList()) {
            tmp.add(controller.getFlightFromRedis(flight));
        }
        ObservableList<Flight> list = FXCollections.observableArrayList(tmp);

        Group flightsGroup = new Group();
        final Label label = new Label("Flights table");
        label.setFont(new Font("Arial", 20));
        table.setEditable(false);

        TableColumn flightId = new TableColumn("ID");
        flightId.setMinWidth(100);
        flightId.setCellValueFactory(new PropertyValueFactory<Flight, String>("id"));

        TableColumn cityFrom = new TableColumn("Departure");
        cityFrom.setMinWidth(100);
        cityFrom.setCellValueFactory(new PropertyValueFactory<Flight, String>("departure"));

        TableColumn cityTo = new TableColumn("Destination");
        cityTo.setMinWidth(100);
        cityTo.setCellValueFactory(new PropertyValueFactory<Flight, String>("destination"));
        table.setItems(list);
        table.getColumns().addAll(flightId, cityFrom, cityTo);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.getChildren().addAll(label, table);
        flightsGroup.getChildren().addAll(vbox);

        Button addFlight = new Button("Add new Flight");
        addFlight.setOnAction(event -> new NewFlightDialog(controller));

        flightsGroup.getChildren().add(addFlight);

        primaryStage.setTitle("Flights app");
        primaryStage.setScene(new Scene(flightsGroup, 320, 275));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);


    }


}
