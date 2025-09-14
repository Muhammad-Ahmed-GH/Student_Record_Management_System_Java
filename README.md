# Student Grading System

A Java Swing application for managing student records, courses, enrollments, and grades.

## Features

- Add and manage student records
- Add and manage courses
- Enroll students in courses
- Assign and update grades
- Calculate GPA for students
- Filter and search students/courses
- Generate student cards

## Project Structure

```
student_record_management_system_java/
├── build.xml
├── manifest.mf
├── README.md
├── build/
│   └── classes/
│       └── studentgradingsystem/
├── files/
│   ├── courses.txt
│   └── students.txt
├── nbproject/
│   ├── build-impl.xml
│   ├── project.properties
│   └── ...
└── src/
    └── studentgradingsystem/
        ├── Course.java
        ├── Student.java
        ├── mainFrame.java
        └── ...
```

## Getting Started

### Prerequisites

- Java JDK 8 or higher
- Apache Ant (for building)
- NetBeans IDE (recommended for GUI editing)

### Build & Run

#### Using NetBeans

1. Open the project in NetBeans.
2. Build and run the project using the IDE buttons.

#### Using Command Line

1. Build the project:
    ```sh
    ant clean
    ant jar
    ```
2. Run the application:
    ```sh
    java -jar dist/StudentGradingSystem.jar
    ```

## Data Files

- `files/students.txt`: Stores student records.
- `files/courses.txt`: Stores course information.

## Main Classes

- [`studentgradingsystem.mainFrame`](src/studentgradingsystem/mainFrame.java): Main GUI window.
- [`studentgradingsystem.Student`](src/studentgradingsystem/Student.java): Student data model.
- [`studentgradingsystem.Course`](src/studentgradingsystem/Course.java): Course data model.

## Customization

- Edit `students.txt` and `courses.txt` in the `files/` directory to pre-populate data.
- Modify GUI using NetBeans Form Editor (`mainFrame.form`).

## License

This project is licensed under the [MIT License](LICENSE).

Made with ❤️ by Muhammad Ahmed