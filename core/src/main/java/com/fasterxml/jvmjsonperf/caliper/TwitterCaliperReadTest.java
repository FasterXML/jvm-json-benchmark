package com.fasterxml.jvmjsonperf.caliper;

import java.io.*;
import java.nio.charset.Charset;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jvmjsonperf.twitter.TwitterSearch;

import com.google.caliper.Param;
import com.google.gson.Gson;
import com.owlike.genson.Genson;
import com.sdicons.json.mapper.JSONMapper;
import com.sdicons.json.model.JSONValue;
import com.sdicons.json.parser.JSONParser;

import flexjson.JSONDeserializer;

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

//    protected static ObjectReader _jacksonReader = new ObjectMapper().reader(TwitterSearch.class);
    protected ObjectReader _jacksonReader;
    
    public Object timeJacksonDatabind(long reps) throws Exception
    {
        if (_jacksonReader == null) {
            _jacksonReader = new ObjectMapper().readerFor(TwitterSearch.class);
        }
        TwitterSearch result = null;
        
        while (--reps >= 0) {
            result = _jacksonReader.readValue(inputStream());
        }
        return result;
    }

//    protected static Gson _gson = new Gson();
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

//    protected static Genson _genson = new Genson();
    protected Genson _genson = new Genson();

    public Object timeGensonDatabind(long reps) throws Exception
    {
        if (_genson == null) {
            _genson = new Genson();
        }
        TwitterSearch result = null;
        while (--reps >= 0) {
            result = _genson.deserialize(new InputStreamReader(inputStream(), "UTF-8"), TwitterSearch.class);
        }
        return result;
    }
    
    protected JSONDeserializer<TwitterSearch> _flexJsonDeserializer;

    public Object timeFlexJson(long reps) throws Exception
    {
        if (_flexJsonDeserializer == null) {
            _flexJsonDeserializer = new JSONDeserializer<TwitterSearch>();
        }
        TwitterSearch result = null;
        while (--reps >= 0) {
            // Seriously?! Can't read using a Stream?
            // .. this is actually bit of unfair advantage for flexjson (not
            // having to do real reads); but it's so slow that we don't care
            String doc = inputAsString();
            try {
                result = _flexJsonDeserializer.deserialize(doc, TwitterSearch.class);
            } catch (Exception e) {
                System.err.println("FAIL: "+e);
                e.printStackTrace();
                throw e;
            }
        }
        return result;
    }
    
    // TODO: enable if/when it works? (and why DOES it fail?!)
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

    protected final static Charset UTF8 = Charset.forName("UTF-8");
    
    protected String inputAsString() {
        // Not really fair: should read it all... but we'll let that fly for now
        return new String(_data, UTF8);
    }
}
