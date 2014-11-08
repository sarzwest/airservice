/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centralbank.client;

import java.io.IOException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import org.apache.ws.security.WSPasswordCallback;

/**
 *
 * @author Tomas "sarzwest" Jiricek
 */
public class PasswordCallback implements CallbackHandler {

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        for (Callback callback : callbacks) {
            ((WSPasswordCallback) callback).setPassword("passwd");
        }
    }
}
