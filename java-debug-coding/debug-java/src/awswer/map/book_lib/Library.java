package awswer.map.book_lib;

public class Library {
    private String libraryCode;
    private String libraryName;

    // Constructor mặc định
    public Library() {
        this.libraryCode = "L000";
        this.libraryName = "None";
    }

    // Constructor với tham số
    public Library(String libraryCode, String libraryName) {
        this.libraryCode = libraryCode;
        this.libraryName = libraryName;
    }

    // Getter cho libraryCode
    public String getLibraryCode() {
        if(libraryCode.charAt(0) != 'L')
            return null;
        else
            return libraryCode;
    }

    // Setter cho libraryCode
    public void setLibraryCode(String libraryCode) {
        this.libraryCode = libraryCode;
    }

    // Getter cho libraryName
    public String getLibraryName() {
        return libraryName;
    }

    // Setter cho libraryName
    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    // Phương thức toString
    @Override
    public String toString() {
        return "Library{" +
                "libraryCode='" + libraryCode + '\'' +
                ", libraryName='" + libraryName + '\'' +
                '}';
    }
}