package io.github.gpc.guava

import grails.plugin.cache.Cacheable

class DemoCacheService {

    @Cacheable(value = 'demo', key = { name })
    String getCachedValue(String name, String value) {
        return value
    }
}
