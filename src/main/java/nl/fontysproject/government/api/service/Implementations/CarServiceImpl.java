package nl.fontysproject.government.api.service.Implementations;

import nl.fontysproject.government.api.model.Car;
import nl.fontysproject.government.api.service.Interfaces.CarService;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.UserTransaction;
import java.util.List;

public class CarServiceImpl implements CarService {

    @PersistenceContext(unitName = "GovernmentDB")
    private EntityManager entityManager;

    @Resource
    private UserTransaction transaction;

    @Override
    public Car getById(long id) {
        return entityManager.find(Car.class, id);
    }

    @Override
    public List<Car> getAll() {
        return entityManager.createQuery("SELECT c FROM Car c", Car.class).getResultList();
    }

    @Override
    public Car create(Car model) {
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
    public Car update(Car model) {
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
        Car carToDelete = entityManager.find(Car.class, id);

        try {
            transaction.begin();
            entityManager.remove(carToDelete);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

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

    //TODO: Remove
    @Override
    public Car assignToOwner(long carId, long ownerId) {
//        Car carToAssign = entityManager.find(Car.class, carId);
//        Owner ownerToAssignTo = entityManager.find(Owner.class, ownerId);
//        carToAssign.setOwner(ownerToAssignTo);
//
//        try {
//            transaction.begin();
//            entityManager.merge(carToAssign);
//            transaction.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return carToAssign;
        throw new NotImplementedException();
    }
}
