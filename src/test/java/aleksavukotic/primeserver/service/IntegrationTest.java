package aleksavukotic.primeserver.service;

import aleksavukotic.primeserver.config.WebConfiguration;
import aleksavukotic.primeserver.server.JettyServerFactory;
import org.eclipse.jetty.server.Server;
import org.hamcrest.CoreMatchers;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class IntegrationTest  {

    private Server server;

    @BeforeMethod
    public void setup() throws Exception {
        server = new JettyServerFactory(9001, WebConfiguration.class).createServer();
        server.start();

    }

    @AfterMethod
    public void cleanup() throws Exception {
        server.stop();

    }

    @Test
    public void testRest(){
        ResponseEntity<List> result = new RestTemplate().getForEntity("http://localhost:9001/primes/10", List.class);
        assertThat(result.getStatusCode().value(), equalTo(200));
        assertThat(result.getBody(), CoreMatchers.<List>equalTo(Arrays.asList(2, 3, 5, 7)));
    }

    @Test
    public void testRestDifferentAlgo(){
        ResponseEntity<List> result = new RestTemplate().getForEntity("http://localhost:9001/primes/10?algo=SieveOfEratosthenes", List.class);
        assertThat(result.getStatusCode().value(), equalTo(200));
        assertThat(result.getBody(), CoreMatchers.<List>equalTo(Arrays.asList(2, 3, 5, 7)));
    }
}
