package com.fasterxml.staxbind.twitter;

import java.io.*;


import com.fasterxml.staxbind.japex.BaseJapexDriver;
import com.fasterxml.staxbind.std.StdConverter;

public abstract class TwitterDriver
    extends BaseJapexDriver<TwitterConverter.Operation>
{
    final StdConverter<TwitterSearch> _converter;

    /**
     * For write tests, we hold serializable objects here
     */
    TwitterSearch[] _writableData;

    /**
     * For read tests, we have byte arrays to read from here
     */
    byte[][] _readableData;

    protected TwitterDriver(StdConverter<TwitterSearch> conv)
    {
        super(StdConverter.Operation.READ);
        if (conv == null) {
            throw new IllegalArgumentException();
        }
        _converter = conv;
    }

    @Override
    protected int runTest(StdConverter.Operation oper) throws Exception
    {
        if (oper == null) {
            throw new RuntimeException("Internal exception: no operation passed");
        }

        final boolean doRead = (oper != StdConverter.Operation.WRITE);
        final boolean doWrite = (oper != StdConverter.Operation.READ);

        // read or read-write?
        if (doRead) {
            int result = 0;
            final ByteArrayOutputStream bos = new ByteArrayOutputStream(16000);
            for (byte[] input : _readableData) {
                TwitterSearch dd = (TwitterSearch) _converter.readData(new ByteArrayInputStream(input));
                if (dd == null) {
                    throw new IllegalStateException("Deserialized doc to null");
                }
                result += dd.size();
                _totalLength += input.length;
                if (doWrite) {
                    result += _converter.writeData(bos, dd);
                    _totalLength += bos.size();
                    bos.reset();
                }
            }
            return result;
        }

        // write-only:
        final ByteArrayOutputStream bos = new ByteArrayOutputStream(16000);
        int result = 0;
        for (TwitterSearch input : _writableData) {
            result += _converter.writeData(bos, input);
            _totalLength += bos.size();
            bos.reset();
        }
        return result;
    }

    @Override
    protected void doLoadTestData(StdConverter.Operation oper, File dir) throws Exception
    {
        /* First of all, read in all the data, bind to in-memory object(s),
         * and then (if read test), convert to the specific type converter
         * uses.
         */
        byte[] readBuffer = new byte[DEFAULT_BUF_SIZE];
        ByteArrayOutputStream tmpStream = new ByteArrayOutputStream(DEFAULT_BUF_SIZE);
        _totalLength = 0;

        File[] files = dir.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return name.endsWith(".jsn");
                }
            });

        StdConverter<TwitterSearch> stdConverter = getStdConverter();
        _readableData = (oper == StdConverter.Operation.WRITE) ?
            null : new byte[files.length][];
        _writableData = (oper == StdConverter.Operation.WRITE) ?
            new TwitterSearch[files.length] : null;

        for (int i = 0, len = files.length; i < len; ++i) {
            File f = files[i];
            // Read file contents, bind to in-memory object (using std conv)
            readAll(f, readBuffer, tmpStream);
            byte[] fileData = tmpStream.toByteArray();
            TwitterSearch origData = (TwitterSearch) stdConverter.readData(new ByteArrayInputStream(fileData));
            if (_writableData != null) {
                _writableData[i] = origData;
            }

            /* Then we better verify that we can round-trip content from
             * object to native format and back: and if it comes back
             * equal to original data, we are good to go.
             */

            tmpStream.reset();
            _converter.writeData(tmpStream, origData);
            byte[] convData = tmpStream.toByteArray();
            
            if (_readableData != null) {
                _readableData[i] = convData;
            }
            TwitterSearch convResults = (TwitterSearch)_converter.readData(new ByteArrayInputStream(convData));
            if (!convResults.equals(origData)) {
                // Not very clean, but let's output for debugging:
                System.err.println("Incorrect mapping");
                System.err.println("Source doc: ["+origData+"]");
                System.err.println("Using "+_converter+": ["+convResults+"]");
                throw new IllegalStateException("Item #"+i+"/"+len+" differs for '"+_converter+"'");
            }
        }
    }

    protected StdConverter<TwitterSearch> getStdConverter()
    {
        // data is in json, let's use Jackson as the base
        return JacksonDriverAutomatic.getConverter();
    }
}
