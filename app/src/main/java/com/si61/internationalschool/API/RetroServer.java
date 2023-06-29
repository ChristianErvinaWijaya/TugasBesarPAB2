package com.si61.internationalschool.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class RetroServer {
    private static final String alamatServer = "https://tugaspab2.000webhostapp.com/";
    private static Retrofit retro;

    public static Retrofit konekRetrofit(){
        if(retro==null){
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            retro = new Retrofit.Builder()
                    .baseUrl(alamatServer)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }

        return retro;
    }
}
