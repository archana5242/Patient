package com.example.demo.Repository;

import com.example.demo.Model.PatientModel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PatientRepository extends CrudRepository<PatientModel,Integer> {
    @Query(value = "select * from patients",nativeQuery = true)
    public List<PatientModel> getPatientList();

    @Query(value = "select * from patients where p_phone= ?", nativeQuery = true)
    public List<PatientModel> searchPatient(String phone);

    @Query(value= "select * from patients where p_phone = :mob and p_name= :name",nativeQuery = true)
    public List<PatientModel> multiSearch(@Param("mob")String mob,@Param("name")String name);


  @Transactional
    @Modifying
    @Query(value = "Delete  from patients where id= :id",nativeQuery = true)
    public void DeletePatient(@Param("id")int id);

  @Transactional
    @Modifying
    @Query(value= "update patients SET p_address= :p_address,p_name= :p_name,p_phone= :p_phone where id= :id",nativeQuery = true)
    public void Update(@Param("id")int id,@Param("p_address")String p_address,@Param("p_name")String p_name,@Param("p_phone")String p_phone);
}
