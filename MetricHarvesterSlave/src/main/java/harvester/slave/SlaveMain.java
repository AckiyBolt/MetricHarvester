package harvester.slave;

import harvester.configuration.Configuration;
import harvester.core.agent.AgentContainer;
import harvester.core.agent.conversation.MessageReciveAgent;
import harvester.core.agent.conversation.MessageSendAgent;
import harvester.core.conversation.ConversationProvider;
import harvester.core.message.Message;
import harvester.core.message.buffer.SynchronizedMessageBuffer;
import harvester.slave.agent.metric.CPUPercentAgent;
import harvester.slave.agent.metric.FQDNAgent;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

public class SlaveMain {

    private static String name;

    public static void main ( String[] args )
            throws InterruptedException,
                   SigarException {

        registrate();
        SynchronizedMessageBuffer buffer = new SynchronizedMessageBuffer();
        AgentContainer metricsContainer = createMetricsContainer( buffer );
        metricsContainer.startAgents();

        System.out.println( "All agents were started. Main thread going to be sleeped." );
        sleeping();
    }

    private static AgentContainer createMetricsContainer ( SynchronizedMessageBuffer buffer ) {
        AgentContainer result = new AgentContainer();

        result.getAgents().add( new CPUPercentAgent( buffer ) );
        result.getAgents().add( new FQDNAgent( buffer ) );
        result.getAgents().add( new MessageReciveAgent( buffer, name ) );
        result.getAgents().add( new MessageSendAgent.Response( buffer, Configuration.RABBIT_MQ.CLIENT_RESPONSE_QUEUE, name ) );

        return result;
    }

    private static void sleeping () {
        try {
            Thread.sleep( 1000 * 60 * 3 );
        } catch ( InterruptedException ex ) {
            System.out.println( ex );
        }
    }

    private static void registrate ()
            throws SigarException {

        Sigar sigar = new Sigar();
        name = sigar.getFQDN();

        Message message = new Message();
        message.setRequest( name );

        ConversationProvider.createSimpleConversation().sendMessage( message, Configuration.RABBIT_MQ.CLIENT_REGISTRATION_QUEUE );
        System.out.println( "Module for " + name + " has been started." );
    }
}