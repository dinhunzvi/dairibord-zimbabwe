package com.example.dairbordzimbabwe.interfaces;

import com.example.dairbordzimbabwe.models.CustomerList;
import com.example.dairbordzimbabwe.models.DefaultResponse;
import com.example.dairbordzimbabwe.models.LoginResponse;
import com.example.dairbordzimbabwe.models.ProductList;
import com.example.dairbordzimbabwe.models.SupplierList;
import com.example.dairbordzimbabwe.models.UserList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {

    /* end points for user */

    /* get all users */
    @GET( "users" )
    Call<UserList> get_users ();

    /* add a new user */
    @FormUrlEncoded
    @POST( "user" )
    Call<DefaultResponse> add_user (
            @Field( "email" ) String email,
            @Field( "username" ) String username,
            @Field( "first_name" ) String first_name,
            @Field( "last_name" ) String last_name
    );

    /* user login */
    @FormUrlEncoded
    @POST( "login" )
    Call<LoginResponse> sign_in (
            @Field( "username" ) String username,
            @Field( "password" ) String password
    );

    /* update user */
    @FormUrlEncoded
    @PUT( "user/{user_id}" )
    Call<DefaultResponse> update_user (
            @Path( "user_id" ) int user_id,
            @Field( "first_name" ) String first_name,
            @Field( "last_name" ) String last_name,
            @Field( "email" ) String email,
            @Field( "username" ) String username
    );

    /* change user password */
    @FormUrlEncoded
    @PUT( "user_password/{user_id}" )
    Call<DefaultResponse> change_password (
            @Path( "user_id" ) int user_id,
            @Field( "current" ) String current,
            @Field( "password" ) String password
    );

    /* end points for supplier */

    /* get all suppliers */
    @GET( "suppliers" )
    Call<SupplierList> get_suppliers ();

    /* add a new supplier */
    @FormUrlEncoded
    @POST( "supplier" )
    Call<DefaultResponse> add_supplier (
            @Field( "supplier_name" ) String supplier_name,
            @Field( "address" ) String address,
            @Field( "phone_number" ) String phone_number
    );

    /* update a supplier's details */
    @FormUrlEncoded
    @PUT( "supplier/{supplier_id}" )
    Call<DefaultResponse> edit_supplier (
            @Path( "supplier_id" ) int supplier_id,
            @Field( "supplier_name" ) String supplier_name,
            @Field( "address" ) String address,
            @Field( "phone_number" ) String phone_number
    );

    /* end points for customers */

    /* get all customers */
    @GET( "customers" )
    Call<CustomerList> get_customers();

    /* add a new customer */
    @FormUrlEncoded
    @POST( "customer" )
    Call<DefaultResponse> add_customer (
            @Field( "customer_name" ) String customer_name,
            @Field( "address" ) String address,
            @Field( "contact_person" ) String contact_person,
            @Field( "contact_mobile" ) String contact_mobile
    );

    /* end points for products come here */

    /* get all products */
    @GET( "products" )
    Call<ProductList> get_products();

    /* add a new product */
    @FormUrlEncoded
    @POST( "product" )
    Call<DefaultResponse> add_product (
            @Field( "product_name" ) String product_name,
            @Field( "quantity_in_stock" ) int stock_quantity,
            @Field( "milk_required" ) int milk_required
    );

}
