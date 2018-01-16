import Jedis_db.DB;
import Location.City;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class LauncherMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        InitFirst.run();

        Group root = new Group();
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
        root.getChildren().add(box1);


        addCity.setOnAction(event -> {
            new NewCityDialog(primaryStage);
        });

        primaryStage.setTitle("Flights app");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);


    }


}
