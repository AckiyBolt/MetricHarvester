package harvester.gui;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    private SatgeImpl ( String fxml, String title ) {

        try {
            Parent root = FXMLLoader.load( getClass().getResource( fxml ) );
            scene = new Scene( root );
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
}