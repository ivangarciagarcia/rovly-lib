package dev.ivangg;

import dev.ivangg.ioutils.MyConfigClass;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        MyConfigClass config = new MyConfigClass();
        System.out.printf("Prop1: %s, Prop2: %s%n", config.getProperty1(),config.getProperty2());
    }
}
