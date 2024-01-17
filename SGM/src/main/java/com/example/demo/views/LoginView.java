package com.example.demo.views;
/*@Author https://github.com/devmarcos23*/
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class LoginView {

	@GetMapping
	public ModelAndView login(@RequestParam(name = "error", required = false) String error) {
		ModelAndView mv = new ModelAndView("login/login");

		if (error != null) {

			if (error.equals("badCredentials")) {

				mv.addObject("badCredentials", true);
			
			}
		}

		return mv;
	}
}
