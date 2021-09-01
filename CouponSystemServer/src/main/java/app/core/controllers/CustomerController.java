package app.core.controllers;

import app.core.entities.Category;
import app.core.entities.Coupon;
import app.core.exceptions.CouponSystemException;
import app.core.exceptions.couponExceptions.CouponNotFoundException;
import app.core.exceptions.customerExceptions.CustomerNotFoundException;
import app.core.responses.CustomerErrorResponse;
import app.core.services.CustomerService;
import app.core.services.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin("*")

public class CustomerController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomerService customerService;

    @GetMapping(path = "/coupon", produces = "application/json")
    public Coupon getOne(@RequestParam long couponId, @RequestParam long customerId) throws CouponNotFoundException {
        try {
            return customerService.getCustomerCouponById(couponId, customerId);
        } catch (CouponNotFoundException e) {
            e.setStatus(HttpStatus.NOT_FOUND);
            throw e;
        }
    }
    @GetMapping(path = "/coupon/all", produces = "application/json")
    public List<Coupon> getAllCoupons(@RequestHeader String token) throws CouponSystemException {
        try {
            long customerId = jwtUtil.extractId(token);
            return customerService.getCustomerCoupons(customerId);
        } catch (CouponSystemException e) {
            e.setStatus(HttpStatus.NOT_FOUND);
            throw e;
        }
    }
    @GetMapping(path = "/coupon/all-by-category", produces = "application/json")
    public List<Coupon> getAllCouponsCategory(@RequestParam Category category, @RequestHeader String token) throws CouponSystemException {
        try {
            long customerId = jwtUtil.extractId(token);
            return customerService.getCustomerCoupons(category, customerId);
        } catch (CouponSystemException e) {
            e.setStatus(HttpStatus.NOT_FOUND);
            throw e;
        }
    }
    @GetMapping(path = "/coupon/all-by-max-price", produces = "application/json")
    public List<Coupon> getAllCouponsMaxPrice(@RequestParam double maxPrice, @RequestHeader String token) throws CouponSystemException {
        try {
            long customerId = jwtUtil.extractId(token);
            return customerService.getCustomerCoupons(maxPrice, customerId);
        } catch (CouponSystemException e) {
            e.setStatus(HttpStatus.NOT_FOUND);
            throw e;
        }
    }
    @GetMapping(path = "/get-one", produces = "application/json")
    public Coupon getCoupon(@RequestParam long couponId) throws CouponNotFoundException{
        try {
            return customerService.getCouponById(couponId);
        } catch (CouponNotFoundException e) {
            e.setStatus(HttpStatus.NOT_FOUND);
            throw e;
        }
    }
    @PostMapping()
    public long purchaseCoupon(@RequestParam long couponId, @RequestHeader String token) throws CouponSystemException {
        try {
            long customerId = jwtUtil.extractId(token);
            return customerService.purchaseCoupon(couponId,customerId);
        } catch (CouponSystemException e) {
            e.setStatus((
                    (  e instanceof CustomerNotFoundException
                            || e instanceof CouponNotFoundException)
                            ? HttpStatus.NOT_FOUND : HttpStatus.BAD_REQUEST
            ));
            throw e;
        }
    }
    @ExceptionHandler
    private ResponseEntity<CustomerErrorResponse> handleException(CouponSystemException e) {
        CustomerErrorResponse error = new CustomerErrorResponse();
        error.setMessage(e.getMessage());
        error.setStatus(e.getStatus().value());
        error.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(e.getStatus()).body(error);
    }
}
