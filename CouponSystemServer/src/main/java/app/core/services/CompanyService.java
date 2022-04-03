package app.core.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import app.core.entities.Category;
import app.core.entities.Company;
import app.core.entities.Coupon;
import app.core.exceptions.CouponSystemException;
import app.core.exceptions.companyExceptions.CompanyAuthenticationException;
import app.core.exceptions.companyExceptions.CompanyNotFoundException;
import app.core.exceptions.couponExceptions.CouponNotFoundException;
import app.core.exceptions.couponExceptions.CouponTitleAlreadyExistsException;
import app.core.repositories.CompanyRepository;
import app.core.repositories.CouponRepository;

/**
 * This class represents the company facade
 *
 * @author Yossi Toohband
 *
 */
@Service
@Transactional
@Primary
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CompanyService extends ClientService {
	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	CouponRepository couponRepository;
	/**
	 * This method checks if the email and password entered are equal to a company's
	 * in database.
	 *
	 * @param email
	 * @param password
	 *
	 * @return if email and password are equal to a company's in database.
	 */
	@Override
	public long login(String email, String password) throws CompanyAuthenticationException {
		Company currentCompany;
		try
		{
			currentCompany = getCompanyByEmail(email);
			if (!(password.equals(currentCompany.getPassword()))) {
				throw new CompanyAuthenticationException("Incorrect password!");
			}
			return currentCompany.getId();
		}catch (CompanyNotFoundException e){
			throw new CompanyAuthenticationException("Incorrect email!");
		}
	}
	public Company getCompanyById(long companyId) throws CompanyNotFoundException {
		Optional<Company> opCom = companyRepository.findById(companyId);
		if (opCom.isPresent()) {
			return opCom.get();
		} else
			throw new CompanyNotFoundException("Error has occurred, Company not Found");
	}

	public Company getCompanyByEmail(String email) throws CompanyNotFoundException {
		Optional<Company> opCom = companyRepository.findByEmail(email);
		if (opCom.isPresent()) {
			return opCom.get();
		} else
			throw new CompanyNotFoundException("Error has occurred, Company no Found");
	}

	/**
	 * This method adds a coupon to database.
	 *
	 * @param coupon
	 * @throws CouponNotFoundException
	 * @throws
	 */
	public long addCoupon(Coupon coupon, long companyId) throws CouponSystemException {
		Company company = getCompanyById(companyId);
		Optional<Coupon> opCoupon = couponRepository.findByCompanyIdAndTitle(companyId, coupon.getTitle());
		if (opCoupon.isPresent()) {
			throw new CouponTitleAlreadyExistsException(
					"Coupon with the same title already exists in this company! Coupon not added.");
		}
		company.addCoupon(coupon);
		return couponRepository.findByCompanyIdAndTitle(companyId, coupon.getTitle()).get().getId();
	}

	/**
	 * This method updates a coupon in database.
	 *
	 * @param coupon
	 */
	public void updateCoupon(Coupon coupon, long companyId) throws CouponSystemException {
		Coupon couponToUpdate = getCouponById(coupon.getId(), companyId);
		if (couponRepository.findByIdIsNotAndCompanyIdAndTitle(coupon.getId(), companyId, coupon.getTitle()).isPresent()){
			throw new CouponTitleAlreadyExistsException(
					"Coupon with the same title already exists in this company! Coupon not added.");
		}
		couponToUpdate.setAmount(coupon.getAmount());
		couponToUpdate.setTitle(coupon.getTitle());
		couponToUpdate.setCategory(coupon.getCategory());
		couponToUpdate.setStartDate(coupon.getStartDate());
		couponToUpdate.setEndDate(coupon.getEndDate());
		couponToUpdate.setImage(coupon.getImage());
		couponToUpdate.setDescription(coupon.getDescription());
		couponToUpdate.setPrice(coupon.getPrice());
	}
	public Coupon getCouponById(long couponId, long companyId) throws CouponNotFoundException {
		Optional<Coupon> opCoupon = couponRepository.findByIdAndCompanyId(couponId, companyId);
		if (opCoupon.isPresent()) {
			return opCoupon.get();
		}
		throw new CouponNotFoundException("No such coupon with id " + couponId + " exists for this company");
	}

	/**
	 * This method deletes a coupon in database.
	 *
	 * @param couponId
	 */
	public void deleteCoupon(long couponId, long companyId) throws CouponNotFoundException {
		Coupon coupToDelete = getCouponById(couponId, companyId);
		couponRepository.delete(coupToDelete);
	}

	/**
	 * This method returns this company's coupons from database.
	 *
	 * @return a List of coupons
	 */
	public List<Coupon> getCompanyCoupons(long companyId) throws CouponSystemException {
		List<Coupon> companyCoupons = couponRepository.findByCompanyId(companyId);
		if (companyCoupons.size() == 0) {
			throw new CouponNotFoundException("No coupons available for this company");
		}
		return companyCoupons;
	}

	/**
	 * This method returns this company's coupons from database by category.
	 *
	 * @param category
	 * @throws CouponNotFoundException
	 *
	 * @return a List of coupons
	 */
	public List<Coupon> getCompanyCoupons(Category category, long companyId) throws CouponSystemException {
		Company currentCompany = getCompanyDetails(companyId);
		List<Coupon> companyCoupons = couponRepository.findByCompanyIdAndCategory(companyId, category);
		if (companyCoupons.size() == 0) {
			throw new CouponNotFoundException(
					"No such coupons with category " + category.name() + " are available for this company");
		}
		return companyCoupons;
	}

	/**
	 * This method returns this company's coupons from database by max price.
	 *
	 * @param maxPrice
	 * @throws CouponNotFoundException
	 *
	 * @return a List of coupons
	 */
	public List<Coupon> getCompanyCoupons(double maxPrice, long companyId) throws CouponSystemException {
		Company currentCompany = getCompanyDetails(companyId);
		List<Coupon> companyCoupons = couponRepository.findByCompanyIdAndPriceLessThanEqual(companyId, maxPrice);
		if (companyCoupons.size() == 0) {
			throw new CouponNotFoundException(
					"No such coupons under the price: " + maxPrice + " are available for this company");
		}
		return companyCoupons;
	}

	/**
	 * This method returns this company Object.
	 *
	 * @return a company Object
	 */
	public Company getCompanyDetails(long companyId) throws CompanyNotFoundException {
		return getCompanyById(companyId);
	}
	public long getCurrentCompanyId(long companyId) {
		return companyId;
	}
}
