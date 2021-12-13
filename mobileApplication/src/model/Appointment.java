package model;

import java.util.Date;

public class Appointment {
	
	private String appointmentId;
	private Date appointmentDate;
	private String description;
	
	public Appointment(String appointmentId, Date appointmentDate, String description) {
		
		//validate inputs against requirements
		boolean isValid = validateInput(appointmentId, 10);
		
		if(isValid) {
			this.appointmentId = appointmentId;
		}
		
		isValid = isValid && setAppointmentDate(appointmentDate);
		isValid = isValid && setAppointmentDescription(description);
		
		if(!isValid) {
			throw new IllegalArgumentException("Invalid input");
		}
	}
	
	public boolean setAppointmentDate(Date appointmentDate) {
		boolean isValid = validateDate(appointmentDate);
		
		if(isValid) {
			this.appointmentDate = appointmentDate;
		}
		return isValid;
	}
	
	public boolean setAppointmentDescription(String description) {
		boolean isValid = validateInput(description, 50);
		
		if(isValid) {
			this.description = description;
		}
		return isValid;
	}
	
	public String getAppointmentId() {
		return appointmentId;
	}
	
	public Date getAppointmentDate() {
		return appointmentDate;
	}
	
	public String getDescription() {
		return description;
	}
	
	private boolean validateInput(String item, int length) {
		return (item != null && item.length() <= length);
	}
	
	private boolean validateDate(Date date) {
		return (!date.before(new Date()));
	}

}
