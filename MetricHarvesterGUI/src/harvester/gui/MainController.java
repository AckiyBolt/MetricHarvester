package harvester.gui;

import com.google.code.morphia.Datastore;
import harvester.model.db.DatastoreManager;
import harvester.model.entity.Client;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Menu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import harvester.model.entity.Metric;
import java.util.List;

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

    public MainController () {
    }

    @FXML
    private void handleButtonAction ( ActionEvent event ) {
        
        SatgeImpl.TASK_INSTANCE.showScene();
//        //initMockGrid();
//        loadGridData();
//        //initMockChart();
//        initMenu();
    }

    private void loadGridData () {
        DatastoreManager manager = new DatastoreManager();
        datastore = manager.getDatastore();

        List<Client> clientMetrics = datastore.find( Client.class ).asList();
        for ( Client client : clientMetrics ) {
            List<Metric> metrics = client.getMetrics();
            initChart( client );
            for ( Metric metric : metrics ) {
                metricTable.getItems().add( metric );
            }
        }
    }

    private void initChart ( Client client ) {
        final NumberAxis xAxis = new NumberAxis( "Steps", 0, 100, 1 );
        final NumberAxis yAxis = new NumberAxis( "Load", 0, 1, 0.001 );
        metricChart.setTitle( "CPU Load (in %)" );
        XYChart.Series series = new XYChart.Series();
        series.setName( client.getName() );

        List<Metric> metrics = client.getMetrics();
        int index = 0;
        for ( Metric metric : metrics ) {
            series.getData().add( new XYChart.Data( index++, (int)(Double.valueOf( metric.getResult() ) * 100) ) );
        }
        metricChart.getData().add( series );
    }

    private void initMockChart () {

        System.out.println( "\nChart initialization" );

        final NumberAxis xAxis = new NumberAxis( 0, 31, 1 );
        xAxis.setMinorTickCount( 0 );
        final NumberAxis yAxis = new NumberAxis( 0, 27, 5 );
        yAxis.setMinorTickLength( yAxis.getTickLength() );
        yAxis.setForceZeroInRange( false );

        metricChart.setTitle( "Temperature Monitoring (in Degrees C)" );

        XYChart.Series seriesApril = new XYChart.Series();
        seriesApril.setName( "April" );
        seriesApril.getData().add( new XYChart.Data( 0, 4 ) );
        seriesApril.getData().add( new XYChart.Data( 3, 10 ) );
        seriesApril.getData().add( new XYChart.Data( 6, 15 ) );
        seriesApril.getData().add( new XYChart.Data( 9, 8 ) );
        seriesApril.getData().add( new XYChart.Data( 12, 5 ) );
        seriesApril.getData().add( new XYChart.Data( 15, 18 ) );
        seriesApril.getData().add( new XYChart.Data( 18, 15 ) );
        seriesApril.getData().add( new XYChart.Data( 21, 13 ) );
        seriesApril.getData().add( new XYChart.Data( 24, 19 ) );
        seriesApril.getData().add( new XYChart.Data( 27, 21 ) );
        seriesApril.getData().add( new XYChart.Data( 30, 21 ) );

        XYChart.Series seriesMay = new XYChart.Series();
        seriesMay.setName( "May" );
        seriesMay.getData().add( new XYChart.Data( 0, 20 ) );
        seriesMay.getData().add( new XYChart.Data( 3, 15 ) );
        seriesMay.getData().add( new XYChart.Data( 6, 13 ) );
        seriesMay.getData().add( new XYChart.Data( 9, 12 ) );
        seriesMay.getData().add( new XYChart.Data( 12, 14 ) );
        seriesMay.getData().add( new XYChart.Data( 15, 18 ) );
        seriesMay.getData().add( new XYChart.Data( 18, 25 ) );
        seriesMay.getData().add( new XYChart.Data( 21, 25 ) );
        seriesMay.getData().add( new XYChart.Data( 24, 23 ) );
        seriesMay.getData().add( new XYChart.Data( 27, 26 ) );
        seriesMay.getData().add( new XYChart.Data( 31, 26 ) );

        XYChart.Series seriesMarch = new XYChart.Series();
        seriesMarch.setName( "March" );
        seriesMarch.getData().add( new XYChart.Data( 0, 2 ) );
        seriesMarch.getData().add( new XYChart.Data( 3, 4 ) );
        seriesMarch.getData().add( new XYChart.Data( 6, 0 ) );
        seriesMarch.getData().add( new XYChart.Data( 9, 5 ) );
        seriesMarch.getData().add( new XYChart.Data( 12, 4 ) );
        seriesMarch.getData().add( new XYChart.Data( 15, 6 ) );
        seriesMarch.getData().add( new XYChart.Data( 18, 8 ) );
        seriesMarch.getData().add( new XYChart.Data( 21, 14 ) );
        seriesMarch.getData().add( new XYChart.Data( 24, 4 ) );
        seriesMarch.getData().add( new XYChart.Data( 27, 6 ) );
        seriesMarch.getData().add( new XYChart.Data( 31, 6 ) );

        metricChart.getData().addAll( seriesMarch, seriesApril, seriesMay );

        System.out.println( "done" );
    }

    private void initMenu () {

        System.out.println( "\nMenu initialization" );

        Menu menuFile = new Menu( "File" );
        Menu menuEdit = new Menu( "Edit" );
        Menu menuView = new Menu( "View" );

        vpsMenu.getItems().addAll( menuFile, menuEdit, menuView );

        System.out.println( "done" );
    }

    @Override
    public void initialize ( URL url, ResourceBundle rb ) {
        initGridColumns();
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

    private void initMockGrid () {

        System.out.println( "\nGrid initialization" );

        System.out.println( "     Grid columns initialization" );
        initGridColumns();
        System.out.println( "     done" );

        System.out.println( "     Grid data initialization" );
        initMockGridData();
        System.out.println( "     done" );

        System.out.println( "done" );
    }

    private void initMockGridData () {

        Metric m1 = new Metric();
        m1.setClientName( "m1 name" );
        m1.setMetric( "m1 metric" );
        m1.setResult( "m1 result" );

        Metric m2 = new Metric();
        m2.setClientName( "m2 name" );
        m2.setMetric( "m2 metric" );
        m2.setResult( "m2 result" );

        Metric m3 = new Metric();
        m3.setClientName( "m3 name" );
        m3.setMetric( "m3 metric" );
        m3.setResult( "m3 result" );

        Metric m4 = new Metric();
        m4.setClientName( "m4 name" );
        m4.setMetric( "m4 metric" );
        m4.setResult( "m4 result" );

        ObservableList<Metric> data = FXCollections.observableArrayList( m1, m2, m3, m4 );

        metricTable.setItems( data );
    }
}
