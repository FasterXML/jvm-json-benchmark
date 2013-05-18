package com.fasterxml.staxbind.std;

import java.io.*;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.staxbind.StdConverter;
import com.fasterxml.staxbind.StdItem;

/**
 * Converter that uses Jackson JSON processor (v2.x) for data binding,
 * using automatic bindings for serialization and deserialization.
 */
public class StdJacksonConverter<T extends StdItem<T>>
    extends StdConverter<T>
{
    protected final ObjectReader _reader;
    protected final ObjectWriter _writer;
    
    protected final Class<T> _itemClass;
    
    public StdJacksonConverter(Class<T> itemClass) {
        this(itemClass, new ObjectMapper());
    }

    protected StdJacksonConverter(Class<T> itemClass, JsonFactory f)
    {
        this(itemClass, new ObjectMapper(f));
    }
    
    protected StdJacksonConverter(Class<T> itemClass, ObjectMapper mapper)
    {
        _itemClass = itemClass;
        _reader = mapper.reader(itemClass);
        _writer = mapper.writerWithType(itemClass);
    }

    @Override
    public T readData(InputStream in) throws IOException
    {
        return _reader.readValue(in);
    }
    
    @Override
    public int writeData(OutputStream out, T data) throws Exception
    {
        _writer.writeValue(out, data);
        return -1;
    }
}
