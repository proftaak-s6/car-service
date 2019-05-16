package nl.fontysproject.government.api.service.Interfaces;

import nl.fontysproject.government.api.model.Car;
import nl.fontysproject.government.api.model.Tracker;

public interface OwnershipHistoryService {
    void add(long ownerId, Car car);
}
