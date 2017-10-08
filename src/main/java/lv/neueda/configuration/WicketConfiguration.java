package lv.neueda.configuration;

import org.springframework.context.annotation.Configuration;
import sun.misc.Contended;

import java.io.Serializable;

/**
 * Created by Vladislavs Mitrosenko on 08.Oct.17.
 */
@Configuration
@JacksonConfigurationSources({
        "classpath*:config/wicket.yml"
})
public class WicketConfiguration extends JacksonConfiguration {
    private String helloText;

    public String getHelloText() {
        return helloText;
    }
}
