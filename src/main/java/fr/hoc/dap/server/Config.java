package fr.hoc.dap.server;

/**
 * @author house
 *
 */
public class Config {

    /**Fichier de permissions acceptées.*/
    private static final String TOKEN_FOLDER = System.getProperty("user.home") + "/dap/tokens";

    /** Fichier de config api.*/
    private static final String CREDENTIAL_FILE_PATH = System.getProperty("user.home") + "/dap/credential_web.json";

    /** Nom de l'application.*/
    private static final String APPLICATION_NAME = "HoC DaP";

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

    /**
     * @return String
     */
    public String getCredentialFilePath() {
        return credentialFilePath;
    }

    /**
     * @return String
     */
    public String getApplicationName() {
        return applicationName;
    }

    /**
     * @param token2 token folder.
     */
    public void setTokenFolder(final String token2) {
        this.tokenFolder = token2;
    }

    /**
     * @param credentialFP credential folder.
     */
    public void setCredentialFilePath(final String credentialFP) {
        this.credentialFilePath = credentialFP;
    }

    /**
     * @param application2 application name.
     */
    public void setApplicationName(final String application2) {
        this.applicationName = application2;
    }

    /**
     * @return Callback Path.
     */
    public String getoAuth2CallbackUrl() {
        // TODO Auto-generated method stub
        return "/oAuth2Callback";
    }
}
