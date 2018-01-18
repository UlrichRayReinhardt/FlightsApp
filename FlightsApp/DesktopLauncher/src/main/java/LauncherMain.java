import FileRead.BackUpData;
import Jedis_db.JedisController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import redis.clients.jedis.JedisPool;

public class LauncherMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        //MainServer.main(new String[]{});
        JedisPool pool = new JedisPool("localhost");
        JedisController controller = new JedisController(pool);

        controller.clear();
        new BackUpData(pool);

        Group flightsGroup = new Group();
        HBox hBox = new HBox();

        Label debugLabel = new Label("");

        VBox buttons = new VBox();
        Button showAllButton = new Button("Show all flights in new window");
        showAllButton.setOnAction((addFlightEvent) -> {
            new AllFlightsWindow(controller);
        });

        buttons.setPadding(new Insets(10, 0, 0, 0));

        buttons.getChildren().addAll(showAllButton);
        hBox.getChildren().addAll(buttons, debugLabel);

        flightsGroup.getChildren().add(hBox);

        primaryStage.setTitle("Flights app");
        primaryStage.setScene(new Scene(flightsGroup, 520, 275));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
