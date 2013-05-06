package org.codehaus.staxbind.std;

import java.io.*;

/**
 * Base class for "standard" converters used for data binding
 * tests that read/write a single object.
 */
public abstract class StdConverter<T extends StdItem<T>>
{
    public enum Operation {
        READ, WRITE, READ_WRITE
    }

    /*
    ///////////////////////////////////////////////////////////
    // Data binding test methods
    ///////////////////////////////////////////////////////////
     */

    /**
     * Method that is to read data in the underlying data format
     * and convert it to expected object representation.
     */
    public abstract T readData(InputStream in) throws Exception;

    /**
     * Method that is to write contents of given objects using
     * the underlying data format.
     *
     * @return Bogus result value; ideally generated from data, arbitrary
     *   but not random. Need to ensure no dead code elimination occurs
     */
    public abstract int writeData(OutputStream out, T data) throws Exception;
}
