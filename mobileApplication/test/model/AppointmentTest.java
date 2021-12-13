package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AppointmentTest {
	
	//Appointment date in the future
    Date currentDate = new Date(System.currentTimeMillis());
    Date afterToday = new Date(currentDate.getTime() + 10);    

    //Appointment date in the past
    Date beforeToday = new Date(currentDate.getTime() - 10);
	
	//Test Contact create
	@Test
	void testCreateAppointmentSuccess() {
		Appointment appointment = new Appointment("123456", afterToday, "This is an appointment to discuss work.");	
		
		assertTrue(appointment != null);
		assertTrue(appointment.getAppointmentId().equals("123456"));
		assertTrue(appointment.getAppointmentDate().equals(afterToday));
		assertTrue(appointment.getDescription().equals("This is an appointment to discuss work."));
	}
	
	@Test
	void testCreateAppointmentAppointmentIdFails() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			  new Appointment("12345678901", afterToday, "This is an appointment to discuss work.");
		    });
	}
	
	@Test
	void testCreateAppointmentAppointmentDateFails() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			  new Appointment("123456", beforeToday, "This is an appointment to discuss work.");
		    });
	}
	
	@Test
	void testCreateAppointmentAppointmentDescriptionFails() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			  new Appointment("123456", afterToday, "This is an appointment to discuss work.This is an appointment to discuss work.This is an appointment to discuss work.This is an appointment to discuss work.");
		    });
	}

}
