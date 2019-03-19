package fr.hoc.dap.server.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
 * Classe qui regroupe les methode des autorisations google.
 */
/**
 * @author house
 *
 */

public class GoogleService {

    /** Variable config.*/

    @Autowired
    private Config defaultConf;

    /**
     * Methode qui créée une instance Globale du Json et thread-safe (appelable depuis plusieurs thread).
     */
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     * path for the direction of credentials.json
     */
    private static List<String> scopes = Arrays.asList(CalendarScopes.CALENDAR_READONLY, GmailScopes.GMAIL_READONLY);

    /**
     * Le port local pour la reponse au consentement de l'utilisateur.
     */
    // private static final int PORT = 8888;

    /**
     * Creates an authorized Credential object.
     * @param userKey variable de users pour plusieurs comptes.
     * @param httpTransport .
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     * @throws GeneralSecurityException .
     */
    protected Credential getCredentials(final String userKey, final NetHttpTransport httpTransport)
            throws IOException, GeneralSecurityException {
        // Load client secrets.
        //LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(PORT).build();
        GoogleAuthorizationCodeFlow flow = getFlow();
        return flow.loadCredential(userKey);
    }

    /**
     * @param laConf2 youpi.
     */
    public void setLaConf(final Config laConf2) {
        this.defaultConf = laConf2;
    }

    /**
     * @return default config for all children.
     */
    public Config getLaConf() {
        return defaultConf;
    }

    /**
     * @return null.
     * @throws IOException .
     * @throws GeneralSecurityException .
     */
    public GoogleAuthorizationCodeFlow getFlow() throws GeneralSecurityException, IOException {
        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        InputStream in = GmailService.class.getResourceAsStream(defaultConf.getCredentialFilePath());
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY,
                clientSecrets, scopes)
                        .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(defaultConf.getTokenFolder())))
                        .setAccessType("offline").build();

        return flow;
    }
}
