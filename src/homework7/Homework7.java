package homework7;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.lang.Integer;

class Teacher extends BaseStaff {
    private final int number;
    private final String gender;
    private final int age;
    private final String major;

    Teacher(int number, String gender, int age, String major) {
        this.number = number;
        this.gender = gender;
        this.age = age;
        this.major = major;
    }

    @Override
    public String getType() {
        return "homework7.Teacher";
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "homework7.Teacher " + number +
                " " + gender +
                " " + age +
                major;
    }
}

class Student extends BaseStaff {
    private final int number;
    private final String name;
    private final String gender;
    private final int age;
    private final int year;

    Student(int number, String name, String gender, int age, int year) {
        this.number = number;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.year = year;
    }

    @Override
    public String getType() {
        return "homework7.Student";
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "homework7.Student " + number +
                " " + name +
                " " + gender +
                " " + age +
                " " + year;
    }
}

class Log {
    private final String type;
    private final int number;

    Log(String type, int number) {
        this.type = type;
        this.number = number;
    }

    BaseStaff asBaseStaff() {
        if (type.equals("T")) {
            return new Teacher(number, "", 0, "");
        } else {
            return new Student(number, "", "", 0, 0);
        }
    }
}

class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int n = scn.nextInt();
        int m = scn.nextInt();
        HashMap<BaseStaff, Integer> hashMap = new HashMap<BaseStaff, Integer>();
        for (int i = 0; i < n; i++) {
            String type = scn.next();
            if (type.equals("homework7.Teacher")) {
                int number = scn.nextInt();
                String gender = scn.next();
                int age = scn.nextInt();
                String major = scn.next();

                hashMap.put(new Teacher(number, gender, age, major), 0);
            } else {
                int number = scn.nextInt();
                String name = scn.next();
                String gender = scn.next();
                int age = scn.nextInt();
                int year = scn.nextInt();

                hashMap.put(new Student(number, name, gender, age, year), 0);
            }
        }

        for (int i = 0;i < m;i++) {
            String type = scn.next();
            int number = scn.nextInt();
            Log log = new Log(type, number);
            BaseStaff staff = log.asBaseStaff();
            hashMap.replace(staff, hashMap.get(staff) + 1);
        }

        int maxCount = 0;
        BaseStaff best = null;
        for (Map.Entry<BaseStaff, Integer> entry : hashMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                best = entry.getKey();
            }
        }
        System.out.println(best.toString());
    }
}
