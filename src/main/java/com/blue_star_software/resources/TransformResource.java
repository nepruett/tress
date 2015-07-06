package com.blue_star_software.resources;

import com.blue_star_software.core.OPMLBuilder;
import com.codahale.metrics.annotation.Timed;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;

@Path("/transform")

public class TransformResource {

    @POST
    @Timed
    @Path("/opml")
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response transformOPML(
            final @FormDataParam("userId") String userId,
            final @FormDataParam("appId") String appId,
            final @FormDataParam("opmlFile") InputStream uploadedInputStream,
            final @FormDataParam("opmlFile") FormDataContentDisposition fileDetail
    ) throws IOException, SAXException, ParserConfigurationException {

        OPMLBuilder opmlBuilder = new OPMLBuilder();

        String filename = fileDetail.getFileName();
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setNamespaceAware(true);
        SAXParser saxParser = spf.newSAXParser();
        XMLReader xmlReader = saxParser.getXMLReader();
        xmlReader.setContentHandler(opmlBuilder);
        xmlReader.parse(new InputSource(uploadedInputStream));

        return Response.ok(opmlBuilder.getOpml()).header("content-disposition", "attachment; filename = updated-" + filename).build();
    }
}
