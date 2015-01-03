/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package printer.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.activation.DataHandler;
import javax.activation.DataSource;

/**
 *
 * @author Tomas "sarzwest" Jiricek
 */
public class ByteArrayDataHandler extends DataHandler {

    public ByteArrayDataHandler( byte[] data, String name ) {
        super( new ByteArrayDataSource( data, name ) );
    }
}

class ByteArrayDataSource implements DataSource {

    private InputStream is;

    private String contentType;

    private String name;

    public ByteArrayDataSource( byte[] array, String name ) {
        this.is = new ByteArrayInputStream( array );
        this.contentType = "application/octet-stream";
        this.name = name;
    }

    @Override
    public String getContentType() {
        return contentType;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return is;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        throw new UnsupportedOperationException();
    }
}
