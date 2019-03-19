/***/
package fr.hoc.dap.server;

/**
 * @author Alucard
 *
 */
public class Greeting {

    /**
     */
    private final long id;
    /**
     */
    private final String content;

    /**
     * @param id2 get the id for the constructor greeting.
     * @param content2 get the content for the constructor greeting.
     */
    public Greeting(final long id2, final String content2) {
        this.id = id2;
        this.content = content2;
    }

    /**
     * @return id.
     */
    public long getId() {
        return id;
    }

    /**
     * @return string of content.
     */
    public String getContent() {
        return content;
    }
}
