package metricharvester.gui;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
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

    @FXML
    private void handleLoadAction ( ActionEvent event ) {
        
        Date date = new Date();
        Message message = new Message();
        
        message.setCommand( "cmd" );
        message.setSlaveId( "id" );
        message.setResult( "res" );
        message.setReciveRequest( date );
        message.setReciveResponse( date );
        message.setSendRequest( date );
        message.setSendResponse( date );

        table.getItems().add( message );
    }

    @FXML
    private void handleClearAction ( ActionEvent event ) {
        table.getItems().clear();
    }

    @Override
    public void initialize ( URL url, ResourceBundle rb ) {
        
        choiceBox.getItems().clear();
        choiceBox.getItems().add( "Test 1" );
        choiceBox.getItems().add( "Test 2" );
        choiceBox.getItems().add( "Test 3" );

        
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