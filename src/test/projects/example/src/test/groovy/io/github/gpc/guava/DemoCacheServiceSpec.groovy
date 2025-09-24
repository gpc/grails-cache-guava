package io.github.gpc.guava

import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class DemoCacheServiceSpec extends Specification implements ServiceUnitTest<DemoCacheService>{

    void "test cachedMethod"() {
        when:
            def name = 'my key'
            def value = 'my val'
            def ret = service.getCachedValue(name, value)
        then:
            ret == value
    }
}
