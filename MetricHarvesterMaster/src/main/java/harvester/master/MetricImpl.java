package harvester.master;

import java.io.Serializable;

public class MetricImpl
        implements Metric,
                   Serializable {

    String ip;
    Integer id;
    String value;

    @Override
    public String getIp () {
        return ip;
    }

    @Override
    public void setIp ( String ip ) {
        this.ip = ip;
    }

    @Override
    public Integer getId () {
        return id;
    }

    @Override
    public void setId ( Integer id ) {
        this.id = id;
    }

    @Override
    public String getValue () {
        return value;
    }

    @Override
    public void setValue ( String value ) {
        this.value = value;
    }
}
