import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private boolean available;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.available = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return available;
    }

    public void borrow() {
        available = false;
    }

    public void returnBook() {
        available = true;
    }
}

class LibraryMember {
    private String memberId;
    private String name;

    public LibraryMember(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }
}

public class LibrarySystem {
    private List<Book> books;
    private List<LibraryMember> members;

    public LibrarySystem() {
        books = new ArrayList<>();
        members = new ArrayList<>();
    }

    public void addBook(String title, String author) {
        books.add(new Book(title, author));
    }

    public void addMember(String memberId, String name) {
        members.add(new LibraryMember(memberId, name));
    }

    public void displayBooks() {
        System.out.println("Available Books:");
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println(book.getTitle() + " by " + book.getAuthor());
            }
        }
    }

    public void borrowBook(String memberId, String title) {
        for (LibraryMember member : members) {
            if (member.getMemberId().equals(memberId)) {
                for (Book book : books) {
                    if (book.getTitle().equals(title) && book.isAvailable()) {
                        book.borrow();
                        System.out.println("Book '" + title + "' borrowed by " + member.getName());
                        return;
                    }
                }
                System.out.println("Book not found or unavailable.");
                return;
            }
        }
        System.out.println("Member not found.");
    }

    public void returnBook(String memberId, String title) {
        for (LibraryMember member : members) {
            if (member.getMemberId().equals(memberId)) {
                for (Book book : books) {
                    if (book.getTitle().equals(title) && !book.isAvailable()) {
                        book.returnBook();
                        System.out.println("Book '" + title + "' returned by " + member.getName());
                        return;
                    }
                }
                System.out.println("Book not found or already returned.");
                return;
            }
        }
        System.out.println("Member not found.");
    }

    public static void main(String[] args) {
        LibrarySystem library = new LibrarySystem();
        library.addBook("The Catcher in the Rye", "J.D. Salinger");
        library.addBook("To Kill a Mockingbird", "Harper Lee");
        library.addMember("1", "Alice");
        library.addMember("2", "Bob");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nLibrary System Menu:");
            System.out.println("1. Display Available Books");
            System.out.println("2. Borrow a Book");
            System.out.println("3. Return a Book");
            System.out.println("4. Quit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    library.displayBooks();
                    break;
                case 2:
                    System.out.print("Enter your Member ID: ");
                    String memberId = scanner.nextLine();
                    System.out.print("Enter the book title you want to borrow: ");
                    String titleToBorrow = scanner.nextLine();
                    library.borrowBook(memberId, titleToBorrow);
                    break;
                case 3:
                    System.out.print("Enter your Member ID: ");
                    memberId = scanner.nextLine();
                    System.out.print("Enter the book title you want to return: ");
                    String titleToReturn = scanner.nextLine();
                    library.returnBook(memberId, titleToReturn);
                    break;
                case 4:
                    System.out.println("Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

