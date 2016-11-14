package acp.accs.simple.web;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CalcController {

    @Autowired
    private LoadBalancerClient loadBalancer;

    @Autowired
    private RestTemplate       restTemplate;



    @RequestMapping("/discover")
    public Object discover() {
        logger.info("loadBalancer is" + loadBalancer);
        ServiceInstance service = loadBalancer.choose("calc-service");
        if (service == null) {
            return "could not found the calc-service form registry server";
        }
        URI uri = service.getUri();
        return uri.toString();
    }

    private final Logger    logger = Logger.getLogger(getClass());
    @Autowired
    private DiscoveryClient client;



    @RequestMapping("/services")
    public Object services() {
        return client.getInstances("calc-service");
    }



    @RequestMapping(value = "/remote/add", method = RequestMethod.GET)
    public String add(@RequestParam Integer a, @RequestParam Integer b) {

        ServiceInstance instance = client.getLocalServiceInstance();
        logger.info("/add, host:" + instance.getHost() + ", service_id:" + instance.getServiceId());

        Map<String, String> map = new HashMap<String, String>();
        map.put("a", a.toString());
        map.put("b", b.toString());
        return restTemplate.getForEntity("http://calc-service/add?a={a}&b={b}", String.class, map).getBody();
    }
}
