package harvester.gui;

import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author T@urus
 */
public enum SatgeImpl {

    MAIN_INSTANCE( "main.fxml", "Metric harvester" ),
    TASK_INSTANCE( "task.fxml", "Task manager" );
    private Scene scene;
    private String title;
    private static Stage stage;
    private FXMLLoader loader;

    private SatgeImpl ( String fxml, String title ) {

        try {
            loader = new FXMLLoader( getClass().getResource( fxml ) );
            scene = new Scene( (Pane) loader.load() );
            
            this.title = title;

        } catch ( IOException ex ) {
            System.out.println( "Can't load fxml: " + fxml );
        }
    }

    public static void setStage ( Stage inStage ) {
        stage = inStage;
    }

    public void showScene () {
        stage.setScene( this.scene );
        stage.setTitle( title );
        stage.show();
    }

    public MainController getMainController () {
        return loader.<MainController>getController();
    }

    public TaskController getTaskController () {
        return loader.<TaskController>getController();
    }
}