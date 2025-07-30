package studentgradingsystem;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Student {
    public String name;
    public int id;
    public long nationalId;
    public String major;
    public String gender;
    public String address;
    public double gpa;
    public ArrayList<Course> enrolledCourses;

    public Student(String name, long nationalId, String major, String gender, String address) {
        this.name = name;
        this.nationalId = nationalId;
        this.major = major;
        this.gender = gender;
        this.address = address;
        gpa = 0;
        mainFrame.studentsLastId++;
        id = mainFrame.studentsLastId;
        enrolledCourses = new ArrayList<>();
    }

    public Student() {
        this("Unknown", 0, "Unknown", "Unknown", "Unknown");
    }

    public Student(String dataLine) {
        StringTokenizer t = new StringTokenizer(dataLine, ",");
        name = t.nextToken();
        nationalId = Long.parseLong(t.nextToken());
        id = Integer.parseInt(t.nextToken());
        major = t.nextToken();
        gender = t.nextToken();
        address = t.nextToken();
        gpa = Double.parseDouble(t.nextToken());
        enrolledCourses = new ArrayList<>();

        while (t.hasMoreTokens()) {
            String courseId = t.nextToken();
            String courseGrade = t.nextToken();

            Course enrolledCourse;
            for (Course c : mainFrame.courses) {
                if (c.id.equals(courseId)) {
                    enrolledCourse = new Course(c);
                    enrolledCourse.grade = courseGrade;
                    enrolledCourses.add(enrolledCourse);
                    break;
                }
            }
        }
    }
}
