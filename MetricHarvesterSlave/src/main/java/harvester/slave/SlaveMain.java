package harvester.slave;

import harvester.core.Configuration;
import harvester.core.agent.AgentContainer;
import harvester.core.agent.conversation.MessageReciveAgent;
import harvester.core.agent.conversation.MessageSendAgent;
import harvester.core.message.buffer.SynchronizedMessageBuffer;
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
        result.getAgents().add( new MessageReciveAgent( buffer, Configuration.RABBIT_MQ.CLIENT_REGISTRATION_QUEUE ) );
        result.getAgents().add( new MessageSendAgent( buffer, Configuration.RABBIT_MQ.CLIENT_RESPONSE_QUEUE ) );

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