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
 * Classe qui englode des methodes pour Gmail.
 */
@Service
public final class GmailService extends GoogleService {

    /**constante de log (pour log4j).*/
    private static final Logger LOG = LogManager.getLogger();

    /**
     * Methode qui créée une instance Globale du Json et thread-safe (appelable depuis plusieurs thread).
     */
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    /**
     * @param userKey user actuel.
     * @return yolo.
     * @throws IOException pazreik.
     * @throws GeneralSecurityException elle est la.
     */
    public Gmail getService(final String userKey) throws GeneralSecurityException, IOException {
        NetHttpTransport httpTransport;
        Gmail service = null;
        httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        service = new Gmail.Builder(httpTransport, JSON_FACTORY, getCredentials(userKey, httpTransport))
                .setApplicationName(getLaConf().getApplicationName()).build();

        return service;
    }

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    /**
     * @param userKey user actuel.
     * @throws IOException If the credentials.json file cannot be found.
     * @throws GeneralSecurityException if exception exists avoid them.
     * @return Variable with table size.
     */
    public Integer getMails(final String userKey) throws IOException, GeneralSecurityException {
        // Build a new authorized API client service.
        // Print the labels in the user's account.
        String user = "me";

        String query = "label:unread";

        List<Message> nbMessageTotal = listMessagesMatchingQuery(user, query, userKey);

        LOG.info("affiche le nombre de messages non lu");
        System.out.println("Nombre de messages non lu : " + nbMessageTotal.size());
        return nbMessageTotal.size();
    }

    /**
     * @param userId ID de l'utilisateur qui lance l'application.
     * @param query Argument qui défini la recherche.
     * @param userKey user actuel
     * @return un tableau contenant tous les messages pour l'id du user.
     * @throws IOException If the credentials.json file cannot be found.
     * @throws GeneralSecurityException kok.
     */
    public List<Message> listMessagesMatchingQuery(final String userId, final String query, final String userKey)
            throws IOException, GeneralSecurityException {
        ListMessagesResponse response = getService(userKey).users().messages().list(userId).setQ(query).execute();

        List<Message> messagesId = new ArrayList<Message>();

        while (response.getMessages() != null) {
            messagesId.addAll(response.getMessages());
            if (response.getNextPageToken() != null) {
                String pageToken = response.getNextPageToken();
                response = getService(userKey).users().messages().list(userId).setQ(query).setPageToken(pageToken)
                        .execute();
            } else {
                break;
            }
        }
        return messagesId;
    }
}
