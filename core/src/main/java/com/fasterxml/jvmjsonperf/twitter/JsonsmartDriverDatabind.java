package com.fasterxml.jvmjsonperf.twitter;

import com.fasterxml.jvmjsonperf.std.StdJsonSmartConverter;

public class JsonsmartDriverDatabind
    extends TwitterDriver
{
    public JsonsmartDriverDatabind() throws Exception
    {
        super(new StdJsonSmartConverter<TwitterSearch>(TwitterSearch.class));
    }
}
