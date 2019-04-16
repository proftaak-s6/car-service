package nl.fontysproject.government.api.service.Interfaces;

import nl.fontysproject.government.api.model.Tracker;

public interface TrackerService extends BaseService<Tracker> {
    Tracker assignToCar(long trackerId, long carId);
}
