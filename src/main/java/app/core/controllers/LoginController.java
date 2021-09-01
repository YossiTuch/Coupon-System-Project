package app.core.controllers;


import app.core.exceptions.AuthenticationException;
import app.core.exceptions.adminExceptions.AdminAuthenticationException;
import app.core.exceptions.companyExceptions.CompanyAuthenticationException;
import app.core.exceptions.customerExceptions.CustomerAuthenticationException;
import app.core.responses.AuthenticationResponse;
import app.core.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class LoginController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AdminService adminService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CustomerService customerService;

    @PostMapping(produces = "application/json")
    public String login(@RequestParam String email, @RequestParam String password, @RequestParam ClientType.CType ct)
            throws AuthenticationException {
        JwtUtil.UserDetails userDetails;
        switch (ct) {
            case ADMIN:
                try {

                    userDetails = new JwtUtil.UserDetails(adminService.login(email, password),
                                                            email, ClientType.CType.ADMIN);
                } catch (AdminAuthenticationException e) {
                    e.setStatus(HttpStatus.UNAUTHORIZED);
                    throw e;
                }
                break;
            case COMPANY:
                try {

                    userDetails = new JwtUtil.UserDetails(companyService.login(email, password),
                                                                email, ClientType.CType.COMPANY);
                }catch(CompanyAuthenticationException e) {
                    e.setStatus(HttpStatus.UNAUTHORIZED);
                    throw e;
                }
                break;
            case CUSTOMER:
                try{

                userDetails = new JwtUtil.UserDetails(customerService.login(email, password),
                                                        email, ClientType.CType.CUSTOMER);
                }catch (CustomerAuthenticationException e){
                    e.setStatus(HttpStatus.UNAUTHORIZED);
                    throw e;
                }
                break;
            default:
                throw new AuthenticationException("No such client type!");
        }
        return jwtUtil.generateToken(userDetails);
    }
    @ExceptionHandler
    private ResponseEntity<AuthenticationResponse> exceptionHandler(AuthenticationException e){
        AuthenticationResponse error = new AuthenticationResponse();
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.UNAUTHORIZED.value());
        error.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }
}

