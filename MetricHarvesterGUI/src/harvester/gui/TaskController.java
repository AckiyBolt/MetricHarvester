package harvester.gui;

import com.google.code.morphia.Datastore;
import harvester.model.db.DatastoreManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import harvester.model.entity.Task;
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
    private ClientHolder clientHolder;
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
        prepareData();
    }
    
    private void prepareData () {
        selectedTask = null;
        selectedTasksList.getItems().clear();
        availableTasksList.getItems().clear();
        addNewTaskTB.setText( null );
        selectedTaskName.setText( null );
        selectedTaskPeriod.setText( null );
        
        Task task1 = new Task();
        task1.setMetricName( "metric name1" );
        task1.setPeriod( 666L );
        Task task2 = new Task();
        task2.setMetricName( "metric name2" );
        task2.setPeriod( 667L );
        Task task3 = new Task();
        task3.setMetricName( "metric name3" );
        task3.setPeriod( 668L );
        selectedTasksList.getItems().add( task1 );
        selectedTasksList.getItems().add( task2 );
        availableTasksList.getItems().add( task3 );
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
        datastore.merge( clientHolder.getClient() );
    }

    public void selectedTaskListClick ( MouseEvent event ) {
        Task focusedItem = selectedTasksList.getFocusModel().getFocusedItem();
        selectedTaskName.setText( focusedItem.getMetricName() );
        selectedTaskPeriod.setText( focusedItem.getPeriod().toString() );
        selectedTaskPeriod.setEditable( true );
        selectedTask = focusedItem;
    }

    public void availableTaskListClick ( MouseEvent event ) {
        Task focusedItem = availableTasksList.getFocusModel().getFocusedItem();
        selectedTaskName.setText( focusedItem.getMetricName() );
        selectedTaskPeriod.setText( focusedItem.getPeriod().toString() );
        selectedTaskPeriod.setEditable( false );
        selectedTask = focusedItem;
    }

    @FXML
    private void back ( ActionEvent event ) {
        SatgeImpl.MAIN_INSTANCE.showScene();
        prepareData();
    }

    
    
    @FXML
    private void selectAllTasks ( ActionEvent event ) {
        ObservableList<Task> items = availableTasksList.getItems();
        clientHolder.INSTANCE.getClient().getTasks().addAll( items );
        selectedTasksList.getItems().addAll( items );
        availableTasksList.getItems().removeAll( items );
    }

    @FXML
    private void deselectAllTasks ( ActionEvent event ) {
        ObservableList<Task> items = selectedTasksList.getItems();
        clientHolder.INSTANCE.getClient().getTasks().removeAll(items );
        availableTasksList.getItems().addAll( items );
        selectedTasksList.getItems().removeAll( items );
    }

    @FXML
    private void selectTask ( ActionEvent event ) {
        Task focusedItem = availableTasksList.getFocusModel().getFocusedItem();
        if (focusedItem == null) return;
        clientHolder.INSTANCE.getClient().getTasks().add( focusedItem );
        selectedTasksList.getItems().add( focusedItem );
        availableTasksList.getItems().remove( focusedItem );
    }

    @FXML
    private void deselectTask ( ActionEvent event ) {
        Task focusedItem = selectedTasksList.getFocusModel().getFocusedItem();
        if (focusedItem == null) return;
        clientHolder.INSTANCE.getClient().getTasks().remove(focusedItem );
        availableTasksList.getItems().add( focusedItem );
        selectedTasksList.getItems().remove( focusedItem );
    }
}