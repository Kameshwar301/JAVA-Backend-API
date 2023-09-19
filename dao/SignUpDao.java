package com.demo.JDBC.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

import com.demo.JDBC.model.AddProducts;
import com.demo.JDBC.model.Response;
import com.demo.JDBC.model.SignUpModel;
import com.demo.JDBC.service.SignUpService;

@Component
public class SignUpDao implements SignUpService {

	Response res = new Response();

	String url = "jdbc:mysql://127.0.0.1:3309/project_work";
	String username = "root";
	String password = "Kameshwar@301";

	@Override
	public Response createUser(SignUpModel datas) {

		String uuid = UUID.randomUUID().toString();
		datas.setUserId(uuid);
		datas.setCreatedBy(uuid);
		datas.setUpdatedBy(uuid);

		Date date = new Date(Calendar.getInstance().getTime().getTime());
		datas.setCreatedDate(date);
		datas.setUpdatedDate(date);
		datas.setIsActive(1);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection con = DriverManager.getConnection(url, username, password);
					Statement st = con.createStatement();) {

				String insertQuery = "INSERT INTO project_work.user_list"
						+ "(user_id,user_name,email,password,first_name,last_name,date_of_birth,address,city,district,state,pincode,created_by,created_date,updated_by,updated_date,phone_number,is_active)"
						+ "VALUES('" + datas.getUserId() + "','" + datas.getUserName() + "','" + datas.getEmail()
						+ "','" + datas.getPassword() + "','" + datas.getFirstName() + "','" + datas.getLastName()
						+ "','" + datas.getDob() + "','" + datas.getAddress() + "','" + datas.getCity() + "','"
						+ datas.getDistrict() + "','" + datas.getState() + "'," + datas.getPincode() + ",'"
						+ datas.getCreatedBy() + "','" + datas.getCreatedDate() + "','" + datas.getUpdatedBy() + "','"
						+ datas.getUpdatedDate() + "'," + datas.getPhoneNumber() + "," + datas.getIsActive() + ");";

				System.out.println(insertQuery);
				st.executeUpdate(insertQuery);

				res.setResponseCode(200);
				res.setResponseMsg("success");
				res.setData("User created successfully!");
			} catch (Exception e) {
				e.printStackTrace();

				res.setResponseCode(500);
				res.setResponseMsg("error");
				res.setData("Internal server error!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}

	@Override
	public Response loginUser(String email, String password) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String loginQuery = "SELECT * FROM project_work.user_list WHERE email = '" + email + "' and password = '"
					+ password + "';";

			System.out.println(loginQuery);
			try (Connection con = DriverManager.getConnection(url, username, this.password);
					PreparedStatement pst = con.prepareStatement(loginQuery);
					ResultSet rs = pst.executeQuery(loginQuery);) {
				String result;

				if (rs.next()) {
					result = "User Loggined successfully";
				} else {
					result = "user doesnot exisit";
				}

				res.setResponseCode(200);
				res.setResponseMsg("success");
				res.setData(result);

			} catch (Exception e) {
				e.printStackTrace();

				res.setResponseCode(500);
				res.setResponseMsg("error");
				res.setData("Internal server error!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public Response accountUpdate(SignUpModel datas) {
		Date date = new Date(Calendar.getInstance().getTime().getTime());
		datas.setUpdatedDate(date);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection con = DriverManager.getConnection(url, username, password);
					Statement st = con.createStatement();) {

				String updateAccount = "UPDATE project_work.user_list SET\r\n" + "first_name ='" + datas.getFirstName()
						+ "' ,last_name ='" + datas.getLastName() + "' ,date_of_birth ='" + datas.getDob()
						+ "' ,address ='" + datas.getAddress() + "' ,city ='" + datas.getCity() + "' ,district ='"
						+ datas.getDistrict() + "' ,state ='" + datas.getState() + "' ,pincode =" + datas.getPincode()
						+ " ," + "phone_number =" + datas.getPhoneNumber() + " WHERE user_id ='" + datas.getUserId()
						+ "' ;";
				System.out.println(updateAccount);
				st.executeUpdate(updateAccount);

				res.setResponseCode(200);
				res.setResponseMsg("success");
				res.setData("User account updated successfully!");
			} catch (Exception e) {
				e.printStackTrace();

				res.setResponseCode(500);
				res.setResponseMsg("error");
				res.setData("Internal server error!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public Response productAdding(AddProducts product) {
		String uuid = UUID.randomUUID().toString();
		product.setCreatedBy(uuid);
		product.setUpdatedBy(uuid);

		Date date = new Date(Calendar.getInstance().getTime().getTime());
		product.setCreatedDate(date);
		product.setUpdatedDate(date);

		product.setUserId("69ed7e0e-ec90-4f29-a0a0-cd383ebfdce1");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection con = DriverManager.getConnection(url, username, password);
					Statement st = con.createStatement();) {
				String productCreate = "INSERT INTO project_work.product_list"
						+ "(product_id,product_name,product_brand,product_type,product_img,product_qty,product_price,product_desc,user_id,created_by,created_date,updated_by,updated_date)"
						+ "VALUES('" + product.getProductId() + "','" + product.getProductName() + "','"
						+ product.getProductBrand() + "','" + product.getProductType() + "','" + product.getProductImg()
						+ "'," + product.getProductQty() + "," + product.getProductPrice() + ",'"
						+ product.getProductDesc() + "','" + product.getUserId() + "','" + product.getCreatedBy()
						+ "','" + product.getCreatedDate() + "','" + product.getUpdatedBy() + "','"
						+ product.getUpdatedDate() + "');";
				System.out.println(productCreate);
				st.executeUpdate(productCreate);

				res.setResponseCode(200);
				res.setResponseMsg("success");
				res.setData("product created successfully!");
			} catch (Exception e) {
				e.printStackTrace();

				res.setResponseCode(500);
				res.setResponseMsg("error");
				res.setData("Internal server error!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public Response updatingProduct(AddProducts product) {
//		product.setUpdatedBy(product.getUserId());
//
		Date date = new Date(Calendar.getInstance().getTime().getTime());
		product.setUpdatedDate(date);

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection con = DriverManager.getConnection(url, username, password);
					Statement st = con.createStatement();) {
				String updateQuery = "UPDATE project_work.product_list SET product_img ='" + product.getProductImg()
						+ "',updated_date ='" + product.getUpdatedDate() + "' WHERE product_id = '"
						+ product.getProductId() + "';";

				System.out.println(updateQuery);
				st.executeUpdate(updateQuery);

				res.setResponseCode(200);
				res.setResponseMsg("success");
				res.setData("product updated successfully!");
			} catch (Exception e) {
				e.printStackTrace();

				res.setResponseCode(500);
				res.setResponseMsg("error");
				res.setData("Internal server error!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	@Override
	public Response deleteUser(String sNo) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try (Connection conn = DriverManager.getConnection(url, username, password);
					Statement st = conn.createStatement();) {
				String deleteQuery = "DELETE FROM project_work.user_list WHERE s_no = '" + sNo + "';";
				System.out.println(deleteQuery);

				st.executeUpdate(deleteQuery);

				res.setResponseCode(200);
				res.setResponseMsg("Success");
				res.setData("user specific data deleted successfully!");

			} catch (Exception e) {
				e.printStackTrace();

				res.setResponseCode(500);
				res.setResponseMsg("error");
				res.setData("Internal server error!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getAll() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String selectProductQuery = "select product_name, product_brand, product_type, product_img, product_qty, product_price, product_desc from project_work.product_list;";

			System.out.println(selectProductQuery);
			try (Connection con = DriverManager.getConnection(url, username, password);
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery(selectProductQuery);) {

				JSONArray jsonArray = new JSONArray();

				while (rs.next()) {
					JSONObject jsonObject = new JSONObject();

					jsonObject.put("productName", rs.getString("product_name"));
					jsonObject.put("productBrand", rs.getString("product_brand"));
					jsonObject.put("productType", rs.getString("product_type"));
					jsonObject.put("productImg", rs.getString("product_img"));
					jsonObject.put("productQty", rs.getString("product_qty"));
					jsonObject.put("productPrice", rs.getString("product_price"));
					jsonObject.put("productDesc", rs.getString("product_desc"));

					jsonArray.add(jsonObject);
				}

				res.setResponseCode(200);
				res.setResponseMsg("Success");
				res.setData("product fetched successfully!");
				res.setjData(jsonArray);

			} catch (Exception e) {
				e.printStackTrace();

				res.setResponseCode(500);
				res.setResponseMsg("error");
				res.setData("Internal server error!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response addToCart() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String cartQuery = "select p.product_name, p.product_brand, p.product_qty, p.product_price, u.user_name, u.phone_number, u.address, u.city, u.district, u.state, u.pincode from product_list as p inner join user_list as u on p.user_id = u.user_id;";
			System.out.println(cartQuery);

			try (Connection con = DriverManager.getConnection(url, username, password);
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery(cartQuery);) {

				JSONArray jArray = new JSONArray();
				while (rs.next()) {
					JSONObject jObject = new JSONObject();

					jObject.put("productName", rs.getString("product_name"));
					jObject.put("productBrand", rs.getString("product_brand"));
					jObject.put("productQty", rs.getString("product_qty"));
					jObject.put("productPrice", rs.getString("product_price"));
					jObject.put("userName", rs.getString("user_name"));
					jObject.put("phoneNumber", rs.getString("phone_number"));
					jObject.put("address", rs.getString("address"));
					jObject.put("city", rs.getString("city"));
					jObject.put("district", rs.getString("district"));
					jObject.put("state", rs.getString("state"));
					jObject.put("pincode", rs.getString("pincode"));

					jArray.add(jObject);
				}
				res.setResponseCode(200);
				res.setResponseMsg("success");
				res.setData("cart successfully added!");
				res.setjData(jArray);

			} catch (Exception e) {
				e.printStackTrace();

				res.setResponseCode(500);
				res.setResponseMsg("error");
				res.setData("Internal server error!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	@Override
	public Response softDelete(SignUpModel datas) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			try (Connection con = DriverManager.getConnection(url, username, password);
					Statement st = con.createStatement();) {

				String updateQuery = "UPDATE project_work.user_list SET is_active = 0 WHERE user_id = '" + datas.getUserId()
						+ "';";
				System.out.println(updateQuery);
				st.executeUpdate(updateQuery);

				res.setResponseCode(200);
				res.setResponseMsg("Success");
				res.setData("user deleted successfully");
			} catch (Exception e) {
				e.printStackTrace();

				res.setResponseCode(500);
				res.setResponseMsg("error");
				res.setData("Internal server error!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

}
