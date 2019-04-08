package fr.hoc.dap.server.service;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.stereotype.Service;

import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.util.store.DataStore;

/**
 * @author Alucard
 *
 */
@Service
public class AdminService extends GoogleService {

    /**
    * @return datas données credential
    * @throws GeneralSecurityException that provides type safety for all the security-related
    * exception classes that extend from it.
    * @throws IOException If the credentials.json file cannot be found.
    */
    public DataStore<StoredCredential> getCredential() throws GeneralSecurityException, IOException {

        DataStore<StoredCredential> datas = getFlow().getCredentialDataStore();

        return datas;
    }
}
