package com.fasterxml.staxbind.std;

import java.io.*;

import com.google.gson.*;

/**
 * Converter that uses Gson package for JSON data binding,
 * using automatic bindings for serialization and deserialization.
 */
public class StdGsonConverter<T extends StdItem<T>>
    extends StdConverter<T>
{
    final Gson _gson;

    final Class<T> _itemClass;

    public StdGsonConverter(Class<T> itemClass)
    {
        _itemClass = itemClass;
        _gson = new Gson();
    }

    @Override
    public T readData(InputStream in) throws IOException
    {
        // Alas, Gson can't eat InputStreams...
        return _gson.fromJson(new InputStreamReader(in, "UTF-8"), _itemClass);
    }
    
    @Override
    public int writeData(OutputStream out, T data) throws Exception
    {
        OutputStreamWriter w = new OutputStreamWriter(out, "UTF-8");
        _gson.toJson(data, w);
        w.flush();
        return -1;
    }
}
