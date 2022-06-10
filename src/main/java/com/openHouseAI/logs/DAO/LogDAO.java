package com.openHouseAI.logs.DAO;

import com.openHouseAI.logs.entity.Log;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LogDAO extends CrudRepository<Log,Integer> {
    List<Log> findByUserId(String userId);
    void deleteByUserId(String userId);
}
