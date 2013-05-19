package com.fasterxml.jvmjsonperf.twitter;

import java.util.*;

import com.fasterxml.jvmjsonperf.StdItem;

@SuppressWarnings("serial")
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

    // // // Setters for whoever needs them
    
    public void setSince_id(long l) { since_id = l; }
    public void setMax_id(long l) { max_id = l; }

    public void setPage(int i) { page = i; }
    public void setResults_per_page(int i) { results_per_page = i; }
    
    public void setQuery(String s) { query = s; }
    public void setRefresh_url(String s) { refresh_url = s; }
    public void setNext_page(String s) { next_page = s; }

    public void setCompleted_in(double d) { completed_in = d; }
    
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

        public void setId(int v) { id = v; }
        public void setText(String v) { text = v; }

        public void setFrom_user_id(int v) { from_user_id = v; }
        public void setTo_user_id(int v) { to_user_id = v; }
        
        public void setFrom_user(String v) { from_user = v; }
        public void setTo_user(String v) { to_user = v; }
        public void setIso_language_code(String v) { iso_language_code = v; }
        public void setProfile_image_url(String v) { profile_image_url = v; }
        public void setCreated_at(String v) { created_at = v; }
    }
}

