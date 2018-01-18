import FlightApp.Flight;
import Jedis_db.JedisController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class AllFlightsWindow extends Dialog<Flight> {

    public AllFlightsWindow(JedisController controller) {
        Stage stage = new Stage();
        stage.setTitle("New flight");

        TableView<Flight> table = new TableView<>();
        List<Flight> tmp = new ArrayList<>();

        for (String flight : controller.getFlightStringList()) {
            tmp.add(controller.getFlightFromRedis(flight));
        }

        ObservableList<Flight> list = FXCollections.observableArrayList(tmp);

        final Label label = new Label("Flights table");
        label.setFont(new Font("Arial", 20));
        table.setEditable(false);

        TableColumn flightId = new TableColumn("ID");
        flightId.setMinWidth(60);
        flightId.setCellValueFactory(new PropertyValueFactory<Flight, String>("id"));

        TableColumn cityFrom = new TableColumn("Departure");
        cityFrom.setMinWidth(100);
        cityFrom.setCellValueFactory(new PropertyValueFactory<Flight, String>("departure"));

        TableColumn cityTo = new TableColumn("Destination");
        cityTo.setMinWidth(100);
        cityTo.setCellValueFactory(new PropertyValueFactory<Flight, String>("destination"));
        table.setItems(list);
        table.getColumns().addAll(flightId, cityFrom, cityTo);

        Group root = new Group();
        VBox allFlightsBox = new VBox();
        allFlightsBox.setSpacing(5);
        allFlightsBox.setPadding(new Insets(10, 10, 10, 10));

        HBox buttons = new HBox();

        Button addFlight = new Button("Add new Flight");
        addFlight.setOnAction((addFlightEvent) -> {
            new NewFlightDialog(controller);
        });
        Label debugLabel = new Label("");

        Button removeFlight = new Button("Remove Flight ");
        removeFlight.setOnAction((removeEvent) -> {
            try {
                Flight flight = table.getSelectionModel().getSelectedItem();
                table.getItems().remove(table.getSelectionModel().getSelectedItem());
                controller.removeFlight(flight.getId());
            } catch (NullPointerException e) {
                debugLabel.setText("Choose flight row and then click button remove");
            }
        });

        Button cleanAll = new Button("Clean         ");
        cleanAll.setOnAction((cleanAllEvent) -> {
            list.clear();
            controller.clear();
        });

        buttons.getChildren().addAll(addFlight,removeFlight,cleanAll);
        allFlightsBox.getChildren().addAll(table, buttons,debugLabel);
        root.getChildren().add(allFlightsBox);

        stage.setScene(new Scene(root, 300, 500));
        stage.show();

    }
}

