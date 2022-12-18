package org.example.spring_mvc.controllsers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/first")
public class FirstController {
	@GetMapping("/hello")
	public String helloPage(@RequestParam("name") String name,
	                        @RequestParam("surname") String surname,
                            Model model
	) {

		model.addAttribute("message", "hello, " + name + " " + surname);

		return "first/hello";
	}

	@GetMapping("/calculator")
	public String calculator(@RequestParam("a") int a,
	                         @RequestParam("b") int b,
	                         @RequestParam("action") String action,
                             Model model
	) {
		switch (action) {
			case "add" -> model.addAttribute("result", a + b);
			case "sub" -> model.addAttribute("result", a - b);
			case "ost" -> model.addAttribute("result", a % b);
			case "mul" -> model.addAttribute("result", a * b);
			case "del" -> model.addAttribute("result", a / b);
		}

		return "first/calc";
	}

	@GetMapping("/goodbye")
	public String goodByePage() {
		return "first/goodbye";
	}
}
