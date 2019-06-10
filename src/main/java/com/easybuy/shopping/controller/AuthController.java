package com.easybuy.shopping.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.easybuy.shopping.auth.jwt.JwtProvider;
import com.easybuy.shopping.auth.response.JwtResponse;
import com.easybuy.shopping.controller.response.CommonReturnType;
import com.easybuy.shopping.dto.CustomerModel;
import com.easybuy.shopping.error.BusinessException;
import com.easybuy.shopping.error.EmBusinessError;
import com.easybuy.shopping.model.User;
import com.easybuy.shopping.service.CustomerService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
public class AuthController extends BaseController{
	
	
    @Autowired
    private AuthenticationManager authenticationManager;
    
	@Autowired
	private CustomerService customerService;
	
    @Autowired
    private JwtProvider jwtProvider;
    
    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                		user.getUsername(),
                		user.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwt = jwtProvider.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }
    
    
    @PostMapping("/signup")
    public CommonReturnType registerUser(@Valid @RequestBody CustomerModel customerModel) throws BusinessException {
        if (customerService.getCustomerInfo(customerModel.getEmail())!= null) {
        	throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }
		customerModel.setEncrptPassword(encoder.encode(customerModel.getPassword()));
		customerService.registerCustomer(customerModel);
		return CommonReturnType.create(customerModel);
    }
}
