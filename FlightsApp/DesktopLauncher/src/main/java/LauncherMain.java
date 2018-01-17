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
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;

import static FlightApp.AirPlaneType.*;


public class LauncherMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        InitServer.run();
        JedisPool pool = new JedisPool("localhost");
        JedisController controller = new JedisController(pool);
       // controller.removeFromRedis();

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

        List<Flight> tmp = new ArrayList<>();
        tmp.add(new Flight("BA", "London", "Kyiv", EMBRAER_170.toString(),"654","LK_4868","215"));
        tmp.add(new Flight("BA", "Riga", "Tokyo", AIRBUS_A310.toString(),"1125","RT_2342","598"));
        tmp.add(new Flight("BA", "Paris", "Tokyo", BOEING_767.toString(),"533","PT_4896","248"));
        tmp.add(new Flight("BA", "London", "Riga", BOEING_777.toString(),"315","LR_8474","188"));


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

        primaryStage.setTitle("Flights app");
        primaryStage.setScene(new Scene(flightsGroup, 320, 275));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);


    }


}
