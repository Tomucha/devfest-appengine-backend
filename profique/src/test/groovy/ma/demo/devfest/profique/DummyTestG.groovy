package ma.demo.devfest.profique

import spock.lang.Specification


class DummyTestG extends Specification {

    def "test field size" () {
        when: "we have two fields"
        def three = 3 + 1

        then:  "size of fields are same"
        three == 4
    }
}
