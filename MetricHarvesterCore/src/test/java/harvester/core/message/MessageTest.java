package harvester.core.message;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author T@urus
 */
public class MessageTest {
    
    @Test
    public void testHashCodeAndEquals () {
        EqualsVerifier.forClass(Message.class)
                .suppress( Warning.NONFINAL_FIELDS )
                .verify();
    }
}