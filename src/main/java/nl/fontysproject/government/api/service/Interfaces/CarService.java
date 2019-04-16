package nl.fontysproject.government.api.service.Interfaces;

import nl.fontysproject.government.api.model.Car;

public interface CarService extends BaseService<Car> {
    Car getByLicensePlateNumber(String licensePlateNumber);
    boolean deleteByLicensePlateNumber(String licensePlateNumber);
    Car assignToOwner(long carId, long ownerId);
}
