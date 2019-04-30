package fr.hoc.dap.server.data;

import org.springframework.data.repository.CrudRepository;

/**
 * Gère les accès au DapUser en BDD.
 * @author house
 */
public interface DapUserRepository extends CrudRepository<DapUser, Long> {

    /**
     * Gere le recuperation d'un user par sa userKey.
     * @param userKey DaP User Key
     * @return userKey
     */
    DapUser findByuserkey(String userKey);

}
