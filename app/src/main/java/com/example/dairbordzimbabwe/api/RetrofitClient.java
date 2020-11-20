package com.example.dairbordzimbabwe.api;

import com.example.dairbordzimbabwe.interfaces.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "http://192.168.1.13/dairibord/api/";
    private Retrofit retrofit;
    private static RetrofitClient m_instance;

    private RetrofitClient () {
        retrofit = new Retrofit.Builder()
                .baseUrl( BASE_URL )
                .addConverterFactory(GsonConverterFactory.create() )
                .build();

    }

    public static synchronized RetrofitClient get_instance () {
        if ( m_instance == null ) {
            m_instance = new RetrofitClient();
        }

        return m_instance;

    }

    public Api get_api() {
        return retrofit.create( Api.class );
    }

}
