import java.util.ArrayList;

public class Taxi {
    int id;
    char Current_point = 'A';
    int total_Earning = 0;
    ArrayList<Booking> bookings = new ArrayList<>();

    public Taxi(int id) {
        this.id = id;
    }

    public int calculateEarning(char from, char to) {
        int distance = Math.abs(to - from) * 15;
        int fare = 100 + ((distance > 5 ? (distance - 5) * 10 : 0));
        return fare;
    }

    public boolean isAvailable(int pickupTime) {
        if (bookings.isEmpty()) return true;
        Booking lastBooking = bookings.get(bookings.size() - 1);
        return lastBooking.DropTime <= pickupTime;
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
        total_Earning += booking.amount;
        Current_point = booking.to;
    }
}
