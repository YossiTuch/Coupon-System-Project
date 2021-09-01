package app.core.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import app.core.entities.Company;
import app.core.entities.Coupon;
import app.core.entities.Customer;
import app.core.exceptions.adminExceptions.AdminAuthenticationException;
import app.core.exceptions.companyExceptions.CompanyAlreadyExistsException;
import app.core.exceptions.companyExceptions.CompanyException;
import app.core.exceptions.companyExceptions.CompanyNotFoundException;
import app.core.exceptions.couponExceptions.CouponNotFoundException;
import app.core.exceptions.customerExceptions.CustomerAlreadyExistsException;
import app.core.exceptions.customerExceptions.CustomerException;
import app.core.exceptions.customerExceptions.CustomerNotFoundException;

/**
 * This class represents the Admin Service
 * 
 * @author Yossi and Avi Tuchband
 *
 */
// need to change the delete and update to use the find method
@Service
@Transactional
@Primary
public class AdminService extends ClientService {
	private final long SUCCESSFUL_LOGIN = 1;
	/**
	 * This method checks if the parameters entered are equal to the admin's
	 * credentials.
	 * 
	 * @param email
	 * @param password
	 * 
	 * @return if the parameters are equal to to admin's credentials.
	 */
	@Override
	public long login(String email, String password) throws AdminAuthenticationException {
		if (!(email.equals("admin") && password.equals("admin"))) {
			throw new AdminAuthenticationException("Admin Username or Password invalid");
		}
		return SUCCESSFUL_LOGIN;
	}
	public boolean isCompanyEmailTaken(long companyId, String email){
		return companyRepository.findByIdIsNotAndEmail(companyId, email).isPresent();
	}
	public boolean isCustomerEmailTaken(long  customerId, String email){
		return customerRepository.findByIdIsNotAndEmail(customerId, email).isPresent();
	}
	/**
	 * This method adds a company to database
	 * 
	 * @param companyToAdd
	 */
	public long addCompany(Company companyToAdd) throws CompanyAlreadyExistsException {
		Optional<Company> opCom = companyRepository.findByEmailOrName(companyToAdd.getEmail(), companyToAdd.getName());
		if(opCom.isPresent()){
			throw new CompanyAlreadyExistsException("Company with the same email or name already exists!");
		}
		companyRepository.save(companyToAdd);
		return companyToAdd.getId();
	}
	/**
	 * This method updates a company in database.
	 * 
	 * @param companyDetails - Holds the details to update the company
	 */
	public void updateCompany(Company companyDetails) throws CompanyException {
		if(isCompanyEmailTaken(companyDetails.getId(), companyDetails.getEmail())){
			throw new CompanyAlreadyExistsException("This email is already taken");
		}
		Company comToUpdate = getCompany(companyDetails.getId());
		comToUpdate.setEmail(companyDetails.getEmail());
		comToUpdate.setPassword(companyDetails.getPassword());
	}
	/**
	 * This method deletes a company from database.
	 * 
	 * @param companyId
	 */
	public void deleteCompany(long companyId) throws CompanyNotFoundException {
		Company comToDelete = getCompany(companyId);
		companyRepository.delete(comToDelete);
	}

	/**
	 * This method returns a List of all companies in database.
	 * 
	 * @return a List of companies
	 */
	public List<Company> getAllCompanies() {
		return companyRepository.findAll();
	}

	/**
	 * This method returns a company from database by id.
	 * 
	 * @param companyId
	 * @return a company Object
	 */
	public Company getCompany(long companyId) throws CompanyNotFoundException {
		Optional<Company> opt = companyRepository.findById(companyId);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new CompanyNotFoundException("Error has occurred, Company not found.");
		}
	}

	/**
	 * This method adds a customer to database.
	 * 
	 * @param customerToAdd
	 */
	public long addCustomer(Customer customerToAdd) throws CustomerAlreadyExistsException{
//		List<Coupon> coupons = customerToAdd.getCoupons();
		customerToAdd.setCoupons(null);
		if(isCustomerEmailTaken(customerToAdd.getId(), customerToAdd.getEmail())){
			throw new CustomerAlreadyExistsException("Customer with the same email: ("
													+ customerToAdd.getEmail() +
														")  already exists");
		}
		customerRepository.save(customerToAdd);
		return customerToAdd.getId();

	}
	public Coupon findCouponById(long couponId) throws CouponNotFoundException {
		Optional<Coupon> opCoup = couponRepository.findById(couponId);
		if (opCoup.isPresent()) {
			return opCoup.get();
		}
		throw new CouponNotFoundException("Error occurred, no such coupon id: " + couponId + " is available");
	}

	/**
	 * This method updates a customer in database.
	 * 
	 * @param customerDetails - Holds the details to update the customer
	 */
	public void updateCustomer(Customer customerDetails) throws CustomerException {
		if(isCustomerEmailTaken(customerDetails.getId(), customerDetails.getEmail())){
			throw new CustomerAlreadyExistsException("Customer with the same email: ("
														+ customerDetails.getEmail() +
															")  already exists");
		}
		Customer customerToUpdate = getCustomer(customerDetails.getId());
		customerToUpdate.setFirstName(customerDetails.getFirstName());
		customerToUpdate.setLastName(customerDetails.getLastName());
		customerToUpdate.setEmail(customerDetails.getEmail());
		customerToUpdate.setPassword(customerDetails.getPassword());
	}
	/**
	 * This method deletes a customer from database and deletes the customer's
	 * purchases.
	 * 
	 * @param customerId
	 */
	public long deleteCustomer(long customerId) throws CustomerNotFoundException {
		Customer customerToDelete = getCustomer(customerId);
		customerRepository.delete(customerToDelete);
		return customerId;
	}

	/**
	 * This method returns a customer from database by customer id.
	 * 
	 * @param customerId
	 * @return a customer Object
	 */
	public Customer getCustomer(long customerId) throws CustomerNotFoundException {
		Optional<Customer> opt = customerRepository.findById(customerId);
		if (opt.isPresent()) {
			return opt.get();
		}
		throw new CustomerNotFoundException("Error has occurred, Customer not found.");
	}

	/**
	 * This method returns a List of all customers from database.
	 * 
	 * @return a List of customers
	 */
	public List<Customer> getAllCustomer() {
		return customerRepository.findAll();
	}
}
