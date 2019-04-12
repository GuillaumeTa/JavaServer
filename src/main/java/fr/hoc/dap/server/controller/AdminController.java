package fr.hoc.dap.server.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.util.store.DataStore;

import fr.hoc.dap.server.service.AdminService;

/**
 * @author Alucard
 *
 */
@Controller
public class AdminController {

    /**
     * Singleton.
     */
    @Autowired
    private AdminService admins;

    /**
     * @return la vue admin.html
     * @param model le model
     * @throws GeneralSecurityException that provides type safety for all the security-related
     * exception classes that extend from it.
     * @throws IOException If the credentials.json file cannot be found.
     */
    @RequestMapping("/admin")
    public String tokenUsers(final ModelMap model) throws GeneralSecurityException, IOException {

        DataStore<StoredCredential> userInfo = admins.getCredential();

        Map<String, StoredCredential> usersMap = new HashMap<>();

        Set<String> allKeys = userInfo.keySet();

        for (String aKey : allKeys) {
            StoredCredential value = userInfo.get(aKey);
            usersMap.put(aKey, value);
        }

        model.addAttribute("allusers", usersMap);

        return "admin";
    }

    /**
     * @return la vue admin.html
     * @param userkey nom de l'user en cour.
     * @throws GeneralSecurityException that provides type safety for all the security-related
     * exception classes that extend from it.
     * @throws IOException If the credentials.json file cannot be found.
     */
    @RequestMapping("/delete/user")
    public String deleteUsr(final String userkey) throws GeneralSecurityException, IOException {
        DataStore<StoredCredential> deleteUser = admins.deleteUser(userkey);
        return "redirect:/admin";
    }
}
