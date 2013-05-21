package com.fasterxml.jvmjsonperf.twitter;

import com.fasterxml.jvmjsonperf.std.StdGensonConverter;

public class GensonDriverDatabind
    extends TwitterDriver
{
    public GensonDriverDatabind() throws Exception
    {
        super(new StdGensonConverter<TwitterSearch>(TwitterSearch.class));
    }
}
