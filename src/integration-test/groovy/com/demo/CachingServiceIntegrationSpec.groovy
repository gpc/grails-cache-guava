package com.demo

import com.demo.helper.ContainerGebSpec
import grails.testing.mixin.integration.Integration

@Integration
class CachingServiceIntegrationSpec extends ContainerGebSpec {

    void 'test cache service with ttl'() {
        when:
        go '/demoCache/index?key=key0&value=value0'

        then:
        browser.driver.pageSource.contains 'value0'

        when:
        go '/demoCache/index?key=key0&value=value1'

        then:
        browser.driver.pageSource.contains 'value0'

        Thread.sleep(10_000)

        when:
        go '/demoCache/index?key=key0&value=value3'

        then:
        browser.driver.pageSource.contains 'value3'
    }

}
