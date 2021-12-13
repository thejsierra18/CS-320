package services;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.Task;
import service.TaskService;

class TaskServiceTest {

	private static TaskService taskService;
	
	@BeforeAll
	static void setup() {
		taskService = TaskService.getService();
	}
	
	@Test
	void testAddTaskSuccess() {
		Task task = new Task("123456", "Do Something", "This is a task to complete work from a backlog");	
		assertTrue(taskService.addTask(task));
		
		Task cachedTask = taskService.getTask(task.getTaskId());
		
		assertTrue(cachedTask != null);
		assertTrue(cachedTask.getTaskId().equals("123456"));
		assertTrue(cachedTask.getName().equals("Do Something"));
		assertTrue(cachedTask.getDescription().equals("This is a task to complete work from a backlog"));
	}
	
	@Test
	void testAddMultipleTaskSuccess() {
		Task task1 = new Task("123457", "Do Something", "This is a task to complete work from a backlog");	
		Task task2 = new Task("123458", "Do a Task", "This is a task to complete work from a backlog");	

		assertTrue(taskService.addTask(task1));
		task1 = taskService.getTask(task1.getTaskId());
		
		assertTrue(task1 != null);
		assertTrue(task1.getTaskId().equals("123457"));
		assertTrue(task1.getName().equals("Do Something"));
		assertTrue(task1.getDescription().equals("This is a task to complete work from a backlog"));
		
		assertTrue(taskService.addTask(task2));
		task2 = taskService.getTask(task2.getTaskId());
		
		assertTrue(task2 != null);
		assertTrue(task2.getTaskId().equals("123458"));
		assertTrue(task2.getName().equals("Do a Task"));
		assertTrue(task2.getDescription().equals("This is a task to complete work from a backlog"));
	}
	
	@Test
	void testAddTaskDuplicateIdFail() {
		Task task1 = new Task("123459", "Do Something", "This is a task to complete work from a backlog");	
		Task task2 = new Task("123459", "Do a Task", "This is a task to complete work from a backlog");	

		assertTrue(taskService.addTask(task1));
		assertFalse(taskService.addTask(task2));
	}
	
	@Test
	void testGetTaskAndUpdateSuccess() {
		Task task = new Task("12345", "Do Something", "This is a task to complete work from a backlog");	
		assertTrue(taskService.addTask(task));
		
		Task updatedTask = taskService.getTask(task.getTaskId());
		updatedTask.setTaskDescription("This is a new description");
		updatedTask.setTaskName("This is a new name");
		
		updatedTask = taskService.getTask(updatedTask.getTaskId());

		assertTrue(updatedTask.getDescription().equals("This is a new description"));
		assertTrue(updatedTask.getName().equals("This is a new name"));	
	}
	
	@Test
	void testGetTaskAndDeleteSuccess() {
		Task task = new Task("1234", "Do Something", "This is a task to complete work from a backlog");	

		assertTrue(taskService.addTask(task));
		
		task = taskService.getTask(task.getTaskId());
		assertTrue(task != null);
		
		assertTrue(taskService.deleteTask(task.getTaskId()));
		assertTrue(taskService.getTask(task.getTaskId()) == null);
	}
	
	@Test
	void testDeleteInvalidTaskFail() {
		String invalidTaskId = "123";

		assertFalse(taskService.deleteTask(invalidTaskId));
	}

}
