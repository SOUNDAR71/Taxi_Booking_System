public class Booking {
    int bookingId, customerId, pickupTime, DropTime, amount;
    char from, to;

    public Booking(int bookingId, int customerId, int pickupTime, int dropTime, char from, char to, int amount) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.pickupTime = pickupTime;
        this.DropTime = dropTime;
        this.from = from;
        this.to = to;
        this.amount = amount;
    }
}
