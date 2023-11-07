import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private String studentID;
    private String name;
    private String email;

    public Student(String studentID, String name, String email) {
        this.studentID = studentID;
        this.name = name;
        this.email = email;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }
}

class Room {
    private String roomNumber;
    private String type;
    private boolean isAvailable;

    public Room(String roomNumber, String type) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.isAvailable = true;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getType() {
        return type;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}

class Booking {
    private static int bookingIDCounter = 1000;
    private int bookingID;
    private Student student;
    private Room room;
    private String status;

    public Booking(Student student, Room room) {
        this.bookingID = bookingIDCounter++;
        this.student = student;
        this.room = room;
        this.status = "Pending";
    }

    public int getBookingID() {
        return bookingID;
    }

    public Student getStudent() {
        return student;
    }

    public Room getRoom() {
        return room;
    }

    public String getStatus() {
        return status;
    }

    public void confirmBooking() {
        status = "Confirmed";
        room.setAvailable(false);
    }
}

class Hostel {
    private String name;
    private ArrayList<Room> rooms;
    private ArrayList<Booking> bookings;

    public Hostel(String name) {
        this.name = name;
        this.rooms = new ArrayList<>();
        this.bookings = new ArrayList<>();
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public ArrayList<Room> getAvailableRooms() {
        ArrayList<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable()) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public Booking bookRoom(Student student, Room room) {
        Booking booking = new Booking(student, room);
        bookings.add(booking);
        return booking;
    }

    public void confirmBooking(int bookingID) {
        for (Booking booking : bookings) {
            if (booking.getBookingID() == bookingID && booking.getStatus().equals("Pending")) {
                booking.confirmBooking();
                System.out.println("Booking confirmed!");
                return;
            }
        }
        System.out.println("Invalid booking ID or booking is already confirmed.");
    }

    public void displayAvailableRooms() {
        ArrayList<Room> availableRooms = getAvailableRooms();
        if (availableRooms.isEmpty()) {
            System.out.println("No available rooms.");
        } else {
            System.out.println("Available Rooms:");
            for (Room room : availableRooms) {
                System.out.println("Room Number: " + room.getRoomNumber() + ", Type: " + room.getType());
            }
        }
    }

    public void displayBookings() {
        System.out.println("Bookings:");
        for (Booking booking : bookings) {
            System.out.println("Booking ID: " + booking.getBookingID() +
                    ", Student: " + booking.getStudent().getName() +
                    ", Room Number: " + booking.getRoom().getRoomNumber() +
                    ", Status: " + booking.getStatus());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hostel hostel = new Hostel("University Hostel");

        while (true) {
            System.out.println("1. Student Registration");
            System.out.println("2. Student Login");
            System.out.println("3. Administrator Login");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    String studentID = scanner.nextLine();
                    System.out.print("Enter Student Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Student Email: ");
                    String email = scanner.nextLine();
                    Student student = new Student(studentID, name, email);
                    System.out.println("Student Registration Successful!\n");
                    break;
                case 2:
                    System.out.print("Enter Student ID: ");
                    String loginID = scanner.nextLine();
                    // Implement student functionality here
                    break;
                case 3:
                    System.out.print("Enter Administrator Password: ");
                    String password = scanner.nextLine();
                    if (password.equals("admin123")) {
                        administratorMenu(hostel, scanner);
                    } else {
                        System.out.println("Invalid Password. Access Denied.\n");
                    }
                    break;
                case 4:
                    System.out.println("Thank you. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid Choice. Please try again.\n");
            }
        }
    }

    private static void administratorMenu(Hostel hostel, Scanner scanner) {
        while (true) {
            System.out.println("Administrator Menu:");
            System.out.println("1. Add Room");
            System.out.println("2. Confirm Booking");
            System.out.println("3. View Available Rooms");
            System.out.println("4. View Bookings");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Room Number: ");
                    String roomNumber = scanner.nextLine();
                    System.out.print("Enter Room Type: ");
                    String roomType = scanner.nextLine();
                    Room room = new Room(roomNumber, roomType);
                    hostel.addRoom(room);
                    System.out.println("Room Added Successfully!\n");
                    break;
                case 2:
                    System.out.print("Enter Booking ID to Confirm: ");
                    int bookingID = scanner.nextInt();
                    hostel.confirmBooking(bookingID);
                    break;
                case 3:
                    hostel.displayAvailableRooms();
                    System.out.println();
                    break;
                case 4:
                    hostel.displayBookings();
                    System.out.println();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid Choice. Please try again.\n");
            }
        }
    }
}
