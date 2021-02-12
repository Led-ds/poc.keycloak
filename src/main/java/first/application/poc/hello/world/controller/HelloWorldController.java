package first.application.poc.hello.world.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloWorldController {

	@GetMapping(path = "/")
	public String index() {
		return "index";
	}
}
