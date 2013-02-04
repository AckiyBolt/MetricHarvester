/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package harvester.core.message;

import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kostiantyn_Belentso
 */
public class MessageBufferTest {
    
    private MessageBuffer buffer;
    private Message msg1;
    private Message msg2;
    private Message msg3;
    
    @Before
    public void setUp () {
        buffer = new MessageBuffer();
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
    public void testGetMessages_getMessagesFromEmptyBuffer_ResultIsNotNull () {
        
        Collection<Message> messages = buffer.getMessages();
        assertNotNull( messages );
    }
    
    @Test
    public void testGetMessages_getMessagesFromEmptyBuffer_ResultIsEmptyCollection () {
        
        Collection<Message> messages = buffer.getMessages();
        assertTrue( messages.isEmpty() );
    }
    
    @Test
    public void testPut_ThreeMessages_BufferWithThreeMessages () {
        buffer.put( msg1 );
        buffer.put( msg2 );
        buffer.put( msg3 );
        
        int expected = 3;
        int actual = buffer.getMessages().size();
        
        assertEquals( expected, actual );
    }
    
    @Test
    public void testPut_Null_NothingWasChanged () {
        buffer.put( null );
        buffer.put( msg2 );
        buffer.put( msg3 );
        
        int expected = 2;
        int actual = buffer.getMessages().size();
        
        assertEquals( expected, actual );
    }
    
    @Test
    public void testPut_MessageWithNullCommand_NothingWasChanged () {
        
        msg1.setCommand( null );
        
        buffer.put( msg1 );
        buffer.put( msg2 );
        buffer.put( msg3 );
        
        int expected = 2;
        int actual = buffer.getMessages().size();
        
        assertEquals( expected, actual );
    }
    
    @Test
    public void testPut_MessageWithEmptyCommand_NothingWasChanged () {
        
        msg1.setCommand( "" );
        
        buffer.put( msg1 );
        buffer.put( msg2 );
        buffer.put( msg3 );
        
        int expected = 2;
        int actual = buffer.getMessages().size();
        
        assertEquals( expected, actual );
    }
    
    @Test
    public void testPutAll_CollectionWithThreeMessages_BufferWithThreeMessages () {
        
        Collection<Message> msgs = new LinkedList<Message>();
        msgs.add( msg1 );
        msgs.add( msg2 );
        msgs.add( msg3 );
        
        buffer.putAll( msgs );
        
        int expected = 3;
        int actual = buffer.getMessages().size();
        
        assertEquals( expected, actual );
    }
    
    @Test
    public void testPutAll_EmptyCollection_NothingWasChanged () {
        
        Collection<Message> msgs = Collections.EMPTY_LIST;
        
        buffer.putAll( msgs );
        
        int expected = 0;
        int actual = buffer.getMessages().size();
        
        assertEquals( expected, actual );
    }
    
    @Test
    public void testPutAll_Null_NothingWasChanged () {
        
        buffer.putAll( null );
        
        int expected = 0;
        int actual = buffer.getMessages().size();
        
        assertEquals( expected, actual );
    }

    @Test
    public void testPush_FromEmptyBuffer_Null () {
        
        Message result = buffer.push( "" );
        assertNull( result );
    }

    @Test
    public void testPush_Null_Null () {
        
        Message result = buffer.push( null );
        assertNull( result );
    }
    
    @Test
    public void testPush_FromFilledBuffer_ExpectedValue () {
        
        String command = "trololo";
        
        Message expected = new Message();
        expected.setCommand( command );
        expected.setResult( command );
        expected.setSlaveId( command );
        expected.setSendRequest( Calendar.getInstance().getTime() );
        
        buffer.put( msg1 );
        buffer.put( msg2 );
        buffer.put( msg3 );
        buffer.put( expected );
        
        Message actual = buffer.push( command );
        
        assertSame( expected, actual );
        assertEquals( expected, actual );
    }
}