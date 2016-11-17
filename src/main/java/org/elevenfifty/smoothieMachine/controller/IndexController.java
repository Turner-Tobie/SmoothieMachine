package org.elevenfifty.smoothieMachine.controller;

import java.io.IOException;

import javax.management.relation.Role;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static org.elevenfifty.smoothieMachine.security.IngredientRole.FRUIT;
import static org.elevenfifty.smoothieMachine.security.IngredientRole.ALCOHOL;
import static org.elevenfifty.smoothieMachine.security.IngredientRole.MILK;
import static org.elevenfifty.smoothieMachine.security.IngredientRole.VEGETABLE;
import static org.elevenfifty.smoothieMachine.security.IngredientRole.YOGURT;

import org.elevenfifty.smoothieMachine.beans.IngredientRoles;
import org.elevenfifty.smoothieMachine.beans.Ingredients;
import org.elevenfifty.smoothieMachine.beans.UserImage;
import org.elevenfifty.smoothieMachine.beans.Users;
import org.elevenfifty.smoothieMachine.repository.IngredientRepository;
import org.elevenfifty.smoothieMachine.repository.IngredientRoleRepository;
import org.elevenfifty.smoothieMachine.repository.UserImageRepository;
import org.elevenfifty.smoothieMachine.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class IndexController {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private IngredientRepository ingredientsRepo;
	
	@Autowired
	private UserImageRepository userImageRepo;
	
	@Autowired
	private IngredientRoleRepository ingredientRoleRepo;

	
	@GetMapping("")
	public String index(Model model, HttpServletRequest request) {
		return "index";

	}

	@GetMapping(path = { "/home", "/", "" })
	public String home(Model model) {
		model.addAttribute("users", userRepo.findAll());
		return "home";
	}

	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}

	@GetMapping(path = { "/users" })
	public String users(Model model) {
		model.addAttribute("users", userRepo.findAll());
		return "home";
	}

//	@GetMapping(path = { "/ingredients" })
//	public String ingredients(Model model) {
//		model.addAttribute("ingredientsRole", ingredientRoleRepo.findAll());
//		model.addAttribute("ingredients", ingredientsRepo.findByIngredientType("FRUIT"));		
//		return "ingredients";
//	}
//
//	@GetMapping("/ingredients/{id}")
//	public String ingredients(Model model, @PathVariable(name = "id") long id) {
//
//		model.addAttribute("id", id);
//
//		Ingredients u = ingredientsRepo.findOne(id);
//		model.addAttribute("ingredients", u);
//
//		return "ingredient_detail";
//	}
//
//	@GetMapping("/ingredients/{id}/edit")
//	public String ingredientEdit(Model model, @PathVariable(name = "id") long id) {
//
//		Ingredients u = ingredientsRepo.findOne(id);
//		model.addAttribute("ingredients", u);
//
//		model.addAttribute("id", id);
//		return "ingredient_edit";
//	}
//
//	@PostMapping("/ingredients/{id}/edit")
//	public String ingredientsEditSave(@PathVariable(name = "id") long id,
//			@ModelAttribute @Valid Ingredients ingredients, BindingResult result, Model model) {
//
//		if (result.hasErrors()) {
//			model.addAttribute("ingredients", ingredients);
//			return "ingredients_edit";
//
//		}
//		ingredientsRepo.save(ingredients);
//		return "redirect:/ingredients/" + ingredients.getId();
//
//	}	
//	
//	@GetMapping("/ingredients/create")
//	public String ingredientCreate(@ModelAttribute @Valid Ingredients ingredients, Model model) {
//		return "ingredient_create";
//	}
//	
//	@PostMapping("/ingredients/create")
//    public String ingredientCreateSave(@ModelAttribute @Valid Ingredients ingredients, Model model) {
//		
//		log.info(ingredients.toString());
//		
//		Ingredients savedIngredient = ingredientsRepo.save(ingredients);		
//		
//			if(ingredients.getIngredientType() == "FRUIT"){
//				IngredientRoles role = new IngredientRoles(savedIngredient, FRUIT);
//				ingredientRoleRepo.save(role);
//			}else if(ingredients.getIngredientType() == "VEGETABLE"){
//				IngredientRoles role = new IngredientRoles(savedIngredient, VEGETABLE);
//				ingredientRoleRepo.save(role);
//			}else if(ingredients.getIngredientType() == "MILK"){
//				IngredientRoles role = new IngredientRoles(savedIngredient, MILK);
//				ingredientRoleRepo.save(role);
//			}else if(ingredients.getIngredientType() == "YOGURT"){
//				IngredientRoles role = new IngredientRoles(savedIngredient, YOGURT);
//				ingredientRoleRepo.save(role);
//			}else if(ingredients.getIngredientType() == "ALCOHOL"){
//				IngredientRoles role = new IngredientRoles(savedIngredient, ALCOHOL);
//				ingredientRoleRepo.save(role);
//			}else{
//				if(ingredients.getIngredientType() == null){
//					log.error("Please Select An Ingredient Type");
//				}
//			}
//		
//		
//        
//            return "redirect:/ingredients/";
//    }		

	@GetMapping("/user/{id}")
	public String user(Model model, @PathVariable(name = "id") long id) {

		model.addAttribute("id", id);

		Users u = userRepo.findOne(id);
		model.addAttribute("user", u);

		UserImage i = userImageRepo.findByUserId(id);
		model.addAttribute("userImage", i);

		return "user_detail";
	}

	@GetMapping("/user/{id}/edit")
	public String userEdit(Model model, @PathVariable(name = "id") long id) {

		Users u = userRepo.findOne(id);

		model.addAttribute("user", u);

		model.addAttribute("id", id);
		return "user_edit";
	}

	private static final Logger log = LoggerFactory.getLogger(IndexController.class);

	@PostMapping("/user/{id}/edit")
	public String userEditSave(@PathVariable(name = "id") long id, @ModelAttribute @Valid Users user,
			BindingResult result, Model model, @RequestParam("file") MultipartFile file,
			@RequestParam(name = "removeImage", defaultValue = "false", required = false) boolean removeImage) {

		if (result.hasErrors()) {
			model.addAttribute("user", user);
			return "user_edit";

		} else {
			if (removeImage) {
				UserImage image = userImageRepo.findByUserId(id);
				if (image != null) {
					// remove if it exists
					userImageRepo.delete(image);
					log.info("User Image Removed");
				}
			} else if (file != null && !file.isEmpty()) {
				try {
					// Load file into proper format(Spring does this!)

					// Load or Create UserImage
					UserImage image = userImageRepo.findByUserId(id);
					// UserImage image = userImageRepo.findByUserId(id);
					if (image == null) {
						image = new UserImage();
						image.setUserId(id);
					}
					image.setContentType(file.getContentType());
					image.setImage(file.getBytes());
					// Store in the database
					userImageRepo.save(image);
					userRepo.save(user);
				} catch (IOException e) {
					log.error("Failed to upload file", e);
				}

			}

		}
		userRepo.save(user);
		return "redirect:/user/" + user.getId();
		// userRepo.save(user);
		// return "user_edit";

	}
	
	@GetMapping("/user/create")
	public String userCreate(@ModelAttribute @Valid Users user, Model model) {
		
		return "user_create";
	}
	
	@PostMapping("/user/create")
    public String userCreateSave(@ModelAttribute @Valid Users user, Model model) {

        userRepo.save(user);
            return "redirect:/user/" + user.getId();
    }
	
	

}
