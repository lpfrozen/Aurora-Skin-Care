package models;

public enum Treatment {
    ACNE_TREATMENT(2750.00),
    SKIN_WHITENING(7650.00),
    MOLE_REMOVAL(3850.00),
    LASER_TREATMENT(12500.00);

    private final double price;
    private static final double TAX_RATE = 0.025;  // 2.5% tax rate

    // Constructor
    Treatment(double price) {
        this.price = price;
    }

    // Getter for price
    public double getPrice() {
        return price;
    }

    // Method to calculate total with tax
    public double calculateTotalWithTax() {
        return Math.ceil(price * (1 + TAX_RATE)); // Rounds up to the nearest decimal
    }

    // Override toString() method for display purposes
    @Override
    public String toString() {
        return name() + " (Price: LKR " + price + ", Total with Tax: LKR " + calculateTotalWithTax() + ")";
    }
}
