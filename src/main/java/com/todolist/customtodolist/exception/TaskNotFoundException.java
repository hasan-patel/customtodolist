package com.todolist.customtodolist.exception;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(String e) {
        super(e);
    }
}
