import models.Appointment;
import models.Dermatologist;
import models.Patient;
import models.Treatment;
import services.AppointmentService;
import services.BillingService;
import utils.TimeUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class AuroraSkinCareApp {
    private static final AppointmentService appointmentService = new AppointmentService();
    private static final BillingService billingService = new BillingService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to Aurora Skin Care Appointment System");

        while (true) {
            displayMenu();
            int choice = getUserChoice();
            switch (choice) {
                case 1 -> bookAppointment();
                case 2 -> updateAppointment();
                case 3 -> viewAppointmentsByDate();
                case 4 -> searchAppointment();
                case 5 -> generateInvoice();
                case 6 -> viewAllInvoices();
                case 7 -> exitApplication();
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\nPlease choose an option:");
        System.out.println("1. Book an Appointment");
        System.out.println("2. Update Appointment");
        System.out.println("3. View Appointments by Date");
        System.out.println("4. Search Appointment by ID or Patient Name");
        System.out.println("5. Generate Invoice");
        System.out.println("6. View All Invoices");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private static void bookAppointment() {
        System.out.println("\n--- Book an Appointment ---");

        // Get Patient details
        String nic = getValidInput("Enter Patient NIC: ");
        String name = getValidInput("Enter Patient Name: ");
        String email = getValidInput("Enter Patient Email: ");
        String phone = getValidInput("Enter Patient Phone Number: ");
        if (!Patient.isValidNIC(nic)) {
            System.out.println("Invalid NIC format. Please try again.");
            return;
        }
        Patient patient = new Patient(nic, name, email, phone);

        // Select Dermatologist
        String dermatologistId = getValidInput("Enter Dermatologist ID (e.g., DR1 or DR2): ");
        String dermatologistName = getValidInput("Enter Dermatologist Name: ");
        Dermatologist dermatologist = new Dermatologist(dermatologistId, dermatologistName,
                new String[]{"Monday 10:00-13:00", "Wednesday 14:00-17:00", "Friday 16:00-20:00", "Saturday 09:00-13:00"});

        // Choose Appointment Date and Time
        LocalDateTime dateTime = getValidDateTime();
        if (dateTime == null || !TimeUtils.isWithinConsultationHours(dateTime)) {
            System.out.println("Selected time is outside consultation hours or invalid. Please choose a valid time.");
            return;
        }

        // Prompt for registration fee payment
        System.out.println("To confirm the appointment, a registration fee of LKR 500 is required.");
        System.out.print("Do you confirm payment of LKR 500 for this appointment? (yes/no): ");
        String paymentConfirmation = scanner.nextLine().trim().toLowerCase();

        if (!paymentConfirmation.equals("yes")) {
            System.out.println("Appointment booking cancelled.");
            return;
        }

        // Book the appointment if payment is confirmed
        if (appointmentService.bookAppointment(patient, dermatologist, dateTime)) {
            System.out.println("Appointment successfully booked.");
        }
    }

    private static LocalDateTime getValidDateTime() {
        System.out.print("Enter Appointment Date and Time (yyyy-MM-dd HH:mm): ");
        try {
            return LocalDateTime.parse(scanner.nextLine().trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please try again.");
            return null;
        }
    }

    private static String getValidInput(String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Input cannot be empty. Please try again.");
            }
        } while (input.isEmpty());
        return input;
    }

    // Other existing methods remain the same...

    private static void updateAppointment() {
        System.out.println("\n--- Update Appointment ---");
        String appointmentId = getValidInput("Enter Appointment ID: ");
        LocalDateTime newDateTime = getValidDateTime();
        if (newDateTime == null || !TimeUtils.isWithinConsultationHours(newDateTime)) {
            System.out.println("Selected time is outside consultation hours or invalid. Please choose a valid time.");
            return;
        }

        if (appointmentService.updateAppointment(appointmentId, newDateTime)) {
            System.out.println("Appointment updated successfully.");
        }
    }

    private static void viewAppointmentsByDate() {
        System.out.println("\n--- View Appointments by Date ---");
        System.out.print("Enter Date (yyyy-MM-dd): ");
        try {
            LocalDate date = LocalDate.parse(scanner.nextLine().trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            List<Appointment> appointments = appointmentService.viewAppointmentsByDate(date);
            if (appointments.isEmpty()) {
                System.out.println("No appointments found for the selected date.");
            } else {
                System.out.println("Appointments on " + date + ":");
                appointments.forEach(System.out::println);
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please try again.");
        }
    }

    private static void searchAppointment() {
        System.out.println("\n--- Search Appointment ---");
        String searchTerm = getValidInput("Enter Appointment ID or Patient Name: ");
        Appointment appointment = appointmentService.searchAppointment(searchTerm);
        if (appointment != null) {
            System.out.println("Appointment Details:");
            System.out.println(appointment);
        } else {
            System.out.println("No appointment found with the provided details.");
        }
    }

    private static void generateInvoice() {
        System.out.println("\n--- Generate Invoice ---");
        String appointmentId = getValidInput("Enter Appointment ID: ");
        Appointment appointment = appointmentService.searchAppointment(appointmentId);

        if (appointment == null) {
            System.out.println("Appointment not found.");
            return;
        }

        System.out.print("Enter Treatment Type (1: Acne Treatment, 2: Skin Whitening, 3: Mole Removal, 4: Laser Treatment): ");
        int treatmentChoice = getUserChoice();
        Treatment treatment = switch (treatmentChoice) {
            case 1 -> Treatment.ACNE_TREATMENT;
            case 2 -> Treatment.SKIN_WHITENING;
            case 3 -> Treatment.MOLE_REMOVAL;
            case 4 -> Treatment.LASER_TREATMENT;
            default -> null;
        };

        if (treatment == null) {
            System.out.println("Invalid treatment choice.");
            return;
        }

        // Generate and display the invoice
        billingService.generateInvoice(appointment, treatment);
    }

    private static void viewAllInvoices() {
        System.out.println("\n--- All Invoices ---");
        billingService.displayAllInvoices();
    }

    private static void exitApplication() {
        System.out.println("Exiting Aurora Skin Care Appointment System. Thank you!");
        System.exit(0);
    }
}
