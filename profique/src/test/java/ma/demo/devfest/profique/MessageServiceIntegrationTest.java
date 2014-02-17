package ma.demo.devfest.profique;

import com.google.appengine.repackaged.org.joda.time.DateTimeComparator;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.Work;
import junit.framework.TestCase;
import ma.demo.devfest.profique.domain.Message;
import ma.demo.devfest.profique.service.impl.MessageServiceImpl;
import ma.demo.devfest.profique.service.impl.OfyProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import java.util.Date;


/**
 * Created by stepan on 16.2.14.
 */
public class MessageServiceIntegrationTest extends TestCase {

    private final LocalServiceTestHelper helper =
            new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

    @Before
    public void setUp() {
        helper.setUp();
    }

    @After
    public void tearDown() {
        helper.tearDown();
    }

    @Test
    public void testCreateMessage() {
        MessageServiceImpl service = new MessageServiceImpl(new OfyProvider());
        Message testMessage = new Message();
        testMessage.setKey("klic");
        Message message = service.createOrUpdateMessage(testMessage);
        assertEquals(DateTimeComparator.getDateOnlyInstance().compare(message.getDateCreated(), new Date()), 0);
        assertEquals(DateTimeComparator.getDateOnlyInstance().compare(message.getDateUpdated(), new Date()), 0);
        assertEquals(testMessage.getKey(), message.getKey());
    }

    @Test
    public void testUpdate() {
        MessageServiceImpl service = new MessageServiceImpl(new OfyProvider());
        Message testMessage = new Message();
        testMessage.setKey("klic");
        Message message = service.createOrUpdateMessage(testMessage);
        assertEquals(DateTimeComparator.getDateOnlyInstance().compare(message.getDateCreated(), new Date()), 0);
        assertEquals(DateTimeComparator.getDateOnlyInstance().compare(message.getDateUpdated(), new Date()), 0);
        assertEquals(testMessage.getKey(), message.getKey());
        assertEquals(testMessage.getMessage(), message.getMessage());

        //update
        message.setMessage("nova zprava");
        Message updateMessage = service.createOrUpdateMessage(message);
        assertEquals(message.getDateCreated(), updateMessage.getDateCreated());
        assertEquals(DateTimeComparator.getDateOnlyInstance().compare(message.getDateUpdated(), updateMessage.getDateCreated()), 0);
        assertEquals(updateMessage.getKey(), message.getKey());
        assertEquals(updateMessage.getMessage(), message.getMessage());
        assertTrue(service.findMessages().length == 1);
    }


}
