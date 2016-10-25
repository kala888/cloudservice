package acp.accs.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import acp.accs.service.ComputeClient;

@RestController
public class ConsumerController {
    @Autowired
    ComputeClient computeClient;



    @RequestMapping(value = "/warped/add", method = RequestMethod.GET)
    public String warpedAdd(@RequestParam Integer a, @RequestParam Integer b) {
        return add(a, b);
    }



    public String add(Integer a, Integer b) {
        return String.valueOf(computeClient.add(a, b));
    }



    @RequestMapping(value = "/calc/1plus2", method = RequestMethod.GET)
    public String plus1and2() {
        return add(1, 2);
    }



    public String addServiceFallback() {
        return "error";
    }
}