package org.codehaus.staxbind.std;

import java.io.*;

/**
 * Templatized version of the JDK Serialization based converter.
 */
public class StdJdkConverter<T extends StdItem<T>>
    extends StdConverter<T>
{
    public StdJdkConverter() { }

    @SuppressWarnings("unchecked")
    @Override
    public T readData(InputStream in)
        throws IOException
    {
        ObjectInputStream oi = new ObjectInputStream(in);
        try {
            Object result = oi.readObject();
            oi.close();
            return (T) result;
        } catch (ClassNotFoundException cnfe) {
            IOException e = new IOException();
            e.initCause(cnfe);
            throw e;
        }
    }
    
    @Override
    public int writeData(OutputStream out, T item) throws Exception
    {
        ObjectOutputStream oo = new ObjectOutputStream(out);
        oo.writeObject(item);
        oo.close();
        return -1;
    }
}
