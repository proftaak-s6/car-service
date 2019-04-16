package nl.fontysproject.government.api.service.Implementations;

import nl.fontysproject.government.api.model.Car;
import nl.fontysproject.government.api.model.Tracker;
import nl.fontysproject.government.api.service.Interfaces.TrackerService;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import java.util.List;

public class TrackerServiceImpl implements TrackerService {

    @PersistenceContext(unitName = "GovernmentDB")
    private EntityManager entityManager;

    @Resource
    private UserTransaction transaction;

    @Override
    public Tracker getById(long id) {
        return entityManager.find(Tracker.class, id);
    }

    @Override
    public List<Tracker> getAll() {
        return entityManager.createQuery("SELECT t FROM Tracker t", Tracker.class).getResultList();
    }

    @Override
    public Tracker create(Tracker model) {
        try {
            transaction.begin();
            entityManager.persist(model);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return model;
    }

    @Override
    public Tracker update(Tracker model) {
        try {
            transaction.begin();
            entityManager.merge(model);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return model;
    }

    @Override
    public boolean delete(long id) {
        Tracker trackerToDelete = entityManager.find(Tracker.class, id);

        try {
            transaction.begin();
            entityManager.remove(trackerToDelete);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public Tracker assignToCar(long trackerId, long carId) {
        Tracker trackerToAssign = entityManager.find(Tracker.class, trackerId);
        Car carToAssignTo = entityManager.find(Car.class, carId);
        carToAssignTo.setTracker(trackerToAssign);

        try {
            transaction.begin();
            entityManager.merge(carToAssignTo);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return trackerToAssign;
    }
}
