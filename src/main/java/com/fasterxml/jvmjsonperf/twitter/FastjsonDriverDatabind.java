package com.fasterxml.jvmjsonperf.twitter;

import com.fasterxml.jvmjsonperf.std.StdFastjsonConverter;

public class FastjsonDriverDatabind
    extends TwitterDriver
{
    public FastjsonDriverDatabind() throws Exception
    {
        super(new StdFastjsonConverter<TwitterSearch>(TwitterSearch.class));
    }
}
