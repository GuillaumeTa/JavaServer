package fr.hoc.dap.server.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.hoc.dap.server.data.DapUser;
import fr.hoc.dap.server.data.DapUserRepository;

/**
 * Permet de faire des tests des entité JPA.
 * @author house
 *
 */
@RestController
public class TestController {

    /**log (pour log4j).*/
    private static final Logger LOG = LogManager.getLogger();

    /** The DapUserRepository.*/
    @Autowired
    private DapUserRepository dapUserRepo;

    /**
     * Créé un nouvel utilisateur DaP avec un userKey.
     * Exemple d'apel : /test/createDapUser?loginName=Alu&userKey=AluPerso
     * @param loginName DaP login name
     * @param userKey DaP User Key
     * @return saveUser
     */
    @RequestMapping("/test/createDapUser")
    public DapUser createDapUser(@RequestParam final String loginName, @RequestParam final String userKey) {

        DapUser monUser = new DapUser();
        monUser.setLoginName(loginName);
        monUser.setUserkey(userKey);

        DapUser saveUser = dapUserRepo.save(monUser);

        return saveUser;

    }

    /**
     * Affiche un tutilisateur déjà enregistré.
     * @param userKey DaP User Key
     * @return loadedUser user loadé
     */
    @RequestMapping("/test/loadDapUser")
    public DapUser loadDapUser(@RequestParam final String userKey) {

        DapUser loadedUser = dapUserRepo.findByuserkey(userKey);

        return loadedUser;

    }

    /**
     * Affiche un tutilisateur déjà enregistré.
     * @return loadedUser user loadé
     */
    @RequestMapping("/test/loadAllDapUser")
    public Iterable<DapUser> loadAllDapUser() {

        Iterable<DapUser> loadedUser = dapUserRepo.findAll();

        LOG.info(loadedUser);

        return loadedUser;

    }
}
