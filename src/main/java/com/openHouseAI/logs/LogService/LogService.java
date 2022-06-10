package com.openHouseAI.logs.LogService;

import com.openHouseAI.logs.Exceptions.LogBadRequestException;
import com.openHouseAI.logs.Exceptions.LogResourceNotFoundException;
import com.openHouseAI.logs.entity.Log;

import java.sql.Timestamp;
import java.util.List;

public interface LogService {
    Log save(Log theLog) throws LogBadRequestException;
    List<Log> findLogsByUserId(String userId) throws LogResourceNotFoundException;
    List<Log> findLogsByActionType(String ActionType) throws LogResourceNotFoundException;
    List<Log> findLogsByActionTypeAndTimeRange(String ActionType,Timestamp startTime,Timestamp endTime) throws LogResourceNotFoundException;
    List<Log> findLogsByTimeRange(Timestamp startTime,Timestamp endTime) throws LogResourceNotFoundException;
    List<Log> findLogsByUserIdAndActionType(String userId, String type) throws LogResourceNotFoundException;
    List<Log> findLogsByUserIdAndTimeRange(String UserId, Timestamp startTime, Timestamp endTime) throws LogResourceNotFoundException;
    List<Log> findLogsByUserIdActionTypeAndTimeRange(String UserId, String type, Timestamp startTime, Timestamp endTime) throws LogResourceNotFoundException;
    void deleteByUserId(String userId) throws LogBadRequestException;
}
