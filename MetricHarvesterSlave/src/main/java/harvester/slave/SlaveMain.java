package harvester.slave;

import harvester.core.conversation.Conversation;
import harvester.core.conversation.ConversationProvider;
import harvester.core.message.Message;
import java.util.Calendar;
import org.hyperic.sigar.SigarException;

public class SlaveMain {

//    private static MetricProvider metricProvider = new MetricProvider();

    public static void main ( String[] args )
            throws SigarException {

        Conversation conversation = ConversationProvider.createSimpleConversation();
        
        Message message = null;
        
        while (0 == 0) {
            message = conversation.reciveMessage( "requestQueue" );
            message.setReciveRequrst( Calendar.getInstance().getTime() );
            
            
            
            
            message.setSendRespose( Calendar.getInstance().getTime() );
            conversation.sendMessage( message, "responseQueue   " );
        }
    }
}