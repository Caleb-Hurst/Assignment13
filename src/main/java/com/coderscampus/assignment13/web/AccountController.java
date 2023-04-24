package com.coderscampus.assignment13.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coderscampus.assignment13.domain.Account;
import com.coderscampus.assignment13.domain.User;
import com.coderscampus.assignment13.service.AccountService;
import com.coderscampus.assignment13.service.UserService;

@Controller
public class AccountController {

	@Autowired
	private AccountService accountService;
	@Autowired
	private UserService userService;

	@GetMapping("/users/{userId}/account/{accountId}")
	public String getAccountPage(@PathVariable("accountId") Long accountId, @PathVariable("userId") Long userId, Model model) {
		Account account = accountService.findAccountById(accountId);
		model.addAttribute("account", account);
		model.addAttribute("accountName", account.getAccountName());
		model.addAttribute("userId", userId);
		return "account";
	}
	
	@GetMapping("/users/{userId}/account")
	public String getCreateNewAccount (@PathVariable("userId") Long userId, ModelMap model) {		
		User user = userService.findById(userId);
		model.put("account", new Account());
		model.put("user", user);
		
		return "account";
	}
	@PostMapping("/users/{userId}/account")
	public String postCreateNewAccount(@PathVariable("userId") Long userId, Account account) {	
		User user = userService.findById(userId);
		user.getAccounts().add(account);
	    accountService.saveAccount(account);	   
	    return "redirect:/users/" + userId ;
	}
	@PostMapping("/users/{userId}/account/{accountId}")
	public String postUpdateAccount(@PathVariable("userId") Long userId, Account account) {	
			
	    accountService.saveAccount(account);	   
	    return "redirect:/users/" + userId ;
	}
	

}
