package com.dcp.portone.controller;

import com.dcp.portone.exceptions.ObjNotFoundException;
import com.dcp.portone.model.request.StudentDetailsRequestModel;
import com.dcp.portone.model.request.UpdateStudentDetailsRequestModel;
import com.dcp.portone.model.response.ResponseHandler;
import com.dcp.portone.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@ControllerAdvice
@RestController
@RequestMapping("/students") // http://localhost:8080/students
public class StudentsController {

    @Autowired
    private StudentService studentService;

    /*    @RequestMapping("/")
    public String home(){
        return "Hello from Nagesh! This is my First Rest Controller Home";
    }*/
    @GetMapping(value = "/getall")
    public List<StudentDetailsRequestModel> getStudents(@RequestParam(value = "page", defaultValue = "1") int page,
                                     @RequestParam(value = "limit", defaultValue = "25") int limit){
        return new ResponseEntity<List<StudentDetailsRequestModel>>(studentService.getAllStudents(), HttpStatus.OK).getBody();
    }
    @GetMapping(value = "/card/{cardNo}")
    public List<StudentDetailsRequestModel> getStudentByCardNo(@PathVariable Integer cardNo){

//        if ((cardNo >= studentService.getAllStudents().size()) ||  cardNo < 0 ) {
        if ((cardNo >= 50) ||  cardNo < 1 ) {
            throw new ObjNotFoundException("Card No not found - " + cardNo);
        }
        /* Studentiit studentRest;
        List<Studentiit> stuDetails = new ThreePrizes().getStudentiitList();
        studentRest = stuDetails.get(studentId);
        return studentRest;*/
        //System.out.println(cardNo);
        List<StudentDetailsRequestModel> students = studentService.findByCardNo(cardNo);
        //System.out.println(student);

        //return student;
        return new ResponseEntity<List<StudentDetailsRequestModel>>(students, HttpStatus.OK).getBody();
    }

    @GetMapping(value = "/name/{studentName}")
    public List<StudentDetailsRequestModel> getStudentByName(@PathVariable String studentName){

        return new ResponseEntity<List<StudentDetailsRequestModel>>(
                studentService.findByName(studentName), HttpStatus.OK).getBody();
    }

    // Build and return Custom Response
    @PostMapping(value = "/add/student", produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_GRAPHQL_RESPONSE_VALUE
    }, consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_GRAPHQL_RESPONSE_VALUE
    })

    public ResponseEntity<Object> addStudent(@Valid @RequestBody StudentDetailsRequestModel studentDetails){
        System.out.println(studentDetails.toString());
        String name = studentDetails.getName();

        //StudentDetailsRequestModel student = studentService.addStudent(studentDetails);

        return ResponseHandler.responseBuilder("Added Student Details.", HttpStatus.OK, studentDetails);

        //return new ResponseEntity<StudentDetailsRequestModel>(student, HttpStatus.OK).getBody();
    }

    @PutMapping(path = "/{studentId}")
    public String updateUser(@PathVariable Integer cardNo, @RequestBody UpdateStudentDetailsRequestModel updateStuDetReqModel){

        return "Update user was called!!!";
    }

    @DeleteMapping(path = "/{id}")
    public Integer deleteUser(@PathVariable Integer id){
        studentService.deleteStudent(id);
        return id;
    }

}
