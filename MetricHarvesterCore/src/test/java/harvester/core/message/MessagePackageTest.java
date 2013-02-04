package harvester.core.message;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author T@urus
 */
public class MessagePackageTest {
    
    private MessagePackage pack;
    private Message msg1;
    private Message msg2;
    private Message msg3;
    
    @Before
    public void setUp () {
        pack = new MessagePackage();
        msg1 = createMessage( "1" );
        msg2 = createMessage( "2" );
        msg3 = createMessage( "3" );
    }
    
    private Message createMessage (String command) {
        Message result = new Message();
        result.setSendRequest( Calendar.getInstance().getTime() );
        result.setCommand( command );
        result.setSlaveId( "Id " + command );
        result.setResult( "result " + command );
        return result;
    }

    @Test
    public void testGetMessages_IfNotInitialized_ReturnNewEmptyCollection () {
        
        Collection<Message> expected = new LinkedList<Message>();
        Collection<Message> actual = pack.getMessages();
        
        assertEquals( expected, actual );
    }

    @Test
    public void testSetMessages_SetCollectionOfMessages_GetWhatWasAdded () {
        
        Collection<Message> expected = new LinkedList<Message>();
        expected.add( msg1 );
        expected.add( msg2 );
        expected.add( msg3 );
        pack.setMessages( expected );
        
        Collection<Message> actual = pack.getMessages();
        
        assertEquals( expected, actual );
    }

    @Test
    public void testAddMessage_AddThreeMessages_HaveThreeMessagesInPack () {
        
        pack.addMessage( msg1 );
        pack.addMessage( msg2 );
        pack.addMessage( msg3 );
        
        int expected = 3;
        int actual = pack.getMessages().size();
        
        assertEquals( expected, actual );
    }

    @Test
    public void testAddMessage_AddThreeMessagesAndNull_HaveThreeMessagesInPack () {
        
        pack.addMessage( msg1 );
        pack.addMessage( msg2 );
        pack.addMessage( msg3 );
        pack.addMessage( null );
        
        int expected = 3;
        int actual = pack.getMessages().size();
        
        assertEquals( expected, actual );
    }

    @Test
    public void testMakeSendRequestDates_PackWithThreeMessages_AllHaveSameDate () {
        
        pack.addMessage( msg1 );
        pack.addMessage( msg2 );
        pack.addMessage( msg3 );
        
        pack.makeSendRequestDates();
        
        Message [] messages = new Message[pack.getMessages().toArray().length];
        pack.getMessages().toArray( messages );
        
        assertEquals( messages[0].getSendRequest(), messages[1].getSendRequest());
        assertEquals( messages[1].getSendRequest(), messages[2].getSendRequest());
        assertEquals( messages[2].getSendRequest(), messages[0].getSendRequest());
    }

    @Test
    public void testMakeReciveRequestDates_PackWithThreeMessages_AllHaveSameDate () {
        
        pack.addMessage( msg1 );
        pack.addMessage( msg2 );
        pack.addMessage( msg3 );
        
        pack.makeReciveRequestDates();
        
        Message [] messages = new Message[pack.getMessages().toArray().length];
        pack.getMessages().toArray( messages );
        
        assertEquals( messages[0].getReciveRequest(), messages[1].getReciveRequest());
        assertEquals( messages[1].getReciveRequest(), messages[2].getReciveRequest());
        assertEquals( messages[2].getReciveRequest(), messages[0].getReciveRequest());
    }

    @Test
    public void testMakeSendResponseDates_PackWithThreeMessages_AllHaveSameDate () {
        
        pack.addMessage( msg1 );
        pack.addMessage( msg2 );
        pack.addMessage( msg3 );
        
        pack.makeSendResponseDates();
        
        Message [] messages = new Message[pack.getMessages().toArray().length];
        pack.getMessages().toArray( messages );
        
        assertEquals( messages[0].getSendResponse(), messages[1].getSendResponse());
        assertEquals( messages[1].getSendResponse(), messages[2].getSendResponse());
        assertEquals( messages[2].getSendResponse(), messages[0].getSendResponse());
    }

    @Test
    public void testMakeReciveResponseDates_PackWithThreeMessages_AllHaveSameDate () {
        
        pack.addMessage( msg1 );
        pack.addMessage( msg2 );
        pack.addMessage( msg3 );
        
        pack.makeReciveResponseDates();
        
        Message [] messages = new Message[pack.getMessages().toArray().length];
        pack.getMessages().toArray( messages );
        
        assertEquals( messages[0].getReciveResponse(), messages[1].getReciveResponse());
        assertEquals( messages[1].getReciveResponse(), messages[2].getReciveResponse());
        assertEquals( messages[2].getReciveResponse(), messages[0].getReciveResponse());
    }
}