package harvester.slave.agent.metric;

import harvester.core.message.Message;
import harvester.core.message.SynchronizedMessageBuffer;
import harvester.slave.agent.SigarAgent;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.SigarException;

/**
 *
 * @author T@urus
 */
public class CPUPercentAgent
        extends SigarAgent {

    private final static int WAIT_TIME = 500;
    private final static int TEST_COUNT = 3000 / WAIT_TIME;
    private final static int DEFAULT_CORE_COUNT = 0;

    public CPUPercentAgent ( SynchronizedMessageBuffer messageBuffer ) {
        super( "CPUPercent", messageBuffer );
    }

    @Override
    protected void loadValue ( Message message )
            throws SigarException {

        int counter = 0;
        double buffer = 0.0;
        int coreCount = DEFAULT_CORE_COUNT;

        while ( counter < TEST_COUNT ) {

            CpuPerc[] source = sigar.getCpuPercList();
            coreCount = source.length;

            for ( int i = 0; i < source.length; i++ )
                buffer += 1.0 - source[i].getIdle();

        }

        String result = String.valueOf( buffer / ( coreCount * TEST_COUNT ) );
        message.setResult( result );
    }
}