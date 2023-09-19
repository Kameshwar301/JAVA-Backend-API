package com.demo.JDBC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.JDBC.dao.SignUpDao;
import com.demo.JDBC.model.AddProducts;
import com.demo.JDBC.model.Response;
import com.demo.JDBC.model.SignUpModel;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class SignUpController {
	
	@Autowired
	private SignUpDao dao;
	
	@PostMapping("/create")
	public ResponseEntity<Response> createUser(@RequestBody SignUpModel datas){
		return ResponseEntity.ok(dao.createUser(datas));
	}
	
	@PutMapping("/userupdate")
	public ResponseEntity<Response> accountUpdate(@RequestBody SignUpModel datas){
		return ResponseEntity.ok(dao.accountUpdate(datas));
	}
	
	@GetMapping("/login")
	public ResponseEntity<Response> loginUser(@RequestParam String email, @RequestParam String password){
		return ResponseEntity.ok(dao.loginUser(email, password));
	}
	
	@DeleteMapping("/userdelete")
	public ResponseEntity<Response> deleteUser(@RequestParam String sNo){
		return ResponseEntity.ok(dao.deleteUser(sNo));
	}
	
	@PostMapping("/productCreate")
	public ResponseEntity<Response> productAdding(@RequestBody AddProducts product){
		return ResponseEntity.ok(dao.productAdding(product));
	}
	@PutMapping("/productUpdate")
	public ResponseEntity<Response> updatingProduct(@RequestBody AddProducts product){
		return ResponseEntity.ok(dao.updatingProduct(product));
	}
	
	@GetMapping("/getproduct")
	public ResponseEntity<Response> getAll(){
		return ResponseEntity.ok(dao.getAll());
	}
	@GetMapping("/addcart")
	public ResponseEntity<Response> addToCart(){
		return ResponseEntity.ok(dao.addToCart());
	}
	
	@DeleteMapping("deleteUser")
	public ResponseEntity<Response> softDelete(@RequestBody SignUpModel datas){
		return ResponseEntity.ok(dao.softDelete(datas));
	}
}
