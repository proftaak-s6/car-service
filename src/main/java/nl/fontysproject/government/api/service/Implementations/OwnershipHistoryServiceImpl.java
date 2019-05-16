package nl.fontysproject.government.api.service.Implementations;

import nl.fontysproject.government.api.model.Car;
import nl.fontysproject.government.api.model.OwnershipHistory;
import nl.fontysproject.government.api.model.Tracker;
import nl.fontysproject.government.api.service.Interfaces.OwnershipHistoryService;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public class OwnershipHistoryServiceImpl implements OwnershipHistoryService {

    @PersistenceContext(unitName = "GovernmentDB")
    private EntityManager manager;

    @Override
    @Transactional
    public void add(long ownerId, Car car) {
        Date today = new Date();
        List<OwnershipHistory> history = car.getOwnershipHistoryList();
        OwnershipHistory last = history.get(history.size() - 1);

        history.add(new OwnershipHistory(ownerId, today, null));
        last.setEndDate(today);

        car.setOwnershipHistoryList(history);

        manager.merge(car);
    }
}
