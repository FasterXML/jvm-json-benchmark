package com.fasterxml.jvmjsonperf.std;

import java.io.*;
import java.util.Arrays;

import com.fasterxml.jvmjsonperf.StdConverter;
import com.fasterxml.jvmjsonperf.StdItem;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

/**
 * Converter that uses Jackson JSON processor for data binding,
 * using automatic bindings for serialization and deserialization.
 */
public class StdFlexJsonConverter<T extends StdItem<T>>
    extends StdConverter<T>
{
    protected final Class<T> _itemClass;

    // left as non-final, for sub-classes to customize if/as necessary
    
    protected JSONSerializer _serializer;
    protected JSONDeserializer<T> _deserializer;
    
    private transient byte[] _readBuffer = new byte[2000];

    public StdFlexJsonConverter(Class<T> itemClass, String[] inclusions)
    {
        _itemClass = itemClass;
        _deserializer = new JSONDeserializer<T>();
        /* 21-May-2013, tatu: As per jvm-serializers driver for flex-json,
         *   looks like we need to add exclusion for some reason:
         */
        _serializer = new JSONSerializer()
            .exclude("*.class");
        if (inclusions != null) {
            _serializer = _serializer.include(inclusions);
        }
    }

    @Override
    public T readData(InputStream in) throws IOException
    {
        // Unbelievable -- can only parse a fucking String? What a joke.
        String doc = _read(in, "UTF-8");
        return _deserializer.deserialize(doc, _itemClass);
    }
    
    @Override
    public int writeData(OutputStream out, T data) throws Exception
    {
        // Ugh. Are you kidding me -- can only output Strings?!?!?!
        OutputStreamWriter w = new OutputStreamWriter(out, "UTF-8");
        w.write(_serializer.deepSerialize(data));
        w.close();
        return -1;
    }

    private String _read(InputStream in, String enc) throws IOException
    {
        /* In a way, this gives unfair advantage to this impl
         * (since we are optimizing away buffer allocations) -- but
         * it is unlikely to matter a lot in the end, what with all
         * inefficiencies it has,
         */
        int ptr = 0;
        int count;

        while ((count = in.read(_readBuffer, ptr, _readBuffer.length - ptr)) > 0) {
            ptr += count;
            // buffer full? Need to realloc
            if (ptr == _readBuffer.length) {
                _readBuffer = Arrays.copyOf(_readBuffer, ptr + ptr);
            }
        }

        return new String(_readBuffer, 0, ptr, enc);
    }
}
