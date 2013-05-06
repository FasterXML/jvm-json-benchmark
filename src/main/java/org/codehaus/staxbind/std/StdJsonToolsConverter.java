package org.codehaus.staxbind.std;

import java.io.*;

import com.sdicons.json.mapper.JSONMapper;
import com.sdicons.json.model.JSONValue;
import com.sdicons.json.parser.JSONParser;

/**
 * Converter that uses Json-tools package for JSON data binding,
 * using automatic bindings for serialization and deserialization.
 */
public class StdJsonToolsConverter<T extends StdItem<T>>
    extends StdConverter<T>
{
    final JSONMapper _mapper;

    final Class<T> _itemClass;

    public StdJsonToolsConverter(Class<T> itemClass)
    {
        _itemClass = itemClass;
        _mapper = new JSONMapper();
    }

    @SuppressWarnings("unchecked")
    @Override
    public T readData(InputStream in) throws Exception
    {
        // two-step process: parse to JSON value, bind to POJO
        JSONParser jp = new JSONParser(in);
        JSONValue v = jp.nextValue();
        return (T) JSONMapper.toJava(v, _itemClass);
    }
    
    @Override
    public int writeData(OutputStream out, T data) throws Exception
    {
        JSONValue v = JSONMapper.toJSON(data);
        // this is stupid; no way to output to a stream (or even reader)?!?
        String jsonStr = v.render(false);
        OutputStreamWriter w = new OutputStreamWriter(out, "UTF-8");
        w.write(jsonStr);
        w.flush();
        return -1;
    }
}
