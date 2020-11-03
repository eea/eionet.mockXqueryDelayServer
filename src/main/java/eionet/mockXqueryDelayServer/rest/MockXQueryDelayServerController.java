package eionet.mockXqueryDelayServer.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.OutputStream;

@RestController
@RequestMapping({ "","/api"})

public class MockXQueryDelayServerController {

    @Value("${waitingTime}")
    Long waitingTime;
    Logger logger = LoggerFactory.getLogger(MockXQueryDelayServerController.class);

    @GetMapping("/test")
    public String test( HttpServletRequest request) throws InterruptedException {
            Thread.sleep(waitingTime);
        return "";
    }


    @GetMapping("/sparql")
    public StreamingResponseBody mimicCrSparqlResultDownload(@RequestParam(value="query" ,required = false)String query, HttpServletRequest request) throws InterruptedException {
            logger.info("Query is:"+query);

        return out -> {
            for (int i = 0; i < 100; i++) {
                out.write((i + " - demo demo demo \n ")
                        .getBytes());
                out.flush();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
