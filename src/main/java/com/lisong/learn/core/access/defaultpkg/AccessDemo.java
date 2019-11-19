package com.lisong.learn.core.access.defaultpkg;

public class AccessDemo {

    protected AccessDemo () {}

    public static AccessDemo createInstance (String... args) {
        AccessDemo ac = new AccessDemo();
        ac.name = args[0];
        ac.age = args[1];
        ac.phone = args[2];
        ac.salary = args[3];
        return ac;
    }

    public String name;
    protected String age;
    String phone;
    private String salary;

    public void sayName() { System.out.println(name);}
    protected void sayAge() { System.out.println(age);}
    void sayPhone() { System.out.println(phone);}
    private void saySalary() { System.out.println(salary);}
}

class AccessDemoTest {

    public static void main(String... args) {
        AccessDemo ac = AccessDemo.createInstance("Mary", "18", "140102456", "780000");
        System.out.println(ac.name);
        System.out.println(ac.age);
        System.out.println(ac.phone);
        //System.out.println(ac.salary);
        ac.sayName();
        ac.sayAge();
        ac.sayPhone();
        //ac.saySalary();
    }
}
