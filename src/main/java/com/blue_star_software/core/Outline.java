package com.blue_star_software.core;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;


public class Outline {

    @JacksonXmlProperty(isAttribute = true)
    private String title;

    @JacksonXmlProperty(isAttribute = true)
    private String xmlUrl;

    @JacksonXmlProperty(isAttribute = true)
    private String htmlUrl;

    @JacksonXmlProperty(isAttribute = true)
    private String url;

    @JacksonXmlProperty(isAttribute = true)
    private String type;

    @JacksonXmlProperty(isAttribute = true)
    private String provider;

    @JacksonXmlProperty(localName = "outline")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Outline> outlines = Lists.newArrayList();

    public Outline(String title) {
        this.title = title;
    }

    public Outline(String title, String provider, String url, String xmlUrl, String htmlUrl, String type){
        this(title);
        this.provider = provider;
        this.url = url;
        this.xmlUrl = xmlUrl;
        this.htmlUrl = htmlUrl;
        this.type = type;
    }

    public Outline(String title, String xmlUrl, String htmlUrl, String type){
        this(title);
        this.xmlUrl = xmlUrl;
        this.htmlUrl = htmlUrl;
        this.type = type;
    }

    public Outline(String title, String provider, String url, String xmlUrl, String htmlUrl, String type, Outline[] outlines){
        this(title, provider, url, xmlUrl, htmlUrl, type);
        this.outlines.addAll(Arrays.asList(outlines));
    }

    public String getTitle() {
        return title;
    }

    public String getProvider() {
        return provider;
    }

    public String getUrl() {
        return url;
    }

    public String getXmlUrl() {
        return xmlUrl;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getType() {
        return type;
    }

    public Outline[] getOutlines() {
        return outlines.toArray(new Outline[outlines.size()]);
    }

    public void addOutline(Outline outline) {
        outlines.add(outline);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o != null && o instanceof Outline) {
            Outline other = (Outline) o;
            return Objects.equal(title, other.title) &&
                Objects.equal(type, other.type) &&
                Objects.equal(provider, other.provider) &&
                Objects.equal(url, other.url) &&
                Objects.equal(xmlUrl, other.xmlUrl) &&
                Objects.equal(htmlUrl, other.htmlUrl) &&
                Objects.equal(outlines, other.outlines);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(title, type, provider, url, xmlUrl, htmlUrl, outlines);
    }
}
