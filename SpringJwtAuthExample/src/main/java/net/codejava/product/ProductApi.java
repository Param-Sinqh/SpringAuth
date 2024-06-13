package net.codejava.product;

import java.net.URI;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductApi {

	@Autowired private ProductRepository repo;
	
	@PostMapping
	//@PreAuthorize("hasRole('ROLE_EDITOR')")
	@RolesAllowed("ROLE_EDITOR")
	public Product create(@RequestBody @Valid Product product) {
		Product savedProduct = repo.save(product);
		
		return savedProduct;
	}
	
	@GetMapping
	//@PreAuthorize("hasRole('ROLE_CUSTOMER') or hasRole('ROLE_EDITOR')")
	@RolesAllowed({"ROLE_EDITOR","ROLE_CUSTOMER"})
	public List<Product> list() {
		return repo.findAll();
	}
}
