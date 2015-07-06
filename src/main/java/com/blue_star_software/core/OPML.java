package com.blue_star_software.core;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

@JacksonXmlRootElement(localName = "opml")
public class OPML {

    @JacksonXmlProperty(isAttribute = true)
    private String version = "1.0";

    @JacksonXmlProperty(localName = "head")
    private Head head = new Head();

    @JacksonXmlProperty(localName = "body")
    private Body body = new Body();

    public void addOutline(Outline outline) {
        body.addOutline(outline);
    }

    private static final class Head {
        @JacksonXmlText(value = true)
        private String title = "No title";
    }

    private static final class Body {
        @JacksonXmlProperty(localName = "outline")
        @JacksonXmlElementWrapper(useWrapping = false)
        private List<Outline> outlines = Lists.newArrayList();

        public Body() {

        }

        public Body(Outline[] outlines) {
            this.outlines.addAll(Arrays.asList(outlines));
        }

        public void addOutline(Outline outline) {
            outlines.add(outline);
        }
    }

}
