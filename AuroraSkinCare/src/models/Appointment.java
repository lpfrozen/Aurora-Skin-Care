package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Appointment {
    private String appointmentId;
    private Patient patient;
    private Dermatologist dermatologist;
    private LocalDateTime dateTime;

    // Constructor
    public Appointment(String appointmentId, Patient patient, Dermatologist dermatologist, LocalDateTime dateTime) {
        this.appointmentId = appointmentId;
        this.patient = patient;
        this.dermatologist = dermatologist;
        this.dateTime = dateTime;
    }

    // Getters and Setters
    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Dermatologist getDermatologist() {
        return dermatologist;
    }

    public void setDermatologist(Dermatologist dermatologist) {
        this.dermatologist = dermatologist;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    // Format date and time for display
    public String getFormattedDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return dateTime.format(formatter);
    }

    // Override toString() method to display appointment details
    @Override
    public String toString() {
        return "Appointment{" +
                "Appointment ID='" + appointmentId + '\'' +
                ", Patient=" + patient +
                ", Dermatologist=" + dermatologist.getName() +
                ", Date and Time=" + getFormattedDateTime() +
                '}';
    }
}
