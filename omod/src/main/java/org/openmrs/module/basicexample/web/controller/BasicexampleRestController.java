package org.openmrs.module.basicexample.web.controller;

import org.openmrs.module.basicexample.Department;
import org.openmrs.module.basicexample.api.BasicexampleService;
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
	
	@RequestMapping(value = "/department/{id}", method = RequestMethod.GET)
	public ResponseEntity<Department> getDepartmentById(@PathVariable Integer id) {
		Department department = basicexampleService.getDepartmentById(id);
		return new ResponseEntity<Department>(department, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/department/{uuid}", method = RequestMethod.GET)
	public ResponseEntity<Department> getDepartmentByUuid(@PathVariable String uuid) {
		Department department = basicexampleService.getDepartmentByUuid(uuid);
		return new ResponseEntity<Department>(department, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/department", method = RequestMethod.POST)
	public ResponseEntity<Department> saveDepartment(@RequestBody Department department) {
		Department savedDepartment = basicexampleService.saveDepartment(department);
		return new ResponseEntity<Department>(savedDepartment, HttpStatus.OK);
	}
}
