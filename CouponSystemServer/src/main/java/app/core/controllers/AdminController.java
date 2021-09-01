package app.core.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import app.core.entities.Company;
import app.core.entities.Customer;
import app.core.exceptions.CouponSystemException;
import app.core.exceptions.companyExceptions.CompanyAlreadyExistsException;
import app.core.exceptions.companyExceptions.CompanyException;
import app.core.exceptions.companyExceptions.CompanyNotFoundException;
import app.core.exceptions.customerExceptions.CustomerAlreadyExistsException;
import app.core.exceptions.customerExceptions.CustomerException;
import app.core.exceptions.customerExceptions.CustomerNotFoundException;
import app.core.responses.AdminErrorResponse;
import app.core.services.AdminService;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")

public class AdminController{

	@Autowired
	private AdminService adminService;
	


	@PostMapping(path = "/company/add", consumes = "application/json", produces = "application/json")
	public long addCompany(@RequestBody Company company) throws CompanyAlreadyExistsException {
		try {
			return adminService.addCompany(company);
		} catch (CompanyAlreadyExistsException e) {
			e.setStatus(HttpStatus.BAD_REQUEST);
			throw e;
		}
	}
	@PutMapping(path = "/company/update", consumes = "application/json", produces = "application/json")
	public void updateCompany(@RequestBody Company comapny) throws CompanyException {
		try {
			adminService.updateCompany(comapny);
		} catch (CompanyException e) {
			e.setStatus((e instanceof CompanyAlreadyExistsException) ? HttpStatus.BAD_REQUEST : HttpStatus.NOT_FOUND);
			throw e;
		}
	}
	@DeleteMapping(path = "/company/{companyId}", produces = "application/json")
	public long deleteCompany(@PathVariable long companyId) throws CompanyNotFoundException {
		try {
			adminService.deleteCompany(companyId);
			return companyId;
		} catch (CompanyNotFoundException e) {
			e.setStatus(HttpStatus.NOT_FOUND);
			throw e;
		}
	}

	@GetMapping(path = "/company/all", produces = "application/json")
	public List<Company> getAllCompanies() {
		return adminService.getAllCompanies();
	}

	@GetMapping(path = "/company/get-one", produces = "application/json")
	public Company getOneCompany(@RequestParam long companyId) throws CompanyNotFoundException {
		try {
			return adminService.getCompany(companyId);
		} catch (CompanyNotFoundException e) {
			e.setStatus(HttpStatus.NOT_FOUND);
			throw e;
		}
	}
	@PostMapping(path = "/customer/add", consumes = "application/json", produces = "application/json")
	public long addCustomer(@RequestBody Customer customerToAdd) throws CustomerAlreadyExistsException {
		try {
			return adminService.addCustomer(customerToAdd);
		} catch (CustomerAlreadyExistsException e) {
			e.setStatus(HttpStatus.BAD_REQUEST);
			throw e;
		}
	}
	@GetMapping(path = "/customer/all", produces = "application/json")
	public List<Customer> getAllCustomers() {
		return adminService.getAllCustomer();
	}

	@PutMapping(path = "/customer/update", consumes = "application/json", produces = "application/json")
	public long updateCustomer(@RequestBody Customer customer) throws CustomerException {
		try {
			adminService.updateCustomer(customer);
			return customer.getId();
		} catch (CustomerException e) {
			e.setStatus((e instanceof CustomerAlreadyExistsException) ? HttpStatus.BAD_REQUEST : HttpStatus.NOT_FOUND);
			throw e;
		}
	}
	@DeleteMapping(path = "/customer/{customerId}", produces = "application/json")
	public long deleteCustomer(@PathVariable long customerId) throws CustomerNotFoundException {
		try {
			return adminService.deleteCustomer(customerId);
		} catch (CustomerNotFoundException e) {
			e.setStatus(HttpStatus.NOT_FOUND);
			throw e;
		}
	}
	@GetMapping(path = "/customer/get-one", produces = "application/json")
	public Customer getOneCustomer(@RequestParam int customerId) throws CustomerNotFoundException {
		try {
			return adminService.getCustomer(customerId);
		} catch (CustomerNotFoundException e) {
			e.setStatus(HttpStatus.NOT_FOUND);
			throw e;
		}
	}
	@ExceptionHandler
	private ResponseEntity<AdminErrorResponse> handleException(CouponSystemException e) {
		AdminErrorResponse error = new AdminErrorResponse();
		error.setMessage(e.getMessage());
		error.setStatus(e.getStatus().value());
		error.setTimestamp(LocalDateTime.now());
		return ResponseEntity.status(e.getStatus()).body(error);
	}
}