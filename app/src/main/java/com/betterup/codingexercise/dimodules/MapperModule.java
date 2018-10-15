package com.betterup.codingexercise.dimodules;

import com.betterup.codingexercise.mappers.AccountInfoDataMapper;

import dagger.Module;
import dagger.Provides;

@Module
public class MapperModule {
    @Provides
    AccountInfoDataMapper provideAccountInfoDataMapper(){
        return new AccountInfoDataMapper();
    }
}
