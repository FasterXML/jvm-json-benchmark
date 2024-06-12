package com.fasterxml.jvmjsonperf.std;

import java.io.*;

import com.owlike.genson.Genson;

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
    public T readData(InputStream in)
    {
        // Alas, Genson can't eat InputStreams either?
        return _genson.deserialize(in, _itemClass);
    }
    
    @Override
    public int writeData(OutputStream out, T data)
    {
        _genson.serialize(data, out);
        return -1;
    }
}
