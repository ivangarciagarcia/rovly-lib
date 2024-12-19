package dev.ivangg.ioutils;

import dev.ivangg.ioutils.annotations.ConfigInitializer;
import dev.ivangg.ioutils.annotations.TConfiguration;
import dev.ivangg.ioutils.annotations.TValue;

@TConfiguration(filename = "myconfig.properties")
public class MyConfigClass {
    @TValue(name = "prop1")
    private String property1;

    @TValue()
    private String prop2;

    public MyConfigClass() {
        ConfigInitializer.initialize(this); // Initialize the class during construction
    }

    public String getProperty1() {
        return this.property1;
    }

    public String getProperty2() {
        return this.prop2;
    }
}
