package it.epicode.be.controller.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/admin")
	public String helloAdmin() {
		return "Welcome Admin to Epicode School";
	}

	@GetMapping("/user")
	public String helloUser() {
		return "Welcome User to Epicode School";
	}

	@GetMapping("/public")
	public String helloPublic() {
		return "Welcome Everybody to Epicode School";
	}

}
