import service.AuthService;
import service.LibraryService;
import util.BookCategory;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AuthService authService = new AuthService();
        LibraryService libraryService = new LibraryService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== E-Library System ===");

        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            if (authService.login(username, password)) {
                System.out.println("Login successful.\n");
                loggedIn = true;
            } else {
                System.out.println("Invalid credentials. Try again.");
            }
        }

        int choice;
        do {
            System.out.println("\n1. List Books");
            System.out.println("2. Borrow Book");
            System.out.println("3. Return Book");
            System.out.println("4. Search by Category");
            System.out.println("5. Exit");
            System.out.print("Choose: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    libraryService.listBooks();
                    break;
                case 2:
                    System.out.print("Enter Book ID to borrow: ");
                    libraryService.borrowBook(scanner.nextLine());
                    break;
                case 3:
                    System.out.print("Enter Book ID to return: ");
                    libraryService.returnBook(scanner.nextLine());
                    break;
                case 4:
                    System.out.print("Enter category (e.g., SCIENCE, HISTORY, TECHNOLOGY): ");
                    try {
                        BookCategory category = BookCategory.valueOf(scanner.nextLine().toUpperCase());
                        libraryService.searchByCategory(category);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid category.");
                    }
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } while (choice != 5);

        scanner.close();
    }
}
