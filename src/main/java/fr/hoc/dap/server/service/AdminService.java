package fr.hoc.dap.server.service;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.stereotype.Service;

import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.util.store.DataStore;

/**
 * Gère la recup d'user ou leur suppression.
 * @author Alucard
 */
@Service
public class AdminService extends GoogleService {

    /**
    * Gère la recuperation des données d'utilisateur.
    * @return datas données credential
    * @throws GeneralSecurityException if exception exists avoid them.
    * @throws IOException If the credentials.json file cannot be found.
    */
    public DataStore<StoredCredential> getCredential() throws GeneralSecurityException, IOException {

        DataStore<StoredCredential> datas = getFlow().getCredentialDataStore();

        return datas;
    }

    /**
     * Gère la suppresion d'un utilisateur.
     * @param userkey key user actuel.
     * @return datas données credential
     * @throws GeneralSecurityException if exception exists avoid them.
     * @throws IOException If the credentials.json file cannot be found.
     */
    public DataStore<StoredCredential> deleteUser(final String userkey) throws GeneralSecurityException, IOException {

        DataStore<StoredCredential> datas = getFlow().getCredentialDataStore().delete(userkey);

        return datas;
    }

}
