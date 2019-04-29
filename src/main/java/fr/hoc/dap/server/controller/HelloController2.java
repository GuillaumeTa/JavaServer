package fr.hoc.dap.server.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller de test html.
 * @author Alucard
 */
@Controller
public class HelloController2 {

    /**
     * @return la vue hello.html
     * @param model spring
     */
    @RequestMapping("/hello2")
    public String hello(final ModelMap model) {
        model.addAttribute("maVar", "toto");

        List<String> bestioles = new ArrayList();

        bestioles.add("Chien");
        bestioles.add("Zebre");
        bestioles.add("Chien");

        model.addAttribute("bebetes", bestioles);

        return "hello";
    }
}
