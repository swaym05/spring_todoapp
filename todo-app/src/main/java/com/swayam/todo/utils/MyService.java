package com.swayam.todo.utils;

import java.util.List;

public interface MyService {

    TodoDto createTask(TodoDto todoDto);

    TodoDto updateTask(TodoDto todoDto, Integer id);

    List<TodoDto> getAllTask();

    void deleteTask(Integer id);

}
