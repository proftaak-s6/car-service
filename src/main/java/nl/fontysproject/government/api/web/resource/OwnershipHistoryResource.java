package nl.fontysproject.government.api.web.resource;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import nl.fontysproject.government.api.controller.OwnershipHistoryController;
import nl.fontysproject.government.api.model.OwnershipHistory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("ownership-histories")
public class OwnershipHistoryResource {

    @Inject
    private OwnershipHistoryController controller;

    @GET
    @Path("/{carId}")
    public Response getByCarId(@PathParam("carId") long carId) {
        List<OwnershipHistory> history = controller.getHistory(carId);

        if (history.size() == 0) {
            return Response.status(404)
                    .type(MediaType.TEXT_PLAIN_TYPE)
                    .entity("No history found for car with ID: " + carId)
                    .build();
        }

        return Response.ok()
                .entity(history)
                .build();
    }

    @PUT
    @Path("/{carId}")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response addHistory(@PathParam("carId") long carId, @RequestBody long ownerId) {
        try {
            controller.addHistory(carId, ownerId);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
}
