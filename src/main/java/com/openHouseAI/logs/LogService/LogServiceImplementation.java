package com.openHouseAI.logs.LogService;

import com.openHouseAI.logs.DAO.ActionsDAO;
import com.openHouseAI.logs.Exceptions.LogBadRequestException;
import com.openHouseAI.logs.Exceptions.LogResourceNotFoundException;
import com.openHouseAI.logs.entity.Log;
import com.openHouseAI.logs.DAO.LogDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LogServiceImplementation implements LogService {

    @Autowired
    LogDAO logDAO;

    @Autowired
    ActionsDAO actionsDAO;

    @Override
    public Log save(Log theLog) throws LogBadRequestException{
        try{
            return logDAO.save(theLog);
        }
        catch (Exception e){
            throw new LogBadRequestException("Invalid request");
        }
    }

    @Override
    public List<Log> findLogsByUserId(String userId) throws LogResourceNotFoundException  {
        List<Log> logs=logDAO.findByUserId(userId);
        if(logs.isEmpty())
            throw new LogResourceNotFoundException("no resource found for this userId");
        return logs;
    }

    @Override
    public List<Log> findLogsByActionType(String ActionType) throws LogResourceNotFoundException{
        List<Log> logs=actionsDAO.findByType(ActionType)
                .stream()
                .map(action->action.getLog())
                .distinct()
                .collect(Collectors.toList());
        if(logs.isEmpty())
            throw new LogResourceNotFoundException("no resource found for this action type");
        return logs;
    }

    @Override
    public List<Log> findLogsByTimeRange(Timestamp startTime, Timestamp endTime) throws LogResourceNotFoundException{
        var logs=actionsDAO.findByTimeBetween(startTime,endTime)
                .stream()
                .map(action->action.getLog())
                .distinct()
                .collect(Collectors.toList());
        if(logs.isEmpty())
            throw new LogResourceNotFoundException("no resource found for this time range");
        return logs;
    }

    @Override
    public List<Log> findLogsByActionTypeAndTimeRange(String ActionType, Timestamp startTime, Timestamp endTime)
                                                        throws LogResourceNotFoundException{
        var logs=actionsDAO.findByType(ActionType)
                .stream()
                .filter(action-> action.getTime().after(startTime)&&action.getTime().before(endTime))
                .map(action->action.getLog())
                .distinct()
                .collect(Collectors.toList());
        if(logs.isEmpty())
            throw new LogResourceNotFoundException("no resource found for this action type and time range");
        return logs;
    }


    @Override
    public List<Log> findLogsByUserIdAndActionType(String userId, String type) throws LogResourceNotFoundException{
        var logs=findLogsByUserId(userId).stream()
                .filter(log->log.getActions().stream().anyMatch(action-> type.equals(action.getType())))
                .collect(Collectors.toList());
        if(logs.isEmpty())
            throw new LogResourceNotFoundException("no resource found for this user id and action type");
        return logs;
    }



    @Override
    public List<Log> findLogsByUserIdAndTimeRange(String userId, Timestamp startTime, Timestamp endTime)
                                                throws LogResourceNotFoundException{
        var logs= findLogsByUserId(userId).stream()
                .filter(user->user.getActions().stream().anyMatch(action-> action.getTime().after(startTime)&&action.getTime().before(endTime)))
                .collect(Collectors.toList());
        if(logs.isEmpty())
            throw new LogResourceNotFoundException("no resource found for this userId and time range");
        return logs;
    }

    @Override
    public List<Log> findLogsByUserIdActionTypeAndTimeRange(String userId, String type, Timestamp startTime, Timestamp endTime)
                                                            throws LogResourceNotFoundException{
        var logs= findLogsByUserId(userId).stream()
                .filter(user->user.getActions().stream().anyMatch(action-> type.equals(action.getType())&&action.getTime().after(startTime)&&action.getTime().before(endTime)))
                .collect(Collectors.toList());
        if(logs.isEmpty())
            throw new LogResourceNotFoundException("no resource found for this userId, action type and time range");
        return logs;
    }

    @Override
    public void deleteByUserId(String userId) throws LogBadRequestException{
        try{
            logDAO.deleteByUserId(userId);
        }
        catch (Exception e){
            throw new LogBadRequestException("resource does not exist");
        }
    }
}
