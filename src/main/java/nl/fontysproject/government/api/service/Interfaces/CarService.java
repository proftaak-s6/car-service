package nl.fontysproject.government.api.service.Interfaces;

import nl.fontysproject.government.api.model.Car;

import java.util.List;

public interface CarService extends BaseService<Car> {
    Car getByLicensePlateNumber(String licensePlateNumber);
    boolean deleteByLicensePlateNumber(String licensePlateNumber);
    List<Car> getCarsWithTrackerByOwner(long ownerId);
}
