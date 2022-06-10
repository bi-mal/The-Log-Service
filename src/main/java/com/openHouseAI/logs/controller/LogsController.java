package com.openHouseAI.logs.controller;

import com.openHouseAI.logs.Exceptions.LogBadRequestException;
import com.openHouseAI.logs.entity.Log;
import com.openHouseAI.logs.LogService.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api")
public class LogsController {
    @Autowired
    LogService logService;

    @GetMapping("/userid/{userId}")
    public ResponseEntity<List<Log>> getLogsByUserId(@PathVariable String userId) {
        return new ResponseEntity<>(logService.findLogsByUserId(userId),HttpStatus.OK);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Log>> getLogsByActionType(@PathVariable String type) {
        return new ResponseEntity<>(logService.findLogsByActionType(type),HttpStatus.OK);
    }

    @GetMapping("/starttime/{startTime}/endtime/{endTime}")
    public ResponseEntity<List<Log>> getLogsByTimeRange
            (@PathVariable Timestamp startTime, @PathVariable Timestamp endTime) {
        return new ResponseEntity<>(logService.findLogsByTimeRange(startTime, endTime),HttpStatus.OK);
    }

    @GetMapping("/userid/{userId}/Type/{type}")
    public ResponseEntity<List<Log>> getLogsByUserAndActionType(@PathVariable String userId, @PathVariable String type) {
        return new ResponseEntity<>(logService.findLogsByUserIdAndActionType(userId, type),HttpStatus.OK);
    }

    @GetMapping("/userid/{userId}/starttime/{startTime}/endtime/{endTime}")
    public ResponseEntity<List<Log>> getLogsByUserAndTimeRange
            (@PathVariable String userId, @PathVariable Timestamp startTime, @PathVariable Timestamp endTime) {
        return new ResponseEntity<>(logService.findLogsByUserIdAndTimeRange(userId, startTime, endTime),HttpStatus.OK);
    }

    @GetMapping("/type/{type}/startTime/{startTime}/endTime/{endTime}")
    public ResponseEntity<List<Log>> getLogsByActionTypeAndTimeRange(@PathVariable String type, @PathVariable Timestamp startTime, @PathVariable Timestamp endTime) {
        return new ResponseEntity<>(logService.findLogsByActionTypeAndTimeRange(type,startTime,endTime),HttpStatus.OK);
    }

    @GetMapping("/userId/{userId}/type/{type}/starttime/{startTime}/endtime/{endTime}")
    public ResponseEntity<List<Log>> getLogsByUserIdAndActionTypeAndTimeRange
            (@PathVariable String userId, @PathVariable String type, @PathVariable Timestamp startTime, @PathVariable Timestamp endTime) {
        return new ResponseEntity<>(logService.findLogsByUserIdActionTypeAndTimeRange(userId, type, startTime, endTime),HttpStatus.OK);
    }

    @PostMapping("/createlog")
    public ResponseEntity<Log> addLog(@RequestBody Log theLog) {

        logService.save(theLog);
        return new ResponseEntity<>(theLog, HttpStatus.CREATED);
    }

    @PutMapping("/updatelog")
    public ResponseEntity<Log> updateLog(@RequestBody Log theLog) {

        logService.save(theLog);
        return new ResponseEntity<>(theLog, HttpStatus.CREATED);

    }

    @DeleteMapping("/userid/{userId}")
    public ResponseEntity deleteLogsByUserId(@PathVariable String userId) {
        logService.deleteByUserId(userId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
