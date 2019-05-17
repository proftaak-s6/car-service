package nl.fontysproject.government.api.web.resource;

import nl.fontysproject.government.api.controller.TrackerController;
import nl.fontysproject.government.api.model.Tracker;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/tracker")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TrackerResource {

    @Inject
    TrackerController trackerController;

    @GET
    @Path("")
    public Response getAll() {
        return Response.ok()
                .entity(trackerController.getAllTrackers())
                .build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") long id) {
        return Response.ok()
                .entity(trackerController.getTrackerById(id))
                .build();
    }

    @GET
    @Path("/available")
    public Response getAvailable() {
        try {
            List<Tracker> trackerList = trackerController.getAvailableTrackers();

            if(trackerList.isEmpty()) {
                return Response.status(
                        Response.Status.NO_CONTENT.getStatusCode(),
                        "No available trackers were found.")
                        .build();
            }

            return Response.ok()
                    .entity(trackerController.getAvailableTrackers())
                    .build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.toString()).build();
        }
    }

    @POST
    @Path("")
    public Response createTracker(Tracker newTracker) {
        return Response.ok()
                .entity(trackerController.createTracker(newTracker))
                .build();
    }

    @PUT
    @Path("")
    public Response updateTracker(Tracker trackerToUpdate) {
        return Response.ok()
                .entity(trackerController.updateTracker(trackerToUpdate))
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteTracker(long id) {
        return Response.ok()
                .entity(trackerController.deleteTracker(id))
                .build();
    }

    @PUT
    @Path("/assign/{trackerId}/to/{carId}")
    public Response assignTrackerToCar(@PathParam("trackerId") long trackerId, @PathParam("carId") long carId) {
        return Response.ok()
                .entity(trackerController.assignTrackerToCar(trackerId, carId))
                .build();
    }
}
