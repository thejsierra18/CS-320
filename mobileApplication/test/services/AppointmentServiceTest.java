package services;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertFalse;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import model.Appointment;
import service.AppointmentService;

class AppointmentServiceTest {
	//Appointment date in the future
    Date currentDate = new Date(System.currentTimeMillis());
    Date afterToday1 = new Date(currentDate.getTime() + 10);
    Date afterToday2 = new Date(currentDate.getTime() + 20);
	
	private static AppointmentService appointmentService;
	
	@BeforeAll
	static void setup() {
		appointmentService = AppointmentService.getService();
	}

	@Test
	void testAddAppointmentSuccess() {
		Appointment appointment = new Appointment("123456", afterToday1, "This is an appointment to discuss work.");
		assertTrue(appointmentService.addAppointment(appointment));
		
		Appointment cachedAppointment = appointmentService.getAppointment(appointment.getAppointmentId());
		
		assertTrue(cachedAppointment != null);
		assertTrue(cachedAppointment.getAppointmentId().equals("123456"));
		assertTrue(cachedAppointment.getAppointmentDate().equals(afterToday1));
		assertTrue(cachedAppointment.getDescription().equals("This is an appointment to discuss work."));
	}
	
	@Test
	void testAddMultipleAppointmentSuccess() {
		Appointment appointment1 = new Appointment("12345678", afterToday1, "This is an appointment to discuss work.");
		Appointment appointment2 = new Appointment("87654321", afterToday2, "This is an appointment to discuss work.");
		
		assertTrue(appointmentService.addAppointment(appointment1));
		appointment1 = appointmentService.getAppointment(appointment1.getAppointmentId());
		
		assertTrue(appointment1 != null);
		assertTrue(appointment1.getAppointmentId().equals("12345678"));
		assertTrue(appointment1.getAppointmentDate().equals(afterToday1));
		assertTrue(appointment1.getDescription().equals("This is an appointment to discuss work."));
		
		assertTrue(appointmentService.addAppointment(appointment2));
		appointment2 = appointmentService.getAppointment(appointment2.getAppointmentId());
		
		assertTrue(appointment2 != null);
		assertTrue(appointment2.getAppointmentId().equals("87654321"));
		assertTrue(appointment2.getAppointmentDate().equals(afterToday2));
		assertTrue(appointment2.getDescription().equals("This is an appointment to discuss work."));
	}
	
	@Test
	void testAddAppointmentDuplicateIdFail() {
		Appointment appointment1 = new Appointment("123", afterToday1, "This is an appointment to discuss work.");
		Appointment appointment2 = new Appointment("123", afterToday2, "This is an appointment to discuss work.");
		
		assertTrue(appointmentService.addAppointment(appointment1));
		assertFalse(appointmentService.addAppointment(appointment2));
	}
	
	@Test
	void testGetAppointmentAndUpdateSuccess() {
		Appointment appointment = new Appointment("1234", afterToday1, "This is an appointment to discuss work.");
		assertTrue(appointmentService.addAppointment(appointment));
		
		Appointment updatedAppointment = appointmentService.getAppointment(appointment.getAppointmentId());
		updatedAppointment.setAppointmentDate(afterToday2);
		updatedAppointment.setAppointmentDescription("This is a new description");
		
		updatedAppointment = appointmentService.getAppointment(updatedAppointment.getAppointmentId());
		
		assertTrue(updatedAppointment.getAppointmentDate().equals(afterToday2));
		assertTrue(updatedAppointment.getDescription().equals("This is a new description"));
	}
	
	@Test
	void testGetAppointmentAndDeleteSuccess() {
		Appointment appointment = new Appointment("1234", afterToday1, "This is an appointment to discuss work.");
		
		assertTrue(appointmentService.addAppointment(appointment));
		
		appointment = appointmentService.getAppointment(appointment.getAppointmentId());
		assertTrue(appointment != null);
		
		assertTrue(appointmentService.deleteAppointment(appointment.getAppointmentId()));
		assertTrue(appointmentService.getAppointment(appointment.getAppointmentId()) == null);
	}
	
	@Test
	void testDeleteInvalidAppointmentFail() {
		String invalidAppointmentId = "1";
		
		assertFalse(appointmentService.deleteAppointment(invalidAppointmentId));
	}

}
