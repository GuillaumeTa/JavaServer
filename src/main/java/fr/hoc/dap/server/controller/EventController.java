package fr.hoc.dap.server.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.hoc.dap.server.service.CalendarService;

/**
 * @author house
 *
 */
@RestController
public class EventController {

    /**
     * Inject la valeure de l'attribut.
     */
    @Autowired
    private CalendarService lastEvent;

    /**
     * @return string of last event.
     * @throws IOException If the credentials.json file cannot be found.
     * @throws GeneralSecurityException if exception exists avoid them.
     */
    //    @RequestMapping("/event/next")
    //    public String showEvent() throws IOException, GeneralSecurityException {
    //        return lastEvent.getEvents();
    //    }

    /**
     * @return string of last event.
     * @throws IOException If the credentials.json file cannot be found.
     * @throws GeneralSecurityException if exception exists avoid them.
     * @param nbResult .
     * @param userKey nom de l'user en cour.
     */
    @RequestMapping("/event/next")
    public String idEvent(@RequestParam(name = "nb", defaultValue = "1") final Integer nbResult,
            @RequestParam() final String userKey) throws IOException, GeneralSecurityException {
        return lastEvent.getEvents(nbResult, userKey);
    }
}
