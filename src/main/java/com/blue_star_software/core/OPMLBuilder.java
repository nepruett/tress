package com.blue_star_software.core;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.Stack;

public class OPMLBuilder extends DefaultHandler {

    private static final String OUTLINE = "outline";

    private OPML opml;
    private Stack<Outline> outlineStack = new Stack<Outline>();

    @Override
    public void startDocument() throws SAXException {
        opml = new OPML();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (OUTLINE.equals(localName)) {
            Outline outline = new Outline(
                    attributes.getValue("title"),
                    attributes.getValue("provider"),
                    attributes.getValue("url"),
                    attributes.getValue("xmlUrl"),
                    attributes.getValue("htmlUrl"),
                    attributes.getValue("type"));
            outlineStack.push(outline);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (OUTLINE.equals(localName)) {
            Outline outline = outlineStack.pop();
            if (outlineStack.empty()) {
                opml.addOutline(outline);
            } else {
                outlineStack.peek().addOutline(outline);
            }
        }
    }

    @Override
    public void endDocument() {
        if (!outlineStack.empty()) {
            opml.addOutline(outlineStack.pop());
        }
    }

    public OPML getOpml() {
        return opml;
    }
}
