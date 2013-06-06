package com.fasterxml.jvmjsonperf;

import java.io.*;

import com.fasterxml.jvmjsonperf.util.TestByteArrayInputStream;


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
    /**********************************************************************
    /* Data binding test methods
    /**********************************************************************
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

    /*
    /**********************************************************************
    /* Helper methods for sub-classes
    /**********************************************************************
     */

    protected byte[] readAll(InputStream in)
        throws IOException
    {
        // assume we are running a test; and so..
        TestByteArrayInputStream bytes = (TestByteArrayInputStream) in;
        int len = bytes.size();
        byte[] result = new byte[len];
        int offset = 0;
        
        while (len > 0) {
            int count = bytes.read(result, offset, len);
            if (count <= 0) {
                throw new IllegalStateException("Still had "+len+" bytes to read");
            }
            len -= count;
            offset += count;
        }
        return result;
    }

}
