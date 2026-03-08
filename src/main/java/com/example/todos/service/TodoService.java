package com.example.todos.service;

import com.example.todos.request.TodoRequest;
import com.example.todos.responses.TodoResponse;

import java.util.List;

public interface TodoService {
    TodoResponse createTodo(TodoRequest todoRequest);
    TodoResponse toggleTodoCompletion(long id);
    List<TodoResponse> getAllTodos();
    void deleteTodo(long id);

}
