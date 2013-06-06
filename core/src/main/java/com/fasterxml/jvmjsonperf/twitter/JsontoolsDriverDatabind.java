package com.fasterxml.jvmjsonperf.twitter;

import com.fasterxml.jvmjsonperf.std.StdJsonToolsConverter;

public class JsontoolsDriverDatabind
    extends TwitterDriver
{
    public JsontoolsDriverDatabind() throws Exception
    {
        super(new StdJsonToolsConverter<TwitterSearch>(TwitterSearch.class));
    }
}
