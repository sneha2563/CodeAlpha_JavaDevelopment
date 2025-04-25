package service;

import model.Book;
import util.BookCategory;

import java.util.*;

public class LibraryService {
    private Map<String, Book> books = new HashMap<>();

    public LibraryService() {
        addBook(new Book("1", "Introduction to AI", BookCategory.TECHNOLOGY));
        addBook(new Book("2", "World History", BookCategory.HISTORY));
        addBook(new Book("3", "Digital Transformation", BookCategory.TECHNOLOGY));
    }

    public void addBook(Book book) {
        books.put(book.getId(), book);
    }

    public void listBooks() {
        for (Book book : books.values()) {
            System.out.println(book);
        }
    }

    public void borrowBook(String id) {
        Book book = books.get(id);
        if (book != null && !book.isBorrowed()) {
            book.borrowBook();
            System.out.println("Book borrowed successfully.");
        } else {
            System.out.println("Book is either unavailable or doesn't exist.");
        }
    }

    public void returnBook(String id) {
        Book book = books.get(id);
        if (book != null && book.isBorrowed()) {
            book.returnBook();
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Book was not borrowed or doesn't exist.");
        }
    }

    public void searchByCategory(BookCategory category) {
        for (Book book : books.values()) {
            if (book.getCategory() == category) {
                System.out.println(book);
            }
        }
    }
}
