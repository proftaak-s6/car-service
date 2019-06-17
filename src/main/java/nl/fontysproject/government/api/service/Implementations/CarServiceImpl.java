package nl.fontysproject.government.api.service.Implementations;

import nl.fontysproject.government.api.model.Car;
import nl.fontysproject.government.api.service.Interfaces.CarService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.util.List;

public class CarServiceImpl implements CarService {

    @PersistenceContext(unitName = "GovernmentDB")
    private EntityManager entityManager;

    @Override
    public Car getById(long id) {
        return entityManager.find(Car.class, id);
    }

    @Override
    public List<Car> getAll() {
        return entityManager.createQuery("SELECT c FROM Car c", Car.class).getResultList();
    }

    @Override
    public List<Car> getCarsWithTracker() {
        return entityManager.createQuery("SELECT c FROM Car c WHERE c.tracker IS NOT NULL", Car.class).getResultList();
    }

    @Override
    @Transactional
    public Car create(Car model) {
        entityManager.persist(model);

        return model;
    }

    @Override
    @Transactional
    public Car update(Car model) {
        entityManager.merge(model);

        return model;
    }

    @Override
    @Transactional
    public boolean delete(long id) {
        Car carToDelete = entityManager.find(Car.class, id);
        entityManager.remove(carToDelete);

        return true;
    }

    @Override
    public Car getByLicensePlateNumber(String licensePlateNumber) {
        TypedQuery<Car> query = entityManager.createQuery("SELECT c FROM Car c WHERE c.licensePlateNumber = :licensePlateNumber",
                Car.class);
        query.setParameter("licensePlateNumber", licensePlateNumber);
        return query.getSingleResult();
    }

    @Override
    public boolean deleteByLicensePlateNumber(String licensePlateNumber) {
        Query query = entityManager.createQuery("DELETE FROM Car c WHERE c.licensePlateNumber = :licensePlateNumber");
        query.setParameter("licensePlateNumber", licensePlateNumber);
        int result = query.executeUpdate();
        return result == 1;
    }

    @Override
    public List<Car> getCarsWithTrackerByOwner(long ownerId) {
        TypedQuery<Car> query = entityManager.createQuery("SELECT c FROM Car c WHERE c.tracker IS NOT NULL AND c.ownerId = :ownerId", Car.class);
        query.setParameter("ownerId", ownerId);
        return query.getResultList();
    }
}
