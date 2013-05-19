package com.fasterxml.jvmjsonperf.twitter;

import java.util.*;

import javax.xml.bind.annotation.*;

import com.fasterxml.jvmjsonperf.StdItem;

@SuppressWarnings("serial")
@XmlRootElement(name="searchResult") // needed by JAXB
public class TwitterSearch
    extends StdItem<TwitterSearch>
{
    private List<Entry> _results;

    public long since_id, max_id;
    public int page;
    public int results_per_page;
    public String query;
    public String refresh_url;
    public String next_page;
    public double completed_in;
    
    public TwitterSearch() { }

    public List<Entry> getResults() {
        if (_results == null) {
            _results = new ArrayList<Entry>();
        }
        return _results;
    }

    public void setResults(List<Entry> r) { _results = r; }

    public int size() { return getResults().size(); }

    /*
    /**********************************************************************
    /* Std methods
    /**********************************************************************
     */

    @Override
        public boolean _equals(TwitterSearch other)
    {
        return getResults().equals(other.getResults());
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("[SearchResults: ");
        sb.append(getResults().size()).append(" entries]");
        return sb.toString();
    }

    /*
    /**********************************************************************
    /* Helper class
    /**********************************************************************
     */

    public final static class Entry
    {
        public int id;
        public String text;
        public String from_user, to_user;
        public int from_user_id, to_user_id;
        public String iso_language_code;
        public String profile_image_url;
        public String created_at;
        
        public Entry() { }
    }
}

