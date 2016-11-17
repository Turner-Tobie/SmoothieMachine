package org.elevenfifty.smoothieMachine.controller;

import static org.elevenfifty.smoothieMachine.security.IngredientRole.ALCOHOL;
import static org.elevenfifty.smoothieMachine.security.IngredientRole.FRUIT;
import static org.elevenfifty.smoothieMachine.security.IngredientRole.MILK;
import static org.elevenfifty.smoothieMachine.security.IngredientRole.VEGETABLE;
import static org.elevenfifty.smoothieMachine.security.IngredientRole.YOGURT;

import javax.validation.Valid;

import org.elevenfifty.smoothieMachine.beans.IngredientRoles;
import org.elevenfifty.smoothieMachine.beans.Ingredients;
import org.elevenfifty.smoothieMachine.repository.IngredientRepository;
import org.elevenfifty.smoothieMachine.repository.IngredientRoleRepository;
import org.elevenfifty.smoothieMachine.security.PermissionService;
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

@Controller
public class IngredientController {
	
	private static final Logger log = LoggerFactory.getLogger(IngredientController.class);
	
	@Autowired
	private IngredientRoleRepository ingredientRoleRepo;
	
	@Autowired
	private IngredientRepository ingredientsRepo;
	
	@Autowired
	private PermissionService permissionService;


	@GetMapping(path = { "/ingredients" })
	public String ingredients(Ingredients ingredient, Model model) {
		model.addAttribute("ingredientsRole", ingredientRoleRepo.findAll());
		
		model.addAttribute("ingredients", ingredientsRepo.findAll());
		model.addAttribute("fruit", ingredientsRepo.findByIngredientType("FRUIT"));
		model.addAttribute("vegetable", ingredientsRepo.findByIngredientType("VEGETABLE"));
		model.addAttribute("milk", ingredientsRepo.findByIngredientType("MILK"));
		model.addAttribute("yogurt", ingredientsRepo.findByIngredientType("YOGURT"));
		model.addAttribute("alcohol", ingredientsRepo.findByIngredientType("ALCOHOL"));
		
		return "ingredients";
	}

	@GetMapping("/ingredients/{id}")
	public String ingredients(Model model, @PathVariable(name = "id") long id) {

		model.addAttribute("id", id);

		Ingredients u = ingredientsRepo.findOne(id);
		model.addAttribute("ingredients", u);

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
		return "redirect:/ingredients" + ingredients.getId();

	}	
	
	@GetMapping("/ingredients/create")
	public String ingredientCreate(@ModelAttribute @Valid Ingredients ingredients, Model model) {
		return "ingredient_create";
	}
	
	@PostMapping("/ingredients/create")
    public String ingredientCreateSave(@ModelAttribute @Valid Ingredients ingredients, Model model) {
		
		log.info(ingredients.toString());
		
		Ingredients savedIngredient = ingredientsRepo.save(ingredients);		
		
			if(ingredients.getIngredientType() == "FRUIT"){
				IngredientRoles role = new IngredientRoles(savedIngredient, FRUIT);
				ingredientRoleRepo.save(role);
			}else if(ingredients.getIngredientType() == "VEGETABLE"){
				IngredientRoles role = new IngredientRoles(savedIngredient, VEGETABLE);
				ingredientRoleRepo.save(role);
			}else if(ingredients.getIngredientType() == "MILK"){
				IngredientRoles role = new IngredientRoles(savedIngredient, MILK);
				ingredientRoleRepo.save(role);
			}else if(ingredients.getIngredientType() == "YOGURT"){
				IngredientRoles role = new IngredientRoles(savedIngredient, YOGURT);
				ingredientRoleRepo.save(role);
			}else if(ingredients.getIngredientType() == "ALCOHOL"){
				IngredientRoles role = new IngredientRoles(savedIngredient, ALCOHOL);
				ingredientRoleRepo.save(role);
			}else{
				if(ingredients.getIngredientType() == null){
					log.error("Please Select An Ingredient Type");
				}
			}
		
		
        
            return "redirect:/ingredients";
    }	
	
}
