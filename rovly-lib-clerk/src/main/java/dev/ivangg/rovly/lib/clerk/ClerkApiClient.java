package dev.ivangg.rovly.lib.clerk;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.ivangg.rovly.lib.clerk.api.ClerkAPI;
import dev.ivangg.rovly.lib.clerk.converters.ClerkJaksonModule;
import dev.ivangg.rovly.lib.clerk.interceptors.ClerkAuthenticationInterceptor;
import dev.ivangg.rovly.lib.clerk.model.ClerkUser;
import dev.ivangg.rovly.lib.clerk.model.ClerkUserCount;
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

    private ClerkAPI api;

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
        api = retrofit.create(ClerkAPI.class);
    }

    public List<ClerkUser> listUsers(List<String> email) throws IOException {
        return new CallProcessor<>(api.listUsers(email)).process();
    }

    public ClerkUserCount countUsers() throws IOException {
        return new CallProcessor<>(api.countUsers()).process();
    }

    public ClerkUser getUser(String userId) throws IOException {
        return new CallProcessor<>(api.getUser(userId)).process();
    }

}
