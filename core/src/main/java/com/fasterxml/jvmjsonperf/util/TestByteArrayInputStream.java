package com.fasterxml.jvmjsonperf.util;

import java.io.*;

/**
 * Custom sub-class so we can carry along some things that are needed
 * to support test oddities...
 */
public class TestByteArrayInputStream
    extends ByteArrayInputStream
{
    protected final int _size;
    
    public TestByteArrayInputStream(byte[] data) {
        super(data);
        _size = data.length;
    }

    public int size() { return _size; }
}
