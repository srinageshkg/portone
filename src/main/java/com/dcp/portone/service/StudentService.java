package com.dcp.portone.service;

import com.dcp.portone.entity.Marks;
import com.dcp.portone.entity.Student;
import com.dcp.portone.model.request.StudentDetailsRequestModel;
import com.dcp.portone.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    String line = "";
    public void saveStudentsData() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/studentsmarks.csv"));
            while ((line = br.readLine()) != null) {
                String [] data = line.split(",");
                Student student = new Student();

                Marks marks = new Marks();
/*                marks.builder()
                        .mathsMarks(Integer.valueOf(data[5]))
                        .physicsMarks(Integer.valueOf(data[6]))
                        .chemistryMarks(Integer.valueOf(data[7]))
                        .totalMarks(Integer.valueOf(data[5]) + Integer.valueOf(data[6]) + Integer.valueOf(data[7]))
                        .build();

                student.builder()
                        .cardNo(Integer.valueOf(data[0]))
                        .name(data[1])
                        .gender(data[2])
                        .dateOfBirth(data[3])
                        .cityTown(data[4])
                        .marks(marks)
                        .build();*/

                marks.setMathsMarks(Integer.valueOf(data[5]));
                marks.setPhysicsMarks(Integer.valueOf(data[6]));
                marks.setPhysicsMarks(Integer.valueOf(data[7]));
                marks.setTotalMarks(Integer.valueOf(data[8]));

                student.setCardNo(Integer.valueOf(data[0]));
                student.setName(data[1]);
                student.setGender(data[2]);
                student.setDateOfBirth(data[3]);
                student.setCityTown(data[4]);
                student.setMarks(marks);

                studentRepository.save(student);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveStudentsData(Student student) {
        studentRepository.save(student);
    }

    public List<StudentDetailsRequestModel> getAllStudents(){
        List<StudentDetailsRequestModel> studentsRecord = new ArrayList<>();

        /*
        studentRepository.findAll().stream().forEach({
        }); */

        for (Student student : studentRepository.findAll()) {
            StudentDetailsRequestModel studentDetailsRequestModel =
                    StudentDetailsRequestModel.builder()
                    .cardNo(student.getCardNo())
                    .name(student.getName())
                    .gender(student.getGender())
                    .cityTown(student.getCityTown())
                    .dateOfBirth(student.getDateOfBirth())
                    .chemistryMarks(student.getMarks().getChemistryMarks())
                    .mathsMarks(student.getMarks().getMathsMarks())
                    .physicsMarks(student.getMarks().getPhysicsMarks())
                    .totalMarks(student.getMarks().getTotalMarks())
                    .build();
            //studentDetailsRequestModel.setTotalMarks(student.getMarks().getTotalMarks());*/
            studentsRecord.add(studentDetailsRequestModel);
        }

        return studentsRecord;
    }

    public List<StudentDetailsRequestModel> studentsBonrInCityAndMonth(String city, String month){

        return null;
    }

    public void updateStudent(StudentDetailsRequestModel student, Integer cardNo){
        System.out.println("Updating the Student details for the crdNo : " + cardNo);

    }

    public StudentDetailsRequestModel addStudent(StudentDetailsRequestModel student){

        Student stu = new Student();
        Marks stuMarks = new Marks();

        stu.setCardNo(student.getCardNo());
        stu.setName(student.getName());
        stu.setGender(student.getGender());
        stu.setCityTown(student.getCityTown());
        stu.setDateOfBirth(student.getDateOfBirth());
        stuMarks.setMathsMarks(student.getMathsMarks());
        stuMarks.setPhysicsMarks(student.getPhysicsMarks());
        stuMarks.setChemistryMarks(student.getChemistryMarks());
        stuMarks.setTotalMarks(student.getTotalMarks());
        stu.setMarks(stuMarks);


        Student student1 = studentRepository.save(stu);

        StudentDetailsRequestModel sdrm = StudentDetailsRequestModel.builder()
                .cardNo(student1.getCardNo())
                .name(student1.getName())
                .gender(student1.getGender())
                .cityTown(student1.getCityTown())
                .dateOfBirth(student1.getDateOfBirth())
                .chemistryMarks(student1.getMarks().getChemistryMarks())
                .mathsMarks(student1.getMarks().getMathsMarks())
                .physicsMarks(student1.getMarks().getPhysicsMarks())
                .totalMarks(student1.getMarks().getTotalMarks())
                .build();
        return sdrm;
    }
    public void deleteStudent(Integer stuId){

        studentRepository.deleteById(stuId.longValue());
    }

    public Integer getStudentTotalMarks(Student student){
        return
                student.getMarks().getTotalMarks();
    }

    public List<StudentDetailsRequestModel> findByCardNo(Integer cardNo) {

        List<StudentDetailsRequestModel> stuListByCardNo = new ArrayList<>();

        for(Student student : studentRepository.findByCardNo(cardNo)) {
            //System.out.println(student);
            StudentDetailsRequestModel sdrm = StudentDetailsRequestModel.builder()
                    .cardNo(student.getCardNo())
                    .name(student.getName())
                    .gender(student.getGender())
                    .cityTown(student.getCityTown())
                    .dateOfBirth(student.getDateOfBirth())
                    .chemistryMarks(student.getMarks().getChemistryMarks())
                    .mathsMarks(student.getMarks().getMathsMarks())
                    .physicsMarks(student.getMarks().getPhysicsMarks())
                    .totalMarks(student.getMarks().getTotalMarks())
                    .build();

            stuListByCardNo.add(sdrm);
        }
        return stuListByCardNo;
    }

    public List<StudentDetailsRequestModel> findByName(String studentName) {

        List<StudentDetailsRequestModel> stuListByName = new ArrayList<>();

        for (Student student : studentRepository.findByName(studentName)) {
            StudentDetailsRequestModel studentDetailsRequestModel =
                    StudentDetailsRequestModel.builder()
                            .cardNo(student.getCardNo())
                            .name(student.getName())
                            .gender(student.getGender())
                            .cityTown(student.getCityTown())
                            .dateOfBirth(student.getDateOfBirth())
                            .chemistryMarks(student.getMarks().getChemistryMarks())
                            .mathsMarks(student.getMarks().getMathsMarks())
                            .physicsMarks(student.getMarks().getPhysicsMarks())
                            .totalMarks(student.getMarks().getTotalMarks())
                            .build();
            //studentDetailsRequestModel.setTotalMarks(student.getMarks().getTotalMarks());*/
            stuListByName.add(studentDetailsRequestModel);
        }
        return stuListByName;
    }

    public List<Student> findByNameContaining(String str) {
        return studentRepository.findByNameContaining(str);
    }

    public List<StudentDetailsRequestModel> findByCityTown(String city){

        List<StudentDetailsRequestModel> sdrmList = new ArrayList<>();

        for( Student student : studentRepository.findByCityTown(city)){
            StudentDetailsRequestModel sdrm = StudentDetailsRequestModel.builder()
                    .cardNo(student.getCardNo())
                    .name(student.getName())
                    .gender(student.getGender())
                    .cityTown(student.getCityTown())
                    .dateOfBirth(student.getDateOfBirth())
                    .chemistryMarks(student.getMarks().getChemistryMarks())
                    .mathsMarks(student.getMarks().getMathsMarks())
                    .physicsMarks(student.getMarks().getPhysicsMarks())
                    .totalMarks(student.getMarks().getTotalMarks())
                    .build();

            sdrmList.add(sdrm);

        }

        return sdrmList;
    }

}
