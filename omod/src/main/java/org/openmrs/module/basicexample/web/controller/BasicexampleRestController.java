package org.openmrs.module.basicexample.web.controller;

import org.openmrs.api.APIException;
import org.openmrs.module.basicexample.Department;
import org.openmrs.module.basicexample.api.BasicexampleService;
import org.openmrs.module.basicexample.response.DepartmentResponse;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.v1_0.controller.MainResourceController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller(value = "basicexampleRestController")
@RequestMapping(value = "/rest/" + RestConstants.VERSION_1 + "/" + "basicexample")
public class BasicexampleRestController extends MainResourceController {
	
	@Autowired
	private BasicexampleService basicexampleService;
	
	@RequestMapping(value = "/department/find/by/id/{id}", method = RequestMethod.GET)
	public ResponseEntity<DepartmentResponse> getDepartmentById(@PathVariable Integer id) {
		Department department = basicexampleService.getDepartmentById(id);
		DepartmentResponse departmentResponse = new DepartmentResponse();
		if (department != null) {
			departmentResponse.setDepartmentName(department.getDepartmentName());
			departmentResponse.setLocation(department.getLocation());
			departmentResponse.setDateCreated(department.getDateCreated());
			departmentResponse.setPatientSafetyMeasures(department.getPatientSafetyMeasures());
			departmentResponse.setLengthofStay(department.getLengthOfStay());
			departmentResponse.setPatientSafety(department.getPatientSafety());
			
			return new ResponseEntity<>(departmentResponse, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new DepartmentResponse(), HttpStatus.NO_CONTENT);
		}
	}
	
	@RequestMapping(value = "/department/find/by/uuid/{uuid}", method = RequestMethod.GET)
	public ResponseEntity<DepartmentResponse> getDepartmentByUuid(@PathVariable String uuid) {
		Department department = basicexampleService.getDepartmentByUuid(uuid);
		DepartmentResponse departmentResponse = new DepartmentResponse();
		if (department != null) {
			departmentResponse.setDepartmentName(department.getDepartmentName());
			departmentResponse.setLocation(department.getLocation());
			departmentResponse.setDateCreated(department.getDateCreated());
			departmentResponse.setPatientSafetyMeasures(department.getPatientSafetyMeasures());
			departmentResponse.setLengthofStay(department.getLengthOfStay());
			departmentResponse.setPatientSafety(department.getPatientSafety());
			
			return new ResponseEntity<>(departmentResponse, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new DepartmentResponse(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/department/save", method = RequestMethod.POST)
	public ResponseEntity<DepartmentResponse> saveDepartment(@RequestBody Department department) {
		try {
			Department savedDepartment = basicexampleService.saveDepartment(department);
			DepartmentResponse departmentResponse = new DepartmentResponse();
			departmentResponse.setDepartmentName(savedDepartment.getDepartmentName());
			departmentResponse.setLocation(savedDepartment.getLocation());
			departmentResponse.setDateCreated(savedDepartment.getDateCreated());
			departmentResponse.setPatientSafetyMeasures(savedDepartment.getPatientSafetyMeasures());
			departmentResponse.setLengthofStay(savedDepartment.getLengthOfStay());
			departmentResponse.setPatientSafety(savedDepartment.getPatientSafety());
			
			return new ResponseEntity<>(departmentResponse, HttpStatus.OK);
		}
		catch (APIException exception) {
			throw new APIException(exception.getMessage());
		}
	}
	
	@RequestMapping(value = "/department/safety-measures/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> getPatientSafetyMeasuresByDepartmentId(@PathVariable Integer id) {
		String patientSafetyMeasures = basicexampleService.getPatientSafetyMeasuresByDepartmentId(id);
		if (patientSafetyMeasures != null) {
			return new ResponseEntity<>(patientSafetyMeasures, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/find/length/of/stay/by/id{id}", method = RequestMethod.GET)
	public ResponseEntity<String> getLengthOfStayByDepartmentId(@PathVariable Integer id) {
		String lengthOfStay = basicexampleService.getLengthOfStayByDepartmentId(id);
		if (lengthOfStay != null) {
			return new ResponseEntity<>(lengthOfStay, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/find/patient/safety/by/id/{id}", method = RequestMethod.GET)
	public ResponseEntity<String> getPatientSafetyByDepartmentId(@PathVariable Integer id) {
		String patientSafety = basicexampleService.getPatientSafetyByDepartmentId(id);
		if (patientSafety != null) {
			return new ResponseEntity<>(patientSafety, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Error retrieving patient safety for department ID: ", HttpStatus.NO_CONTENT);
		}
	}
	
	@RequestMapping(value = "/update/patients/safety/by/id/{id}", method = RequestMethod.POST)
	public ResponseEntity<String> updatePatientSafetyByDepartmentId(@PathVariable Integer id,
	        @RequestBody String newPatientSafety) {
		String patientSafety = basicexampleService.updatePatientSafetyByDepartmentId(id, newPatientSafety);
		
		if (patientSafety.equals("Patient safety updated successfully.")) {
			return ResponseEntity.ok(patientSafety);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(patientSafety);
		}
	}
}
