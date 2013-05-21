package com.fasterxml.jvmjsonperf.std;

import static net.minidev.json.parser.JSONParser.DEFAULT_PERMISSIVE_MODE;

import java.io.*;

import net.minidev.json.JSONValue;
import net.minidev.json.mapper.AMapper;
import net.minidev.json.mapper.Mapper;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

import com.fasterxml.jvmjsonperf.StdConverter;
import com.fasterxml.jvmjsonperf.StdItem;

/**
 * Converter that uses JSON-Smart for data binding
 * using automatic bindings for serialization and deserialization.
 */
public class StdJsonSmartConverter<T extends StdItem<T>>
    extends StdConverter<T>
{
    protected final Class<T> _itemClass;
    protected AMapper<T> _mapper;
    
    public StdJsonSmartConverter(Class<T> itemClass) {
        _itemClass = itemClass;
        _mapper = Mapper.getMapper(itemClass);
    }
 
    @Override
    public T readData(InputStream in) throws IOException
    {
        try {
            JSONParser p = new JSONParser(DEFAULT_PERMISSIVE_MODE);
            return p.parse(in, _mapper);
       } catch (ParseException e) {
           throw new IOException(e.getMessage(), e);
       }
    }
    
    @Override
    public int writeData(OutputStream out, T data) throws Exception
    {
        OutputStreamWriter w = new OutputStreamWriter(out, "UTF-8");
        w.write(JSONValue.toJSONString(data));
        w.flush();
        return -1;
    }
}
