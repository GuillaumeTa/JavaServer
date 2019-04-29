package fr.hoc.dap.server;

/**
 * Classe de config pour token et credential.
 * @author Alucard
 */
public class Config {

    /**Fichier de permissions acceptées.*/
    private static final String TOKEN_FOLDER = System.getProperty("user.home") + "/dap/tokens";

    /** Fichier de config api.*/
    private static final String CREDENTIAL_FILE_PATH = System.getProperty("user.home") + "/dap/credential_web.json";

    /** Nom de l'application.*/
    private static final String APPLICATION_NAME = "HoC DaP Google";

    /** tokenFolder variable setter.*/
    private String tokenFolder;

    /** credentialFP variable setter.*/
    private String credentialFilePath;

    /** applicationName variable setter.*/
    private String applicationName;

    /** Constructeur.*/
    public Config() {
        tokenFolder = TOKEN_FOLDER;
        credentialFilePath = CREDENTIAL_FILE_PATH;
        applicationName = APPLICATION_NAME;
    }

    /**
     * @return String
     */
    public String getTokenFolder() {
        return tokenFolder;
    }

    /** @return String*/
    public String getCredentialFilePath() {
        return credentialFilePath;
    }

    /** @return String*/
    public String getApplicationName() {
        return applicationName;
    }

    /** @param newToken token folder*/
    public void setTokenFolder(final String newToken) {
        this.tokenFolder = newToken;
    }

    /** @param newCredentialFP credential folder*/
    public void setCredentialFilePath(final String newCredentialFP) {
        this.credentialFilePath = newCredentialFP;
    }

    /** @param newApplication application name*/
    public void setApplicationName(final String newApplication) {
        this.applicationName = newApplication;
    }

    /** @return Callback Path*/
    public String getoAuth2CallbackUrl() {
        // TODO Auto-generated method stub
        return "/oAuth2Callback";
    }
}
