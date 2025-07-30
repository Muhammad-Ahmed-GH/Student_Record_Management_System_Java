package studentgradingsystem;

import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class mainFrame extends javax.swing.JFrame {
    static ArrayList<Student> students;
    static ArrayList<Course> courses;
    static int studentsLastId = 0;

    public mainFrame() {
        students = new ArrayList<>();
        courses = new ArrayList<>();
        loadCourses();
        loadStudents();
        initStudentsLastId();
        initComponents();
        UIManager.put("OptionPane.messageFont", new Font("consolas", Font.PLAIN, 14));
        UIManager.put("OptionPane.buttonFont", new Font("consolas", Font.PLAIN, 14));
    }

    private void initStudentsLastId() {
        for (Student student : students) {
            if (student.id > studentsLastId)
                studentsLastId = student.id;
        }
        if (studentsLastId == 0)
            studentsLastId = 1233;
    }

    private void loadStudents() {
        try {
            Scanner scanner = new Scanner(new File("files/students.txt"));
            while (scanner.hasNextLine()) {
                students.add(new Student(scanner.nextLine()));
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Error Loading Students File");
            System.exit(0);
        }
    }

    private void loadCourses() {
        try {
            Scanner scanner = new Scanner(new File("files/courses.txt"));
            while (scanner.hasNextLine()) {
                courses.add(new Course(scanner.nextLine()));
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Error Loading Courses File");
            System.exit(0);
        }
    }

    private void saveStudentsToFile() {
        try {
            PrintWriter pWriter = new PrintWriter(new File("files/students.txt"));
            for (Student student : students) {
                String line = student.name + "," + student.nationalId + "," + student.id + "," + student.major + "," +
                        student.gender + "," + student.address + "," + student.gpa;

                for (Course enrolledCourse : student.enrolledCourses)
                    line += ("," + enrolledCourse.id + "," + enrolledCourse.grade);

                line += "\n";
                pWriter.write(line);
            }
            pWriter.close();
        } catch (Exception e) {
            System.out.println("Error Saving Students to File");
            System.exit(0);
        }
    }

    private void saveCoursesToFile() {
        try {
            PrintWriter pWriter = new PrintWriter(new File("files/courses.txt"));
            for (Course course : courses) {
                pWriter.write(course.name + "," + course.id + "," + course.creditHours + "\n");
            }
            pWriter.close();
        } catch (Exception e) {
            System.out.println("Error Saving Students to File");
            System.exit(0);
        }
    }

    private boolean doesStudentExist(long studentNationalId) {
        for (Student student : students)
            if (student.nationalId == studentNationalId)
                return true;
        return false;
    }

    private boolean doesCourseExist(String courseId) {
        for (Course course : courses)
            if (course.id.equals(courseId))
                return true;
        return false;
    }

    private void cleanAddStudentPanel() {
        studentNameField.setText("");
        nationalIdField.setText("");
        maleFemaleGroup.clearSelection();
        majorComboBox.setSelectedIndex(0);
        addressComboBox.setSelectedIndex(0);
    }

    private void cleanAddCoursePanel() {
        courseNameField.setText("");
        courseIdField.setText("");
        courseCreditHoursField.setText("");
    }

    private void cleanEnrollInCoursePanel() {
        studentIdField.setText("");
        courseIdField2.setText("");
    }

    private void cleanAssignGradePanel() {
        studentIdField2.setText("");
        courseIdField3.setText("");
        courseGradeComboBox.setSelectedIndex(0);
    }

    private void cleanFindCoursePanel() {
        idField1.setText("");
        jTextArea3.setText("");
    }

    private double gradeToPoints(String grade) {
        switch (grade) {
            case "A+":
            case "A":
                return 4.0;
            case "B":
                return 3.0;
            case "C":
                return 2.0;
            case "D":
                return 1.0;
            case "F":
                return 0.0;
        }
        return -1;
    }

    private double calculateGpa(Student student) {
        double pointsSum = 0;
        int creditsSum = 0;
        for (Course course : student.enrolledCourses) {
            if (gradeToPoints(course.grade) >= 0) {
                pointsSum += (gradeToPoints(course.grade) * course.creditHours);
                creditsSum += course.creditHours;
            }
        }
        if (creditsSum == 0)
            return 0;
        return pointsSum / creditsSum;
    }

    String spaces(int number) {
        String str = "";
        for (int i = 0; i < number; i++)
            str += " ";
        return str;
    }

    private void fillCard(Student student) {
        String card = "";
        card += " ___________________________________________________________\n";
        card += "|                     Student Info Card                     |\n";
        card += "|___________________________________________________________|\n";
        card += "| Name       | " + student.name + spaces(45 - student.name.length()) + "|\n";
        card += "| Id         | " + student.id + spaces(45 - String.valueOf(student.id).length()) + "|\n";
        card += "| National id| " + student.nationalId + spaces(45 - String.valueOf(student.nationalId).length())
                + "|\n";
        card += "| Gender     | " + student.gender + spaces(45 - student.gender.length()) + "|\n";
        card += "| Major      | " + student.major + spaces(45 - student.major.length()) + "|\n";
        card += "| Address    | " + student.address + spaces(45 - student.address.length()) + "|\n";
        card += "| GPA        | " + student.gpa + spaces(45 - String.valueOf(student.gpa).length()) + "|\n";
        card += "|____________|______________________________________________|\n";
        card += "| Courses                                                   |\n";
        if (student.enrolledCourses.isEmpty())
            card += "| No Enrolled Courses                                       |\n";
        else {
            for (Course course : student.enrolledCourses)
                card += "| Course Id: " + course.id + ", grade: " + course.grade
                        + spaces(38 - course.id.length() - course.grade.length()) + "|\n";
        }
        card += "|___________________________________________________________|";
        jTextArea1.setText(card);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        maleFemaleGroup = new javax.swing.ButtonGroup();
        filterGroup = new javax.swing.ButtonGroup();
        mainPanel = new javax.swing.JPanel();
        mainMenuPanel = new javax.swing.JPanel();
        mainMenuTitle = new javax.swing.JLabel();
        addStudentButton = new javax.swing.JButton();
        addCourseButton = new javax.swing.JButton();
        enrollInCourseButton = new javax.swing.JButton();
        assignGradeButton2 = new javax.swing.JButton();
        calculateGPAButton = new javax.swing.JButton();
        generateCardButton = new javax.swing.JButton();
        filterButton = new javax.swing.JButton();
        findCourseButton = new javax.swing.JButton();
        addStudentPanel = new javax.swing.JPanel();
        addStudentTitle = new javax.swing.JLabel();
        pleaseLabel1 = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        nationalIdLabel = new javax.swing.JLabel();
        majorLabel = new javax.swing.JLabel();
        genderLabel = new javax.swing.JLabel();
        addressLabel = new javax.swing.JLabel();
        studentNameField = new javax.swing.JTextField();
        nationalIdField = new javax.swing.JTextField();
        majorComboBox = new javax.swing.JComboBox<>();
        maleRadioButton = new javax.swing.JRadioButton();
        femaleRadioButton = new javax.swing.JRadioButton();
        addressComboBox = new javax.swing.JComboBox<>();
        continueButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        addCoursePanel = new javax.swing.JPanel();
        addCourseTitle = new javax.swing.JLabel();
        pleaseLabel2 = new javax.swing.JLabel();
        courseNameLabel = new javax.swing.JLabel();
        courseIdLabel = new javax.swing.JLabel();
        creditHoursLabel = new javax.swing.JLabel();
        courseNameField = new javax.swing.JTextField();
        courseIdField = new javax.swing.JTextField();
        courseCreditHoursField = new javax.swing.JTextField();
        continueButton2 = new javax.swing.JButton();
        BackButton2 = new javax.swing.JButton();
        enrollInCoursePanel = new javax.swing.JPanel();
        enrolLInCourseTitle = new javax.swing.JLabel();
        pleaseLabel3 = new javax.swing.JLabel();
        studentIdLabel = new javax.swing.JLabel();
        courseIdLabel2 = new javax.swing.JLabel();
        studentIdField = new javax.swing.JTextField();
        courseIdField2 = new javax.swing.JTextField();
        continueButton3 = new javax.swing.JButton();
        backButton3 = new javax.swing.JButton();
        assignGradePanel = new javax.swing.JPanel();
        assignGradeTitle = new javax.swing.JLabel();
        pleaseLabel4 = new javax.swing.JLabel();
        studentIdLabel2 = new javax.swing.JLabel();
        courseIdLLabel3 = new javax.swing.JLabel();
        gradeLabel = new javax.swing.JLabel();
        studentIdField2 = new javax.swing.JTextField();
        courseIdField3 = new javax.swing.JTextField();
        courseGradeComboBox = new javax.swing.JComboBox<>();
        continueButton4 = new javax.swing.JButton();
        backButton4 = new javax.swing.JButton();
        calculateGPAPanel = new javax.swing.JPanel();
        gpaCalcTitle = new javax.swing.JLabel();
        studentIdLabel3 = new javax.swing.JLabel();
        studentIdField3 = new javax.swing.JTextField();
        continueButton5 = new javax.swing.JButton();
        backButton5 = new javax.swing.JButton();
        generateCardPanel = new javax.swing.JPanel();
        cardGeneratorTitle = new javax.swing.JLabel();
        studentIdLabel4 = new javax.swing.JLabel();
        studentIdField4 = new javax.swing.JTextField();
        scrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        continueButton6 = new javax.swing.JButton();
        backButton6 = new javax.swing.JButton();
        filterPanel = new javax.swing.JPanel();
        filterByTitle = new javax.swing.JLabel();
        filterLabel = new javax.swing.JLabel();
        idLabel = new javax.swing.JLabel();
        idField = new javax.swing.JTextField();
        radioButton1 = new javax.swing.JRadioButton();
        radioButton2 = new javax.swing.JRadioButton();
        continueButton7 = new javax.swing.JButton();
        backButton7 = new javax.swing.JButton();
        scrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        findCoursePanel = new javax.swing.JPanel();
        findCourseTitle = new javax.swing.JLabel();
        idLabel1 = new javax.swing.JLabel();
        idField1 = new javax.swing.JTextField();
        continueButton8 = new javax.swing.JButton();
        backButton8 = new javax.swing.JButton();
        scrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Student Grading System");
        setSize(new java.awt.Dimension(753, 479));
        getContentPane().setLayout(new java.awt.CardLayout());

        mainPanel.setLayout(new java.awt.CardLayout());

        mainMenuPanel.setBackground(new java.awt.Color(0, 153, 153));

        mainMenuTitle.setFont(new java.awt.Font("Consolas", 1, 33)); // NOI18N
        mainMenuTitle.setForeground(new java.awt.Color(255, 255, 255));
        mainMenuTitle.setText("University Management System");

        addStudentButton.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        addStudentButton.setText("Add Student");
        addStudentButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addStudentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStudentButtonActionPerformed(evt);
            }
        });

        addCourseButton.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        addCourseButton.setText("Add Course");
        addCourseButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addCourseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCourseButtonActionPerformed(evt);
            }
        });

        enrollInCourseButton.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        enrollInCourseButton.setText("Enroll in Course");
        enrollInCourseButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        enrollInCourseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enrollInCourseButtonActionPerformed(evt);
            }
        });

        assignGradeButton2.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        assignGradeButton2.setText("Assign Grade");
        assignGradeButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        assignGradeButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assignGradeButton2ActionPerformed(evt);
            }
        });

        calculateGPAButton.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        calculateGPAButton.setText("Calculate GPA");
        calculateGPAButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        calculateGPAButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calculateGPAButtonActionPerformed(evt);
            }
        });

        generateCardButton.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        generateCardButton.setText("Generate Card");
        generateCardButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        generateCardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateCardButtonActionPerformed(evt);
            }
        });

        filterButton.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        filterButton.setText("Filter by");
        filterButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        filterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterButtonActionPerformed(evt);
            }
        });

        findCourseButton.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        findCourseButton.setText("Find Course");
        findCourseButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        findCourseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findCourseButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainMenuPanelLayout = new javax.swing.GroupLayout(mainMenuPanel);
        mainMenuPanel.setLayout(mainMenuPanelLayout);
        mainMenuPanelLayout.setHorizontalGroup(
                mainMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(mainMenuPanelLayout.createSequentialGroup()
                                .addGap(118, 118, 118)
                                .addGroup(mainMenuPanelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(mainMenuTitle)
                                        .addGroup(mainMenuPanelLayout.createSequentialGroup()
                                                .addGap(9, 9, 9)
                                                .addGroup(mainMenuPanelLayout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(addStudentButton,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 214,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(enrollInCourseButton,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 214,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(calculateGPAButton,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 214,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(filterButton,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 214,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(89, 89, 89)
                                                .addGroup(mainMenuPanelLayout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(generateCardButton,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 214,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(addCourseButton,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 214,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(assignGradeButton2,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 214,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(findCourseButton,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 214,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(101, Short.MAX_VALUE)));
        mainMenuPanelLayout.setVerticalGroup(
                mainMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(mainMenuPanelLayout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(mainMenuTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 65,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56)
                                .addGroup(mainMenuPanelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(addStudentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(addCourseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(31, 31, 31)
                                .addGroup(mainMenuPanelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(assignGradeButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 45,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(enrollInCourseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(33, 33, 33)
                                .addGroup(mainMenuPanelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(calculateGPAButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(generateCardButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addGroup(mainMenuPanelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(filterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(findCourseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(85, Short.MAX_VALUE)));

        mainPanel.add(mainMenuPanel, "card3");

        addStudentPanel.setBackground(new java.awt.Color(0, 153, 153));

        addStudentTitle.setFont(new java.awt.Font("Consolas", 1, 33)); // NOI18N
        addStudentTitle.setForeground(new java.awt.Color(255, 255, 255));
        addStudentTitle.setText("Adding a New Student...");

        pleaseLabel1.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        pleaseLabel1.setForeground(new java.awt.Color(255, 255, 255));
        pleaseLabel1.setText("Please Fill the Student's Data");

        nameLabel.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        nameLabel.setForeground(new java.awt.Color(255, 255, 255));
        nameLabel.setText("Name");

        nationalIdLabel.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        nationalIdLabel.setForeground(new java.awt.Color(255, 255, 255));
        nationalIdLabel.setText("National ID");

        majorLabel.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        majorLabel.setForeground(new java.awt.Color(255, 255, 255));
        majorLabel.setText("Major");

        genderLabel.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        genderLabel.setForeground(new java.awt.Color(255, 255, 255));
        genderLabel.setText("Gender");

        addressLabel.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        addressLabel.setForeground(new java.awt.Color(255, 255, 255));
        addressLabel.setText("Address");

        studentNameField.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        studentNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentNameFieldActionPerformed(evt);
            }
        });

        nationalIdField.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N

        majorComboBox.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        majorComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CS", "AI", "BMD" }));
        majorComboBox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        maleFemaleGroup.add(maleRadioButton);
        maleRadioButton.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        maleRadioButton.setForeground(new java.awt.Color(255, 255, 255));
        maleRadioButton.setText("Male");
        maleRadioButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        maleFemaleGroup.add(femaleRadioButton);
        femaleRadioButton.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        femaleRadioButton.setForeground(new java.awt.Color(255, 255, 255));
        femaleRadioButton.setText("Female");
        femaleRadioButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        addressComboBox.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        addressComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cairo", "Alexandria", "Giza",
                "Luxor", "Aswan", "Qena", "Sohag", "Asyut", "Minya", "Beni Suef", "Faiyum", "Ismailia", "Port Said",
                "Suez", "Damietta", "Dakahlia", "Kafr El Sheikh", "Gharbia", "Monufia", "Beheira", "Sharqia",
                "Qalyubia", "Red Sea", "South Sinai", "North Sinai", "Matrouh", "New Valley" }));
        addressComboBox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        continueButton.setFont(new java.awt.Font("Consolas", 0, 15)); // NOI18N
        continueButton.setText("Continue");
        continueButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        continueButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                continueButtonActionPerformed(evt);
            }
        });

        backButton.setFont(new java.awt.Font("Consolas", 0, 15)); // NOI18N
        backButton.setText("Back");
        backButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout addStudentPanelLayout = new javax.swing.GroupLayout(addStudentPanel);
        addStudentPanel.setLayout(addStudentPanelLayout);
        addStudentPanelLayout.setHorizontalGroup(
                addStudentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(addStudentPanelLayout.createSequentialGroup()
                                .addGroup(addStudentPanelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(addStudentPanelLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(addStudentPanelLayout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                false)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                addStudentPanelLayout.createSequentialGroup()
                                                                        .addComponent(majorLabel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                187,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(majorComboBox, 0,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                addStudentPanelLayout.createSequentialGroup()
                                                                        .addComponent(nationalIdLabel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                187,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(nationalIdField))
                                                        .addGroup(addStudentPanelLayout.createSequentialGroup()
                                                                .addComponent(nameLabel,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 187,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(studentNameField,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 233,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGroup(addStudentPanelLayout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                false)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                addStudentPanelLayout.createSequentialGroup()
                                                                        .addComponent(continueButton,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                187,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                53, Short.MAX_VALUE)
                                                                        .addComponent(backButton,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                187,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                addStudentPanelLayout.createSequentialGroup()
                                                                        .addComponent(addressLabel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                187,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(addressComboBox, 0,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                addStudentPanelLayout.createSequentialGroup()
                                                                        .addComponent(genderLabel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                187,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(maleRadioButton,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                111,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(femaleRadioButton,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                105,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(addStudentPanelLayout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(addStudentPanelLayout.createSequentialGroup()
                                                        .addGap(144, 144, 144)
                                                        .addComponent(addStudentTitle))
                                                .addGroup(addStudentPanelLayout.createSequentialGroup()
                                                        .addGap(181, 181, 181)
                                                        .addComponent(pleaseLabel1))))
                                .addContainerGap(178, Short.MAX_VALUE)));
        addStudentPanelLayout.setVerticalGroup(
                addStudentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(addStudentPanelLayout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(addStudentTitle)
                                .addGap(18, 18, 18)
                                .addComponent(pleaseLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addGroup(addStudentPanelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(addStudentPanelLayout.createSequentialGroup()
                                                .addGap(4, 4, 4)
                                                .addComponent(studentNameField, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        35, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(addStudentPanelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(nationalIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(addStudentPanelLayout.createSequentialGroup()
                                                .addGap(1, 1, 1)
                                                .addComponent(nationalIdField, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(addStudentPanelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(majorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(addStudentPanelLayout.createSequentialGroup()
                                                .addGap(4, 4, 4)
                                                .addComponent(majorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(addStudentPanelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(genderLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(maleRadioButton)
                                        .addComponent(femaleRadioButton))
                                .addGap(18, 18, 18)
                                .addGroup(addStudentPanelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(addressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(addressComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 37,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(addStudentPanelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(continueButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(85, Short.MAX_VALUE)));

        mainPanel.add(addStudentPanel, "card2");

        addCoursePanel.setBackground(new java.awt.Color(0, 153, 153));

        addCourseTitle.setFont(new java.awt.Font("Consolas", 1, 33)); // NOI18N
        addCourseTitle.setForeground(new java.awt.Color(255, 255, 255));
        addCourseTitle.setText("Adding a New Course...");

        pleaseLabel2.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        pleaseLabel2.setForeground(new java.awt.Color(255, 255, 255));
        pleaseLabel2.setText("Please Fill the Course Data");

        courseNameLabel.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        courseNameLabel.setForeground(new java.awt.Color(255, 255, 255));
        courseNameLabel.setText("Name");

        courseIdLabel.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        courseIdLabel.setForeground(new java.awt.Color(255, 255, 255));
        courseIdLabel.setText("ID");

        creditHoursLabel.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        creditHoursLabel.setForeground(new java.awt.Color(255, 255, 255));
        creditHoursLabel.setText("Credit Hours");

        courseNameField.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N

        courseIdField.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N

        courseCreditHoursField.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N

        continueButton2.setFont(new java.awt.Font("Consolas", 0, 15)); // NOI18N
        continueButton2.setText("Continue");
        continueButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        continueButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                continueButton2ActionPerformed(evt);
            }
        });

        BackButton2.setFont(new java.awt.Font("Consolas", 0, 15)); // NOI18N
        BackButton2.setText("Back");
        BackButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BackButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout addCoursePanelLayout = new javax.swing.GroupLayout(addCoursePanel);
        addCoursePanel.setLayout(addCoursePanelLayout);
        addCoursePanelLayout.setHorizontalGroup(
                addCoursePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(addCoursePanelLayout.createSequentialGroup()
                                .addGap(230, 230, 230)
                                .addComponent(pleaseLabel2)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addCoursePanelLayout
                                .createSequentialGroup()
                                .addContainerGap(161, Short.MAX_VALUE)
                                .addGroup(addCoursePanelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addCoursePanelLayout
                                                .createSequentialGroup()
                                                .addComponent(addCourseTitle, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        487, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(87, 87, 87))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addCoursePanelLayout
                                                .createSequentialGroup()
                                                .addGroup(addCoursePanelLayout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                false)
                                                        .addGroup(addCoursePanelLayout.createSequentialGroup()
                                                                .addComponent(continueButton2,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 187,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        Short.MAX_VALUE)
                                                                .addComponent(BackButton2,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 187,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(addCoursePanelLayout.createSequentialGroup()
                                                                .addComponent(creditHoursLabel,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 187,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(courseCreditHoursField))
                                                        .addGroup(addCoursePanelLayout.createSequentialGroup()
                                                                .addComponent(courseIdLabel,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 187,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(courseIdField))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                addCoursePanelLayout.createSequentialGroup()
                                                                        .addComponent(courseNameLabel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                187,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(courseNameField,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                233,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(158, 158, 158)))));
        addCoursePanelLayout.setVerticalGroup(
                addCoursePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(addCoursePanelLayout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(addCourseTitle)
                                .addGap(18, 18, 18)
                                .addComponent(pleaseLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addGroup(addCoursePanelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(courseNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 26,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(courseNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(addCoursePanelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(courseIdField, javax.swing.GroupLayout.PREFERRED_SIZE, 26,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(courseIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(addCoursePanelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(creditHoursLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(courseCreditHoursField, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(43, 43, 43)
                                .addGroup(addCoursePanelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(continueButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 37,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(BackButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 37,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(166, Short.MAX_VALUE)));

        mainPanel.add(addCoursePanel, "card2");

        enrollInCoursePanel.setBackground(new java.awt.Color(0, 153, 153));

        enrolLInCourseTitle.setFont(new java.awt.Font("Consolas", 1, 33)); // NOI18N
        enrolLInCourseTitle.setForeground(new java.awt.Color(255, 255, 255));
        enrolLInCourseTitle.setText("Enrolling in Course");

        pleaseLabel3.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        pleaseLabel3.setForeground(new java.awt.Color(255, 255, 255));
        pleaseLabel3.setText("Please Fill in the Blanks");

        studentIdLabel.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        studentIdLabel.setForeground(new java.awt.Color(255, 255, 255));
        studentIdLabel.setText("Student ID");

        courseIdLabel2.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        courseIdLabel2.setForeground(new java.awt.Color(255, 255, 255));
        courseIdLabel2.setText("Course ID");

        studentIdField.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N

        courseIdField2.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N

        continueButton3.setFont(new java.awt.Font("Consolas", 0, 15)); // NOI18N
        continueButton3.setText("Continue");
        continueButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        continueButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                continueButton3ActionPerformed(evt);
            }
        });

        backButton3.setFont(new java.awt.Font("Consolas", 0, 15)); // NOI18N
        backButton3.setText("Back");
        backButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout enrollInCoursePanelLayout = new javax.swing.GroupLayout(enrollInCoursePanel);
        enrollInCoursePanel.setLayout(enrollInCoursePanelLayout);
        enrollInCoursePanelLayout.setHorizontalGroup(
                enrollInCoursePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, enrollInCoursePanelLayout
                                .createSequentialGroup()
                                .addGap(0, 160, Short.MAX_VALUE)
                                .addGroup(enrollInCoursePanelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                enrollInCoursePanelLayout.createSequentialGroup()
                                                        .addComponent(enrolLInCourseTitle)
                                                        .addGap(195, 195, 195))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, enrollInCoursePanelLayout
                                                .createSequentialGroup()
                                                .addGroup(enrollInCoursePanelLayout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                false)
                                                        .addGroup(enrollInCoursePanelLayout.createSequentialGroup()
                                                                .addComponent(continueButton3,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 187,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        Short.MAX_VALUE)
                                                                .addComponent(backButton3,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 187,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(enrollInCoursePanelLayout.createSequentialGroup()
                                                                .addComponent(courseIdLabel2,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 187,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(courseIdField2))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                enrollInCoursePanelLayout.createSequentialGroup()
                                                                        .addComponent(studentIdLabel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                187,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(studentIdField,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                233,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(159, 159, 159))))
                        .addGroup(enrollInCoursePanelLayout.createSequentialGroup()
                                .addGap(221, 221, 221)
                                .addComponent(pleaseLabel3)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        enrollInCoursePanelLayout.setVerticalGroup(
                enrollInCoursePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(enrollInCoursePanelLayout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addComponent(enrolLInCourseTitle)
                                .addGap(18, 18, 18)
                                .addComponent(pleaseLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addGroup(enrollInCoursePanelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(studentIdField, javax.swing.GroupLayout.PREFERRED_SIZE, 26,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(studentIdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(enrollInCoursePanelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(courseIdField2, javax.swing.GroupLayout.PREFERRED_SIZE, 26,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(courseIdLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(36, 36, 36)
                                .addGroup(enrollInCoursePanelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(continueButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 37,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(backButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 37,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(185, Short.MAX_VALUE)));

        mainPanel.add(enrollInCoursePanel, "card5");

        assignGradePanel.setBackground(new java.awt.Color(0, 153, 153));

        assignGradeTitle.setFont(new java.awt.Font("Consolas", 1, 33)); // NOI18N
        assignGradeTitle.setForeground(new java.awt.Color(255, 255, 255));
        assignGradeTitle.setText("Assigning Grade...");

        pleaseLabel4.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        pleaseLabel4.setForeground(new java.awt.Color(255, 255, 255));
        pleaseLabel4.setText("Please Fill in the blanks");

        studentIdLabel2.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        studentIdLabel2.setForeground(new java.awt.Color(255, 255, 255));
        studentIdLabel2.setText("Student ID");

        courseIdLLabel3.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        courseIdLLabel3.setForeground(new java.awt.Color(255, 255, 255));
        courseIdLLabel3.setText("Course ID");

        gradeLabel.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        gradeLabel.setForeground(new java.awt.Color(255, 255, 255));
        gradeLabel.setText("Grade");

        studentIdField2.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N

        courseIdField3.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N

        courseGradeComboBox.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        courseGradeComboBox
                .setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A+", "A", "B", "C", "D", "F" }));
        courseGradeComboBox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        continueButton4.setFont(new java.awt.Font("Consolas", 0, 15)); // NOI18N
        continueButton4.setText("Continue");
        continueButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        continueButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                continueButton4ActionPerformed(evt);
            }
        });

        backButton4.setFont(new java.awt.Font("Consolas", 0, 15)); // NOI18N
        backButton4.setText("Back");
        backButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout assignGradePanelLayout = new javax.swing.GroupLayout(assignGradePanel);
        assignGradePanel.setLayout(assignGradePanelLayout);
        assignGradePanelLayout.setHorizontalGroup(
                assignGradePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, assignGradePanelLayout
                                .createSequentialGroup()
                                .addGap(0, 160, Short.MAX_VALUE)
                                .addGroup(assignGradePanelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(assignGradePanelLayout.createSequentialGroup()
                                                .addComponent(continueButton4, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, assignGradePanelLayout
                                                .createSequentialGroup()
                                                .addGroup(assignGradePanelLayout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                false)
                                                        .addComponent(backButton4,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 187,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                assignGradePanelLayout.createSequentialGroup()
                                                                        .addComponent(gradeLabel,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                187,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(courseGradeComboBox, 0,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                assignGradePanelLayout.createSequentialGroup()
                                                                        .addComponent(courseIdLLabel3,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                187,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addComponent(courseIdField3))
                                                        .addGroup(assignGradePanelLayout.createSequentialGroup()
                                                                .addComponent(studentIdLabel2,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 187,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(studentIdField2,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 233,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(159, 159, 159))))
                        .addGroup(assignGradePanelLayout.createSequentialGroup()
                                .addGap(203, 203, 203)
                                .addGroup(assignGradePanelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(assignGradeTitle)
                                        .addGroup(assignGradePanelLayout.createSequentialGroup()
                                                .addGap(21, 21, 21)
                                                .addComponent(pleaseLabel4)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        assignGradePanelLayout.setVerticalGroup(
                assignGradePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(assignGradePanelLayout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(assignGradeTitle)
                                .addGap(18, 18, 18)
                                .addComponent(pleaseLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49)
                                .addGroup(assignGradePanelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(studentIdField2, javax.swing.GroupLayout.PREFERRED_SIZE, 26,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(studentIdLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(assignGradePanelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(courseIdField3, javax.swing.GroupLayout.PREFERRED_SIZE, 26,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(courseIdLLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(assignGradePanelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(gradeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(courseGradeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35)
                                .addGroup(assignGradePanelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(backButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 37,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(continueButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 37,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(138, Short.MAX_VALUE)));

        mainPanel.add(assignGradePanel, "card5");

        calculateGPAPanel.setBackground(new java.awt.Color(0, 153, 153));

        gpaCalcTitle.setFont(new java.awt.Font("Consolas", 1, 33)); // NOI18N
        gpaCalcTitle.setForeground(new java.awt.Color(255, 255, 255));
        gpaCalcTitle.setText("GPA Calculator");

        studentIdLabel3.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        studentIdLabel3.setForeground(new java.awt.Color(255, 255, 255));
        studentIdLabel3.setText("Student ID");

        studentIdField3.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N

        continueButton5.setFont(new java.awt.Font("Consolas", 0, 15)); // NOI18N
        continueButton5.setText("Continue");
        continueButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        continueButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                continueButton5ActionPerformed(evt);
            }
        });

        backButton5.setFont(new java.awt.Font("Consolas", 0, 15)); // NOI18N
        backButton5.setText("Back");
        backButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout calculateGPAPanelLayout = new javax.swing.GroupLayout(calculateGPAPanel);
        calculateGPAPanel.setLayout(calculateGPAPanelLayout);
        calculateGPAPanelLayout.setHorizontalGroup(
                calculateGPAPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(calculateGPAPanelLayout.createSequentialGroup()
                                .addGap(230, 230, 230)
                                .addComponent(gpaCalcTitle)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, calculateGPAPanelLayout
                                .createSequentialGroup()
                                .addGap(0, 160, Short.MAX_VALUE)
                                .addGroup(calculateGPAPanelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(calculateGPAPanelLayout.createSequentialGroup()
                                                .addComponent(continueButton5, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(backButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 187,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(calculateGPAPanelLayout.createSequentialGroup()
                                                .addComponent(studentIdLabel3, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(studentIdField3, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        233, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(159, 159, 159)));
        calculateGPAPanelLayout.setVerticalGroup(
                calculateGPAPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(calculateGPAPanelLayout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addComponent(gpaCalcTitle)
                                .addGap(25, 25, 25)
                                .addGroup(calculateGPAPanelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(studentIdLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(studentIdField3, javax.swing.GroupLayout.PREFERRED_SIZE, 26,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(70, 70, 70)
                                .addGroup(calculateGPAPanelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(continueButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 37,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(backButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 37,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(256, Short.MAX_VALUE)));

        mainPanel.add(calculateGPAPanel, "card5");

        generateCardPanel.setBackground(new java.awt.Color(0, 153, 153));

        cardGeneratorTitle.setFont(new java.awt.Font("Consolas", 1, 33)); // NOI18N
        cardGeneratorTitle.setForeground(new java.awt.Color(255, 255, 255));
        cardGeneratorTitle.setText("Card Generator");

        studentIdLabel4.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        studentIdLabel4.setForeground(new java.awt.Color(255, 255, 255));
        studentIdLabel4.setText("Student ID");

        studentIdField4.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N

        scrollPane1.setBorder(null);
        scrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(200, 255, 227));
        jTextArea1.setColumns(62);
        jTextArea1.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        scrollPane1.setViewportView(jTextArea1);

        continueButton6.setFont(new java.awt.Font("Consolas", 0, 15)); // NOI18N
        continueButton6.setText("Continue");
        continueButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        continueButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                continueButton6ActionPerformed(evt);
            }
        });

        backButton6.setFont(new java.awt.Font("Consolas", 0, 15)); // NOI18N
        backButton6.setText("Back");
        backButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout generateCardPanelLayout = new javax.swing.GroupLayout(generateCardPanel);
        generateCardPanel.setLayout(generateCardPanelLayout);
        generateCardPanelLayout.setHorizontalGroup(
                generateCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(generateCardPanelLayout.createSequentialGroup()
                                .addGroup(generateCardPanelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(generateCardPanelLayout.createSequentialGroup()
                                                .addGap(230, 230, 230)
                                                .addComponent(cardGeneratorTitle))
                                        .addGroup(generateCardPanelLayout.createSequentialGroup()
                                                .addGap(145, 145, 145)
                                                .addComponent(studentIdLabel4, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(studentIdField4, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        233, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(generateCardPanelLayout.createSequentialGroup()
                                                .addGap(109, 109, 109)
                                                .addGroup(generateCardPanelLayout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                false)
                                                        .addGroup(generateCardPanelLayout.createSequentialGroup()
                                                                .addComponent(continueButton6,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 187,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        Short.MAX_VALUE)
                                                                .addComponent(backButton6,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 187,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(scrollPane1,
                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(128, Short.MAX_VALUE)));
        generateCardPanelLayout.setVerticalGroup(
                generateCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(generateCardPanelLayout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addComponent(cardGeneratorTitle)
                                .addGap(27, 27, 27)
                                .addGroup(generateCardPanelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(studentIdLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(studentIdField4, javax.swing.GroupLayout.PREFERRED_SIZE, 26,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(generateCardPanelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(continueButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 37,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(backButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 37,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(scrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 261,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(33, Short.MAX_VALUE)));

        mainPanel.add(generateCardPanel, "card5");

        filterPanel.setBackground(new java.awt.Color(0, 153, 153));

        filterByTitle.setFont(new java.awt.Font("Consolas", 1, 33)); // NOI18N
        filterByTitle.setForeground(new java.awt.Color(255, 255, 255));
        filterByTitle.setText("Filter By");

        filterLabel.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        filterLabel.setForeground(new java.awt.Color(255, 255, 255));
        filterLabel.setText("Filter:");

        idLabel.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        idLabel.setForeground(new java.awt.Color(255, 255, 255));
        idLabel.setText("Course ID");

        idField.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N

        filterGroup.add(radioButton1);
        radioButton1.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        radioButton1.setForeground(new java.awt.Color(255, 255, 255));
        radioButton1.setText("Students by Course");
        radioButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        radioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton1ActionPerformed(evt);
            }
        });

        filterGroup.add(radioButton2);
        radioButton2.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        radioButton2.setForeground(new java.awt.Color(255, 255, 255));
        radioButton2.setText("Courses by Student");
        radioButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        radioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioButton2ActionPerformed(evt);
            }
        });

        continueButton7.setFont(new java.awt.Font("Consolas", 0, 15)); // NOI18N
        continueButton7.setText("Continue");
        continueButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        continueButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                continueButton7ActionPerformed(evt);
            }
        });

        backButton7.setFont(new java.awt.Font("Consolas", 0, 15)); // NOI18N
        backButton7.setText("Back");
        backButton7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButton7ActionPerformed(evt);
            }
        });

        scrollPane2.setBorder(null);
        scrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jTextArea2.setEditable(false);
        jTextArea2.setBackground(new java.awt.Color(200, 255, 227));
        jTextArea2.setColumns(62);
        jTextArea2.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jTextArea2.setRows(5);
        jTextArea2.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        scrollPane2.setViewportView(jTextArea2);

        javax.swing.GroupLayout filterPanelLayout = new javax.swing.GroupLayout(filterPanel);
        filterPanel.setLayout(filterPanelLayout);
        filterPanelLayout.setHorizontalGroup(
                filterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(filterPanelLayout.createSequentialGroup()
                                .addGroup(filterPanelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(filterPanelLayout.createSequentialGroup()
                                                .addGap(277, 277, 277)
                                                .addComponent(filterByTitle))
                                        .addGroup(filterPanelLayout.createSequentialGroup()
                                                .addGap(110, 110, 110)
                                                .addGroup(filterPanelLayout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(filterPanelLayout
                                                                .createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING,
                                                                        false)
                                                                .addGroup(filterPanelLayout.createSequentialGroup()
                                                                        .addComponent(continueButton7,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                187,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                        .addComponent(backButton7,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                187,
                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addComponent(scrollPane2,
                                                                        javax.swing.GroupLayout.Alignment.TRAILING,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(filterPanelLayout.createSequentialGroup()
                                                                .addGap(45, 45, 45)
                                                                .addGroup(filterPanelLayout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(filterPanelLayout
                                                                                .createSequentialGroup()
                                                                                .addComponent(idLabel,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        187,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(
                                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(idField,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        233,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(filterPanelLayout
                                                                                .createSequentialGroup()
                                                                                .addComponent(filterLabel,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                        181,
                                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(5, 5, 5)
                                                                                .addGroup(filterPanelLayout
                                                                                        .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                false)
                                                                                        .addComponent(radioButton2,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                Short.MAX_VALUE)
                                                                                        .addComponent(
                                                                                                radioButton1))))))))
                                .addContainerGap(127, Short.MAX_VALUE)));
        filterPanelLayout.setVerticalGroup(
                filterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(filterPanelLayout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addComponent(filterByTitle)
                                .addGap(18, 18, 18)
                                .addGroup(filterPanelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(filterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(radioButton1))
                                .addGap(2, 2, 2)
                                .addComponent(radioButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(filterPanelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(idField, javax.swing.GroupLayout.PREFERRED_SIZE, 26,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(idLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(filterPanelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(continueButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 37,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(backButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 37,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addComponent(scrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 188,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(32, Short.MAX_VALUE)));

        mainPanel.add(filterPanel, "card5");

        findCoursePanel.setBackground(new java.awt.Color(0, 153, 153));

        findCourseTitle.setFont(new java.awt.Font("Consolas", 1, 33)); // NOI18N
        findCourseTitle.setForeground(new java.awt.Color(255, 255, 255));
        findCourseTitle.setText("Find Course");

        idLabel1.setFont(new java.awt.Font("Consolas", 0, 20)); // NOI18N
        idLabel1.setForeground(new java.awt.Color(255, 255, 255));
        idLabel1.setText("Course ID");

        idField1.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N

        continueButton8.setFont(new java.awt.Font("Consolas", 0, 15)); // NOI18N
        continueButton8.setText("Continue");
        continueButton8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        continueButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                continueButton8ActionPerformed(evt);
            }
        });

        backButton8.setFont(new java.awt.Font("Consolas", 0, 15)); // NOI18N
        backButton8.setText("Back");
        backButton8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        backButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButton8ActionPerformed(evt);
            }
        });

        scrollPane3.setBorder(null);
        scrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jTextArea3.setEditable(false);
        jTextArea3.setBackground(new java.awt.Color(200, 255, 227));
        jTextArea3.setColumns(62);
        jTextArea3.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        jTextArea3.setRows(5);
        jTextArea3.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        scrollPane3.setViewportView(jTextArea3);

        javax.swing.GroupLayout findCoursePanelLayout = new javax.swing.GroupLayout(findCoursePanel);
        findCoursePanel.setLayout(findCoursePanelLayout);
        findCoursePanelLayout.setHorizontalGroup(
                findCoursePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(findCoursePanelLayout.createSequentialGroup()
                                .addGroup(findCoursePanelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(findCoursePanelLayout.createSequentialGroup()
                                                .addGap(277, 277, 277)
                                                .addComponent(findCourseTitle))
                                        .addGroup(findCoursePanelLayout.createSequentialGroup()
                                                .addGap(110, 110, 110)
                                                .addGroup(findCoursePanelLayout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                                false)
                                                        .addGroup(findCoursePanelLayout.createSequentialGroup()
                                                                .addComponent(continueButton8,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 187,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        Short.MAX_VALUE)
                                                                .addComponent(backButton8,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 187,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(scrollPane3,
                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(findCoursePanelLayout.createSequentialGroup()
                                                .addGap(158, 158, 158)
                                                .addComponent(idLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 187,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(idField1, javax.swing.GroupLayout.PREFERRED_SIZE, 233,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(127, Short.MAX_VALUE)));
        findCoursePanelLayout.setVerticalGroup(
                findCoursePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(findCoursePanelLayout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addComponent(findCourseTitle)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(findCoursePanelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(idField1, javax.swing.GroupLayout.PREFERRED_SIZE, 26,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(idLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(findCoursePanelLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(continueButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 37,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(backButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 37,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(23, 23, 23)
                                .addComponent(scrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 136,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(174, Short.MAX_VALUE)));

        mainPanel.add(findCoursePanel, "card5");

        getContentPane().add(mainPanel, "card3");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void generateCardButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_generateCardButtonActionPerformed
        mainPanel.removeAll();
        studentIdField4.setText("");
        jTextArea1.setText("");
        jTextArea1.setEditable(false);
        mainPanel.add(generateCardPanel);
        revalidate();
        repaint();
    }// GEN-LAST:event_generateCardButtonActionPerformed

    private void calculateGPAButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_calculateGPAButtonActionPerformed
        mainPanel.removeAll();
        studentIdField3.setText("");
        mainPanel.add(calculateGPAPanel);
        revalidate();
        repaint();
    }// GEN-LAST:event_calculateGPAButtonActionPerformed

    private void assignGradeButton2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_assignGradeButton2ActionPerformed
        mainPanel.removeAll();
        cleanAssignGradePanel();
        mainPanel.add(assignGradePanel);
        revalidate();
        repaint();
    }// GEN-LAST:event_assignGradeButton2ActionPerformed

    private void enrollInCourseButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_enrollInCourseButtonActionPerformed
        mainPanel.removeAll();
        cleanEnrollInCoursePanel();
        mainPanel.add(enrollInCoursePanel);
        revalidate();
        repaint();
    }// GEN-LAST:event_enrollInCourseButtonActionPerformed

    private void addCourseButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_addCourseButtonActionPerformed
        mainPanel.removeAll();
        cleanAddCoursePanel();
        mainPanel.add(addCoursePanel);
        revalidate();
        repaint();
    }// GEN-LAST:event_addCourseButtonActionPerformed

    private void addStudentButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_addStudentButtonActionPerformed
        mainPanel.removeAll();
        cleanAddStudentPanel();
        mainPanel.add(addStudentPanel);
        revalidate();
        repaint();
    }// GEN-LAST:event_addStudentButtonActionPerformed

    private void backButton6ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_backButton6ActionPerformed
        mainPanel.removeAll();
        mainPanel.add(mainMenuPanel);
        revalidate();
        repaint();
    }// GEN-LAST:event_backButton6ActionPerformed

    private void backButton5ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_backButton5ActionPerformed
        mainPanel.removeAll();
        mainPanel.add(mainMenuPanel);
        revalidate();
        repaint();
    }// GEN-LAST:event_backButton5ActionPerformed

    private void backButton4ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_backButton4ActionPerformed
        mainPanel.removeAll();
        mainPanel.add(mainMenuPanel);
        revalidate();
        repaint();
    }// GEN-LAST:event_backButton4ActionPerformed

    private void backButton3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_backButton3ActionPerformed
        mainPanel.removeAll();
        mainPanel.add(mainMenuPanel);
        revalidate();
        repaint();
    }// GEN-LAST:event_backButton3ActionPerformed

    private void BackButton2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_BackButton2ActionPerformed
        mainPanel.removeAll();
        mainPanel.add(mainMenuPanel);
        revalidate();
        repaint();
    }// GEN-LAST:event_BackButton2ActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_backButtonActionPerformed
        mainPanel.removeAll();
        mainPanel.add(mainMenuPanel);
        revalidate();
        repaint();
    }// GEN-LAST:event_backButtonActionPerformed

    private void continueButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_continueButtonActionPerformed
        boolean missingData = studentNameField.getText().isEmpty() ||
                nationalIdField.getText().isEmpty() ||
                majorComboBox.getSelectedItem().toString().isEmpty() ||
                addressComboBox.getSelectedItem().toString().isEmpty() ||
                (!maleRadioButton.isSelected() && !femaleRadioButton.isSelected());

        if (missingData) {
            JOptionPane.showMessageDialog(this, "Missing Data. Please Fill All the Fields", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            long studentNationalId = Long.parseLong(nationalIdField.getText());
            if (doesStudentExist(studentNationalId)) {
                JOptionPane.showMessageDialog(this, "A Student with the Same National Id Already Exists", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            String studentName = studentNameField.getText();
            String studentMajor = majorComboBox.getSelectedItem().toString();
            String studentAddress = addressComboBox.getSelectedItem().toString();
            String studentGender;
            if (maleRadioButton.isSelected())
                studentGender = "male";
            else
                studentGender = "female";

            for (int i = 0; i < studentName.length(); i++)
                if (studentName.charAt(i) == ',')
                    throw new Exception();

            Student newStudent = new Student(studentName, studentNationalId, studentMajor, studentGender,
                    studentAddress);

            students.add(newStudent);
            saveStudentsToFile();
            JOptionPane.showMessageDialog(this, "Student Added Successfully\nNew Student Id is " + newStudent.id,
                    "Student Added Successfully", JOptionPane.INFORMATION_MESSAGE);
            cleanAddStudentPanel();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid Data", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }// GEN-LAST:event_continueButtonActionPerformed

    private void continueButton2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_continueButton2ActionPerformed
        boolean missingData = courseNameField.getText().isEmpty() ||
                courseIdField.getText().isEmpty() ||
                courseCreditHoursField.getText().isEmpty();

        if (missingData) {
            JOptionPane.showMessageDialog(this, "Missing Data. Please Fill All the Fields", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            String courseName = courseNameField.getText();
            String courseId = courseIdField.getText();
            byte courseCreditHours = Byte.parseByte(courseCreditHoursField.getText());

            for (int i = 0; i < courseName.length(); i++)
                if (courseName.charAt(i) == ',')
                    throw new Exception();

            for (int i = 0; i < courseId.length(); i++)
                if (courseId.charAt(i) == ',')
                    throw new Exception();

            if (doesCourseExist(courseId)) {
                JOptionPane.showMessageDialog(this, "A Course with the Same Id Already Exists", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            Course newCourse = new Course(courseName, courseId, courseCreditHours);
            courses.add(newCourse);
            saveCoursesToFile();
            JOptionPane.showMessageDialog(this, "Course Added Successfully", "Course Added Successfully",
                    JOptionPane.INFORMATION_MESSAGE);
            cleanAddCoursePanel();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid Data", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }// GEN-LAST:event_continueButton2ActionPerformed

    private void continueButton3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_continueButton3ActionPerformed
        boolean missingData = studentIdField.getText().isEmpty() || courseIdField2.getText().isEmpty();
        if (missingData) {
            JOptionPane.showMessageDialog(this, "Missing Data. Please Fill All the Fields", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            int studentId = Integer.parseInt(studentIdField.getText());
            String courseId = courseIdField2.getText();

            Student tempStudent = null;
            Course tempCourse = null;
            for (Student student : students)
                if (student.id == studentId) {
                    tempStudent = student;
                    break;
                }

            for (Course course : courses)
                if (course.id.equals(courseId)) {
                    tempCourse = new Course(course);
                    break;
                }

            if (tempStudent == null && tempCourse == null)
                JOptionPane.showMessageDialog(this, "Neither the Student nor the Course has been Found", "Error",
                        JOptionPane.ERROR_MESSAGE);
            else if (tempStudent == null)
                JOptionPane.showMessageDialog(this, "Student Not Found", "Error", JOptionPane.ERROR_MESSAGE);
            else if (tempCourse == null)
                JOptionPane.showMessageDialog(this, "Course Not Found", "Error", JOptionPane.ERROR_MESSAGE);
            else {
                for (Course course : tempStudent.enrolledCourses) {
                    if (course.id.equals(courseId)) {
                        JOptionPane.showMessageDialog(this, "This Student has Already Enrolled in This Course", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                tempStudent.enrolledCourses.add(tempCourse);
                saveStudentsToFile();
                JOptionPane.showMessageDialog(this, "Enrollment Done Successfully", "Enrollment Done Successfully",
                        JOptionPane.INFORMATION_MESSAGE);
                cleanEnrollInCoursePanel();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid Data", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }// GEN-LAST:event_continueButton3ActionPerformed

    private void continueButton4ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_continueButton4ActionPerformed
        boolean missingData = studentIdField2.getText().isEmpty() || courseIdField3.getText().isEmpty();
        if (missingData) {
            JOptionPane.showMessageDialog(this, "Missing Data. Please Fill All the Fields", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            int studentId = Integer.parseInt(studentIdField2.getText());
            String courseId = courseIdField3.getText();

            Student tempStudent = null;
            Course tempCourse = null;
            for (Student student : students)
                if (student.id == studentId) {
                    tempStudent = student;
                    break;
                }
            for (Course course : courses)
                if (course.id.equals(courseId)) {
                    tempCourse = new Course(course);
                    break;
                }

            if (tempStudent == null && tempCourse == null)
                JOptionPane.showMessageDialog(this, "Neither the Student nor the Course has been Found", "Error",
                        JOptionPane.ERROR_MESSAGE);
            else if (tempStudent == null)
                JOptionPane.showMessageDialog(this, "Student Not Found", "Error", JOptionPane.ERROR_MESSAGE);
            else if (tempCourse == null)
                JOptionPane.showMessageDialog(this, "Course Not Found", "Error", JOptionPane.ERROR_MESSAGE);

            else {
                for (Course course : tempStudent.enrolledCourses) {
                    if (course.id.equals(courseId)) {
                        course.grade = courseGradeComboBox.getSelectedItem().toString();
                        tempStudent.gpa = calculateGpa(tempStudent); // new
                        JOptionPane.showMessageDialog(this, "Grade Assigned Successfully and GPA Updated Successfully",
                                "Grade Assigned Successfully", JOptionPane.INFORMATION_MESSAGE);
                        saveStudentsToFile();
                        cleanAssignGradePanel();
                        return;
                    }
                }
                JOptionPane.showMessageDialog(this, "This Student did not Enroll in this Course ", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid Data", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }// GEN-LAST:event_continueButton4ActionPerformed

    private void continueButton5ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_continueButton5ActionPerformed
        if (studentIdField3.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Missing Data. Please Fill All the Fields", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            int studentId = Integer.parseInt(studentIdField3.getText());
            for (Student student : students) {
                if (student.id == studentId) {
                    student.gpa = calculateGpa(student);
                    saveStudentsToFile();
                    JOptionPane.showMessageDialog(this, "GPA Calculated Successfully\nStudent GPA = " + student.gpa,
                            "GPA Calculated Successfully", JOptionPane.INFORMATION_MESSAGE);
                    studentIdField3.setText("");
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Student Not Found", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid Data", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }// GEN-LAST:event_continueButton5ActionPerformed

    private void continueButton6ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_continueButton6ActionPerformed
        if (studentIdField4.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Missing Data. Please Fill All the Fields", "Error",
                    JOptionPane.ERROR_MESSAGE);
            jTextArea1.setText("");
            return;
        }
        try {
            int studentId = Integer.parseInt(studentIdField4.getText());
            for (Student student : students) {
                if (student.id == studentId) {
                    fillCard(student);
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Student Not Found", "Error", JOptionPane.ERROR_MESSAGE);
            jTextArea1.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid Data", "Error", JOptionPane.ERROR_MESSAGE);
            jTextArea1.setText("");
        }
    }// GEN-LAST:event_continueButton6ActionPerformed

    private void filterButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_filterButtonActionPerformed
        mainPanel.removeAll();
        idField.setText("");
        jTextArea2.setText("");
        radioButton1.doClick();
        mainPanel.add(filterPanel);
        revalidate();
        repaint();
    }// GEN-LAST:event_filterButtonActionPerformed

    private void continueButton7ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_continueButton7ActionPerformed
        if (idField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Missing Data", "Error", JOptionPane.ERROR_MESSAGE);
            jTextArea2.setText("");
        }
        try {
            if (radioButton1.isSelected())
                findStudentsByCourse(idField.getText());
            else
                findCoursesByStudent(Integer.parseInt(idField.getText()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid Data", "Error", JOptionPane.ERROR_MESSAGE);
            jTextArea2.setText("");
        }
    }// GEN-LAST:event_continueButton7ActionPerformed

    private void findStudentsByCourse(String courseId) {
        String text = "";
        for (Student student : students) {
            for (Course course : student.enrolledCourses)
                if (course.id.equals(courseId)) {
                    text += student.name + ", " + student.id + ", " + student.major + "\n";
                    break;
                }
        }
        if (text.isEmpty())
            text = "No Data Found";
        jTextArea2.setText(text);
    }

    private void findCoursesByStudent(int studentId) {
        String text = "";
        for (Student student : students) {
            if (student.id == studentId) {
                for (Course course : student.enrolledCourses)
                    text += course.name + ", " + course.id + "\n";
                break;
            }
        }
        if (text.isEmpty())
            text = "No Data Found";
        jTextArea2.setText(text);
    }

    private void backButton7ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_backButton7ActionPerformed
        mainPanel.removeAll();
        mainPanel.add(mainMenuPanel);
        revalidate();
        repaint();
    }// GEN-LAST:event_backButton7ActionPerformed

    private void radioButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_radioButton1ActionPerformed
        idLabel.setText("Course ID");
    }// GEN-LAST:event_radioButton1ActionPerformed

    private void radioButton2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_radioButton2ActionPerformed
        idLabel.setText("Student ID");
    }// GEN-LAST:event_radioButton2ActionPerformed

    private void studentNameFieldActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_studentNameFieldActionPerformed
    }// GEN-LAST:event_studentNameFieldActionPerformed

    private void findCourseButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_findCourseButtonActionPerformed
        mainPanel.removeAll();
        cleanFindCoursePanel();
        mainPanel.add(findCoursePanel);
        mainPanel.revalidate();
        mainPanel.repaint();
    }// GEN-LAST:event_findCourseButtonActionPerformed

    private void continueButton8ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_continueButton8ActionPerformed
        if (idField1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Missing Data. Please Fill All the Fields", "Error",
                    JOptionPane.ERROR_MESSAGE);
            jTextArea1.setText("");
            return;
        }
        String courseId = idField1.getText();
        for (Course course : courses) {
            if (course.id.equals(courseId)) {
                String data = "Course name: " + course.name + "\n";
                data += "Course ID: " + course.id + "\n";
                data += "Course credit hours: " + course.creditHours + "\n";
                int numStudents = 0;
                for (Student student : students) {
                    for (Course enrolledCourse : student.enrolledCourses) {
                        if (enrolledCourse.id.equals(courseId)) {
                            numStudents++;
                            break;
                        }
                    }
                }
                data += "Registered students: " + numStudents + "\n";

                jTextArea3.setText(data);
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Course Not Found", "Error", JOptionPane.ERROR_MESSAGE);
        jTextArea3.setText("");
        idField1.setText("");

    }// GEN-LAST:event_continueButton8ActionPerformed

    private void backButton8ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_backButton8ActionPerformed
        mainPanel.removeAll();
        mainPanel.add(mainMenuPanel);
        revalidate();
        repaint();
    }// GEN-LAST:event_backButton8ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackButton2;
    private javax.swing.JButton addCourseButton;
    private javax.swing.JPanel addCoursePanel;
    private javax.swing.JLabel addCourseTitle;
    private javax.swing.JButton addStudentButton;
    private javax.swing.JPanel addStudentPanel;
    private javax.swing.JLabel addStudentTitle;
    private javax.swing.JComboBox<String> addressComboBox;
    private javax.swing.JLabel addressLabel;
    private javax.swing.JButton assignGradeButton2;
    private javax.swing.JPanel assignGradePanel;
    private javax.swing.JLabel assignGradeTitle;
    private javax.swing.JButton backButton;
    private javax.swing.JButton backButton3;
    private javax.swing.JButton backButton4;
    private javax.swing.JButton backButton5;
    private javax.swing.JButton backButton6;
    private javax.swing.JButton backButton7;
    private javax.swing.JButton backButton8;
    private javax.swing.JButton calculateGPAButton;
    private javax.swing.JPanel calculateGPAPanel;
    private javax.swing.JLabel cardGeneratorTitle;
    private javax.swing.JButton continueButton;
    private javax.swing.JButton continueButton2;
    private javax.swing.JButton continueButton3;
    private javax.swing.JButton continueButton4;
    private javax.swing.JButton continueButton5;
    private javax.swing.JButton continueButton6;
    private javax.swing.JButton continueButton7;
    private javax.swing.JButton continueButton8;
    private javax.swing.JTextField courseCreditHoursField;
    private javax.swing.JComboBox<String> courseGradeComboBox;
    private javax.swing.JTextField courseIdField;
    private javax.swing.JTextField courseIdField2;
    private javax.swing.JTextField courseIdField3;
    private javax.swing.JLabel courseIdLLabel3;
    private javax.swing.JLabel courseIdLabel;
    private javax.swing.JLabel courseIdLabel2;
    private javax.swing.JTextField courseNameField;
    private javax.swing.JLabel courseNameLabel;
    private javax.swing.JLabel creditHoursLabel;
    private javax.swing.JLabel enrolLInCourseTitle;
    private javax.swing.JButton enrollInCourseButton;
    private javax.swing.JPanel enrollInCoursePanel;
    private javax.swing.JRadioButton femaleRadioButton;
    private javax.swing.JButton filterButton;
    private javax.swing.JLabel filterByTitle;
    private javax.swing.ButtonGroup filterGroup;
    private javax.swing.JLabel filterLabel;
    private javax.swing.JPanel filterPanel;
    private javax.swing.JButton findCourseButton;
    private javax.swing.JPanel findCoursePanel;
    private javax.swing.JLabel findCourseTitle;
    private javax.swing.JLabel genderLabel;
    private javax.swing.JButton generateCardButton;
    private javax.swing.JPanel generateCardPanel;
    private javax.swing.JLabel gpaCalcTitle;
    private javax.swing.JLabel gradeLabel;
    private javax.swing.JTextField idField;
    private javax.swing.JTextField idField1;
    private javax.swing.JLabel idLabel;
    private javax.swing.JLabel idLabel1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JPanel mainMenuPanel;
    private javax.swing.JLabel mainMenuTitle;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JComboBox<String> majorComboBox;
    private javax.swing.JLabel majorLabel;
    private javax.swing.ButtonGroup maleFemaleGroup;
    private javax.swing.JRadioButton maleRadioButton;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nationalIdField;
    private javax.swing.JLabel nationalIdLabel;
    private javax.swing.JLabel pleaseLabel1;
    private javax.swing.JLabel pleaseLabel2;
    private javax.swing.JLabel pleaseLabel3;
    private javax.swing.JLabel pleaseLabel4;
    private javax.swing.JRadioButton radioButton1;
    private javax.swing.JRadioButton radioButton2;
    private javax.swing.JScrollPane scrollPane1;
    private javax.swing.JScrollPane scrollPane2;
    private javax.swing.JScrollPane scrollPane3;
    private javax.swing.JTextField studentIdField;
    private javax.swing.JTextField studentIdField2;
    private javax.swing.JTextField studentIdField3;
    private javax.swing.JTextField studentIdField4;
    private javax.swing.JLabel studentIdLabel;
    private javax.swing.JLabel studentIdLabel2;
    private javax.swing.JLabel studentIdLabel3;
    private javax.swing.JLabel studentIdLabel4;
    private javax.swing.JTextField studentNameField;
    // End of variables declaration//GEN-END:variables
}