package metricharvester.gui;

import com.google.code.morphia.query.Query;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author T@urus
 */
public class FormController
        implements Initializable {

    @FXML
    private ChoiceBox choiceBox;
    @FXML
    private TableView table;
    private DatabaseManager manager;

    @FXML
    private void handleLoadAction ( ActionEvent event ) {
        Query<Message> find = manager.getDatastore().find( Message.class );
        List<Message> msgList = find.asList();
        
        for ( Message message : msgList ) {
            table.getItems().add( message );
        }
    }

    @FXML
    private void handleClearAction ( ActionEvent event ) {
        table.getItems().clear();
    }

    @Override
    public void initialize ( URL url, ResourceBundle rb ) {
        try {
            manager = new DatabaseManager();
            
        } catch ( UnknownHostException ex ) {
            Logger.getLogger( FormController.class.getName() ).log( Level.SEVERE, null, ex );
        }
            
    //        choiceBox.getItems().clear();
    //        choiceBox.getItems().add( "Test 1" );
    //        choiceBox.getItems().add( "Test 2" );
    //        choiceBox.getItems().add( "Test 3" );

        
        table.getColumns().clear();
        addColumn( "slaveId" );
        addColumn( "command" );
        addColumn( "result" );
        addColumn( "sendRequest" );
        addColumn( "reciveRequest" );
        addColumn( "sendResponse" );
        addColumn( "reciveResponse" );
    }

    private void addColumn ( String val ) {
        TableColumn column = new TableColumn();
        column.setText( val );
        column.setMinWidth( 790 / 7 );
        column.setCellValueFactory( new PropertyValueFactory( val ) );
        table.getColumns().add( column );
    }
}