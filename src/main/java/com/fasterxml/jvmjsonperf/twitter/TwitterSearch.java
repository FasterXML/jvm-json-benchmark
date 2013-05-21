package com.fasterxml.jvmjsonperf.twitter;

import java.util.*;

import com.fasterxml.jvmjsonperf.StdItem;

@SuppressWarnings("serial")
public class TwitterSearch
    extends StdItem<TwitterSearch>
{
    protected List<TwitterEntry> results;

    public long since_id, max_id;
    public int page;
    public int results_per_page;
    public String query;
    public String refresh_url;
    public String next_page;
    public double completed_in;
    
    public TwitterSearch() { }

    public List<TwitterEntry> getResults() {
        return results;
    }

    public void setResults(List<TwitterEntry> r) { results = r; }
    
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

    // // // ditto for getters

    public long getSince_id() { return since_id; }
    public long getMax_id() { return max_id; }

    public int getPage() { return page; }
    public int getResults_per_page() { return results_per_page; }

    public String getQuery() { return query; }
    public String getRefresh_url() { return refresh_url; }
    public String getNext_page() { return next_page; }

    public double getCompleted_in() { return completed_in; }
    
    /*
    /**********************************************************************
    /* Std methods
    /**********************************************************************
     */

    @Override
    public boolean _equals(TwitterSearch other)
    {
        List<TwitterEntry> e1 = getResults();
        List<TwitterEntry> e2 = other.getResults();

        if (e1 == null || e2 == null) {
            return false;
        }
        if (e1.size() != e2.size()) {
            return false;
        }
        for (int i = 0, len = e1.size(); i < len; ++i) {
            TwitterEntry entry1 = e1.get(i);
            TwitterEntry entry2 = e2.get(i);

            if (!entry1.equals(entry2)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("[SearchResults: ");
        sb.append(getResults().size()).append(" entries]");
        return sb.toString();
    }
}

