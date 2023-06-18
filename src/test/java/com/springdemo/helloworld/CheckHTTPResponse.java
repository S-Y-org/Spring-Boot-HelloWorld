package com.springdemo.helloworld;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//(01)(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) --> This means we can start our webserver with
//a random port number
public class CheckHTTPResponse {
    //(02) We then need to get that port and annotate that with LocalServer Port
    //this allows our testing framework to inject field with a random port
    @LocalServerPort
    private int port;

    //(03) we can use the @Autowired annotation to tell spring to get a TestRestTemplate from the context.
    @Autowired
    private TestRestTemplate testRestTemplate;

    //(04)For our test itslef, annotated with @Test , we want it to pass when our expected value is the
    //same as our actual value from the web server
    @Test
    public  void shouldPassIfStringMatches(){
        //(05)we'll write an assertEquals statement that compares the string we're  going to put in
        //our Controller with the string we are serving from local host at the random port

        assertEquals("Hello World from Spring Boot",
                testRestTemplate.getForObject("http://localhost:" + port + "/",
                        String.class ));
        //(06)We need to use the getForObject method call on our instance of the TestRestTemplate and convert
        //that to a string to do a comparison with the string we want to use
    }
}
