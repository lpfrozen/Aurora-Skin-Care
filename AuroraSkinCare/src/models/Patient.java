package models;

public class Patient {
    private String nic;
    private String name;
    private String email;
    private String phoneNumber;

    // Constructor
    public Patient(String nic, String name, String email, String phoneNumber) {
        this.nic = nic;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Getters and Setters
    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Static method to validate NIC format
    public static boolean isValidNIC(String nic) {
        // Example validation: check if NIC is 10 or 12 characters long
        // This is a placeholder; adjust according to the actual NIC format rules
        return nic != null && (nic.matches("\\d{9}[Vv]") || nic.matches("\\d{12}"));
    }

    // Override toString() method to display patient information
    @Override
    public String toString() {
        return "Patient{" +
                "NIC='" + nic + '\'' +
                ", Name='" + name + '\'' +
                ", Email='" + email + '\'' +
                ", PhoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
