package com.fasterxml.staxbind.dbconv;

import com.fasterxml.staxbind.std.StdJdkConverter;

public final class JdkDriver
    extends DbconvDriver
{
    public JdkDriver() throws Exception
    {
        super(new StdJdkConverter<DbData>());
    }
}
