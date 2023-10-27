package com.dcp.portone.repository;

import com.dcp.portone.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    public List<Student> findByCardNo(Integer cardNo);

    public List<Student> findByName(String studentName);

    public List<Student> findByNameContaining(String studentName);

    public List<Student> findByCityTown(String city);

    public List<Student> findAll();

    //public List<Student> findByCityTownNotNull(String city);
    //@Modifying @Transactional @Query
    // Native Named Param s.firstName = :firstName   (@Param("firstName" String firstName args)
    // @Query(value="Select * from student s where s.cityTown == ?1", nativeQuery= true)
    //finfByNameAndCityTown FindByAgeIn(Collection<Age> ages) IgnoreCase     findByStartDateBetween
    //findByAgeLessThan LessThanEqual StartDateAfter FirstNameLike StartingWith EndingWith Containing ByAgeOrderByLastnameDesc
}

/*
    // JPQL
    @Query("select s from Student s where s.mathsMarks > :?1 and s.physicsMarks > ?2 and s.chemistryMarks > ?3 ")
    public List<Student> findByGreaterThanMarks(Integer m1, Integer m2, Integer m3);
    // @Query( value = "select * from tabl_student", nativeQuery = true)
    // @Modifying @Transactional
    // OneToOne relationship Course  and CourseMaterial - private Course course  ToString(exclude = "Course"
    // OnrToOne birectional - parent entity @OneToOne(mappedBy="course" -> defined in child table private Course course;
    @OneToOne(cascade = CascadeType.ALL parent and child data insertion happens from a child repository, fetch = FetchType.EAGER, optional = false)
    @ToString(exclude = "couse")
    // @JoinColumn( name = "course_id", referencedColumnName = "courseID --- in Course Table")  Cascading imp
    // bi direction mapping from course to courseMaterial
    @OneToOne(
            mappedBy = "couse"
    )
    private CourseMateial courseMaterial;
    @JoinColumn(name = "", referencedColumnName = "")

    @OneToMany(cascade = CascadeType.ALL)
    // A Teacher can teach multiple courses. in Teacher table --- private List<Course> courses;

    @ManyToMany(cascade =  CascadeType.ALL)
    @JoinTable( name = "somr_table_map", joinColumns = @JoinColumn( name = "xx_id", referencedColumnName = "xxId"), inverseJoinColumns = @JoinColumn(name = "", referencedColumnName = ))
*/
