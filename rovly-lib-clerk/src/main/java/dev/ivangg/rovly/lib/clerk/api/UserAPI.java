package dev.ivangg.rovly.lib.clerk.api;

import dev.ivangg.rovly.lib.clerk.model.user.ClerkUser;
import dev.ivangg.rovly.lib.clerk.model.user.ClerkUserCount;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface UserAPI {
    @GET("users")
    Call<List<ClerkUser>> listUsers(@Query("email") List<String> email);

    @GET("users/count")
    Call<ClerkUserCount> countUsers();

    @GET("users/{user_id}")
    Call<ClerkUser> getUser(@Path("user_id") String userId);
}
