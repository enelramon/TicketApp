package com.ucne.ticketcompose.di

import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.ucne.ticketcompose.data.local.TicketDb
import com.ucne.ticketcompose.data.remote.TicketApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn ( SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesTicketDatabase(@ApplicationContext appContext: Context): TicketDb =
        Room.databaseBuilder(
            appContext,
            TicketDb::class.java,
            "Ticket.db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideCoinApi(moshi: Moshi): TicketApi {
        return Retrofit.Builder()
            .baseUrl("https://api.coinpaprika.com")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(TicketApi::class.java)
    }
}