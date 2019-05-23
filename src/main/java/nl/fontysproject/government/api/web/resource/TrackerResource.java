package nl.fontysproject.government.api.web.resource;

import nl.fontysproject.government.api.controller.TrackerController;
import nl.fontysproject.government.api.model.Tracker;
import nl.fontysproject.government.api.web.dto.TrackerDto;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
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
        List<Tracker> trackerList = trackerController.getAllTrackers();
        if(trackerList.isEmpty()){
            return Response.status(Response.Status.NO_CONTENT.getStatusCode(), "No trackers found").build();
        }
        return Response.ok()
                .entity(trackerList)
                .build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") long id) {
        Tracker tracker = trackerController.getTrackerById(id);

        if(tracker == null){
            return Response.status(Response.Status.NOT_FOUND.getStatusCode(),"No tracker found").build();
        }
        return Response.ok()
                .entity(tracker)
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
    public Response createTracker(TrackerDto newTracker, @Context UriInfo context) {
        Tracker tracker;

        try{
            tracker = trackerController.createTracker(newTracker.toModel());
        } catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.toString()).build();
        }

        URI location = context.getAbsolutePathBuilder()
                .path(Long.toString(tracker.getId()))
                .build();

        return Response.status(Response.Status.CREATED.getStatusCode(), location.toString()).build();
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
        try{
            trackerController.deleteTracker(id);
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.toString()).build();
        }
        return Response.ok()
                .build();
    }

    @PUT
    @Path("/assign/{trackerId}/to/{carId}")
    public Response assignTrackerToCar(@PathParam("trackerId") long trackerId, @PathParam("carId") long carId) {
        Tracker tracker;
        try{
            tracker = trackerController.assignTrackerToCar(trackerId,carId);
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.toString()).build();
        }
        return Response.ok()
                .entity(tracker)
                .build();
    }
}
