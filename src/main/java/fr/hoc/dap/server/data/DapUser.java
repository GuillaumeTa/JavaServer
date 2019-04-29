package fr.hoc.dap.server.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Contient tous les attributs pour gerer un utilisateur DaP.
 * @author house
 *
 */
@Entity
public class DapUser {

    /** Dap user Id.*/
    @Id
    @GeneratedValue
    private Long id;
    /** Dap userKey (to store in google credential).*/
    @Column(nullable = false, unique = true)
    private String userkey;
    /** Dap user login name.*/
    private String loginName;

    /** @return the id*/
    public Long getId() {
        return id;
    }

    /** @param newId the id to set*/
    public void setId(final Long newId) {
        this.id = newId;
    }

    /** @return the userkey*/
    public String getUserkey() {
        return userkey;
    }

    /** @param newUserkey the userkey to set*/
    public void setUserkey(final String newUserkey) {
        this.userkey = newUserkey;
    }

    /** @return the loginName*/
    public String getLoginName() {
        return loginName;
    }

    /** @param newLoginName the loginName to set*/
    public void setLoginName(final String newLoginName) {
        this.loginName = newLoginName;
    }

}
