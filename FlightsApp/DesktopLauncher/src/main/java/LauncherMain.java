import FlightApp.Direction;
import FlightApp.Flight;
import Jedis_db.DB;
import Jedis_db.JedisController;
import Location.City;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import redis.clients.jedis.JedisPool;


public class LauncherMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        InitServer.run();
        JedisPool pool = new JedisPool("localhost");
        JedisController controller = new JedisController(pool);
        controller.removeFromRedis();

        /*Group cityGroup = new Group();
        ObservableList<City> list = FXCollections.observableArrayList(DB.getDbInstance().getCityList());

        ComboBox<City> comboBoxfrom = new ComboBox<>();
        comboBoxfrom.setItems(list);
        comboBoxfrom.getSelectionModel().select(1);

        ComboBox<City> comboBoxto = new ComboBox<>();
        comboBoxto.setItems(list);
        comboBoxto.getSelectionModel().select(1);


        VBox box1 = new VBox();
        Label toCities = new Label("Select City to:");
        box1.getChildren().add(toCities);
        box1.getChildren().add(comboBoxfrom);
        Label fromCities = new Label("Select City from:");
        box1.getChildren().add(fromCities);
        box1.getChildren().add(comboBoxto);
        Button addCity = new Button("add city");
        box1.getChildren().add(addCity);
        cityGroup.getChildren().add(box1);

        addCity.setOnAction(event -> {
            new NewCityDialog(primaryStage);
        });*/
        TableView<Flight> table = new TableView<>();
        ObservableList<Flight> list = FXCollections.observableArrayList(DB.getDbInstance().getFlightsList());

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
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);

        flightsGroup.getChildren().addAll(vbox);

        primaryStage.setTitle("Flights app");
        primaryStage.setScene(new Scene(flightsGroup, 300, 275));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);


    }


}
