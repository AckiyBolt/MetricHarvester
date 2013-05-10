package harvester.gui;

import com.google.code.morphia.Datastore;
import harvester.model.db.DatastoreManager;
import harvester.model.entity.Client;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import harvester.model.entity.Task;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author T@urus
 */
public class TaskController
        implements Initializable {

    private Datastore datastore;
    private Task selectedTask;
    @FXML
    private ListView<Task> selectedTasksList;
    @FXML
    private ListView<Task> availableTasksList;
    @FXML
    private TextField addNewTaskTB;
    @FXML
    private TextField selectedTaskName;
    @FXML
    private TextField selectedTaskPeriod;

    @Override
    public void initialize ( URL url, ResourceBundle rb ) {
        DatastoreManager manager = new DatastoreManager();
        datastore = manager.getDatastore();
    }

    public void prepareData () {
        selectedTask = null;
        selectedTasksList.getItems().clear();
        availableTasksList.getItems().clear();
        addNewTaskTB.setText( null );
        selectedTaskName.setText( null );
        selectedTaskPeriod.setText( null );
        
        List<Task> clientTasks = ClientHolder.INSTANCE.getClient().getTasks();
        List<Task> dbTasks = datastore.find( Task.class ).asList();
        dbTasks.removeAll( clientTasks );
        availableTasksList.getItems().addAll( dbTasks );
        selectedTasksList.getItems().addAll( clientTasks );
    }

    @FXML
    private void addTask ( ActionEvent event ) {
        String name = addNewTaskTB.getText();
        Task task = new Task();
        task.setMetricName( name );
        datastore.save( task );
        availableTasksList.getItems().add( task );
    }

    @FXML
    private void setSelectedTaskPeriod ( ActionEvent event ) {
        Long period = Long.valueOf( selectedTaskPeriod.getText() );
        selectedTask.setPeriod( period );
        
        datastore.merge( ClientHolder.INSTANCE.getClient() );
    }

    @FXML
    public void selectedTaskListClick ( MouseEvent event ) {
        selectedTaskPeriod.setEditable( true );
        
        Task focusedItem = selectedTasksList.getFocusModel().getFocusedItem();
        if ( focusedItem == null )
            return;
        
        selectedTaskName.setText( focusedItem.getMetricName() );
        if ( focusedItem.getPeriod() != null )
            selectedTaskPeriod.setText( focusedItem.getPeriod().toString() );
        selectedTask = focusedItem;
    }

    @FXML
    public void availableTaskListClick ( MouseEvent event ) {
        selectedTaskPeriod.setEditable( false );
        
        Task focusedItem = availableTasksList.getFocusModel().getFocusedItem();
        if ( focusedItem == null )
            return;
        
        selectedTaskName.setText( focusedItem.getMetricName() );
        selectedTask = focusedItem;
    }

    @FXML
    private void back ( ActionEvent event ) {
        SatgeImpl.MAIN_INSTANCE.showScene();
    }

    @FXML
    private void selectAllTasks ( ActionEvent event ) {
        ObservableList<Task> items = availableTasksList.getItems();
        
        Client client = ClientHolder.INSTANCE.getClient();
        client.getTasks().addAll( items );
        datastore.merge( client );
        
        selectedTasksList.getItems().addAll( items );
        availableTasksList.getItems().removeAll( items );
    }

    @FXML
    private void deselectAllTasks ( ActionEvent event ) {
        ObservableList<Task> items = selectedTasksList.getItems();
        
        Client client = ClientHolder.INSTANCE.getClient();
        client.getTasks().removeAll( items );
        datastore.merge( client );
        
        availableTasksList.getItems().addAll( items );
        selectedTasksList.getItems().removeAll( items );
    }

    @FXML
    private void selectTask ( ActionEvent event ) {
        Task focusedItem = availableTasksList.getFocusModel().getFocusedItem();
        if ( focusedItem == null )
            return;
        
        Client client = ClientHolder.INSTANCE.getClient();
        client.getTasks().add( focusedItem );
        datastore.merge( client );
        
        selectedTasksList.getItems().add( focusedItem );
        availableTasksList.getItems().remove( focusedItem );
    }

    @FXML
    private void deselectTask ( ActionEvent event ) {
        Task focusedItem = selectedTasksList.getFocusModel().getFocusedItem();
        if ( focusedItem == null )
            return;
        
        Client client = ClientHolder.INSTANCE.getClient();
        client.getTasks().remove( focusedItem );
        datastore.merge( client );
        
        availableTasksList.getItems().add( focusedItem );
        selectedTasksList.getItems().remove( focusedItem );
    }
}