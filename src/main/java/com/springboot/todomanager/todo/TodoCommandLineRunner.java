package com.springboot.todomanager.todo;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TodoCommandLineRunner implements CommandLineRunner {
	
	
	public TodoCommandLineRunner(TodoRepository repository) {
		super();
		this.repository = repository;
	}
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	private TodoRepository repository;
	
	private int todosCount = 0;
	
	@Override
	public void run(String... args) throws Exception {
		repository.save(new Todo(++todosCount, "Tony","Get AWS Certified 1",
				LocalDate.now().plusYears(1), false ));
		repository.save(new Todo(++todosCount, "Tony","Learn DevOps 2",
				LocalDate.now().plusYears(2), false ));
		repository.save(new Todo(++todosCount, "John","Learn Full Stack Devlopment 3",
				LocalDate.now().plusYears(3), false ));
		
		
//		List<Todo> todos = repository.findByUsername("Tony");
//		
//		todos.forEach(todo -> logger.info(todo.toString()));
	}
}
