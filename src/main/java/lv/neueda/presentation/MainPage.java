package lv.neueda.presentation;

import lv.neueda.configuration.WicketConfiguration;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class MainPage extends RootPage {
    @SpringBean
    private WicketConfiguration wicketConfiguration;

    public MainPage(PageParameters parameters) {
        super(parameters);

        add(new Label("hello", wicketConfiguration.getHelloText()));
    }
}
