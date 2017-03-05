package cz.simek.phm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public abstract class CommonConfig {
    @Autowired
    protected Environment environment;

    public CommonConfig() {
    }

    public String get(String key) {
        return this.environment.getRequiredProperty(key);
    }

    public String getOptional(String key) {
        return this.environment.getProperty(key);
    }

    public Integer getInteger(String key) {
        return (Integer) this.environment.getRequiredProperty(key, Integer.class);
    }


    public Integer getIntegerOptional(String key) {
        return (Integer) this.environment.getProperty(key, Integer.class);
    }

    public Long getLong(String key) {
        return (Long) this.environment.getRequiredProperty(key, Long.class);
    }

    public Long getLongOptional(String key) {
        return (Long) this.environment.getProperty(key, Long.class);
    }

    public boolean getBoolean(String key) {
        return ((Boolean) this.environment.getRequiredProperty(key, Boolean.class)).booleanValue();
    }

    public boolean getBooleanOptional(String key) {
        return ((Boolean) this.environment.getProperty(key, Boolean.class, Boolean.valueOf(false))).booleanValue();
    }

    public Environment getEnvironment() {
        return this.environment;
    }

}