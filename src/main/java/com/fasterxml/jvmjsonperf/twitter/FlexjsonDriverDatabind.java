package com.fasterxml.jvmjsonperf.twitter;

import com.fasterxml.jvmjsonperf.std.StdFlexJsonConverter;

public class FlexjsonDriverDatabind
    extends TwitterDriver
{
    public FlexjsonDriverDatabind() throws Exception
    {
        // important: include List of entries
        super(new StdFlexJsonConverter<TwitterSearch>(TwitterSearch.class,
                new String[] { "results" } ));
        
    }
}
