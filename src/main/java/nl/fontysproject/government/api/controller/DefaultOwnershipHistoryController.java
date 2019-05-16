package nl.fontysproject.government.api.controller;

import nl.fontysproject.government.api.model.Car;
import nl.fontysproject.government.api.model.OwnershipHistory;
import nl.fontysproject.government.api.service.Interfaces.CarService;
import nl.fontysproject.government.api.service.Interfaces.OwnershipHistoryService;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;

public class DefaultOwnershipHistoryController implements OwnershipHistoryController {

    @Inject
    private OwnershipHistoryService service;

    @Inject
    private CarService carService;

    @Override
    public void addHistory(long carId, long trackerId) {
        Car car = carService.getById(carId);
        service.add(trackerId, car);
    }

    @Override
    public List<OwnershipHistory> getHistory(long carId) {
        Car car = carService.getById(carId);

        if (car == null) {
            throw new NotFoundException();
        }

        return car.getOwnershipHistoryList();
    }
}
