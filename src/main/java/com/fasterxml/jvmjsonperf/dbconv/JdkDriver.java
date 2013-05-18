package com.fasterxml.jvmjsonperf.dbconv;

import com.fasterxml.jvmjsonperf.std.StdJdkConverter;

public final class JdkDriver
    extends DbconvDriver
{
    public JdkDriver() throws Exception
    {
        super(new StdJdkConverter<DbData>());
    }
}
