package com.fasterxml.jvmjsonperf.twitter;

import com.fasterxml.jvmjsonperf.std.StdFlexJsonConverter;

public class FlexjsonDriverDatabind
    extends TwitterDriver
{
    public FlexjsonDriverDatabind() throws Exception
    {
        super(new StdFlexJsonConverter<TwitterSearch>(TwitterSearch.class));
    }
}
