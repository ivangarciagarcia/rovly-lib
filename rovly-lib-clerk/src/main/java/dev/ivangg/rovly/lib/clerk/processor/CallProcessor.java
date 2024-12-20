package dev.ivangg.rovly.lib.clerk.processor;

import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class CallProcessor<R>  {

    private Call<R> call;

    public CallProcessor(Call<R> call) {
        super();
        this.call = call;
    }

    public R process() throws IOException{
        Response<R> response = call.execute();
        if (response.isSuccessful()) {
            return response.body();
        } else {
            throw new IOException("Server responded with " + response.code()+" : " + response.errorBody().string());
        }
    }

}
