package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class TaskTest {

	@Test
	void testCreateTaskSuccess() {
		Task task = new Task("123456", "Do Something", "This is a task to complete work from a backlog");	
		
		assertTrue(task != null);
		assertTrue(task.getTaskId().equals("123456"));
		assertTrue(task.getName().equals("Do Something"));
		assertTrue(task.getDescription().equals("This is a task to complete work from a backlog"));
	}
	
	@Test
	void testCreateTaskTaskIdFails() {
		  Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Task("12345678911", "Do Something", "This is a task to complete work from a backlog");	
		    });	
	}
	
	@Test
	void testCreateTaskTaskNameFails() {
		  Assertions.assertThrows(IllegalArgumentException.class, () -> {
			  new Task("123456", "Do Somethingggggggggg", "This is a task to complete work from a backlog");
		    });	
	}
	
	@Test
	void testCreateTaskTaskDescriptionFails() {
		  Assertions.assertThrows(IllegalArgumentException.class, () -> {
			  new Task("123456", "Do Something", "This is a task to complete work from a backlogggggg");
		    });	
	}
	
}
