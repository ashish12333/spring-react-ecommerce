package com.ashish.ecom.project.spring.service;

import java.io.IOException;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

//import com.ashish.ecom.project.spring.controller.product;
import com.ashish.ecom.project.spring.model.Product;
import com.ashish.ecom.project.spring.repo.ProductRepo;

@Service
public class ProductService {

	@Autowired
	private ProductRepo repo;
	
	
	public List<Product> getallproducts() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
	
	
	public Product getallproductbyid(int id) {
		// TODO Auto-generated method stub
		return repo.findById(id).orElse(new Product());
	}


	public Product addproduct(Product product, MultipartFile imageFile) throws IOException{
		// TODO Auto-generated method stub
		product.setImagename(imageFile.getOriginalFilename());
		product.setImagetype(imageFile.getContentType());
		product.setImagedat(imageFile.getBytes());
		
		return  repo.save(product);
		
	}


	public Product updateproduct(int id, Product prod, MultipartFile imagefile) throws IOException  {
		
		// TODO Auto-generated method stub
		
		prod.setImagedat(imagefile.getBytes());
		prod.setImagename(imagefile.getOriginalFilename());
		prod.setImagetype(imagefile.getContentType());
		
		return repo.save(prod);
	}


	public  void deleted(int id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);
	
	}


	public List<Product> searchproduct(String keyword) {
		// TODO Auto-generated method stub
		return repo.searchproducts(keyword);
	}


//	public  demo(String keyword) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	
	

}
