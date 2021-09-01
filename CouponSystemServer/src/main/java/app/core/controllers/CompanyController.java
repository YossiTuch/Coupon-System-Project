package app.core.controllers;

import java.time.LocalDateTime;
import java.util.*;

import app.core.entities.Category;
import app.core.exceptions.couponExceptions.CouponNotFoundException;
import app.core.services.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import app.core.entities.Coupon;
import app.core.exceptions.CouponSystemException;
import app.core.exceptions.companyExceptions.CompanyNotFoundException;
import app.core.responses.CompanyErrorResponse;
import app.core.services.CompanyService;

@RestController
@RequestMapping("/api/company")
@CrossOrigin("*")
public class CompanyController {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private CompanyService companyService;

	@GetMapping(path = "/coupon", produces = "application/json")
	public Coupon getCoupon(@RequestParam long couponId, @RequestHeader String token) throws CouponNotFoundException{
		try {
			return companyService.getCouponById(couponId, getCompanyIdFromToken(token));
		} catch (CouponNotFoundException e) {
			e.setStatus(HttpStatus.NOT_FOUND);
			throw e;
		}
	}
	@GetMapping(path = "/coupon/all" , produces = "application/json")
	public List<Coupon> getAllCoupons(@RequestHeader String token) throws CouponSystemException {
		try {
			return companyService.getCompanyCoupons(getCompanyIdFromToken(token));
		} catch (CouponSystemException e) {
			e.setStatus(HttpStatus.NOT_FOUND);
			throw e;
		}
	}
	@GetMapping(path = "/coupon/all-of-company/{companyId}" , produces = "application/json")
	public List<Coupon> getAllCouponsWithCompanyId(@PathVariable long companyId) throws CouponSystemException {
		try {
			return companyService.getCompanyCoupons(companyId);
		} catch (CouponSystemException e) {
			e.setStatus(HttpStatus.NOT_FOUND);
			throw e;
		}
	}
	@GetMapping(path = "/coupon/all-by-category" , produces = "application/json")
	public List<Coupon> getAllCouponsCategory(@RequestParam Category category, @RequestHeader String token) throws CouponSystemException {
		try {
			return companyService.getCompanyCoupons(category, getCompanyIdFromToken(token));
		} catch (CouponSystemException e) {
			e.setStatus(HttpStatus.NOT_FOUND);
			throw e;
		}
	}
	@GetMapping(path = "/coupon/all-by-max-price" , produces = "application/json")
	public List<Coupon> getAllCouponsMaxPrice(@RequestParam double maxPrice, @RequestHeader String token) throws CouponSystemException {
		try {
			return companyService.getCompanyCoupons(maxPrice, getCompanyIdFromToken(token));
		} catch (CouponSystemException e) {
			e.setStatus(HttpStatus.NOT_FOUND);
			throw e;
		}
	}
	@PostMapping(path = "/coupon", consumes = "application/json", produces = "application/json")
	public long addCoupon(@RequestBody Coupon coupon, @RequestHeader String token) throws CouponSystemException {
		try {
			return companyService.addCoupon(coupon, getCompanyIdFromToken(token));
		} catch (CouponSystemException e) {
			e.setStatus((e instanceof CompanyNotFoundException)? HttpStatus.NOT_FOUND : HttpStatus.BAD_REQUEST);
			throw e;
		}
	}
	@PutMapping(path = "/coupon/", consumes = "application/json", produces = "application/json")
	public long updateCoupon(@RequestBody Coupon coupon, @RequestHeader String token) throws CouponSystemException {
		try {
			companyService.updateCoupon(coupon, getCompanyIdFromToken(token));
			return coupon.getId();
		} catch (CouponSystemException e) {
			e.setStatus((e instanceof CompanyNotFoundException)? HttpStatus.NOT_FOUND : HttpStatus.BAD_REQUEST);
			throw e;
		}
	}
	@DeleteMapping(path = "/coupon/{couponId}", produces = "application/json")
	public  long deleteCoupon(@PathVariable long couponId, @RequestHeader String token) throws CouponNotFoundException{
		try {
			companyService.deleteCoupon(couponId, getCompanyIdFromToken(token));
			return couponId;
		} catch (CouponNotFoundException e) {
			e.setStatus(HttpStatus.NOT_FOUND);
			throw e;
		}
	}
	private long getCompanyIdFromToken(String token) {
		return jwtUtil.extractId(token);
	}
	@ExceptionHandler
	private ResponseEntity<CompanyErrorResponse> handleException(CouponSystemException e) {
		CompanyErrorResponse error = new CompanyErrorResponse();
		error.setMessage(e.getMessage());
		error.setStatus(e.getStatus().value());
		error.setTimestamp(LocalDateTime.now());
		return ResponseEntity.status(e.getStatus()).body(error);
	}
}
