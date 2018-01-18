import FlightApp.AirPlaneType;
import FlightApp.Flight;
import FlightApp.FlightBuilder;
import Jedis_db.JedisController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class NewFlightDialog extends Dialog<Flight> {

    Flight tmp;
    public NewFlightDialog(JedisController controller) {
        Stage stage = new Stage();
        stage.setTitle("New flight");
        Group group = new Group();

        List<String> cityList = controller.getCityList();
        ObservableList<String> list = FXCollections.observableArrayList(cityList);

        ComboBox<String> comboBoxfrom = new ComboBox<>();
        comboBoxfrom.setItems(list);
        comboBoxfrom.getSelectionModel().select(0);

        ComboBox<String> comboBoxto = new ComboBox<>();
        comboBoxto.setItems(list);
        comboBoxto.getSelectionModel().select(0);

        VBox mainBox = new VBox();
        HBox builderBox = new HBox();

        VBox vbox0 = new VBox();
        Label airCompany = new Label("Select aircompany:");
        TextField aircom = new TextField();
        vbox0.getChildren().add(airCompany);
        vbox0.getChildren().add(aircom);

        VBox vbox1 = new VBox();
        Label toCities = new Label("Select City to:");
        vbox1.getChildren().add(toCities);
        vbox1.getChildren().add(comboBoxfrom);

        VBox vbox2 = new VBox();
        Label fromCities = new Label("Select City from:");
        vbox2.getChildren().add(fromCities);
        vbox2.getChildren().add(comboBoxto);

        VBox vbox3 = new VBox();
        Label choosePlane = new Label("Choose plane:");
        ComboBox<AirPlaneType> planeTypeComboBox = new ComboBox<>();
        planeTypeComboBox.getItems().setAll(AirPlaneType.values());
        comboBoxto.getSelectionModel().select(1);
        vbox3.getChildren().add(choosePlane);
        vbox3.getChildren().add(planeTypeComboBox);

        Label output = new Label();
        Button submit = new Button("Submit");
        submit.setOnAction(event -> {

            tmp = new FlightBuilder()
                    .setCompany(aircom.getText())
                    .setDeparture(controller.getCityFromRedis(comboBoxfrom.getValue()))
                    .setDestination(controller.getCityFromRedis(comboBoxto.getValue()))
                    .setAirplane(planeTypeComboBox.getValue().toString())
                    .build();
            controller.addToRedis(tmp);
            output.setText("new Flight created"  + tmp.getInfo());
        });
        builderBox.getChildren().addAll(vbox0, vbox1, vbox2, vbox3);
        mainBox.getChildren().addAll(builderBox, submit, output);

      /*new Flight(
                        new FlightContext
                                (aircom.getAccessibleText(),
                comboBoxfrom.getSelectionModel().getSelectedItem(),
                comboBoxto.getSelectionModel().getSelectedItem(),
                planeTypeComboBox.getSelectionModel().toString())),controller);*/


        group.getChildren().add(mainBox);
        stage.setScene(new Scene(group, 450, 200));
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
