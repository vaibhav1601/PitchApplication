package z_aksys.solutions.pitchapplication.API;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import z_aksys.solutions.pitchapplication.Request.NewsRequest;
import z_aksys.solutions.pitchapplication.Response.NewsResponse;

/**
 * Created by Vaibhav on 24/03/18.
 */

public interface RestAPI {

    @GET("pitchapp/api/news")
    Call<NewsResponse> getNews(@Header("Content-Type") String content_type, @Header("APP-KEY") String app_Key, @Header("APP-SECRET") String app_Secret, @Query("emp_no") String emp_no, @Query("start") String start, @Query("limit") String limit);

    @GET("pitchapp/api/faq")
    Call<NewsResponse> getFaq(@Header("Content-Type") String content_type, @Header("APP-KEY") String app_Key, @Header("APP-SECRET") String app_Secret, @Query("emp_no") String emp_no, @Query("start") String start, @Query("limit") String limit);

}


