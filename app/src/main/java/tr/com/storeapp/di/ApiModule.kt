package tr.com.storeapp.di

import android.content.SharedPreferences
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import tr.com.storeapp.BuildConfig
import tr.com.storeapp.network.ApiService
import tr.com.storeapp.network.repository.ApiHelper
import tr.com.storeapp.network.repository.ApiRepositoryImp
import tr.com.storeapp.utils.AppHelper
import tr.com.storeapp.utils.TOKEN
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

/**
 * The Main [Module] that holds all api core classes and provides these instances
 */
@Module
class ApiModule {

    /**
     * Provides BASE_URL
     */
    @Provides
    @Named("BASE_URL")
    fun provideBaseUrl() : String {
        return BuildConfig.BASE_URL
    }

    /**
     * Provides [GsonBuilder] instance
     */
    @Provides
    fun provideGsonBuilder() : GsonBuilder {
        return GsonBuilder()
    }

    /**
     * Provides [GsonConverterFactory] instance
     */
    @Provides
    fun provideConverterFactory(builder: GsonBuilder) : Converter.Factory {
        return GsonConverterFactory.create(builder.create())
    }

    /**
     * Provides [RxJava2CallAdapterFactory] instance
     */
    @Provides
    fun provideRxConverterFactory() : RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    /**
     * Provides [HttpLoggingInterceptor] instance
     */
    @Provides
    fun provideLoggingIntercepter() : HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG)
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        else
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE

        return httpLoggingInterceptor
    }

    /**
     * Provides [Interceptor] for managing Authorization
     */
    @Provides
    @Named("AuthInterceptor")
    internal fun provideAuthInterceptor(sharedPreferences: SharedPreferences): Interceptor {
        return Interceptor { chain ->
            val original = chain.request()
            var request = original.newBuilder().build()

            val token = sharedPreferences.getString(TOKEN, "")

            if (token != null && token != "") {
                request = original.newBuilder()
                    .header("Authorization", "Bearer $token")
                    .method(original.method, original.body)
                    .build()
            }

            chain.proceed(request)
        }
    }

    /**
     * Provides [OkHttpClient] instance
     */
    @Provides
    fun provideOkHttp(httpLoggingInterceptor: HttpLoggingInterceptor, @Named("AuthInterceptor") authInterceptor: Interceptor) : OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(BuildConfig.TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(BuildConfig.TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(BuildConfig.TIMEOUT.toLong(), TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(authInterceptor)
            .retryOnConnectionFailure(true)
            .build()
    }

    /**
     * Provides [Retrofit] instance
     */
    @Provides
    fun provideRetrofit(@Named("BASE_URL") baseUrl : String, okHttpClient: OkHttpClient, rxJava2CallAdapterFactory: RxJava2CallAdapterFactory, gsonConverterFactory: Converter.Factory) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    /**
     * Provides [ApiService] instance
     */
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    /**
     * Provides [ApiHelper] instance
     */
    @Provides
    fun provideApiHelper(apiService: ApiService, appHelper: AppHelper) : ApiHelper {
        return ApiHelper(apiService, appHelper)
    }

    /**
     * Provides [ApiRepositoryImp] instance
     */
    @Singleton
    @Provides
    fun provideApiRepositoryImp(apiHelper: ApiHelper) : ApiRepositoryImp {
        return ApiRepositoryImp(apiHelper)
    }

}