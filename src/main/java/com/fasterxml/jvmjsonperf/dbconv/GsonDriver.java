package com.fasterxml.jvmjsonperf.dbconv;

import com.fasterxml.jvmjsonperf.std.StdGsonConverter;

/**
 * Driver that uses Gson-based data binding for JSON serialization
 */
public final class GsonDriver
    extends DbconvDriver
{
    public GsonDriver() throws Exception
    {
        super(new StdGsonConverter<DbData>(DbData.class));
    }
}
