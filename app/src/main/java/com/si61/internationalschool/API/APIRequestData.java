package com.si61.internationalschool.API;

import com.si61.internationalschool.Model.ModelResponse;
import com.si61.internationalschool.Model.ModelResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIRequestData {
    @GET("retrieve.php")
    Call<ModelResponse> ardRetrieve();

}
