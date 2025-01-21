package dev.ivangg.rovly.lib.clerk.api;

import dev.ivangg.rovly.lib.clerk.model.email.ClerkEmail;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EmailAPI {

    @GET("email_addresses/{email_address_id}")
    Call<ClerkEmail> getEmail(@Path("email_address_id") String emailId);
}
