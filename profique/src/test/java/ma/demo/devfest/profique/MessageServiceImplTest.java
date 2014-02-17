package ma.demo.devfest.profique;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.Work;
import ma.demo.devfest.profique.domain.Message;
import ma.demo.devfest.profique.service.impl.MessageServiceImpl;
import ma.demo.devfest.profique.service.impl.OfyProvider;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by stepan on 16.2.14.
 */
public class MessageServiceImplTest {

    @Mock
    Objectify ofyMock;
    @Mock
    OfyProvider providerMock;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createOrUpdateShouldRunInTransaction() {
        when(providerMock.get()).thenReturn(ofyMock);

        MessageServiceImpl service = new MessageServiceImpl(providerMock);
        Message message = new Message();
        message.setKey("klic");
        service.createOrUpdateMessage(message);
        verify(ofyMock, times(1)).transact(any(Work.class));
    }
}
