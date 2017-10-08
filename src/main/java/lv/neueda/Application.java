package lv.neueda;

import lv.neueda.presentation.MainPage;
import org.apache.wicket.Page;
import org.apache.wicket.core.request.mapper.HomePageMapper;
import org.apache.wicket.core.request.mapper.MountedMapper;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WicketFilter;
import org.apache.wicket.spring.SpringWebApplicationFactory;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.filter.RequestContextFilter;

import javax.servlet.Filter;

import static org.apache.wicket.protocol.http.WicketFilter.APP_FACT_PARAM;

/**
 * Created by Vladislavs Mitrosenko on 08.Oct.17.
 */
@Configuration(value = Application.APPLICATION)
@SpringBootApplication(scanBasePackageClasses = {Application.class})
@EnableScheduling
@EnableCaching
@EnableAsync
public class Application extends WebApplication {
    public static final String MAIN = "main";
    public static final String APPLICATION = "application";

    @Override
    public Class<? extends Page> getHomePage() {
        return MainPage.class;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    protected void init() {
        super.init();

        getRequestCycleSettings().setResponseRequestEncoding("UTF-8");
        getMarkupSettings().setDefaultMarkupEncoding("UTF-8");

        getDebugSettings().setOutputMarkupContainerClassName(true);
        getDebugSettings().setAjaxDebugModeEnabled(true);

        getComponentInstantiationListeners().add(new SpringComponentInjector(this));

        mount(new HomePageMapper(MainPage.class));
        mount(new MountedMapper(MAIN, MainPage.class));
    }

    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE)
    public FilterRegistrationBean wicketFilter() {
        final Filter filter = new WicketFilter();
        final FilterRegistrationBean result = new FilterRegistrationBean(filter);

        result.addUrlPatterns("/*");
        //result.addInitParameter("applicationClassName", Application.class.getName());

        result.addInitParameter(APP_FACT_PARAM, SpringWebApplicationFactory.class.getName());
        result.addInitParameter("applicationBean", APPLICATION);
        result.addInitParameter(WicketFilter.FILTER_MAPPING_PARAM, "/*");

        return result;
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public FilterRegistrationBean requestContextFilter() {
        final Filter filter = new RequestContextFilter();
        final FilterRegistrationBean result = new FilterRegistrationBean(filter);

        result.addUrlPatterns("/*");

        return result;
    }

}
