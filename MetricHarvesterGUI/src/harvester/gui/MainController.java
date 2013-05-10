package harvester.gui;

import com.google.code.morphia.Datastore;
import harvester.model.db.DatastoreManager;
import harvester.model.entity.Client;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Menu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import harvester.model.entity.Metric;
import java.util.List;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

/**
 *
 * @author T@urus
 */
public class MainController
        implements Initializable {

    private Datastore datastore;
    @FXML
    private TableView metricTable;
    @FXML
    private AreaChart<Number, Number> metricChart;
    @FXML
    private Menu vpsMenu;
    private MenuItem updateMenuItem;

    @FXML
    private void handleButtonAction ( ActionEvent event ) {
        SatgeImpl.TASK_INSTANCE.showScene();
    }

    private void fillGridData ( Client client ) {
        metricTable.getItems().clear();
        List<Metric> metrics = client.getMetrics();
        metricTable.getItems().addAll( metrics );
    }

    private void fillChart ( Client client ) {
        metricChart.getData().clear();
        
        metricChart.setTitle( "CPU Load (in %)" );
        XYChart.Series series = new XYChart.Series();
        series.setName( client.getName() );

        List<Metric> metrics = client.getMetrics();
        int index = 0;
        for ( Metric metric : metrics ) {
            series.getData().add( new XYChart.Data( index++, ( int ) ( Double.valueOf( metric.getResult() ) * 100 ) ) );
        }
        metricChart.getData().add( series );
    }

    private void initMenu () {
        
        vpsMenu.getItems().clear();
        vpsMenu.getItems().addAll( updateMenuItem, new SeparatorMenuItem() );

        List<Client> clients = datastore.find( Client.class ).asList();

        for ( final Client client : clients ) {

            MenuItem item = new MenuItem( client.getName() );
            item.addEventHandler( ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                @Override
                public void handle ( ActionEvent t ) {
                    fillGridData( client );
                    fillChart( client );
                }
            } );

            vpsMenu.getItems().add( item );
        }
    }

    private void createUpdateMenuItem () {
        updateMenuItem = new MenuItem( "Update" );
        updateMenuItem.addEventHandler( ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            public void handle ( ActionEvent t ) {
                initMenu();
            }
        } );
    }

    @Override
    public void initialize ( URL url, ResourceBundle rb ) {
        DatastoreManager manager = new DatastoreManager();
        datastore = manager.getDatastore();
        
        initGridColumns();
        
        createUpdateMenuItem();
        vpsMenu.getItems().add( updateMenuItem );
        
        metricChart.getXAxis().setLabel( "Steps" );
        metricChart.getYAxis().setLabel( "Load" );
    }

    private void initGridColumns () {

        metricTable.getColumns().clear();
        metricTable.getColumns().add( createColumn( "FQDN", "clientName" ) );
        metricTable.getColumns().add( createColumn( "Metric", "metric" ) );
        metricTable.getColumns().add( createColumn( "Result", "result" ) );
        metricTable.getColumns().add( createColumn( "Send request", "sendRequest" ) );
        metricTable.getColumns().add( createColumn( "Recive request", "reciveRequest" ) );
        metricTable.getColumns().add( createColumn( "Send responce", "sendResponse" ) );
        metricTable.getColumns().add( createColumn( "Recive responce", "reciveResponse" ) );
    }

    private TableColumn createColumn ( String columnName, String propName ) {

        TableColumn column = new TableColumn( columnName );
        column.setPrefWidth( 110.0 );
        column.setCellValueFactory(
                new PropertyValueFactory<Metric, String>( propName ) );

        return column;
    }
}