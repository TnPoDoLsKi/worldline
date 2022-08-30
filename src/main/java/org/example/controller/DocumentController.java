package org.example.controller;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.config.LoggerConfig;
import org.example.dto.FileDTO;
import org.example.repository.impl.DocumentRepositoryImpl;
import org.example.repository.impl.UserRepositoryImpl;
import org.example.service.DocumentService;
import org.example.service.impl.DocumentServiceImpl;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataParam;

import java.io.IOException;
import java.util.logging.Logger;

@Path("/Document")
public class DocumentController {
    private final DocumentService documentService;

    private final Logger logger = LoggerConfig.getLogger(DocumentController.class);

    public DocumentController() {
        this.documentService = new DocumentServiceImpl(new DocumentRepositoryImpl(), new UserRepositoryImpl());
    }

    @POST
    @Consumes({MediaType.MULTIPART_FORM_DATA})
    @Produces(MediaType.APPLICATION_JSON)
    public Response upload(@FormDataParam("file") final FormDataBodyPart body, @QueryParam("user") String user) {
        logger.info("Upload file request received for user " + user);
        return Response.ok(documentService.uploadDocument(body.getFileName().get(), body.getMediaType().toString(), body.getContent(), user)).build();
    }

    @GET
    @Path("{id}")
    public Response retrieve(@PathParam("id") String id, @QueryParam("user") String user) throws IOException {
        logger.info("Retrieve file request received for user " + user);
        FileDTO fileDTO = documentService.retrieveDocument(id,user);
        return Response.ok(fileDTO.getContent(), fileDTO.getMediaType()).header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDTO.getFilename() + "\"").build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") String id, @QueryParam("user") String user) {
        logger.info("Delete file request received for user " + user);
        documentService.delete(id, user);
        return Response.ok().build();
    }
}
