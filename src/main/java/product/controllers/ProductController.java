package product.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import product.data.ProductService;
import product.model.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@GetMapping("")
	public String listProducts (Model model) {
		model.addAttribute("products", productService.getAllProduct());
		return "/product/products";
	}
	
	@GetMapping("/add-new-product")
	public String addNewProductGet (Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		return "/product/add";
	}
	
	@PostMapping("/add-new-product")
	public String addNewProductPost (Model model
			, @ModelAttribute(name = "product") @Valid Product product, Errors errors) {
		if (errors.hasErrors()) {
			return "/product/add";
			}
		
		productService.createProduct(product);
		return "redirect:/products";
	}
	
	@GetMapping("/edit-product/{id}")
	public String editProductGet (Model model, 
			@PathVariable(name = "id") Long id) {
		Product product = productService.getProductById(id);
		model.addAttribute("product", product);
		return "/product/edit";
	}
	
	@PostMapping("/edit-product/{id}")
	public String editProductPost (Model model, 
			@PathVariable(name = "id") Long id
			, @ModelAttribute(name = "product") @Valid Product product, Errors errors) {
		if (errors.hasErrors()) {
			return "product/edit";
			}
		productService.editProduct(id, product);
		return "redirect:/products";
	}
	
	@GetMapping("/delete-product/{id}")
	public String deleteProductGet (Model model
			, @PathVariable(name = "id") Long id) {
		
		Product product = productService.getProductById(id);
		model.addAttribute("product", product);
		return "/product/delete";
	}
	
	@PostMapping("/delete-product/{id}")
	public String deleteProductPost (HttpServletRequest request , Model model
			, @PathVariable(name = "id") Long id) {
		
		String choose = request.getParameter("choose");
		if (choose.equals("yes")){
			productService.deleteProduct(productService.getProductById(id));
		}
		return "redirect:/products";
	}
	
}
