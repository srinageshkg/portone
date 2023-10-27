/*
package com.dcp.portone.repository;

import com.dcp.portone.entity.Marks;
import com.dcp.portone.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

*/
/*    @Test
    public void saveStudent(){
        Student student = Student.builder()
                .cardNo(1)
                .name("Bhuvanesh")
                .gender('M')
                .dateOfBirth("11/07/2023")
                .cityTown("Erode")
                .Maths(68)
                .Physics(64)
                .Chemistry(78)
                .Total(210)
                .build();
        studentRepository.save(student);
    }*//*


    @Test
    public void saveStudentWithMarks(){
        Marks marks = Marks.builder()
                .mathsMarks(62)
                .physicsMarks(45)
                .chemistryMarks(91)
                .totalMarks(198)
                .build();

        Student student = Student.builder()
                .cardNo(1)
                .name("Harish")
                .gender("M")
                .dateOfBirth("06/03/2023")
                .cityTown("Salem")
                .marks(marks)
                .build();

        studentRepository.save(student);

    }

    @Test
    public void printAllStudents(){
        Iterable<Student> studentList = studentRepository.findAll();

        System.out.println("Student List : " + studentList);
    }

*/
/*    @Test
    public void loadStudentsData(){
        String line = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/studentsmarks.csv"));
            while ((line = br.readLine()) != null) {
                String [] data = line.split(",");
                Student student = new Student();

                student.setCardNo(Integer.valueOf(data[0]));
                student.setName(data[1]);
                //student.setGender(Character.valueOf(data[2]));
                student.setGender(data[2]);
                student.setDateOfBirth(data[3]);
                student.setCityTown(data[4]);

                Marks marks = new Marks();
                marks.setMathsMarks(Integer.valueOf(data[5]));
                marks.setPhysicsMarks(Integer.valueOf(data[6]));
                marks.setChemistryMarks(Integer.valueOf(data[7]));
                marks.setTotalMarks(Integer.valueOf(data[8]));

                student.setMarks(marks);

                studentRepository.save(student);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*//*


    @Test
    public void loadStudentData(Student student){
        studentRepository.save(student);
    }
}*/
