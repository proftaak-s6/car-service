package nl.fontysproject.government.api.service.Implementations;

import nl.fontysproject.government.api.model.Car;
import nl.fontysproject.government.api.model.Tracker;
import nl.fontysproject.government.api.service.Interfaces.TrackerService;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.util.List;

public class TrackerServiceImpl implements TrackerService {

    @PersistenceContext(unitName = "GovernmentDB")
    private EntityManager entityManager;

    @Override
    public Tracker getById(long id) {
        return entityManager.find(Tracker.class, id);
    }

    @Override
    public List<Tracker> getAll() {
        return entityManager.createQuery("SELECT t FROM Tracker t", Tracker.class).getResultList();
    }

    @Override
    @Transactional
    public Tracker create(Tracker model) {

        entityManager.persist(model);


        return model;
    }

    @Override
    @Transactional
    public Tracker update(Tracker model) {

        entityManager.merge(model);


        return model;
    }

    @Override
    @Transactional
    public boolean delete(long id) {
        Tracker trackerToDelete = entityManager.find(Tracker.class, id);


        entityManager.remove(trackerToDelete);


        return true;
    }

    @Override
    @Transactional
    public Tracker assignToCar(long trackerId, long carId) {
        Tracker trackerToAssign = entityManager.find(Tracker.class, trackerId);
        Car carToAssignTo = entityManager.find(Car.class, carId);
        carToAssignTo.setTracker(trackerToAssign);

        entityManager.merge(carToAssignTo);

        return trackerToAssign;
    }

    @Override
    public List<Tracker> getAvailableTrackers() {
        return entityManager.createQuery("SELECT t FROM Tracker t WHERE t.car IS NULL", Tracker.class).getResultList();
    }
}
