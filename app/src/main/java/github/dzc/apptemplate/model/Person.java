package github.dzc.apptemplate.model;

/**
 * Created by dzc on 15/11/7.
 */
public class Person {
    String name;

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
