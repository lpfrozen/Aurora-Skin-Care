package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Invoice {
    private String invoiceId;
    private Appointment appointment;
    private Treatment treatment;
    private double totalAmount;
    private LocalDateTime invoiceDate;

    // Constructor
    public Invoice(String invoiceId, Appointment appointment, Treatment treatment) {
        this.invoiceId = invoiceId;
        this.appointment = appointment;
        this.treatment = treatment;
        this.totalAmount = treatment.calculateTotalWithTax();
        this.invoiceDate = LocalDateTime.now();
    }

    // Getters and Setters
    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public Treatment getTreatment() {
        return treatment;
    }

    public void setTreatment(Treatment treatment) {
        this.treatment = treatment;
        this.totalAmount = treatment.calculateTotalWithTax(); // Update total amount when treatment changes
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public LocalDateTime getInvoiceDate() {
        return invoiceDate;
    }

    // Method to get formatted date and time for display
    public String getFormattedInvoiceDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return invoiceDate.format(formatter);
    }

    // Method to generate and display invoice details
    public String generateInvoiceDetails() {
        return "Invoice ID: " + invoiceId + "\n" +
                "Invoice Date: " + getFormattedInvoiceDate() + "\n" +
                "Patient: " + appointment.getPatient().getName() + " (NIC: " + appointment.getPatient().getNic() + ")\n" +
                "Dermatologist: " + appointment.getDermatologist().getName() + "\n" +
                "Appointment Date and Time: " + appointment.getFormattedDateTime() + "\n" +
                "Treatment: " + treatment.name() + "\n" +
                "Treatment Price (before tax): LKR " + treatment.getPrice() + "\n" +
                "Total Amount (with 2.5% tax): LKR " + totalAmount + "\n";
    }

    // Override toString() method to display a summary of the invoice
    @Override
    public String toString() {
        return "Invoice{" +
                "Invoice ID='" + invoiceId + '\'' +
                ", Patient=" + appointment.getPatient().getName() +
                ", Treatment=" + treatment.name() +
                ", Total Amount=LKR " + totalAmount +
                ", Invoice Date=" + getFormattedInvoiceDate() +
                '}';
    }
}
