package com.example.wd_mvvm.injection

import com.example.wd_mvvm.Constants
import com.example.wd_mvvm.repository.Repository
import com.example.wd_mvvm.repository.remote.RemoteDataSource
import com.example.wd_mvvm.repository.remote.RemoteDataSourceImpl
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object AppModules {

    private val repository = module {
        single { Repository() }
    }

    private val retrofit = module {
        single {
             Retrofit.Builder()
                .baseUrl( Constants.url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build().create(RemoteDataSource::class.java)
        }
    }

    private val remoteDataSource = module {
        single {
            RemoteDataSourceImpl()
        }
    }

    val modules = listOf(repository, retrofit, remoteDataSource)
}