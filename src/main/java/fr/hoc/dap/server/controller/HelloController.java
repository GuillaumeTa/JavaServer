package fr.hoc.dap.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe Hello.
 * @author Alucard
 */
@RestController
public class HelloController {
    /** @return string of Welcome*/
    @RequestMapping("/")
    public String bonjour() {
        return "Welcome !";
    }

    /** @return string of Hello World*/
    @RequestMapping("/hello")
    public String index1() {
        return "Hello World !";
    }
}
