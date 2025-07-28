import java.util.*;

public class TaxiBooking {
    static ArrayList<Taxi> taxis = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static int customerIdCounter = 1;

    public static void main(String[] args) {
        System.out.println("Enter the number of Taxis:");
        int numberOfTaxis = sc.nextInt();
        initializeTaxis(numberOfTaxis);

        while (true) {
            System.out.println("\nChoose the Menu:\n1. Book Taxi\n2. Display Details\n3. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    bookTaxi();
                    break;
                case 2:
                    displayDetails();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid Input");
            }
        }
    }

    public static void initializeTaxis(int number) {
        for (int i = 1; i <= number; i++) {
            taxis.add(new Taxi(i));
        }
    }

    public static void bookTaxi() {
        int customerId = customerIdCounter++;

        System.out.print("Enter Pickup Point (A-F): ");
        char pickup = sc.next().toUpperCase().charAt(0);

        System.out.print("Enter Drop Point (A-F): ");
        char drop = sc.next().toUpperCase().charAt(0);

        System.out.print("Enter Pickup Time (1-12): ");
        int pickupTime = sc.nextInt();

        Taxi selectedTaxi = null;
        int minDistance = Integer.MAX_VALUE;

        for (Taxi t : taxis) {
            if (t.isAvailable(pickupTime)) {
                int distance = Math.abs(t.Current_point - pickup);
                if (selectedTaxi == null || distance < minDistance ||
                        (distance == minDistance && t.total_Earning < selectedTaxi.total_Earning)) {
                    selectedTaxi = t;
                    minDistance = distance;
                }
            }
        }

        if (selectedTaxi == null) {
            System.out.println("No Taxi Available");
            return;
        }

        int dropTime = pickupTime + Math.abs(drop - pickup);
        int amount = selectedTaxi.calculateEarning(pickup, drop);
        int bookingId = selectedTaxi.bookings.size() + 1;

        Booking booking = new Booking(bookingId, customerId, pickupTime, dropTime, pickup, drop, amount);
        selectedTaxi.addBooking(booking);

        System.out.println("Taxi " + selectedTaxi.id + " is Allocated.");
    }

    public static void displayDetails() {
        for (Taxi t : taxis) {
            System.out.println("Taxi ID: " + t.id + " | Total Earning: ₹" + t.total_Earning);
            for (Booking b : t.bookings) {
                System.out.println("  BookingID: " + b.bookingId + ", CustomerID: " + b.customerId +
                        ", From: " + b.from + ", To: " + b.to +
                        ", Pickup: " + b.pickupTime + ", Drop: " + b.DropTime + ", Amount: ₹" + b.amount);
            }
        }
    }
}
