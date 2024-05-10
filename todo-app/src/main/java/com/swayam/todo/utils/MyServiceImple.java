package com.swayam.todo.utils;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MyServiceImple implements MyService {

    @Autowired
    private TaskRepo taskRepo;

    @Override
    public TodoDto createTask(TodoDto dto) {
        ToDo task = new ToDo();
        BeanUtils.copyProperties(dto,task);

        ToDo saved = this.taskRepo.save(task);

        TodoDto todto = new TodoDto();
        BeanUtils.copyProperties(saved,todto);

        return todto;
    }

    @Override
    public TodoDto updateTask(TodoDto todoDto, Integer id) {
        Optional<ToDo> taskOptional = this.taskRepo.findById(id);
        if (!taskOptional.isPresent()) {
            throw new EntityNotFoundException("Task not found with id: " + id);
        }
        ToDo task_old = taskOptional.get();
        BeanUtils.copyProperties(todoDto, task_old, "id");
        ToDo newTask = this.taskRepo.save(task_old);
        TodoDto updated = new TodoDto();
        BeanUtils.copyProperties(newTask, updated);

        return updated;
    }

    @Override
    public List<TodoDto> getAllTask() {
        List<ToDo> toDo = this.taskRepo.findAll();
        List<TodoDto> todoDtoList = new ArrayList<>();
        for(ToDo x: toDo){
            TodoDto todoDto = new TodoDto();
            BeanUtils.copyProperties(x,todoDto);
            todoDtoList.add(todoDto);
        }

        return todoDtoList;
    }

    @Override
    public void deleteTask(Integer id) {
        Optional<ToDo> taskOptional = this.taskRepo.findById(id);
        if (!taskOptional.isPresent()) {
            throw new EntityNotFoundException("Task not found with id: " + id);
        }
        ToDo todo = this.taskRepo.findById(id).get();
        this.taskRepo.delete(todo);
    }

}
