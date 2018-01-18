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
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import redis.clients.jedis.JedisPool;

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

        VBox elements = new VBox();
        Button showAllButton = new Button("Show all flights in new window");
        showAllButton.setOnAction((addFlightEvent) -> {
            new AllFlightsWindow(controller);
        });
        elements.setPadding(new Insets(10, 0, 0, 0));

        TableView<String> flightSuits = new TableView<>();

        List<Flight> tmp = controller.getFlightList();
        ObservableList<Flight> list = FXCollections.observableArrayList(tmp);

        Label debugLabel = new Label("");
        elements.getChildren().addAll(showAllButton,flightSuits);
       // hBox.getChildren().addAll(elements, debugLabel);

        primaryStage.setTitle("Flights app");
        primaryStage.setScene(new Scene(flightsGroup, 520, 275));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
