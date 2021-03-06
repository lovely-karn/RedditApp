package com.lovely.dwelloassignment.retrofit;

import com.lovely.dwelloassignment.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * RestClient
 */
public final class RestClient {
    private static final int TIME_OUT = 120;

    private static Retrofit retrofit = null;

    /**
     * Empty Constructor
     * not called
     */
    private RestClient() {
    }


    /**
     * Gets api interface.
     *
     * @return object of ApiInterface
     */
    public static ApiInterface getApiInterface() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient().build())
                    //.client(secureConnection().build())
                    .build();
        }
        return retrofit.create(ApiInterface.class);
    }


    /**
     * Gets retrofit builder.
     *
     * @return object of Retrofit
     */
    public static Retrofit getRetrofitBuilder() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient().build())
                    .build();
        }
        return retrofit;
    }

    /**
     * Gets request header.
     *
     * @return the request header
     */
    private static OkHttpClient getRequestHeader() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                //.addInterceptor(getLoggingInterceptor())
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
        return okHttpClient;
    }

    /**
     * @return object of OkHttpClient.Builder
     */
    private static OkHttpClient.Builder httpClient() {

        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        // add your other interceptors …
        // add logging as last interceptor
        httpClient.addInterceptor(getLoggingInterceptor());

        return httpClient;
    }

    /**
     * Method to get object of HttpLoggingInterceptor
     *
     * @return object of HttpLoggingInterceptor
     */
    private static HttpLoggingInterceptor getLoggingInterceptor() {
        final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        //logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        if (BuildConfig.DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        }
        return logging;
    }
}
