class Person {
    private String name;
    private int age;

    // Constructor
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Getter cho tên
    public String getName() {
        return name;
    }

    // Getter cho tuổi
    public int getAge() {
        return age;
    }

    // Phương thức để in thông tin
    public void displayInfo() {
        System.out.println("Name: " + name + ", Age: " + age);
    }
}

public class DemoPerson {
    public static void main(String[] args) {
        // Tạo đối tượng Person
        Person person1 = new Person("Alice", 30);
        Person person2 = new Person("Bob", 25);

        // Hiển thị thông tin
        person1.displayInfo();
        person2.displayInfo();
    }
}
