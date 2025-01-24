public class Collection {
    public static void main(String[] args) {
        java.util.List<String> list = new java.util.ArrayList<>();

        // Adding elements to the list
        list.add("Hello");
        list.add("World");

        // Printing the list
        for (String item : list) {
            System.out.println(item);
        }
    }
}
