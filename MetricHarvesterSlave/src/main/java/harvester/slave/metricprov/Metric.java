package harvester.slave.metricprov;

public interface Metric {

    String getIp ();

    void setIp ( String ip );

    Integer getId ();

    void setId ( Integer id );

    String getValue ();

    void setValue ( String value );
}