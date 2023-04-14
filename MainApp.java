package pecunia;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * NAMEs: Yurii Bezborodov (30114669), Tamerlan Ormanbayev (30141208)
 * TUTORIALs: T07, T03
 * DATE: 18th MARCH 2022
 */

public class MainApp extends Application {

    /**
     * Main() launches things.
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * start() opens up the chosen .fxml file as soon as user opens the program, with
     * an FXMLLoader, in a 600x400 window.
     * We also set the title of a window, and show it right to the user.
     *
     *
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("WecomeMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Pecunia v1.0");
        stage.setScene(scene);
        stage.show();

    }
}
