# grails-cache-guava

Forked from the archived project https://github.com/qube-ag/grails-cache-guava

This Grails plugin extends the grails-cache plugin.

The guava cache provides a simple in memory cache with maximal capacity and TTL.


## Usage

### Dependency

```groovy
dependencies {
    implementation 'org.grails.plugins:cache'
    implementation 'io.github.gpc:grails-cache-guava:2.0.0-SNAPSHOT'
}
```

### Configuration

```yaml
grails:
  cache:
    guava:            
      defaultTtl: 3600
      caches:
        message:
          maxCapacity: 5000
          ttl: 60
        maps:
          maxCapacity: 6000
          ttl: 30
        countries:
          maxCapacity: 1000
```

The GrailsGuavaCacheManager is automatically configured by the plugin.

### Annotations and Services

Just use grails-cache's annotations and services as described in 
its [documentation](http://grails-plugins.github.io/grails-cache/snapshot/guide/index.html)
