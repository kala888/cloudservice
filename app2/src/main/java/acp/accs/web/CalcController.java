package acp.accs.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import acp.accs.service.RemoteCalcService;

@RestController
public class CalcController {
    @Autowired
    RemoteCalcService calcService;



    @RequestMapping(value = "/warped/add", method = RequestMethod.GET)
    public String warpedAdd(@RequestParam Integer a, @RequestParam Integer b) {
        return calcService.add(a, b);
    }



    @RequestMapping(value = "/calc/1plus2", method = RequestMethod.GET)
    public String plus1and2() {
        return calcService.add(1, 2);
    }
}