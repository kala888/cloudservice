package acp.accs.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class RemoteCalcService {
    @Autowired
    RestTemplate restTemplate;



    @HystrixCommand(fallbackMethod = "addServiceFallback")
    public String add(Integer a, Integer b) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("a", a.toString());
        map.put("b", b.toString());
        return restTemplate.getForEntity("http://CALC-SERVICE/add?a={a}&b={b}", String.class, map).getBody();
    }



    public String addServiceFallback(Integer a, Integer b) {
        return "error, could not serv you, your request is  "+a+" + "+b+" =?";
    }
}