package com.example.dairbordzimbabwe.interfaces;

import com.example.dairbordzimbabwe.models.DefaultResponse;
import com.example.dairbordzimbabwe.models.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

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
}
