package com.fasterxml.jvmjsonperf.twitter;

import com.fasterxml.jvmjsonperf.std.StdGsonConverter;

public class GsonDriverDatabind
    extends TwitterDriver
{
    public GsonDriverDatabind() throws Exception
    {
        super(new StdGsonConverter<TwitterSearch>(TwitterSearch.class));
    }
}
