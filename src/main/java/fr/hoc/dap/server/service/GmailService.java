package fr.hoc.dap.server.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;

/**
 * @author house
 *
 */
@Service
public final class GmailService extends GoogleService {

    /**log (pour log4j).*/
    private static final Logger LOG = LogManager.getLogger();

    //TODO tag by Djer |JavaDoc| NON ! Pas une méthode ! est c'est une "fabrique à JSON" pas vraiment un "JSON"
    //CHANGED on GoogleService
    //TODO tag by Djer |POO| Pas utile car deja défini dans la classe parente !
    //CHANGED
    /**
     * Instance of Json.
     */
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    /**
     * @param userKey user actuel.
     * @return service all we need to for connect userKey
     * @throws IOException If the credentials.json file cannot be found.
     * @throws GeneralSecurityException if exception exists avoid them.
     */
    //TODO tag by Djer |POO| "buildService" serait mieux comme nom de méthode
    //CHANGED
    //TODO tag by Djer |POO| Devrait etre privée
    //CHANGED
    private Gmail buildService(final String userKey) throws GeneralSecurityException, IOException {
        NetHttpTransport httpTransport;
        Gmail service = null;
        httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        service = new Gmail.Builder(httpTransport, JSON_FACTORY, getCredentials(userKey, httpTransport))
                .setApplicationName(getLaConf().getApplicationName()).build();

        return service;
    }

    /**
     * renvoi le nombre d'email non lus.
     * @param userKey user actuel.
     * @throws IOException If the credentials.json file cannot be found.
     * @throws GeneralSecurityException if exception exists avoid them.
     * @return Variable with table size.
     */
    //TODO tag by Djer |JavaDoc| getNbEmails, ou "retrieveNbEmail" serait mieux
    public Integer retrieveNbEmails(final String userKey) throws IOException, GeneralSecurityException {
        // Build a new authorized API client service.
        // Print the labels in the user's account.
        String user = "me";

        String query = "label:unread";

        List<Message> nbMessageTotal = listMessagesMatchingQuery(user, query, userKey);

        //TODO tag by Djer |Log4J| Contextualise tes messages : nbMessageTotal.size() +
        //" email non lus pour l'utilisateur : " + userKey
        //CHANGED
        LOG.info(nbMessageTotal.size() + " email non lus pour l'utilisateur : " + userKey);
        return nbMessageTotal.size();
    }

    /**
     * renvois la list des messages.
     * @param userId ID de l'utilisateur qui lance l'application.
     * @param query Argument qui défini la recherche.
     * @param userKey user actuel
     * @return un tableau contenant tous les messages pour l'id du user.
     * @throws IOException If the credentials.json file cannot be found.
     * @throws GeneralSecurityException if exception exists avoid them.
     */
    //TODO tag by Djer |POO| Devrait être privée
    public List<Message> listMessagesMatchingQuery(final String userId, final String query, final String userKey)
            throws IOException, GeneralSecurityException {
        //TODO tag by Djer |Log4J| Une petite log ? "retrieving emais matchin filter : " +
        //query " for user + " userKey + " (GUser : " + userId + ")"
        //CHANGED
        ListMessagesResponse response = buildService(userKey).users().messages().list(userId).setQ(query).execute();
        LOG.info("retrieving emais matchin filter : " + "'" + query + "' for user " + userKey + " (GUser : " + userId
                + ")");

        List<Message> messagesId = new ArrayList<Message>();

        while (response.getMessages() != null) {
            messagesId.addAll(response.getMessages());
            if (response.getNextPageToken() != null) {
                String pageToken = response.getNextPageToken();
                response = buildService(userKey).users().messages().list(userId).setQ(query).setPageToken(pageToken)
                        .execute();
            } else {
                break;
            }
        }
        return messagesId;
    }
}
