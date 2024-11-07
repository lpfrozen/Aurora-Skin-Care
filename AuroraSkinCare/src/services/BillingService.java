package services;

import models.Appointment;
import models.Invoice;
import models.Treatment;

public class BillingService {
    private static final int MAX_INVOICES = 100;
    private Invoice[] invoices = new Invoice[MAX_INVOICES];
    private int invoiceCount = 0;
    private static final double TAX_RATE = 0.025;

    public double calculateTotalWithTax(Treatment treatment) {
        double total = treatment.getPrice();
        return Math.ceil(total * (1 + TAX_RATE));
    }

    public Invoice generateInvoice(Appointment appointment, Treatment treatment) {
        if (invoiceCount < MAX_INVOICES) {
            String invoiceId = "INV" + (invoiceCount + 1);
            Invoice invoice = new Invoice(invoiceId, appointment, treatment);
            invoices[invoiceCount++] = invoice;
            System.out.println("Invoice generated successfully with ID: " + invoiceId);
            return invoice;
        } else {
            System.out.println("Cannot generate invoice: Maximum invoice capacity reached.");
            return null;
        }
    }

    // New method to display all invoices
    public void displayAllInvoices() {
        if (invoiceCount == 0) {
            System.out.println("No invoices available.");
        } else {
            System.out.println("All Invoices:");
            for (int i = 0; i < invoiceCount; i++) {
                System.out.println(invoices[i].generateInvoiceDetails());
            }
        }
    }
}
