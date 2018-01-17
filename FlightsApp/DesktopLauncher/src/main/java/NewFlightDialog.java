import FlightApp.Flight;
import Jedis_db.JedisController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class NewFlightDialog extends Dialog<Flight> {
    public NewFlightDialog(JedisController controller) {
        Stage stage = new Stage();
        stage.setTitle("New flight");

        Group group = new Group();

        //Label label1 = new Label("Enter city:");

        List<String> cityList = controller.readCitiesFromRedis();
        ObservableList<String> list = FXCollections.observableArrayList(cityList);

        ComboBox<String> comboBoxfrom = new ComboBox<>();
        comboBoxfrom.setItems(list);
        comboBoxfrom.getSelectionModel().select(1);

        ComboBox<String> comboBoxto = new ComboBox<>();
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
        group.getChildren().add(box1);

        stage.setScene(new Scene(group, 400, 200));
        stage.show();

       /* Button getLocation = new Button("fetch location data");
        content.getChildren().add(getLocation);
        getLocation.setOnAction(event -> {
            if (textField.getText() != null && !textField.getText().isEmpty()) {
                Location location = Location.fetchLocation(textField.getText());
                latresp.setText(String.valueOf(location.lat()));
                lngresp.setText(String.valueOf(location.lng()));
            }
        });

        Button submit = new Button("Submit");
        content.getChildren().add(submit);
        submit.setOnAction(event2 -> {

        });*/
    }

}
