package com.fasterxml.staxbind.dbconv;

import org.codehaus.staxbind.std.StdFlexJsonConverter;

/**
 * Driver that uses FlexJson-based data binding for JSON serialization
 */
public final class FlexJsonDriver
    extends DbconvDriver
{
    public FlexJsonDriver() throws Exception
    {
        super(new StdFlexJsonConverter<DbData>(DbData.class));
    }
}
