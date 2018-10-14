package com.betterup.codingexercise.dimodules;

import com.betterup.codingexercise.restclients.AccountRestClient;
import com.betterup.codingexercise.restclients.AccountRestClientImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class RestClientModule {
    @Provides
    public AccountRestClient provAccountRestClient(){
        return new AccountRestClientImpl();
    }
}
