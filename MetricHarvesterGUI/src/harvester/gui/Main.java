package harvester.gui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 *
 * @author T@urus
 */
public class Main
        extends Application {
            
    @Override
    public void start ( Stage stage )
            throws Exception {
        
        SatgeImpl.setStage( stage );
        SatgeImpl.MAIN_INSTANCE.showScene();
    }

    public static void main ( String[] args ) {
        launch( args );
    }
}