package org.example.controller;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.config.LoggerConfig;
import org.example.dto.ProfileDTO;
import org.example.repository.impl.ProfileRepositoryImpl;
import org.example.repository.impl.UserRepositoryImpl;
import org.example.service.ProfileService;
import org.example.service.impl.ProfileServiceImpl;

import java.util.logging.Logger;

@Path("/Profiles")
public class ProfileController {
    private final ProfileService profileService;
    private final Logger logger = LoggerConfig.getLogger(ProfileController.class);

    public ProfileController() {
        this.profileService = new ProfileServiceImpl(new ProfileRepositoryImpl(), new UserRepositoryImpl());
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(ProfileDTO profile, @QueryParam("user") String user) {
        logger.info("Create profile request received for user " + user);
        return Response.ok(profileService.create(profile, user)).build();
    }
}
