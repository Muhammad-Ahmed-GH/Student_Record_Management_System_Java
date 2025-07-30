package studentgradingsystem;

import java.util.StringTokenizer;

public class Course {
    public String name;
    public String id;
    public byte creditHours;
    public String grade;

    public Course() {
        name = id = "Unknown";
        creditHours = 0;
        grade = "Not Yet Assigned";
    }

    public Course(String name, String id, byte creditHours) {
        this.name = name;
        this.id = id;
        this.creditHours = creditHours;
        grade = "Not Yet Assigned";
    }

    public Course(String name, String id, byte creditHours, String grade) {
        this(name, id, creditHours);
        this.grade = grade;
    }

    public Course(Course course) {
        this(course.name, course.id, course.creditHours, course.grade);
    }

    public Course(String dataLine) {
        StringTokenizer t = new StringTokenizer(dataLine, ",");
        name = t.nextToken();
        id = t.nextToken();
        creditHours = Byte.parseByte(t.nextToken());
    }
}
