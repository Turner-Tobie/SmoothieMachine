package org.elevenfifty.smoothieMachine.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.elevenfifty.smoothieMachine.beans.Ingredients;
import org.elevenfifty.smoothieMachine.beans.IngredientsAlcohol;
import org.elevenfifty.smoothieMachine.beans.IngredientsMilk;
import org.elevenfifty.smoothieMachine.beans.IngredientsNew;
import org.elevenfifty.smoothieMachine.beans.IngredientsVeggies;
import org.elevenfifty.smoothieMachine.beans.IngredientsYogurt;
import org.elevenfifty.smoothieMachine.beans.UserImage;
import org.elevenfifty.smoothieMachine.beans.Users;
import org.elevenfifty.smoothieMachine.repository.BoozeRepository;
import org.elevenfifty.smoothieMachine.repository.IngredientsNewRepository;
import org.elevenfifty.smoothieMachine.repository.IngredientsRepository;
import org.elevenfifty.smoothieMachine.repository.MilkRepository;
import org.elevenfifty.smoothieMachine.repository.VegetablesRepository;
import org.elevenfifty.smoothieMachine.repository.YogurtRepository;
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
	private IngredientsNewRepository ingredientsNewRepo;
	
	@Autowired
	private IngredientsRepository ingredientsRepo;

	@Autowired
	private VegetablesRepository veggiesRepo;

	@Autowired
	private MilkRepository milkRepo;

	@Autowired
	private YogurtRepository yogurtRepo;

	@Autowired
	private BoozeRepository boozeRepo;

	@Autowired
	private UserImageRepository userImageRepo;

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

	@GetMapping(path = { "/ingredients" })
	public String ingredients(Model model) {
		model.addAttribute("ingredients", ingredientsRepo.findAll());
		model.addAttribute("veggies", veggiesRepo.findAll());
		model.addAttribute("booze", boozeRepo.findAll());
		model.addAttribute("yogurt", yogurtRepo.findAll());
		model.addAttribute("milk", milkRepo.findAll());
		return "ingredients";
	}

	@GetMapping("/ingredients/{id}")
	public String ingredients(Model model, @PathVariable(name = "id") long id) {

		model.addAttribute("id", id);

		Ingredients u = ingredientsRepo.findOne(id);
		model.addAttribute("ingredients", u);

		UserImage i = userImageRepo.findByUserId(id);
		model.addAttribute("userImage", i);

		return "ingredient_detail";
	}

	@GetMapping("/ingredients/{id}/edit")
	public String ingredientEdit(Model model, @PathVariable(name = "id") long id) {

		Ingredients u = ingredientsRepo.findOne(id);
		model.addAttribute("ingredients", u);

		model.addAttribute("id", id);
		return "ingredient_edit";
	}

	@PostMapping("/ingredients/{id}/edit")
	public String ingredientsEditSave(@PathVariable(name = "id") long id,
			@ModelAttribute @Valid Ingredients ingredients, BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("ingredients", ingredients);
			return "ingredients_edit";

		}
		ingredientsRepo.save(ingredients);
		return "redirect:/ingredients/" + ingredients.getId();

	}


	@GetMapping("/ingredients_y/{id}")
	public String ingredientsYogurt(Model model, @PathVariable(name = "id") long id) {

		model.addAttribute("id", id);

		IngredientsYogurt y = yogurtRepo.findOne(id);
		model.addAttribute("yogurt", y);
		return "ingredient_detail_y";

	}

	@GetMapping("/ingredients_y/{id}/edit")
	public String ingredientsYogurtEdit(Model model, @PathVariable(name = "id") long id) {

		IngredientsYogurt y = yogurtRepo.findOne(id);
		model.addAttribute("yogurt", y);

		model.addAttribute("id", id);
		return "ingredient_edit_y";
	}

	@PostMapping("/ingredients_y/{id}/edit")
	public String ingredientsYogurtEditSave(@PathVariable(name = "id") long id,
			@ModelAttribute @Valid IngredientsYogurt yogurt, BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("yogurt", yogurt);
			return "ingredients_edit_y";

		}
		yogurtRepo.save(yogurt);
		return "redirect:/ingredients_y/" + yogurt.getId();

	}

	@GetMapping("/ingredients_m/{id}")
	public String ingredientsMilk(Model model, @PathVariable(name = "id") long id) {

		model.addAttribute("id", id);

		IngredientsMilk m = milkRepo.findOne(id);
		model.addAttribute("milk", m);
		return "ingredient_detail_m";

	}

	@GetMapping("/ingredients_m/{id}/edit")
	public String ingredientsMilkEdit(Model model, @PathVariable(name = "id") long id) {

		IngredientsMilk m = milkRepo.findOne(id);
		model.addAttribute("milk", m);

		model.addAttribute("id", id);
		return "ingredient_edit_m";
	}

	@PostMapping("/ingredients_m/{id}/edit")
	public String ingredientsMilkEditSave(@PathVariable(name = "id") long id,
			@ModelAttribute @Valid IngredientsMilk milk, BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("milk", milk);
			return "ingredients_edit_m";

		}
		milkRepo.save(milk);
		return "redirect:/ingredients_m/" + milk.getId();

	}

	@GetMapping("/ingredients_b/{id}")
	public String ingredientsBooze(Model model, @PathVariable(name = "id") long id) {

		model.addAttribute("id", id);

		IngredientsAlcohol b = boozeRepo.findOne(id);
		model.addAttribute("booze", b);
		return "ingredient_detail_b";

	}

	@GetMapping("/ingredients_b/{id}/edit")
	public String ingredientsBoozeEdit(Model model, @PathVariable(name = "id") long id) {

		IngredientsAlcohol b = boozeRepo.findOne(id);
		model.addAttribute("booze", b);

		model.addAttribute("id", id);
		return "ingredient_edit_b";
	}
	

	@PostMapping("/ingredients_b/{id}/edit")
	public String ingredientsAlcoholEditSave(@PathVariable(name = "id") long id,
			@ModelAttribute @Valid IngredientsAlcohol booze, BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("booze", booze);
			return "ingredients_edit_b";

		}
		boozeRepo.save(booze);
		return "redirect:/ingredients_b/" + booze.getId();

	}
	

	@GetMapping("/ingredients_v/{id}")
	public String ingredientsVeggies(Model model, @PathVariable(name = "id") long id) {

		model.addAttribute("id", id);

		IngredientsVeggies v = veggiesRepo.findOne(id);
		model.addAttribute("veggies", v);
		return "ingredient_detail_v";

	}

	@GetMapping("/ingredients_v/{id}/edit")
	public String ingredientsVeggieEdit(Model model, @PathVariable(name = "id") long id) {

		IngredientsVeggies v = veggiesRepo.findOne(id);
		model.addAttribute("veggies", v);

		model.addAttribute("id", id);
		return "ingredient_edit_v";
	}

	@PostMapping("/ingredients_v/{id}/edit")
	public String ingredientsVeggiesEditSave(@PathVariable(name = "id") long id,
			@ModelAttribute @Valid IngredientsVeggies veggies, BindingResult result, Model model) {

		if (result.hasErrors()) {
			model.addAttribute("veggies", veggies);
			return "ingredients_edit_v";

		}
		veggiesRepo.save(veggies);
		return "redirect:/ingredients_v/" + veggies.getId();

	}
	
	
	@GetMapping("/ingredients/create")
	public String ingredientCreate(@ModelAttribute @Valid IngredientsNew ingredient, Model model) {
		model.addAttribute("ingredient", ingredient);
		return "ingredient_create";
	}
	
	@PostMapping("/ingredients/create")
    public String ingredientCreateSave(@ModelAttribute @Valid IngredientsNew ingredient, Model model) {
		
        ingredientsNewRepo.save(ingredient);
            return "redirect:/ingredients/";
    }		

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
