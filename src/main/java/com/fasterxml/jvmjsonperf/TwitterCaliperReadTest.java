package com.fasterxml.jvmjsonperf;

import java.io.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jvmjsonperf.twitter.TwitterSearch;

import com.google.caliper.Param;
import com.google.gson.Gson;
import com.sdicons.json.mapper.JSONMapper;
import com.sdicons.json.model.JSONValue;
import com.sdicons.json.parser.JSONParser;

public class TwitterCaliperReadTest
    extends CaliperTestBase
{
    @Param({ "data/twitter/twitter-search.jsn" })
    public String filename;

    protected byte[] _data;
    
    @Override
    protected void setUp() throws IOException
    {
        _data = readAll(new File(filename));
    }

    /*
    /**********************************************************************
    /* Test methods
    /**********************************************************************
     */

    protected ObjectReader _jacksonReader;
    
    public Object timeJacksonDatabind(long reps) throws Exception
    {
        if (_jacksonReader == null) {
            _jacksonReader = new ObjectMapper().reader(TwitterSearch.class);
        }
        TwitterSearch result = null;
        
        while (--reps >= 0) {
            result = _jacksonReader.readValue(inputStream());
        }
        return result;
    }

    protected Gson _gson;
    
    public Object timeGsonDatabind(long reps) throws Exception
    {
        if (_gson == null) {
            _gson = new Gson();
        }
        TwitterSearch result = null;
        while (--reps >= 0) {
            result = _gson.fromJson(new InputStreamReader(inputStream(), "UTF-8"), TwitterSearch.class);
        }
        return result;
    }

    // TODO: enable
    public Object XXXtimeJsontoolsDatabind(long reps) throws Exception
    {
        TwitterSearch result = null;
        while (--reps >= 0) {
            InputStream in = inputStream();
            JSONParser jp = new JSONParser(in);
            JSONValue v = jp.nextValue();
            result = (TwitterSearch) JSONMapper.toJava(v, TwitterSearch.class);
            in.close();
        }
        return result;
    }
    
    /*
    /**********************************************************************
    /* Internal methods
    /**********************************************************************
     */

    protected InputStream inputStream() {
        return new ByteArrayInputStream(_data);
    }
}
