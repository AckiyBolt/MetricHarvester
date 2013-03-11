package harvester.slave;

import harvester.core.agent.AgentContainer;
import harvester.core.message.SynchronizedMessageBuffer;
import harvester.slave.agent.MessageReciveAgent;
import harvester.slave.agent.MessageSendAgent;
import harvester.slave.agent.metric.CPUPercentAgent;
import harvester.slave.agent.metric.FQDNAgent;

public class SlaveMain {

    public static void main ( String[] args )
            throws InterruptedException {

        SynchronizedMessageBuffer buffer = new SynchronizedMessageBuffer();
        AgentContainer metricsContainer = createMetricsContainer( buffer );
        metricsContainer.startAgents();

        System.out.println( "All threads were started. Main thread going to be sleeped." );
        sleeping();
    }

    private static AgentContainer createMetricsContainer ( SynchronizedMessageBuffer buffer ) {
        AgentContainer result = new AgentContainer();

        result.getAgents().add( new CPUPercentAgent( buffer ) );
        result.getAgents().add( new FQDNAgent( buffer ) );
        result.getAgents().add( new MessageReciveAgent( buffer ) );
        result.getAgents().add( new MessageSendAgent( buffer ) );

        return result;
    }

    private static void sleeping () {
        try {
            Thread.sleep( 1000 * 60 * 3 );
        } catch ( InterruptedException ex ) {
            System.out.println( ex );
        }
    }
}