package com.nivekaa.soko.config;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

/**
 * @author nivekaa
 * Created 26/03/2020 at 23:32
 * Class com.nivekaa.soko.config.Configuration
 */

public class Configuration {
    Yaml yaml = new Yaml();

    private Configuration() {
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("customer.yaml");
    }

    public static Configuration getInstance() {
        return new Configuration();
    }


    public static class Field{

    }
}
