package ma.demo.devfest.profique

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig
import com.google.appengine.tools.development.testing.LocalServiceTestHelper
import ma.demo.devfest.profique.domain.Message
import ma.demo.devfest.profique.service.impl.MessageServiceImpl
import ma.demo.devfest.profique.service.impl.OfyProvider
import spock.lang.Shared
import spock.lang.Specification

/**
 * Created by stepan on 18.2.14.
 */
class MessageIntegrationTest extends Specification {

    def final helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

    // run before every feature method
    def setup() {
        helper.setUp()
    }

    //run after every feature method
    def cleanup() {
        helper.tearDown()
    }

    def "createOfUpdate should insert or update message" () {
        setup: def message = new Message();
            message.key = "key";
            def ofyProvider = new OfyProvider()
            def messageService = new MessageServiceImpl(ofyProvider);

        when: "when inserting message"
            def saved = messageService.createOrUpdateMessage(message)
        then: "return value should have create date, update date"
            saved.dateCreated != null;
            saved.dateUpdated != null;
        and: "key and value must be same"
            saved.key == message.key
            saved.message == message.message
    }

    def "insertOrUpdate method should also update message"() {
        setup: def message = new Message();
               message.key = "key";
               def ofyProvider = new OfyProvider()
               def messageService = new MessageServiceImpl(ofyProvider);
               def saved = messageService.createOrUpdateMessage(message)
               saved.message = "pew"

        when: "when updating that message"
            def updated = messageService.createOrUpdateMessage(saved)
        then: "create date should be same, update date should be different and message must be updated"
            updated.dateCreated == saved.dateCreated
            updated.dateUpdated != saved.dateUpdated
            updated.message == saved.message
        and: "key must be same"
            updated.key == saved.key
    }
}
