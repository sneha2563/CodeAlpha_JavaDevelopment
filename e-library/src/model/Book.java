package model;

import util.BookCategory;

public class Book {
    private String id;
    private String title;
    private BookCategory category;
    private boolean isBorrowed;

    public Book(String id, String title, BookCategory category) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.isBorrowed = false;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public BookCategory getCategory() { return category; }
    public boolean isBorrowed() { return isBorrowed; }

    public void borrowBook() { this.isBorrowed = true; }
    public void returnBook() { this.isBorrowed = false; }

    @Override
    public String toString() {
        return title + " [" + category + "] " + (isBorrowed ? "(Borrowed)" : "(Available)");
    }
}
