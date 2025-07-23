package telephone;
public class RealEstatePriceCalculator {
    public static void main(String[] args) {
        double sizeSqFt = 1500;
        double pricePerSqFt = 5000;
        double registrationTaxPercent = 8;
        double gstPercent = 3;

        double basePrice = sizeSqFt * pricePerSqFt;
        double registrationTax = basePrice * registrationTaxPercent / 100;
        double gst = basePrice * gstPercent / 100;
        double totalPrice = basePrice + registrationTax + gst;

        System.out.println("Flat size (sq ft): " + sizeSqFt);
        System.out.println("Price per sq ft: ₹" + pricePerSqFt);
        System.out.println("Base price: ₹" + basePrice);
        System.out.println("Registration tax (8%): ₹" + registrationTax);
        System.out.println("GST (3%): ₹" + gst);
        System.out.println("Total registration value: ₹" + totalPrice);
    }
}






