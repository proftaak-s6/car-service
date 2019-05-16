package nl.fontysproject.government.api.web.resource;

import nl.fontysproject.government.api.controller.CarController;
import nl.fontysproject.government.api.model.Car;
import nl.fontysproject.government.api.web.dto.CarDto;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/car")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CarResource {

    @Inject
    CarController carController;

    @GET
    @Path("")
    public Response getAll() {
        return Response.ok()
                .entity(carController.getAllCars())
                .build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") long id) {
        return Response.ok()
                .entity(carController.getCarById(id))
                .build();
    }

    @GET
    @Path("/rdw/{licensePlateNumber}")
    public Response getCarFromRDW(@PathParam("licensePlateNumber") String licensePlateNumber) {
        return Response.ok()
                .entity(carController.getCarFromRDW(licensePlateNumber))
                .build();
    }

    @GET
    @Path("/license-plate/{licensePlateNumber}")
    public Response getCarByLicensePlateNumber(@PathParam("licensePlateNumber") String licensePlateNumber) {
        return Response.ok()
                .entity(carController.getCarByLicensePlateNumber(licensePlateNumber))
                .build();
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCar(CarDto newCar) {
        return Response.ok()
                .entity(carController.createCar(newCar.toModel()))
                .build();
    }

    // TODO: Decide if needed
    @PUT
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCar(Car carToUpdate) {
        return Response.ok()
                .entity(carController.updateCar(carToUpdate))
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCar(@PathParam("id") long id) {
        return Response.ok()
                .entity(carController.deleteCar(id))
                .build();
    }

    @DELETE
    @Path("/license-plate/{licensePlateNumber}")
    public Response deleteCarByLicensePlateNumber(@PathParam("licensePlateNumber") String licensePlateNumber) {
        return Response.ok()
                .entity(carController.deleteCarByLicensePlateNumber(licensePlateNumber))
                .build();
    }

    @PUT
    @Path("/assign/{carId}/to/{ownerId}")
    public Response assignCarToOwner(@PathParam("carId") long carId, @PathParam("ownerId") long ownerId) {
        return Response.ok()
                .entity(carController.assignOwnerToCar(carId, ownerId))
                .build();
    }
}
