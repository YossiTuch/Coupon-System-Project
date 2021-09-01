package app.core.services;

import org.springframework.beans.factory.annotation.Autowired;

import app.core.exceptions.CouponSystemException;
import app.core.repositories.CompanyRepository;
import app.core.repositories.CouponRepository;
import app.core.repositories.CustomerRepository;

/**
 * This class represents the super class of the clients of the system.
 * 
 * @author Yossi and Avi Tuchband
 *
 */
public abstract class ClientService {

	@Autowired
	protected CompanyRepository companyRepository;
	@Autowired
	protected CustomerRepository customerRepository;
	@Autowired
	protected CouponRepository couponRepository;

	public abstract long login(String email, String password) throws CouponSystemException;
}
