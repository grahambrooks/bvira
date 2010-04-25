package bvira.framework;

import bvira.util.Lists;
import org.junit.Test;

import java.util.List;

public class ConfigurationUnitTests {
    /*
    Declaritive specification
    Needs another class for the values which are loaded by a reader
     */
    static class ConfigurationParameter {
        static ConfigurationParameter parameter = new ConfigurationParameter("database.credentials", containing(requiredProperty("username"), requiredProperty("password")));

        public ConfigurationParameter(String name, Constraint... builders) {

        }

        class Constraint {
        }

        static Constraint containing(Constraint... builders) {
            return null;
        }

        static Constraint requiredProperty(String name) {
            return null;
        }
    }

    interface ConfigurationReader {
        void read(List<ConfigurationParameter> parameters, List<ConfigurationValue> values);

    }

    static interface ConfigurationValue {
        String getName();

        String getSource();

        Object getValue();

        Object getValue(String name);
    }

    static class SingleConfigurationValue implements ConfigurationValue {
        public String getName() {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }

        public String getSource() {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }

        public Object getValue() {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }

        public Object getValue(String name) {
            return null;  //To change body of implemented methods use File | Settings | File Templates.
        }
    }

    static class ApplicationConfigurationItem {
        ConfigurationParameter a;
        ConfigurationValue v;
    }

    interface ApplicationConfigurationProvider {
        ConfigurationValue get(ConfigurationParameter parameter);
    }

    @Test
    public void configurationPatterTest() {
        ConfigurationParameter configurationParameter = new ConfigurationParameter("some.propery.name");

        ConfigurationReader reader = new ConfigurationReader() {
            public void read(List<ConfigurationParameter> parameters, List<ConfigurationValue> values) {
                values.add(new SingleConfigurationValue());
            }
        };

        List<ConfigurationValue> config = Lists.create();
        reader.read(Lists.create(configurationParameter), config);

        config.get(0).getValue();
    }
}
