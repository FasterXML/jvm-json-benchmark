package com.fasterxml.jvmjsonperf.caliper;

import com.google.caliper.Benchmark;
import com.google.caliper.runner.CaliperMain;

public class CaliperLauncher
{
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception
    {
        if (args.length < 1) {
            System.err.println("Missing argument: name of test class");
            System.exit(1);
        }
        Class<?> cls0 = null;
        try {
            cls0 = Class.forName(args[0]);
        } catch (Throwable t) {
            System.err.println("Invalid class '"+args[0]+"', failed to load: "+t);
            System.exit(2);
        }
        if (!Benchmark.class.isAssignableFrom(cls0)) {
            System.err.println("Invalid class '"+args[0]+"': not an instance of Benchmark");
            System.exit(3);
        }
        // And push out the class name arg...
        String[] newArgs = new String[args.length-1];
        System.arraycopy(args, 1, newArgs, 0, newArgs.length);
        
        CaliperMain.main((Class<? extends Benchmark>) cls0, newArgs);
    }
}
