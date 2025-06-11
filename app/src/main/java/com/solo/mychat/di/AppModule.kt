package com.solo.mychat.di

import android.content.Context
import androidx.room.Room
import com.solo.mychat.BuildConfig
import com.solo.mychat.data.local.db.AppDatabase
import com.solo.mychat.data.local.db.ChatDao
import com.solo.mychat.data.local.db.GoalsDao
import com.solo.mychat.data.remote.api.ApiService
import com.solo.mychat.data.remote.interceptors.MyInterceptor
import com.solo.mychat.data.repository.ChatRepositoryImpl
import com.solo.mychat.data.repository.GoalRepositoryImpl
import com.solo.mychat.domain.repository.ChatRepository
import com.solo.mychat.domain.repository.GoalsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(MyInterceptor())
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .connectTimeout(30, TimeUnit.SECONDS) // ⏱ Increase connection timeout
            .readTimeout(30, TimeUnit.SECONDS)    // ⏱ Increase read timeout
            .writeTimeout(30, TimeUnit.SECONDS)   // ⏱ Increase write timeout
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideNetworkService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    fun provideChatRepository(
        apiService: ApiService,
        chatDao: ChatDao
    ): ChatRepository = ChatRepositoryImpl(
        apiService,
        chatDao
    )

    @Singleton
    @Provides
    fun provideGoalsDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context = context,
            klass = AppDatabase::class.java,
            name = "goals_db"
        ).fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideGoalsDao(appDatabase: AppDatabase): GoalsDao =
        appDatabase.goalsDao()

    @Singleton
    @Provides
    fun provideChatDao(appDatabase: AppDatabase): ChatDao =
        appDatabase.chatDao()

    @Provides
    fun provideGoalRepository(
        goalsDao: GoalsDao
    ): GoalsRepository = GoalRepositoryImpl(goalsDao)

}