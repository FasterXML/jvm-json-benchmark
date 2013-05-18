package com.fasterxml.staxbind.std;

import java.io.*;

import com.sun.japex.Params;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

/**
 * Converter that uses XStream on top of regular Stax 1
 * implementation (such as Woodstox).
 */
public class StdXstreamConverter<T extends StdItem<T>>
    extends StdConverter<T>
{
    /**
     * Root-level 'factory' object should be thread-safe, since it
     * carries no state.
     */
    protected XStream _xstream;

    public StdXstreamConverter() {
        // nothing to set as we need access to params...
    }

    public void prepare(Params driverParams)
    {
        /* Hmmh. Looks like prepare() does get called multiple
         * times on a single driver; once per each test case.
         */
        _xstream = new XStream(new StaxDriver());
        // No need to resolve refs, won't have cycles
        _xstream.setMode(XStream.NO_REFERENCES);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T readData(InputStream in) throws Exception
    {
        return (T) _xstream.fromXML(in);
    }
    
    @Override
    public int writeData(OutputStream out, T data) throws Exception
    {
        _xstream.toXML(data, out);
        return -1;
    }
}

