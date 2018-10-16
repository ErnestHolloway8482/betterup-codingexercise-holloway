package com.betterup.codingexercise.dimodules;

import com.betterup.codingexercise.daos.AccountInfoDAO;
import com.betterup.codingexercise.daos.AccountInfoDAOImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class DAOModule {
    @Provides
    public static AccountInfoDAO provideAccountInfoDAO() {
        return new AccountInfoDAOImpl();
    }
}
