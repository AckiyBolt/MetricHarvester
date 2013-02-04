package harvester.core.conversation;

/**
 *
 * @author T@urus
 */
public class ConversationProvider {
    
    public static Conversation createSimpleConversation () {
        return new SimpleConversation();
    }
}