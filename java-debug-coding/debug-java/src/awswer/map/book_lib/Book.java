package awswer.map.book_lib;

public class Book {
    private String title;
    private String author;
    private String isbn;
    private Library library;

    // Constructor mặc định
    public Book() {
        this.title = "None";
        this.author = "None";
        this.isbn = "None";
        this.library = new Library();
    }

    // Constructor với tham số
    public Book(String title, String author, String isbn, Library library) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.library = library;
    }

    // Getter cho title
    public String getTitle() {
        return title;
    }

    // Setter cho title
    public void setTitle(String title) {
        this.title = title;
    }

    // Getter cho author
    public String getAuthor() {
        if(author.length() > 10)
            return null;
        else
            return author;
    }

    // Setter cho author
    public void setAuthor(String author) {
        this.author = author;
    }

    // Getter cho isbn
    public String getIsbn() {
        return isbn;
    }

    // Setter cho isbn
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    // Getter cho library
    public Library getLibrary() {
        return library;
    }

    // Setter cho library
    public void setLibrary(Library library) {
        this.library = library;
    }

    // Phương thức toString
    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", library=" + library +
                '}';
    }
}