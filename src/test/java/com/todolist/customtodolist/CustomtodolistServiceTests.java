package com.todolist.customtodolist;

import com.todolist.customtodolist.domainModel.Task;
import com.todolist.customtodolist.repo.TaskRepo;
import com.todolist.customtodolist.service.TaskService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class CustomtodolistServiceTests {

    @Mock
    private TaskRepo taskRepo;

    @InjectMocks
    private TaskService taskService;

    @Captor
    private ArgumentCaptor<Task> anyUserEntity;

    @Test
    public void create_savesEntityBasedOnInput() {
        var entity = new Task("Task 9");
        when(taskRepo.save(any())).thenReturn(entity);

        taskService.addTask(new Task("Task 9"));
        verify(taskRepo).save(anyUserEntity.capture()); // Use capture()
        assertThat(anyUserEntity, hasProperty("text", equalTo("Task 9")));
        assertThat(anyUserEntity, hasProperty("isActive", equalTo(true)));
        assertThat(anyUserEntity, hasProperty("isComplete", equalTo(false)));
    }

}
