package com.swayam.todo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private MyService myService;

    @RequestMapping("")
    public String home(){
        return "index.jsp";
    }

    @PostMapping("/addtask")
    public ResponseEntity<TodoDto> createUser(@RequestBody  TodoDto todoDto){
        TodoDto createtask = this.myService.createTask(todoDto);
        System.out.println("Response Entity: " + createtask.toString());
        return  new ResponseEntity<>(createtask, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TodoDto> updateTask(@RequestBody TodoDto todoDto,@PathVariable Integer id){
        TodoDto updatedtask = this.myService.updateTask(todoDto,id);
        return ResponseEntity.ok(updatedtask);
    }


    @GetMapping("/tasklist/")
    public ResponseEntity<List<TodoDto>> getAllTask(){
        List<TodoDto> todoDtoList = this.myService.getAllTask();
        return new ResponseEntity<>(todoDtoList,HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer id ){
        this.myService.deleteTask(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Successfully",true),HttpStatus.OK);
    }


}
