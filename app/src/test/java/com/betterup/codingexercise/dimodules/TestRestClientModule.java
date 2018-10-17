package com.betterup.codingexercise.dimodules;

import com.betterup.codingexercise.restclients.AccountRestClient;
import com.betterup.codingexercise.restclients.AccountRestClientImpl;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TestRestClientModule {
    @Singleton
    @Provides
    public AccountRestClient provideAccountRestClient(){
        return Mockito.mock(AccountRestClientImpl.class);
    }
}
