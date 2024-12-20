package dev.ivangg.rovly.lib.clerk.interceptors;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class ClerkAuthenticationInterceptor implements Interceptor {

    private String clerkApiKey;

    public ClerkAuthenticationInterceptor(String clerkApiKey) {
        super();
        this.clerkApiKey = clerkApiKey;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        return chain.proceed(injectAuthDataIntoRequest(chain.request()));
    }

    private Request injectAuthDataIntoRequest(Request req) {
        // Cabecera de autenticacion
        return req.newBuilder()
                .header("Authorization", "Bearer " + clerkApiKey)
                .build();
    }
}
