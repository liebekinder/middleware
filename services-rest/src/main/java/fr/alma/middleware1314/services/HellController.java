package fr.alma.middleware1314.services;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Arnaud Thimel : thimel@codelutin.com
 */
@Controller
@RequestMapping("sample")
public class HellController {

    @RequestMapping(value="sayHello", method = RequestMethod.POST)
    @ResponseBody
    public String sayHello(@RequestParam(value = "name") String name) {
        return "Hello " + name;
    }

}
