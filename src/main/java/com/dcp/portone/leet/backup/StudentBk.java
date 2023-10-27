package com.dcp.portone.leet.backup;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

class Student {
    //(long studentId, String countryCode, int yearEnrolled, int ageEnrolled, String gender, boolean progExp)
    private static long lastStudentId = 1;
    private final static Random random = new Random();
    private final long studentId;
    private final String countryCode;
    private final int yearEnrolled;
    private final int ageEnrolled;
    private final String gender;
    private final boolean progExp;
    private final Map<String, CourseEngagement> engagementMap = new HashMap<>();

    public void watchLecture(String courseCode, int lectureNumber, int month, int year) {
        var activity = engagementMap.get(courseCode);
        if (activity != null) {
            activity.watchLecture(lectureNumber, LocalDate.of(year, month, 1));
        }
    }

    public void addCourse(Course newCourse) {
        addCourse(newCourse, LocalDate.now());
    }
    public void addCourse(Course newCourse, LocalDate enrollDate) {
        engagementMap.put(newCourse.courseCode(),
                new CourseEngagement(newCourse, enrollDate, "Enrollment"));
    }

    public int getAge() {
        return ageEnrolled + getYearsSinceEnrolled();
    }
    public int getMonthsSinceActive(String courseCode) {
        CourseEngagement info = engagementMap.get(courseCode);
        return info == null ? 0: info.getMonthsSinceActive();
    }

    public int getMonthsSinceActive() {
        int inactiveMonths = (LocalDate.now().getYear() - 2014) * 12;
        for (String key: engagementMap.keySet()) {
            inactiveMonths = Math.min(inactiveMonths, getMonthsSinceActive(key));
        }
        return inactiveMonths;
    }
    public double getPercentageComplete(String courseCode) {
        var info = engagementMap.get(courseCode);
        return (info == null) ? 0 : info.getPercentageComplete();
    }

    public int getYearsSinceEnrolled() {
        return LocalDate.now().getYear() - yearEnrolled;
    }

    private static String getRandomVal(String... data) {
        return data[random.nextInt(data.length)];
    }
    public static Student getRandomStudent(Course... courses) {
        int maxYear = LocalDate.now().getYear() + 1;
        Student student = new Student(
                getRandomVal("AU", "US", "CN", "IN", "GB", "UA"),
                random.nextInt(2015, maxYear),
                random.nextInt(18,90),
                getRandomVal("M", "F", "U"),
                random.nextBoolean(),
                courses );

        for(Course course: courses) {
            int lecture = random.nextInt(30, course.lectureCount());
            int year = random.nextInt(student.getYearEnrolled(), maxYear);
            int month = random.nextInt(1,13);
            if (year == (maxYear-1)) {
                if (month > LocalDate.now().getMonthValue()) {
                    month = LocalDate.now().getMonthValue();
                }
            }
            student.watchLecture(course.courseCode(), lecture, month, year);
        }
        return student;

    }

    public long getStudentId() {
        return studentId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public int getYearEnrolled() {
        return yearEnrolled;
    }

    public int getAgeEnrolled() {
        return ageEnrolled;
    }

    public String getGender() {
        return gender;
    }

    public boolean hasProgExp() {
        return progExp;
    }

    public Map<String, CourseEngagement> getEngagementMap() {
        return Map.copyOf(engagementMap);
    }
    Student(String countryCode, int yearEnrolled, int ageEnrolled, String gender,
            boolean progExp, Course... courses) {
        this.studentId = lastStudentId++;
        this.countryCode = countryCode;
        this.yearEnrolled = yearEnrolled;
        this.ageEnrolled = ageEnrolled;
        this.gender = gender;
        this.progExp = progExp;

        for (Course course: courses) {
            addCourse(course, LocalDate.of(yearEnrolled, 1, 1));
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", countryCode='" + countryCode + '\'' +
                ", yearEnrolled=" + yearEnrolled +
                ", ageEnrolled=" + ageEnrolled +
                ", gender='" + gender + '\'' +
                ", progExp=" + progExp +
                ", engagementMap=" + engagementMap +
                '}';
    }
}
record Course(String courseCode, String title, int lectureCount) {
    @Override
    public String courseCode() {
        return courseCode;
    }

    @Override
    public String title() {
        return title;
    }

    @Override
    public int lectureCount() {
        return lectureCount;
    }
    public Course {
        if (lectureCount <= 0)
            lectureCount = 1;
    }

    public Course(String courseCode, String title) {
        this(courseCode, title, 40);
    }

    @Override
    public String toString() {
        return "%s %s".formatted(courseCode, title);
    }
}

class CourseEngagement {
    private final Course course;
    private final LocalDate enrollmentDate;
    private String engagementType;
    private int lastLecture;
    private LocalDate lastActivityDate;

    public CourseEngagement(Course course, LocalDate enrollmentDate, String engagementType) {
        this.course = course;
        this.enrollmentDate = this.lastActivityDate = enrollmentDate;
        this.engagementType = engagementType;
    }

    public String getCourseCode() {
        return course.courseCode();
    }
    public int getEnrolementYear() {
        return enrollmentDate.getYear();
    }
    public int getLastActivtyYear() {
        return lastActivityDate.getYear();
    }
    public String getLastActivtyMonth() {
//        return lastActivityDate.getMonth().toString();
        return "%tb".formatted(lastActivityDate);
    }
    public int getMonthsSinceActive() {
        LocalDate now = LocalDate.now();
        var months = Period.between(lastActivityDate, now).toTotalMonths();
        return (int) months;
    }

    public double getPercentageComplete() {
        return lastLecture * 100.0 / course.lectureCount();
    }
    public void watchLecture(int lectureNumber, LocalDate currentDate) {
        lastLecture = Math.max(lectureNumber, lastLecture);
        lastActivityDate = currentDate;
        engagementType = "Lecture " + lastLecture;
    }

    @Override
    public String toString() {
        return "%s: %s %d %s [%d]".formatted(course.courseCode(), getLastActivtyMonth(), getLastActivtyYear(),
                engagementType, getMonthsSinceActive());
    }
}