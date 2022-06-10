package com.openHouseAI.logs.DAO;

import com.openHouseAI.logs.entity.Action;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.util.List;

public interface ActionsDAO extends CrudRepository<Action,Integer> {
    List<Action> findByTimeBetween(Timestamp startTime, Timestamp endTime);
    List<Action> findByType(String type);
}
