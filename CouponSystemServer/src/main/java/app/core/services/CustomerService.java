package app.core.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import app.core.entities.Category;
import app.core.entities.Coupon;
import app.core.entities.Customer;
import app.core.exceptions.CouponSystemException;
import app.core.exceptions.couponExceptions.CouponAlreadyPurchasedException;
import app.core.exceptions.couponExceptions.CouponDateExpiredException;
import app.core.exceptions.couponExceptions.CouponNotFoundException;
import app.core.exceptions.couponExceptions.CouponOutOfStockException;
import app.core.exceptions.customerExceptions.CustomerAuthenticationException;
import app.core.exceptions.customerExceptions.CustomerNotFoundException;

@Service
@Transactional
@Primary
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CustomerService extends ClientService {
	/**
	 * This method checks if the email and password entered are equal to a
	 * customer's in database.
	 *
	 * @param email
	 * @param password
	 *
	 * @return if email and password are equal to a customer's in database.
	 */
	@Override
	public long login(String email, String password) throws CustomerAuthenticationException {
		Customer currentCustomer;
		try
		{
		currentCustomer= getByEmail(email);
			if (!(password.equals(currentCustomer.getPassword()))) {
				throw new CustomerAuthenticationException("Incorrect password!");
			}
			return currentCustomer.getId();
		}catch (CustomerNotFoundException e){
			throw new CustomerAuthenticationException("Incorrect email!");
		}

	}
	private Customer getById(long customerId) throws CustomerNotFoundException {
		Optional<Customer> opCustomer = customerRepository.findById(customerId);
		if (opCustomer.isPresent()) {
			return opCustomer.get();
		}
		throw new CustomerNotFoundException("Error has occurred, no such customer exists");
	}
	private Customer getByEmail(String email) throws CustomerNotFoundException {
		Optional<Customer> opCustomer = customerRepository.findByEmail(email);
		if (opCustomer.isPresent()) {
			return opCustomer.get();
		}
		throw new CustomerNotFoundException("Error has occurred, no such customer exists");
	}
	/**
	 * This method add coupon purchase to database.
	 *
	 * @param couponId
	 */
	public long purchaseCoupon(long couponId, long customerId) throws CouponSystemException {
		Customer currentCustomer = getCustomerDetails(customerId);
		Coupon couponToPurchase = getCouponById(couponId);
		if(couponToPurchase.getAmount() <= 0){
			throw new CouponOutOfStockException("Coupon out of stock!");
		}
		if(couponToPurchase.getEndDate().isBefore(LocalDate.now())) {
			throw new CouponDateExpiredException("Coupon's Date Expired!");
		}
		if (couponRepository.findByIdAndBuyersListId(couponId, customerId).isPresent()){
			throw new CouponAlreadyPurchasedException("Customer already has this coupon");
		}
		couponToPurchase.setAmount(couponToPurchase.getAmount() - 1);
		currentCustomer.addCoupon(couponToPurchase);
		return couponId;
	}
	public Coupon getCouponById(long couponId) throws CouponNotFoundException {
		Optional<Coupon> opCoup = couponRepository.findById(couponId);
		if(opCoup.isPresent()){
			return opCoup.get();
		}
		throw new CouponNotFoundException("Error occurred, no such coupon id: " + couponId + " is available");
	}
	public Coupon getCustomerCouponById(long couponId, long customerId) throws CouponNotFoundException {
		Optional<Coupon> opCoup = couponRepository.findByIdAndBuyersListId(couponId, customerId);
			if(opCoup.isPresent()){
				return opCoup.get();
			}
			throw new CouponNotFoundException("Not such coupon for this customer");
	}
	/**
	 * This method returns a list of this customer's coupons.
	 *
	 * @return a List of coupons
	 */
	public List<Coupon> getCustomerCoupons(long customerId) throws CouponSystemException {
		List<Coupon> customerCoupons = couponRepository.findByBuyersListId(customerId);
		if(customerCoupons.size() == 0 ){
			throw new CouponNotFoundException("No coupons are available for this customer");
		}
		return customerCoupons;
	}
	/**
	 * This method returns a list of this customer's coupons by category.
	 *
	 * @param category
	 * @return a List of coupons
	 */
	public List<Coupon> getCustomerCoupons(Category category, long customerId) throws CouponSystemException {
		List<Coupon> customerCoupons = couponRepository.findByBuyersListIdAndCategory(customerId, category);
		if(customerCoupons.size() == 0 ){
			throw new CouponNotFoundException("No such coupons with category "  +category.name() + " are available for this customer");
		}
		return customerCoupons;
	}
	/**
	 * This method returns a list of this customer's coupons by max price.
	 *
	 * @param maxPrice
	 * @return a List of coupons
	 */
	public List<Coupon> getCustomerCoupons(double maxPrice, long customerId) throws CouponSystemException {
		List<Coupon> customerCoupons = couponRepository.findByBuyersListIdAndPriceLessThanEqual(customerId, maxPrice);
		if(customerCoupons.size() == 0 ){
			throw new CouponNotFoundException("No such coupons under the price: "  +maxPrice + " are available for this customer");
		}
		return customerCoupons;
	}
	/**
	 * This method returns this customer Object.
	 *
	 * @return a customer Object
	 */
	public Customer getCustomerDetails(long customerId) throws CustomerNotFoundException {
		return getById(customerId);
	}
}
