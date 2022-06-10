package com.openHouseAI.logs.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table
public class Action  {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="time")
    private Timestamp time;

    @Column(name="type")
    private String type;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="log_id")
    private Log log;


    @OneToOne(mappedBy="action", cascade=CascadeType.ALL)
    private Properties properties;


    //create and generate constructor
    public Action() {

    }

    public Action(Timestamp time, String type) {
        this.time = time;
        this.type = type;
    }

    //generate getters and setters


    public Log getLog() {
        return log;
    }

    public void setLog(Log log) {
        this.log = log;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
        properties.setAction(this);
    }

    @Override
    public String toString() {
        return "Action [\n time=" + time + ",\n type=" + type  + "]";
    }
}
