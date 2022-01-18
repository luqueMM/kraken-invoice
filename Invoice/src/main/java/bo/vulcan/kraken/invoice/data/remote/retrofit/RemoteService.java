/*
package bo.vulcan.kraken.invoice.data.remote.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import bo.vulcan.kraken.invoice.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RemoteService {

    private static RemoteService instance;

    private final Retrofit retrofit;
    private AuthenticationApi authenticationApi;

    public static synchronized RemoteService getInstance() {
        if (instance == null) {
            instance = new RemoteService();
        }
        return instance;
    }

    public RemoteService() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        if(BuildConfig.DEBUG){
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(new HeaderInterceptor(authToken))
                .addInterceptor(connectivityInterceptor)
                .sslSocketFactory(getSSLConfig(context).getSocketFactory())
                .build();

        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        retrofit = new Retrofit.Builder()
                .client(httpClient)
                .baseUrl(BuildConfig.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public AuthenticationApi getAuthenticationApi(){
        if(authenticationApi != null){
            return authenticationApi;
        }

        authenticationApi = createService(AuthenticationApi.class);//retrofit.create(AuthenticationApi.class);
        return authenticationApi;
    }

    private <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }

}
*/
