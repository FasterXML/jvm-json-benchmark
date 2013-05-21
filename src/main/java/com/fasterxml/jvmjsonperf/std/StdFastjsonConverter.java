package com.fasterxml.jvmjsonperf.std;

import java.io.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.parser.Feature;

import com.fasterxml.jvmjsonperf.StdConverter;
import com.fasterxml.jvmjsonperf.StdItem;

/**
 * Converter that uses Gson package for JSON data binding,
 * using automatic bindings for serialization and deserialization.
 */
public class StdFastjsonConverter<T extends StdItem<T>>
    extends StdConverter<T>
{
    protected final Class<T> _itemClass;

    public StdFastjsonConverter(Class<T> itemClass)
    {
        _itemClass = itemClass;
    }

    @Override
    public T readData(InputStream in) throws IOException
    {
        /* Unfortunately it looks like this lib does not read from stream?
         * Will need to read & buffer then...
         */
        byte[] data = readAll(in);
        return JSON.parseObject(data, _itemClass, Feature.DisableCircularReferenceDetect);
    }
    
    @Override
    public int writeData(OutputStream out, T data) throws Exception
    {
        out.write(JSON.toJSONBytes(data,
                SerializerFeature.WriteEnumUsingToString,SerializerFeature.DisableCircularReferenceDetect));
        return -1;
    }
}
