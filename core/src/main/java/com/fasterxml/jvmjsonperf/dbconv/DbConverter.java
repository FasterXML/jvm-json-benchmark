package com.fasterxml.jvmjsonperf.dbconv;

import java.io.*;

import com.fasterxml.jvmjsonperf.StdConverter;

/**
 * Base class for per-format converters used for "DB Converter" performance
 * test suite.
 */
public abstract class DbConverter
    extends StdConverter<DbData>
{
    final static String FIELD_TABLE = "table";
    final static String FIELD_ROW = "row";

    /**
     * Method that is to read all the data and convert it to
     * representation of full database contents
     */
    @Override
    public abstract DbData readData(InputStream in) throws Exception;

    /**
     * Method that is to read all the data and convert it to
     * representation of full database contents
     *
     * @return Bogus result value; ideally generated from data, arbitrary
     *   but not random. Need to ensure no dead code elimination occurs
     */
    @Override
    public abstract int writeData(OutputStream out, DbData data) throws Exception;
}
