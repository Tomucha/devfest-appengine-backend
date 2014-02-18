package ma.demo.devfest.profique

import com.googlecode.objectify.Objectify
import com.googlecode.objectify.Work
import ma.demo.devfest.profique.domain.Message
import ma.demo.devfest.profique.service.impl.MessageServiceImpl
import ma.demo.devfest.profique.service.impl.OfyProvider
import spock.lang.Specification

/**
 * Created by stepan on 18.2.14.
 */
class MessageServiceTest extends Specification {



    def "insertOrUpdate should run in transaction" () {
        setup:
            def providerMock = Mock(OfyProvider)
            def ofyMock = Mock(Objectify)
            providerMock.get() >> ofyMock
            def service = new MessageServiceImpl(providerMock)
            def message = new Message()
            message.key = "key"
        when:
            service.createOrUpdateMessage(message)
        then:
            1 * ofyMock.transact(_ as Work)
    }
}
