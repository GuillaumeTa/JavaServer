package fr.hoc.dap.server.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.hoc.dap.server.Greeting;

/**
 * @author house
 *
 */
@RestController
public class GreetingController {

    /**     */
    private static final String TEMPLATE = "Hello, %s!";
    /**     */
    private final AtomicLong counter = new AtomicLong();

    /**
     * @param name value asked (?name=).
     * @return new instance with name value and template.
     */
    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") final String name) {
        return new Greeting(counter.incrementAndGet(), String.format(TEMPLATE, name));
    }
}
