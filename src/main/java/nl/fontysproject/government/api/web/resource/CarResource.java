package nl.fontysproject.government.api.web.resource;

import nl.fontysproject.government.api.controller.CarController;
import nl.fontysproject.government.api.model.Car;
import nl.fontysproject.government.api.web.dto.CarDto;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/car")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CarResource {

    @Inject
    CarController carController;

    @GET
    @Path("")
    public Response getAll() {
        List<Car> carList = carController.getAllCars();
        if (carList.isEmpty()){
            return Response.status(Response.Status.NO_CONTENT.getStatusCode(), "No cars found").build();
        }

        return Response.ok()
                .entity(carList)
                .build();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") long id) {
        Car car = carController.getCarById(id);
        if (car == null){
            return Response.status(Response.Status.NOT_FOUND.getStatusCode(), "No car found").build();
        }
        return Response.ok()
                .entity(car)
                .build();
    }

    @GET
    @Path("/rdw/{licensePlateNumber}")
    public Response getCarFromRDW(@PathParam("licensePlateNumber") String licensePlateNumber) {
        Car car = carController.getCarByLicensePlateNumber(licensePlateNumber);

        if (car == null){
            return Response.status(Response.Status.NOT_FOUND.getStatusCode(), "No car found").build();
        }

        return Response.ok()
                .entity(carController.getCarFromRDW(licensePlateNumber))
                .build();
    }

    @GET
    @Path("/license-plate/{licensePlateNumber}")
    public Response getCarByLicensePlateNumber(@PathParam("licensePlateNumber") String licensePlateNumber) {
        Car car = carController.getCarByLicensePlateNumber(licensePlateNumber);

        if (car == null){
            return Response.status(Response.Status.NOT_FOUND.getStatusCode(), "No car found").build();
        }

        return Response.ok()
                .entity(carController.getCarByLicensePlateNumber(licensePlateNumber))
                .build();
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCar(CarDto newCar, @Context UriInfo context) {
        Car car;

        try{
            car = carController.createCar(newCar.toModel());
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.toString()).build();
        }

        URI location = context.getAbsolutePathBuilder()
                .path(Long.toString(car.getId()))
                .build();

        return Response.status(Response.Status.CREATED.getStatusCode(), location.toString()).build();
    }

    // TODO: Decide if needed
    @PUT
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCar(Car carToUpdate) {
        Car car;

        try{
            car = carController.updateCar(carToUpdate);
        }catch (Exception e){
            return Response.status(Response.Status.NOT_MODIFIED.getStatusCode(), e.toString()).build();
        }
        return Response.ok()
                .entity(car)
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCar(@PathParam("id") long id) {
        try{
            carController.deleteCar(id);
        }catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.toString()).build();
        }

        return Response.ok()
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
