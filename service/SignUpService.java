package com.demo.JDBC.service;

import org.springframework.stereotype.Service;

import com.demo.JDBC.model.AddProducts;
import com.demo.JDBC.model.Response;
import com.demo.JDBC.model.SignUpModel;

@Service
public interface SignUpService {
	
	public Response createUser(SignUpModel datas);
	public Response loginUser(String email, String password);
	public Response accountUpdate(SignUpModel datas);
	public Response deleteUser(String sNo);
	public Response softDelete(SignUpModel datas);
	
	public Response productAdding(AddProducts product);
	public Response updatingProduct(AddProducts product);
	public Response getAll();
	public Response addToCart();

}
