package com.demo.helper;

import geb.spock.GebSpec;
import grails.testing.mixin.integration.Integration
import groovy.transform.PackageScope
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.RemoteWebDriver
import org.springframework.beans.factory.annotation.Value
import org.testcontainers.Testcontainers;
import org.testcontainers.containers.BrowserWebDriverContainer
import org.testcontainers.containers.PortForwardingContainer;
import spock.lang.Shared

import java.time.Duration;

@Integration
class ContainerGebSpec extends GebSpec {

    private static final String HOST_TESTCONTAINERS_INTERNAL = 'host.testcontainers.internal'

    @Shared
    BrowserWebDriverContainer webDriverContainer

    void setupSpec() {
        webDriverContainer = new BrowserWebDriverContainer()
    }
    
    @PackageScope
    void initialize() {
        if (!webDriverContainer.running) {
            webDriverContainer.tap {
                addExposedPort(serverPort)
                withAccessToHost(true)
                start()
            }
            Testcontainers.exposeHostPorts(serverPort)
            if(hostName != HOST_TESTCONTAINERS_INTERNAL) {
                String ip = PortForwardingContainer.INSTANCE.network.get().ipAddress
                webDriverContainer.execInContainer("/bin/sh", '-c', "echo '$ip\t$hostName' | sudo tee -a /etc/hosts")
            }
            
            browser.driver = new RemoteWebDriver(webDriverContainer.seleniumAddress, new ChromeOptions(), false)
            browser.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30))
        }
    }
    
    void setup() {
        initialize()
        baseUrl = "http://${hostName}:$serverPort"
    }

    void cleanupSpec() {
        webDriverContainer.stop()
    }
    
    String getHostName() {
        return HOST_TESTCONTAINERS_INTERNAL
    }
}