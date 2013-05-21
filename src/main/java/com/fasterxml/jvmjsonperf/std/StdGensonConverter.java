package com.fasterxml.jvmjsonperf.std;

import java.io.*;

import com.owlike.genson.Genson;
import com.owlike.genson.TransformationException;

import com.fasterxml.jvmjsonperf.StdConverter;
import com.fasterxml.jvmjsonperf.StdItem;

/**
 * Converter that uses Gson package for JSON data binding,
 * using automatic bindings for serialization and deserialization.
 */
public class StdGensonConverter<T extends StdItem<T>>
    extends StdConverter<T>
{
    protected final Genson _genson;

    protected final Class<T> _itemClass;

    public StdGensonConverter(Class<T> itemClass)
    {
        _itemClass = itemClass;
        _genson = new Genson();
    }

    @Override
    public T readData(InputStream in) throws IOException
    {
        // Alas, Genson can't eat InputStreams either?
        try {
            return _genson.deserialize(new InputStreamReader(in, "UTF-8"), _itemClass);
        } catch (TransformationException e) {
            throw new IOException(e.getMessage(), e);
        }
    }
    
    @Override
    public int writeData(OutputStream out, T data) throws Exception
    {
        OutputStreamWriter w = new OutputStreamWriter(out, "UTF-8");
        w.write(_genson.serialize(data));
        w.close();
        return -1;
    }
}
