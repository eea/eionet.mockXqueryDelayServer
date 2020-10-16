package eionet.mockXqueryDelayServer.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class MockXQueryDelayServerController {

    @Value("${waitingTime}")
    Long waitingTime;

    @GetMapping("/test")
    public String test(HttpServletRequest request) throws InterruptedException {
        String remoteHost = request.getRemoteHost();
        if (remoteHost.contains("eionet.europa.eu")) {
            Thread.sleep(waitingTime);
        }
        return "";
    }
}
