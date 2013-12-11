/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package airservice.exceptions;

/**
 *
 * @author Tomas "sarzwest" Jiricek
 */
public class SystemException extends Exception {
    public static final String SYSTEM_ERROR = "System unexpected error\n"
            + "Please contact administrator about this event at admin@airservice.cz\n";

    /**
     * Creates a new instance of
     * <code>SystemException</code> without detail message.
     */
    public SystemException() {
        super(SYSTEM_ERROR);
    }

    /**
     * Constructs an instance of
     * <code>SystemException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public SystemException(String msg) {
        super(SYSTEM_ERROR + msg);
    }
}
