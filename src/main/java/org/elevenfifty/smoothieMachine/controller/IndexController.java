package org.elevenfifty.smoothieMachine.controller;


import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static org.elevenfifty.smoothieMachine.security.UserRole.USER;

import org.elevenfifty.smoothieMachine.beans.UserImage;
import org.elevenfifty.smoothieMachine.beans.UserRoles;
import org.elevenfifty.smoothieMachine.beans.Users;
import org.elevenfifty.smoothieMachine.repository.UserImageRepository;
import org.elevenfifty.smoothieMachine.repository.UserRepository;
import org.elevenfifty.smoothieMachine.repository.UserRoleRepository;
import org.elevenfifty.smoothieMachine.security.PermissionService;
import org.elevenfifty.smoothieMachine.service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;





@Controller
public class IndexController {

	@Autowired
	private UserRepository userRepo;	
	
	@Autowired
	private UserImageRepository userImageRepo;	
	
	@Autowired
	private UserRoleRepository userRoleRepo;
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private PermissionService permissionService;

	
	@GetMapping("")
	public String index(Model model, HttpServletRequest request) {
		return "index";

	}

	@GetMapping(path = { "/home", "/", "" })
	public String home(Model model) {
		model.addAttribute("users", userRepo.findAllByOrderByFirstNameAscLastNameAsc());
		return "home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView getLoginPage(@RequestParam Optional<String> error) {
		return new ModelAndView("login", "error", error);
	}

	@GetMapping(path = { "/users" })
	public String users(Model model) {
		model.addAttribute("users", userRepo.findAll());
		return "home";
	}

		

	@GetMapping("/user/{userId}")
	public String user(Model model, @PathVariable long userId) {

		model.addAttribute("user", userRepo.findOne(userId));

		

		List<UserImage> images = userImageRepo.findByUserId(userId);
		if (!CollectionUtils.isEmpty(images)) {
			model.addAttribute("userImage", images.get(0));
		}
		model.addAttribute("permissions", permissionService);
		return "user_detail";
	}

	@GetMapping("/user/{userId}/edit")
	public String userEdit(Model model, @PathVariable long userId) {

		model.addAttribute("user", userRepo.findOne(userId));
		
		
		
		List<UserImage> images = userImageRepo.findByUserId(userId);
		if (!CollectionUtils.isEmpty(images)) {
			model.addAttribute("userImage", images.get(0));
		}
		return "user_edit";
	}

	private static final Logger log = LoggerFactory.getLogger(IndexController.class);

	@PostMapping("/user/{userId}/edit")
	public String userEditSave(@ModelAttribute Users user,
			@PathVariable long userId,
			@RequestParam(name = "removeImage", defaultValue = "false") boolean removeImage,
			@RequestParam("file") MultipartFile file,
			Model model) {

		
		log.debug("Saving user " + user);
		userRepo.save(user);
		model.addAttribute("message", "User " + user.getEmail() + " saved.");

		if (removeImage) {
			imageService.deleteImage(user);
		} else {
			imageService.saveImage(file, user);
		}
		userRepo.save(user);
		return "redirect:/user/" + user.getId();
		// userRepo.save(user);
		// return "user_edit";

	}
	
	@RequestMapping("/register")
	public String register(Model model) {
	return "user_create";
}
	
	@GetMapping("/user/create")
	public String userCreate(@ModelAttribute @Valid Users user, Model model) {
		model.addAttribute("user", new Users());
		return "user_create";
	}
	
	@PostMapping("/user/create")
    public String userCreateSave(@ModelAttribute Users user,
			@RequestParam("file") MultipartFile file, Model model) {

		log.info(user.toString());
		
		Users savedUser = userRepo.save(user);
		UserRoles role = new UserRoles(savedUser, USER);
        userRoleRepo.save(role);
        imageService.saveImage(file, savedUser);
        
            return user(model, savedUser.getId());
    }
	
	

}
