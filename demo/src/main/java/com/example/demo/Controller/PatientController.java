package com.example.demo.Controller;

import com.example.demo.Model.PatientModel;
import com.example.demo.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PatientController {

    PatientModel patientGlobal = new PatientModel();
    List<PatientModel> patientList = new ArrayList<>();
    @Autowired
    PatientRepository patientRepository;
    @ResponseBody
    @PostMapping(path="patientEntrys", consumes = "application/json", produces = "application/json")
    //public String addPatients(@RequestBody PatientModel patientModel){
           // System.out.println("Name ="+ patientModel.getpName());
        //System.out.println("Address ="+ patientModel.getpAddress());
       // System.out.println("Phone "+ patientModel.getpPhone());
    // return "Patient Added Sucessfully";
        //public List<PatientModel> addPatients(@RequestBody PatientModel patientModel){

            //System.out.println("Name ="+ patientModel.getpName());
        //System.out.println("Address ="+ patientModel.getpAddress());
        //System.out.println("Phone "+ patientModel.getpPhone());


        //List<PatientModel> x = new ArrayList<>();
        //x.add(patientModel);
        //return x;


        //public HashMap addPatients(@RequestBody PatientModel patientModel) {

        //System.out.println("Name =" + patientModel.getpName());
        //System.out.println("Address =" + patientModel.getpAddress());
        //System.out.println("Phone " + patientModel.getpPhone());

        // return "Patient Added Sucessfully";
        //HashMap<String, Object> hashmap = new HashMap<>();
        //hashmap.put("status", "success");
        //hashmap.put("data", patientModel);
        //return hashmap;



        public Map<String,Object> addPatients(@RequestBody PatientModel patientModel){

            System.out.println("Name ="+ patientModel.getpName());
            System.out.println("Address ="+ patientModel.getpAddress());
            System.out.println("Phone "+ patientModel.getpPhone());

        List<PatientModel> x = new ArrayList<>();
        x.add(patientModel);
        patientList.add(patientModel);
        Map<String,Object> map = new HashMap<>();
        patientRepository.save(patientModel);
            map.put("status","success");
            map.put("data",x);

            return map;

        }
        @GetMapping(path="view")
        public Map viewPatients(){

            Map<String,Object> map = new HashMap<>();
            map.put("status","success");
            map.put("data",patientList);
            return map;

        }
        @PostMapping  (path="searchPatient", consumes = "application/json", produces = "application/json")
        public Map searchPatients(@RequestBody PatientModel patientModel) {

            String getPhone = patientModel.getpPhone();
            System.out.println(getPhone);
            Map<String,Object> map = new HashMap<>();
            for (PatientModel  patient:patientList
                 ) {
                if(getPhone.equals(patient.getpPhone())){
                    map.put("status","success");
                    map.put("data",patientList);
            }
           else{
               map.put("status","Failed");
                }
            }

            //  map.put("status","success");

            //}
return map;
            //}

         }


    @PostMapping  (path="delete", consumes = "application/json", produces = "application/json")
    public Map deletePatients(@RequestBody PatientModel patientModel) {

        String getPhone = patientModel.getpPhone();
        
        Map<String,Object> map = new HashMap<>();
        for (PatientModel  patient:patientList
        ) {
            if(getPhone.equals (patient.getpPhone())){
                map.put("status","success");
               map.remove(patient);
            }
            else{
                map.put("status","Failed");
            }
        }

        //  map.put("status","success");

        //}
        return map;
        //}

    }
    @GetMapping(path="/views")
    public HashMap viewPatient(){

        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("data",patientRepository.findAll());
        return hashMap;

    }

    @GetMapping(path="/viewed")
    public List viewPatientList(){

       List<PatientModel> patientList = (List<PatientModel>)patientRepository.findAll();
      return patientList;
    }
    @GetMapping(path="/viewPatients")
    public List viewPatientDetails(){

        List<PatientModel> patientList = (List<PatientModel>)patientRepository.getPatientList();
        return patientList;
    }
    @PostMapping(path = "/searchPatients",consumes = "application/json",produces = "application/json")
    public List searchPatientDetails(@RequestBody PatientModel patientModel){
        List<PatientModel> patientList = patientRepository.searchPatient(patientModel.getpPhone());
        return patientList;
    }
@PostMapping(path = "/multiSearch",consumes = "application/json",produces = "application/json")
    public HashMap multiSearchPatients(@RequestBody PatientModel patientModel){
        List<PatientModel> patientList = patientRepository.multiSearch(patientModel.getpPhone(),patientModel.getpName());
       HashMap<String,Object> map = new HashMap<>();
        if(patientList.isEmpty()){
         map.put("status","not Found");
        }
        else{
           map.put("status","Found");
           map.put("data",patientList);

        }
        return map;
    }
    @DeleteMapping(path="/delete",consumes = "application/json",produces = "application/json")
    public HashMap deletePatientRow(@RequestBody PatientModel patientModel){
        patientRepository.DeletePatient(patientModel.getId());
        HashMap<String,Object> map = new HashMap<>();
        map.put("status","delete Success");
        return map;

    }
    @PutMapping(path="/update", consumes = "application/json",produces = "application/json")
    public HashMap updatePatients(@RequestBody PatientModel patientModel){
        patientRepository.Update(patientModel.getId(),patientModel.getpAddress(),patientModel.getpName(),patientModel.getpPhone());
       HashMap<String,Object> map = new HashMap<>();
map.put("status","updated successfully");
return map;
    }
}
