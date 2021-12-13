package model;

public class Task {
	
	private String taskId;
	private String name;
	private String description;
	
	public Task(String taskId, String name, String description) {
		
		//validate inputs against requirements
		boolean isValid = validateInput(taskId, 10);
			
		if(isValid) {
			this.taskId = taskId;
		}
		
		isValid = isValid && setTaskName(name);
		isValid = isValid && setTaskDescription(description);
		
		if(!isValid) {
			throw new IllegalArgumentException("Invalid input");
		}
	}

	public boolean setTaskName(String name) {
		boolean isValid = validateInput(name, 20);
		
		if(isValid) {
			this.name = name;
		}
		return isValid;
	}
	
	public boolean setTaskDescription(String description) {
		boolean isValid = validateInput(description, 50);
		
		if(isValid) {
			this.description = description;
		}
		return isValid;
	}
	
	public String getTaskId() {
		return taskId;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	private boolean validateInput(String item, int length) {
		return (item != null && item.length() <= length);
	}
}

