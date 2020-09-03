package com.astek.myquotes.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.astek.myquotes.entitites.ConfirmationToken;
import com.astek.myquotes.entitites.Utilisateur;
import com.astek.myquotes.repositories.ConfirmationTokenRepository;
import com.astek.myquotes.security.Role;
import com.astek.myquotes.services.EmailSenderService;
import com.astek.myquotes.services.UsersService;
import com.astek.myquotes.utility.Log;
import com.astek.myquotes.utility.bundles.ResDataBundle;

@Controller
@RequestMapping("/user")
public class UserController extends InitiatedController {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	private UsersService userService;
	
	@Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private EmailSenderService emailSenderService;
    
	public UserController() {
		System.out.println("le service:" + userService);
	}

	@PostConstruct
	public void init() {
		System.out.println("le service:" + userService);
	}

//	@GetMapping("/list")
//	public String list(Model model) {
//		model.addAttribute("users", userService.findAll());
//		return "user/list";
//	}
//
//	@GetMapping("/delete")
//	public String delete(@RequestParam(name = "id") String login) {
//		if (userService.findById(login) == null) {
//			return "redirect:/user/list?error=delete";
//		}
//		userService.deleteById(login);
//		return "redirect:/user/list";
//	}

	@GetMapping("/add")
	public String add(Model model) {
		return goEdit(new Utilisateur(), model);
	}

	@GetMapping("/edit")
	public String edit(@RequestParam(name = "id") String login, Model model) {
		Utilisateur user = userService.findById(login);
		if (user != null) {
			return goEdit(user, model);
		}
		return "redirect:/user/list?error=edit";
	}

	private String goEdit(Utilisateur user, Model model) {
		model.addAttribute("user", user);
		return "user/edit";
	}

	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("user") Utilisateur user, BindingResult br, Model model) {	
		
		if (br.hasErrors()) {
			return goEdit(user, model);
		}
		// Check if login + @mail do not exist in DB
		Utilisateur existingEmailUser = userService.findByEmail(user.getEmail());
		Utilisateur existingNicknameUser = userService.findByNickname(user.getNickname());
		if (existingEmailUser==null && existingNicknameUser==null) {
			// Create the new user
			// set date and role, desactivate it and crypt password
			user.setDtCreation(new Date());
			user.setRole(Role.ROLE_USER);
			user.setEnable(Boolean.FALSE);
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			String email = user.getEmail().toLowerCase();
			email = email.replaceAll("\\s", "");
			user.setEmail(email);
			userService.save(user);			
			

			// Send email confirmation
			ConfirmationToken confirmationToken = new ConfirmationToken(user);

			confirmationTokenRepository.save(confirmationToken);

			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(user.getEmail());
			mailMessage.setSubject("Finissez votre inscription "+ user.getNickname() +" !");
			mailMessage.setFrom(ResDataBundle.getMyQuoteEmail());
//			mailMessage.setText("To confirm your account, please click here : "
//					+ "http://localhost:8080/user/confirm-account?token=" + confirmationToken.getConfirmationToken());
			
			mailMessage.setText(ResDataBundle.getEmailMessageStart() + ResDataBundle.getUrl() + "/user/confirm-account?token=" + confirmationToken.getConfirmationToken());
			emailSenderService.sendEmail(mailMessage);

//			modelAndView.addObject("emailId", user.getEmail());
//			modelAndView.setViewName("successfulRegisteration");
			return "redirect:/index?successfulregistrationemailsent=true";
			

		} else {
			System.out.println("Can't create user nickname or email or login already exists");
//			modelAndView.addObject("message","This email already exists!");
//            modelAndView.setViewName("error");
			return "error";
		}

//		return "redirect:/user/list";
	//	return "redirect:/";
	}
	
	@RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public String confirmUserAccount(ModelAndView modelAndView, @RequestParam("token")String confirmationToken)
    {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null)
        {
        	Utilisateur user = userService.findByEmail(token.getUser().getEmail());
            user.setEnable(true);
            userService.save(user);
            modelAndView.setViewName("accountVerified");
        }
        else
        {
            modelAndView.addObject("message","The link is invalid or broken!");
            modelAndView.setViewName("error");
        }

        return "redirect:/index?successfulregistration=true";        
    }
//	
//	@InitBinder
//    public void initBinder(WebDataBinder binder) {
////        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        sdf.setLenient(true);
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
//    }
}
