package com.springboot.todomanager.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

@Service
@SessionAttributes("name")
public class TodoService {
	// use with TodoController.java
	private static List<Todo> todos = new ArrayList<>();
	
	private static int todosCount = 0;
	
	static {
		todos.add(new Todo(++todosCount, "Tony","Get AWS Certified 1",
							LocalDate.now().plusYears(1), false ));
		todos.add(new Todo(++todosCount, "Tony","Learn DevOps 1",
				LocalDate.now().plusYears(2), false ));
		todos.add(new Todo(++todosCount, "John","Learn Full Stack Devlopment 1",
				LocalDate.now().plusYears(3), false ));
	}
	
	public List<Todo>findByUsername(String username){
		Predicate<? super Todo> Predicate = todo -> todo.getUsername().equalsIgnoreCase(username);
		return todos.stream().filter(Predicate).toList();
	}
	
	public void addTodo(String username, String description, LocalDate targetDate, boolean done) {
		Todo todo = new Todo(++todosCount, username, description, targetDate, done);
		todos.add(todo);
	}
	
	public void deleteById(int id) {
		// todo.getId() == id
		// todo -> todo.getId() == id
		Predicate<? super Todo> Predicate = todo -> todo.getId() == id;
		todos.removeIf(Predicate);
	}

	public Todo findById(int id) {
		Predicate<? super Todo> Predicate = todo -> todo.getId() == id;
		Todo todo = todos.stream().filter(Predicate).findFirst().get();
		return todo;
	}

	public void updateTodo(@Valid Todo todo) {
		deleteById(todo.getId());
		todos.add(todo);
	}
}
