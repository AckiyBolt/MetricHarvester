/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package harvester.model.entity;

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
        EqualsVerifier.forClass(Metric.class)
                .suppress( Warning.NONFINAL_FIELDS )
                .verify();
    }
}