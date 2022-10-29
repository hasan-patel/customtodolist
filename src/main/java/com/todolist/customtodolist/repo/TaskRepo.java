package com.todolist.customtodolist.repo;

import com.todolist.customtodolist.domainModel.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

//rightclick JpaRepository then goto superClass for better understanding..
public interface TaskRepo extends JpaRepository<Task, Long> {

    void deleteTaskById(Long id);

    Optional<Task> findTaskById(Long id);

    //@Query("Select t from Task t where t.isActive=true")
    //List<Task> findAllIsActive();
    List<Task> findByIsActiveIsTrue();

    List<Task> findByIsActiveIsTrueAndIsComplete(Boolean isComplete);
}
