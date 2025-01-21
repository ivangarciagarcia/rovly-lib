package dev.ivangg.rovly.lib.clerk;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.ivangg.rovly.lib.clerk.api.EmailAPI;
import dev.ivangg.rovly.lib.clerk.api.UserAPI;
import dev.ivangg.rovly.lib.clerk.converters.ClerkJaksonModule;
import dev.ivangg.rovly.lib.clerk.interceptors.ClerkAuthenticationInterceptor;
import dev.ivangg.rovly.lib.clerk.model.email.ClerkEmail;
import dev.ivangg.rovly.lib.clerk.model.user.ClerkUser;
import dev.ivangg.rovly.lib.clerk.model.user.ClerkUserCount;
import dev.ivangg.rovly.lib.clerk.processor.CallProcessor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class ClerkApiClient {


    private ClerkAuthenticationInterceptor interceptor;

    private OkHttpClient httpClient;
    private Retrofit retrofit;

    private UserAPI userAPI;
    private EmailAPI emailAPI;

    public ClerkApiClient(String apiKey) {
        this.interceptor = new ClerkAuthenticationInterceptor(apiKey);

        // Cliente Http con interceptores para la autenticacion
        httpClient = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1,TimeUnit.MINUTES)
                .writeTimeout(1,TimeUnit.MINUTES)
                .addInterceptor(this.interceptor)

                .build();

        // ObjectMapper para los JSON de clerk
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.registerModule(new ClerkJaksonModule());

        // Retrofit para manejo de conmunicaciones
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.clerk.com/v1/")
                .client(httpClient)
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .build();

        // Creamos la API
        userAPI = retrofit.create(UserAPI.class);
        emailAPI = retrofit.create(EmailAPI.class);
    }

    public List<ClerkUser> listUsers(List<String> email) throws IOException {
        return new CallProcessor<>(userAPI.listUsers(email)).process();
    }

    public ClerkUserCount countUsers() throws IOException {
        return new CallProcessor<>(userAPI.countUsers()).process();
    }

    public ClerkUser getUser(String userId) throws IOException {
        return new CallProcessor<>(userAPI.getUser(userId)).process();
    }

    public ClerkEmail getEmail(String emailId) throws IOException {
        return new CallProcessor<>(emailAPI.getEmail(emailId)).process();
    }

}
