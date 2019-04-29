package fr.hoc.dap.server.service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.gmail.GmailScopes;

import fr.hoc.dap.server.Config;

/**
 * Classe de recuperation des données utilisateurs.
 * @author house
 *
 */

public class GoogleService {

    /** The Config.*/
    @Autowired
    private Config defaultConf;

    /** Json's Instance.*/
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     * path for the direction of credentials.json
     */
    private static List<String> scopes = Arrays.asList(CalendarScopes.CALENDAR_READONLY, GmailScopes.GMAIL_READONLY);

    /**
     * Creates an authorized Credential object.
     * @param userKey variable de users pour plusieurs comptes.
     * @param httpTransport Thread-safe httpTransport
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     * @throws GeneralSecurityException if exception exists avoid them.
     */
    protected Credential getCredentials(final String userKey, final NetHttpTransport httpTransport)
            throws IOException, GeneralSecurityException {
        GoogleAuthorizationCodeFlow flow = getFlow();
        return flow.loadCredential(userKey);
    }

    /**
     * @return flow
     * @throws IOException If the credentials.json file cannot be found.
     * @throws GeneralSecurityException if exception exists avoid them.
     */
    public GoogleAuthorizationCodeFlow getFlow() throws GeneralSecurityException, IOException {
        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        File in = new java.io.File(defaultConf.getCredentialFilePath());
        Reader targetReader = new FileReader(in);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, targetReader);
        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY,
                clientSecrets, scopes)
                        .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(defaultConf.getTokenFolder())))
                        .setAccessType("offline").build();

        return flow;
    }

    /** @param newConf nouvelle configuration.*/
    public void setLaConf(final Config newConf) {
        this.defaultConf = newConf;
    }

    /** @return String*/
    public Config getLaConf() {
        return defaultConf;
    }
}
