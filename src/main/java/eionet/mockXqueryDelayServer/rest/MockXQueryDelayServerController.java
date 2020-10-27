package eionet.mockXqueryDelayServer.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping({ "","/api"})

public class MockXQueryDelayServerController {

    @Value("${waitingTime}")
    Long waitingTime;
    Logger logger = LoggerFactory.getLogger(MockXQueryDelayServerController.class);

    @GetMapping({ "/test","/sparql"})
    public String test(@RequestParam(value="query" ,required = false)String query, HttpServletRequest request) throws InterruptedException {
        if(query!=null){
            logger.info("Query is:"+query);
        }
        String remoteHost = request.getRemoteHost();
        if (remoteHost.contains("eionet.europa.eu")) {
            Thread.sleep(waitingTime);
        }
        return "";
    }
}
