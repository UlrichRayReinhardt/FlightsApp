import Location.City;
import Location.Location;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NewCityDialog extends Dialog<City> {
    public NewCityDialog(Stage primaryStage) {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);
        Label label1 = new Label("Enter city:");
        TextField textField = new TextField();
        VBox content = new VBox();
        HBox hb1 = new HBox();
        hb1.getChildren().addAll(label1, textField);
        hb1.setSpacing(10);

        HBox hb2 = new HBox();
        Label lat = new Label("Latitude: ");
        double latitude = 0;
        double longitude = 0;
        Label latresp = new Label(String.valueOf(latitude));
        hb2.getChildren().addAll(lat, latresp);

        HBox hb3 = new HBox();
        Label lng = new Label("Longitude: ");
        Label lngresp = new Label(String.valueOf(longitude));
        hb3.getChildren().addAll(lng, lngresp);
        content.getChildren().addAll(hb1, hb2, hb3);

        Scene dialogScene = new Scene(content, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();

        Button getLocation = new Button("fetch location data");
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
            //DB.getDbInstance().addCity(new City(textField.getText(),latitude,longitude));
        });
    }
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

}

