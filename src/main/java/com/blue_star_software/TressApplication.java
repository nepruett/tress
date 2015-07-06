package com.blue_star_software;

import com.blue_star_software.resources.TransformResource;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.yunspace.dropwizard.xml.XmlBundle;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.forms.MultiPartBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class TressApplication extends Application<TressConfiguration> {

    public static void main(final String[] args) throws Exception {
        new TressApplication().run(args);
    }

    @Override
    public String getName() {
        return "Tress";
    }

    @Override
    public void initialize(final Bootstrap<TressConfiguration> bootstrap) {
        XmlBundle xmlBundle = new XmlBundle();
        xmlBundle.getXmlMapper().enable(ToXmlGenerator.Feature.WRITE_XML_DECLARATION);
        xmlBundle.getXmlMapper().enable(SerializationFeature.INDENT_OUTPUT);
        bootstrap.addBundle(xmlBundle);
        bootstrap.addBundle(new MultiPartBundle());
        bootstrap.addBundle(new AssetsBundle("/assets/", "/", "index.html"));
    }

    @Override
    public void run(final TressConfiguration configuration,
                    final Environment environment) {
        final TransformResource transformResource = new TransformResource();
        environment.jersey().register(transformResource);
    }

}
