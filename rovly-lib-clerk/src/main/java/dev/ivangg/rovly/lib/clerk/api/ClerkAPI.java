package dev.ivangg.rovly.lib.clerk.api;

import dev.ivangg.rovly.lib.clerk.model.ClerkUser;
import dev.ivangg.rovly.lib.clerk.model.ClerkUserCount;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface ClerkAPI {
    @GET("users")
    Call<List<ClerkUser>> listUsers(@Query("email") List<String> email);

    @GET("users/count")
    Call<ClerkUserCount> countUsers();

}
