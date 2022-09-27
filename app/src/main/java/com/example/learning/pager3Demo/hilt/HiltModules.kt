package com.example.learning.pager3Demo.hilt

import android.content.Context
import androidx.room.Room
import com.example.learning.pager3Demo.remote.MovieInterface
import com.example.learning.pager3Demo.ui.details.MovieDetailsRepository
import com.example.learning.pager3Demo.utils.Constants
import com.example.learning.roomDataBase.room.AppDatabase
import com.example.learning.roomDataBase.room.User
import com.example.learning.roomDataBase.room.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object HiltModules {
    @Provides
    fun provideRetrofitInterface(): MovieInterface {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(
            GsonConverterFactory.create()
        ).build().create(MovieInterface::class.java)
    }

    @Provides
    fun provideRepository(movieInterface: MovieInterface):MovieDetailsRepository {
        return MovieDetailsRepository(movieInterface)
    }

    @Provides
    fun provideUserDao(appDatabase:AppDatabase) : UserDao{
        return appDatabase.userDao()
    }

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext : Context): AppDatabase{
        return Room.databaseBuilder(appContext,
        AppDatabase::class.java,
        "app_database"
        ).build()
    }
}