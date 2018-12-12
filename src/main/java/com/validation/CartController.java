package com.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {

	@Autowired
	private ValidatorImpl validator;

	@RequestMapping(value = "/shopping")
	public String buyerCart(@RequestBody User user) throws Exception {
		ValidationResult result = validator.validate(user);
		if(result.isHasErrors()){
			result.getErrorMsgMap();
		}
		return "";
	}

}
