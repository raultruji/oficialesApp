package oficiales_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import oficiales_app.dtos.UserDto;
import oficiales_app.entities.User;
import oficiales_app.services.IUserService;
import oficiales_app.validations.exceptions.UserAlreadyExistsException;


@Controller
public class RegistrationController {

	@Autowired IUserService userService;
	
	
	@GetMapping("/oficialesapp/registration")
	public String showRegistrationForm(WebRequest request, Model model) {
		UserDto userDto = new UserDto();
		model.addAttribute("user", userDto);
		return "registration";
	}
	
	@PostMapping("/oficialesapp/registration")
	public ModelAndView registerUserAccount(
			@ModelAttribute("user") @Valid UserDto userDto, 
			HttpServletRequest request,
			Errors errors) {
		try {
			User registered = userService.registerNewUser(userDto);
		}catch(UserAlreadyExistsException ex) {
			ModelAndView mav = new ModelAndView();
			mav.addObject("message","Ya existe una cuenta con ese nombre");
			return mav;
		}
		
		return new ModelAndView("successRegister", "user", userDto);
		
	}
}