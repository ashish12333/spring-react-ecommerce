package com.ashish.ecom.project.spring.controller;

import java.io.IOException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ashish.ecom.project.spring.model.Product;
import com.ashish.ecom.project.spring.service.ProductService;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ProductController {
	
	
	@Autowired
	ProductService service;
	
	
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getallprouct() {
		return new ResponseEntity<>(service.getallproducts(),HttpStatus.OK); 
	}
	
	
	
	@GetMapping("/product/{id}")
	public  ResponseEntity< Product >getproductbyid(@PathVariable int id) {
		
		Product prod =service.getallproductbyid(id);
		if(prod!=null)
		return new ResponseEntity<>( prod,HttpStatus.OK);
		else
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping(value="/product", consumes="multipart/form-data")
	public ResponseEntity<?> addproduct(@RequestPart Product product ,@RequestPart MultipartFile imageFile){
		 try {
			 System.out.println(product);
		Product product1= service.addproduct(product,imageFile);
		return new ResponseEntity<> (product1,HttpStatus.CREATED);
	}
		 catch (Exception e) {
			 return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		 }
	
			 
		 }
	
	@GetMapping("/product/{productid}/image")
	public ResponseEntity<byte []> getimagebyid(@PathVariable int productid ){
		Product p=service.getallproductbyid(productid);
		byte [] imgfile= p.getImagedat();
	return new ResponseEntity(imgfile,HttpStatus.OK);
		
	}
	
	
	@PutMapping("/product/{id}")
	public  ResponseEntity<String> updateproduct (@PathVariable int id , @RequestPart Product product, @RequestPart MultipartFile imageFile){
		
		Product p1=null;
		try {
		p1=service.updateproduct(id, product,imageFile);
		}
		
		catch (IOException e) {
			throw new RuntimeException(e);
			
		}
		if(p1!=null) {
		return new ResponseEntity<>("product updated",HttpStatus.OK);
		
	}
		else {
			return new ResponseEntity<>("not returned ",HttpStatus.EXPECTATION_FAILED);
		}


}
	@DeleteMapping("/product/{id}")
	public ResponseEntity<String > deleteproduct(@PathVariable int id ){
		Product prod =service.getallproductbyid(id);
		if(prod !=null) {
		
	   service.deleted(id);
	   
	   return new ResponseEntity<>("product found",HttpStatus.FOUND);
	}
		else {
			return new ResponseEntity<>("product not found",HttpStatus.NOT_FOUND);
		}
	}
		
		
	@GetMapping	("/products/search")
public ResponseEntity<List<Product>> searchproduct(@RequestParam String keyword){
		System.out.println("searching"+keyword);
	List<Product> l1= service.searchproduct(keyword);
	return new ResponseEntity<>(l1,HttpStatus.OK);
}
	}
