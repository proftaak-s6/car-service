package nl.fontysproject.government.api.controller;

import nl.fontysproject.government.api.model.Tracker;
import nl.fontysproject.government.api.service.Implementations.TrackerServiceImpl;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class TrackerController {

    @Inject
    TrackerServiceImpl trackerService;

    public Tracker getTrackerById(long id) {
        return trackerService.getById(id);
    }

    public Tracker createTracker(Tracker model) {
        return trackerService.create(model);
    }

    public Tracker updateTracker(Tracker model) {
        return trackerService.update(model);
    }

    public List<Tracker> getAllTrackers() {
        return trackerService.getAll();
    }

    public boolean deleteTracker(long id) {
        return trackerService.delete(id);
    }

    public Tracker assignTrackerToCar(long trackerId, long carId) {
        return trackerService.assignToCar(trackerId, carId);
    }

    public List<Tracker> getAvailableTrackers() {
        return trackerService.getAvailableTrackers();
    }
}
