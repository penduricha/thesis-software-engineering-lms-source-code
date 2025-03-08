package awswer.map.book_lib;

public class Test {
    public static void main(String[] args) {
        //public Library(String libraryCode, String libraryName)
        Library library1 = new Library("L001","IUH");

        //public Book(String title, String author, String isbn, Library library)
        Book book1 = new Book("Conan","Gosho","N-1032",library1);
        //5đ
        //Câu 1 2đ
        System.out.println(library1);

        //Câu 2 2đ
        System.out.println(book1);

        //Câu 3, 4, 5, 6, 7, 8 (0.5đ) => 3đ
        //Câu 3
        System.out.println(library1.getLibraryCode());

        //Câu 4
        System.out.println(library1.getLibraryName());

        //Câu 5
        System.out.println(book1.getTitle());

        //Câu 6
        System.out.println(book1.getAuthor());

        //Câu 7
        System.out.println(book1.getIsbn());

        //Câu 8
        System.out.println(book1.getLibrary());

        //5đ
        //Câu 9 1đ
        Library library9 = new Library();
        System.out.println(library9);

        //Câu 10 1đ
        Book book10 = new Book();
        System.out.println(book10);

        //ràng buộc thuộc tính 2đ
        //Câu 11 1đ
        Library library11 = new Library("A000","IUH");
        System.out.println(library11.getLibraryCode());

        //Câu 12 1đ ràng buộc tên tác giả <= 10 kí tự trả về null nếu qua 10 kí tự
        Book book12 = new Book("Conan","Aoyama Yoshimasa Gosho","N-1032",library11);
        System.out.println(book12.getAuthor());

        //Update
        //Câu 13 Cập nhật thư viện của book là
        //Library library13 = new Library("L002","UEH"); 1đ
        book1.setLibrary(new Library("L002","UEH"));
        System.out.println(book1);

    }
}
