package com.fasterxml.jvmjsonperf;

import java.io.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jvmjsonperf.twitter.TwitterSearch;

import com.google.caliper.Param;
import com.google.gson.Gson;

public class TwitterCaliperReadTest
    extends CaliperTestBase
{
    @Param({ "data/twitter/twitter-search.jsn" })
    public String filename;

    protected byte[] _data;
    
    protected ObjectReader _jacksonReader;
    
    protected Gson _gson;

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
    
    public Object timeJacksonDatabind(int reps) throws Exception
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

    public Object timeGsonDatabind(int reps) throws Exception
    {
        if (_gson == null) {
            _gson = new Gson();
        }
        return _gson.fromJson(new InputStreamReader(inputStream(), "UTF-8"), TwitterSearch.class);
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
