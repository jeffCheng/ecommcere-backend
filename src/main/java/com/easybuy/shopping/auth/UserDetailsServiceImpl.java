package com.easybuy.shopping.auth;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.easybuy.shopping.dto.CustomerModel;
import com.easybuy.shopping.model.Role;
import com.easybuy.shopping.model.User;
import com.easybuy.shopping.service.CustomerService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private CustomerService customerService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = new User();
		CustomerModel customerModel =customerService.getCustomerInfo(username);
		if(customerModel == null ) throw new UsernameNotFoundException("User is not found.");
		System.out.println(customerModel.getEmail()+customerModel.getEncrptPassword());
		user.setUsername(customerModel.getEmail());
		user.setPassword(customerModel.getEncrptPassword());
		
		Set<Role> roles = new HashSet<Role>();
		if(customerModel.getRole() == 0) {
			roles.add(Role.ADMIN);
			roles.add(Role.PM);
			roles.add(Role.CUSTOMER);
		}
		else if(customerModel.getRole() == 1) {
			 roles.add(Role.PM);
			 roles.add(Role.CUSTOMER);
		}
		else roles.add(Role.CUSTOMER);
		
		user.setRoles(roles);
 		return UserPrinciple.build(user);
	}
	
}
