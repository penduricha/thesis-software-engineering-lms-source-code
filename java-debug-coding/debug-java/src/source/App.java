package source;

class Student {
    private Long id;
    private String name;
    private int age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Student(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

public class App {
    public static void main(String[] args) {
        Student student1 = new Student(1L,"Mari",10);
        Student student2 = new Student(2L,"Ben",10);
        Student student3 = new Student(3L,"Mark",10);
        System.out.println(student1);
        System.out.println(student2);
        System.out.println(student3);

    }
}
