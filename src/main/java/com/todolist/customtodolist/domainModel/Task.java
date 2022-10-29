package com.todolist.customtodolist.domainModel;

import javax.persistence.*;
import java.io.Serializable;

@Entity //this makes the below class is mapped to any DB we have configured
public class Task implements Serializable {
    @Id //this declares my id as primary key
    @GeneratedValue(strategy = GenerationType.AUTO) //this helps us decide the strategy for declaring new primary keys
    @Column(nullable = false, updatable = false) //we can configure columns like this :O
    private Long id;
    private String text;
    private Boolean isActive;
    private Boolean isComplete;

    public Task() {}

    public Task(String text) {
        this.text = text;
        this.isActive = true;
        this.isComplete = false;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getIsComplete() {
        return this.isComplete;
    }

    public void setIsComplete(Boolean isComplete) {
        this.isComplete = isComplete;
    }

    @Override
    public String toString() {
        return "Task{" +
                " id='" + this.id + "'" +
                ", text='" + this.text + "'" +
                ", isActive='" + this.isActive + "'" +
                ", isComplete='" + this.isComplete + "'" +
                "}";
    }

}
