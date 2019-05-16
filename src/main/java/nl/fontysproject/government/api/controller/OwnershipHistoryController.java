package nl.fontysproject.government.api.controller;

import nl.fontysproject.government.api.model.OwnershipHistory;

import java.util.List;

public interface OwnershipHistoryController {
    void addHistory(long carId, long ownerId);
    List<OwnershipHistory> getHistory(long carId);
}
