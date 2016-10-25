package acp.accs.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import acp.accs.service.ComputeService;

@RestController
public class ConsumerController {
    @Autowired
    ComputeService computeService;



    @RequestMapping(value = "/warped/add", method = RequestMethod.GET)
    public String warpedAdd(@RequestParam Integer a, @RequestParam Integer b) {
        return computeService.add(a, b);
    }



    @RequestMapping(value = "/calc/1plus2", method = RequestMethod.GET)
    public String plus1and2() {
        return computeService.add(1, 2);
    }
}