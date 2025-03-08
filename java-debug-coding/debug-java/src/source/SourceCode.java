package source;

public class SourceCode {
    public String getSource(){
        return
                "class Student {\n" +
                        "    private Long id;\n" +
                        "    private String name;\n" +
                        "    private int age;\n" +
                        "\n" +
                        "    public Long getId() {\n" +
                        "        return id;\n" +
                        "    }\n" +
                        "\n" +
                        "    public void setId(Long id) {\n" +
                        "        this.id = id;\n" +
                        "    }\n" +
                        "\n" +
                        "    public String getName() {\n" +
                        "        return name;\n" +
                        "    }\n" +
                        "\n" +
                        "    public void setName(String name) {\n" +
                        "        this.name = name;\n" +
                        "    }\n" +
                        "\n" +
                        "    public int getAge() {\n" +
                        "        return age;\n" +
                        "    }\n" +
                        "\n" +
                        "    public void setAge(int age) {\n" +
                        "        this.age = age;\n" +
                        "    }\n" +
                        "\n" +
                        "    public Student(Long id, String name, int age) {\n" +
                        "        this.id = id;\n" +
                        "        this.name = name;\n" +
                        "        this.age = age;\n" +
                        "    }\n" +
                        "\n" +
                        "    @Override\n" +
                        "    public String toString() {\n" +
                        "        return \"Student{\" +\n" +
                        "                \"id=\" + id +\n" +
                        "                \", name='\" + name + '\\'' +\n" +
                        "                \", age=\" + age +\n" +
                        "                '}';\n" +
                        "    }\n" +
                        "}\n" +
                        "\n" +
                        "public class App {\n" +
                        "    public static void main(String[] args) {\n" +
                        "        Student student1 = new Student(1L,\"Mari\",10);\n" +
                        "        Student student2 = new Student(2L,\"Ben\",10);\n" +
                        "        Student student3 = new Student(3L,\"Mark\",10);\n" +
                        "        System.out.println(student1);\n" +
                        "        System.out.println(student2);\n" +
                        "        System.out.println(student3);\n" +
                        "\n" +
                        "    }\n" +
                        "}";
    }
}
