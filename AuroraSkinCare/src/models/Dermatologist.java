package models;

public class Dermatologist {
    private String id;
    private String name;
    private String[] availableTimes;

    // Constructor
    public Dermatologist(String id, String name, String[] availableTimes) {
        this.id = id;
        this.name = name;
        this.availableTimes = availableTimes;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getAvailableTimes() {
        return availableTimes;
    }

    public void setAvailableTimes(String[] availableTimes) {
        this.availableTimes = availableTimes;
    }

    // Method to display available times as a formatted string
    public String getAvailableTimesAsString() {
        StringBuilder times = new StringBuilder();
        for (String time : availableTimes) {
            times.append(time).append("\n");
        }
        return times.toString().trim();
    }

    // Override toString() method to display dermatologist information
    @Override
    public String toString() {
        return "Dermatologist{" +
                "ID='" + id + '\'' +
                ", Name='" + name + '\'' +
                ", Available Times=\n" + getAvailableTimesAsString() +
                '}';
    }
}
