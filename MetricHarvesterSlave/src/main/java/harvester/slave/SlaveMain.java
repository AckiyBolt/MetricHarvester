package harvester.slave;

import harvester.core.agent.AgentContainer;
import harvester.core.conversation.Conversation;
import harvester.core.conversation.ConversationProvider;
import harvester.core.message.Message;
import harvester.core.message.MessagePackage;
import harvester.core.message.SynchronizedMessageBuffer;
import harvester.slave.agent.SigarAgent;
import harvester.slave.agent.metric.CPUPercentAgent;
import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.hyperic.sigar.SigarException;

public class SlaveMain {

//    private static MetricProvider metricProvider = new MetricProvider();

    public static void main ( String[] args )
            throws SigarException {

        SynchronizedMessageBuffer buffer = new SynchronizedMessageBuffer();
        AgentContainer metricsContainer = createMetricsContainer(buffer);
        
        Conversation conversation = ConversationProvider.createSimpleConversation();
        
        Message message = null;
        
        while (0 == 0) {
            message = conversation.reciveMessage( "requestQueue" );
            message.setReciveRequest( Calendar.getInstance().getTime() );
            
//            ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
//            executor.execute( null );
//            executor.shutdown();
//        try {
//            executor.awaitTermination(1, TimeUnit.DAYS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
            
            
            message.setSendResponse( Calendar.getInstance().getTime() );
            conversation.sendMessage( message, "responseQueue" );
        }
    }

    private static AgentContainer createMetricsContainer (SynchronizedMessageBuffer buffer) {
        AgentContainer result = new AgentContainer();
        
        SigarAgent agent = new CPUPercentAgent( buffer );
        
        result.getAgents().add( new CPUPercentAgent( buffer ) );
        
        return result;
    }
}