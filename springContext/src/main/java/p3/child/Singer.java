package p3.child;

public class Singer {
    private String name;
    private int age;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Singer{" +
                "p3.name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
