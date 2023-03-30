package org.example;

public class Book extends Product {

    private String author;
    private String isbn;

    public Book(String name, double price, int quantity, String comment, String author, String isbn) {
        super(name, price, quantity, comment);
        this.author = author;
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String toString() {
        return super.toString() + "\nAuthor: " + author + "\nISBN: " + isbn;
    }
}
