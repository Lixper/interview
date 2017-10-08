package lv.neueda.configuration;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JacksonConfigurationSources {
	String[] value();
}
