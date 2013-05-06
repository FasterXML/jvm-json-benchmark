package com.fasterxml.staxbind.dbconv;

import com.sun.japex.Params;
import com.sun.japex.TestCase;

public final class XstreamDriver extends DbconvDriver
{
    public XstreamDriver()
    {
        super(new XstreamConverter());
    }

    @Override
    public void prepare(TestCase testCase)
    {
        /* 09-Dec-2008, tatus: must call init on converter first,
         *   since that constructs XStream instance, needed by 
         *   data loader
         */
        ((XstreamConverter)_converter).prepare((Params) this);
        super.prepare(testCase);
    }
}
