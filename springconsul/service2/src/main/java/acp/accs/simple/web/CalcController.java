package acp.accs.simple.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalcController {
    private final Logger    logger = Logger.getLogger(getClass());
    @Autowired
    private DiscoveryClient client;



    @RequestMapping(value = "/plus1and2", method = RequestMethod.GET)
    public Integer add() {
        ServiceInstance instance = client.getLocalServiceInstance();
        Integer r = 1 + 2;
        logger.info("/add, host:" + instance.getHost() + ", service_id:" + instance.getServiceId() + ", result:" + r);
        return r;
    }
}
