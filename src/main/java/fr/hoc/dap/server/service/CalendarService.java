package fr.hoc.dap.server.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

/**
 * @author Alucard.
 */
@Service
public final class CalendarService extends GoogleService {

    //TODO tag by Djer |JavaDoc| Pas utilede préciser "constante" on le voit deja grace au "static final"
    /**constante de log (pour log4j).*/
    private static final Logger LOG = LogManager.getLogger();

    /**
     * Instance of Json.
     */
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    /**
     * //TODO tag by Djer |JavaDoc| Il manque la "description" de la méthode (la première ligne)
     * @throws IOException If the credentials.json file cannot be found.
     * @throws GeneralSecurityException if exception exists avoid them.
     * @return list of events //TODO tag by Djer |JavaDoc| NE renvoie PAS une liste, mais une "representation textuelle des prochains évènnements"
     * @param nbResult stocke tous les resultats.
     * @param userKey user actuel.
     */
    public String getEvents(final Integer nbResult, final String userKey) throws IOException, GeneralSecurityException {
        // Build a new authorized API client service

        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service = new Calendar.Builder(httpTransport, JSON_FACTORY, getCredentials(userKey, httpTransport))
                .setApplicationName(getLaConf().getApplicationName()).build();

        //TODO tag by Djer |POO| Ce commentaire est devenue FAUX !
        // List the next 10 events from the primary calendar.
        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = service.events().list("primary").setMaxResults(nbResult).setTimeMin(now).setOrderBy("startTime")
                .setSingleEvents(true).execute();

        List<Event> items = events.getItems();

        String result = "";

        if (items.isEmpty()) {
            result = "Il n'y a pas d'évenements";
            LOG.warn("Il n'y a pas d'évenements");
            //TODO tag by Djer |POO| PAS de SysOut sur un serveur !
            System.out.println("No upcoming events found.");
        } else {
            //TODO tag by Djer |POO| PAS de SysOut sur un serveur !
            System.out.println("Upcoming events\n");
            LOG.info("Affiche les prochains évenements à venir" + "\n");
            for (Event event : items) {
                DateTime start = event.getStart().getDateTime();
                if (start == null) {
                    start = event.getStart().getDate();
                }
                //TODO tag by Djer |POO| PAS de SysOut sur un serveur !
                System.out.printf("%s (%s)\n", event.getSummary(), start);
                //TODO tag by Djer |POO| PAS de SysOut sur un serveur !
                System.out.printf("\n", event.getId());

                result += event.getSummary() + " (" + start + ") ";
            }
        }
        return result;
    }
}
