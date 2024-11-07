package services;

import models.Appointment;
import models.Dermatologist;
import models.Patient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentService {
    private static final int MAX_APPOINTMENTS = 100;
    private Appointment[] appointments = new Appointment[MAX_APPOINTMENTS];
    private int appointmentCount = 0;

    // Method to book a new appointment
    public boolean bookAppointment(Patient patient, Dermatologist dermatologist, LocalDateTime dateTime) {
        if (appointmentCount < MAX_APPOINTMENTS) {
            String appointmentId = "APT" + (appointmentCount + 1);
            appointments[appointmentCount++] = new Appointment(appointmentId, patient, dermatologist, dateTime);
            System.out.println("Appointment booked successfully with ID: " + appointmentId);
            return true;
        } else {
            System.out.println("Cannot book appointment: Maximum capacity reached.");
            return false;
        }
    }

    // Method to update an existing appointment
    public boolean updateAppointment(String appointmentId, LocalDateTime newDateTime) {
        Appointment appointment = searchAppointment(appointmentId);
        if (appointment != null) {
            appointment.setDateTime(newDateTime);
            System.out.println("Appointment updated successfully.");
            return true;
        } else {
            System.out.println("Appointment ID not found.");
            return false;
        }
    }

    // Method to view all appointments for a specific date
    public List<Appointment> viewAppointmentsByDate(LocalDate date) {
        List<Appointment> results = new ArrayList<>();
        for (int i = 0; i < appointmentCount; i++) {
            if (appointments[i].getDateTime().toLocalDate().equals(date)) {
                results.add(appointments[i]);
            }
        }
        return results;
    }

    // Method to search for an appointment by ID or patient name
    public Appointment searchAppointment(String searchTerm) {
        for (int i = 0; i < appointmentCount; i++) {
            if (appointments[i].getAppointmentId().equalsIgnoreCase(searchTerm) ||
                    appointments[i].getPatient().getName().equalsIgnoreCase(searchTerm)) {
                return appointments[i];
            }
        }
        System.out.println("No appointment found with the provided search term.");
        return null;
    }

    // Method to display all appointments (for testing and debugging purposes)
    public void displayAllAppointments() {
        if (appointmentCount == 0) {
            System.out.println("No appointments available.");
        } else {
            System.out.println("All Appointments:");
            for (int i = 0; i < appointmentCount; i++) {
                System.out.println(appointments[i]);
            }
        }
    }
}
