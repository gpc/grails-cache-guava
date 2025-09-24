package io.github.gpc.guava

import grails.converters.JSON

class DemoCacheController {

    DemoCacheService demoCacheService

    def index(String key, String value) {
        render ([result:demoCacheService.getCachedValue(key, value)] as JSON)
    }
}
