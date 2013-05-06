package org.codehaus.staxbind.twitter;

import java.util.*;

import javax.xml.bind.annotation.*;

import org.codehaus.staxbind.std.StdItem;

@SuppressWarnings("serial")
@XmlRootElement(name="searchResult") // needed by JAXB
public class TwitterSearch
    extends StdItem<TwitterSearch>
{
    private List<Entry> _results;

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
    ///////////////////////////////////////////////////
    // Std methods
    ///////////////////////////////////////////////////
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
    ///////////////////////////////////////////////////
    // Helper class
    ///////////////////////////////////////////////////
     */

    public final static class Entry
    {
        int _id;
        String _text;
        String _from_user, _to_user;
        int _from_user_id, _to_user_id;
        String _iso_language_code;
        String _profile_image_url;
        String _Created_at;

        public Entry() { }

        public int getId() { return _id; }
        public int getfrom_user_id() { return _from_user_id; }
        public int getto_user_id() { return _to_user_id; }

        public String getfrom_user() { return _from_user; }
        public String getto_user() { return _to_user; }
    }
}

