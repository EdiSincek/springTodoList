package com.edi.springboot.todolist.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class TodoService {
	
	private static List<Todo> todos = new ArrayList<>();
	private static int todosCount = 0;
	
	static {
		todos.add(new Todo(++todosCount, "Edi", "Description 1", LocalDate.now().plusYears(1), false));
		todos.add(new Todo(++todosCount, "edi", "Description 2", LocalDate.now().plusYears(2), false));
		todos.add(new Todo(++todosCount, "username3", "Description 3", LocalDate.now().plusYears(3), false));
	}
	
	public List<Todo> findByUsername(final String username){
		Predicate<? super Todo> predicate = todo -> todo.getUsername().equalsIgnoreCase(username);

		return todos.stream().filter(predicate).toList();
	}
	
	public void addTodo(final String username, final String description, final LocalDate targetDate, final boolean done) {
		Todo todo = new Todo(++todosCount, username, description, targetDate, done);
		todos.add(todo);
	}
	
	public void deleteById(final int id) {
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		todos.removeIf(predicate);
	}

	public Todo findById(int id) {
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		Todo todo = todos.stream().filter(predicate).findFirst().get();
		return todo;
	}

	public void updateTodo(@Valid Todo todo) {
		deleteById(todo.getId());
		todos.add(todo);
	}

}
