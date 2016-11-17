package org.elevenfifty.smoothieMachine.controller;


import org.elevenfifty.smoothieMachine.beans.Ingredients;
import org.elevenfifty.smoothieMachine.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class PurchaseController {

	@Autowired
	private IngredientRepository ingredientsRepo;	
	
	
	
	
	@GetMapping(path = {"/custom_smoothie"})
	public String customSmoothieCreate(Ingredients ingredient, Model model){
		
		model.addAttribute("ingredients", ingredientsRepo.findAll());
		model.addAttribute("fruit", ingredientsRepo.findByIngredientType("FRUIT"));
		model.addAttribute("vegetable", ingredientsRepo.findByIngredientType("VEGETABLE"));
		model.addAttribute("milk", ingredientsRepo.findByIngredientType("MILK"));
		model.addAttribute("yogurt", ingredientsRepo.findByIngredientType("YOGURT"));
		model.addAttribute("alcohol", ingredientsRepo.findByIngredientType("ALCOHOL"));
		
		
		
		
		
		
		return "custom_smoothie";
	}
	
//	@PostMapping(path= {"/custom_smoothie"})
//	public String customSmoothieCreate(@ModelAttribute CustomSmoothie customSmoothie, @RequestParam("file") MultipartFile file,Ingredients ingredients, Model model){
//		
//		customSmoothie.setTotal(ingredients.getPrice().add(ingredients.getPrice()));
//		
//		CustomSmoothie savedPurchase = purchaseRepo.save(customSmoothie);
//		
//		return "purchase";
//	}
	
	
}
