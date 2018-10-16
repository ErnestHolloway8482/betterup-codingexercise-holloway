package com.betterup.codingexercise.managers;

import com.betterup.codingexercise.views.Screen;

public interface ScreenManager {
//    <T extends Screen> T getScreenFromClass(final Class<T> screenClass);
    <T extends Screen> Screen getScreenFromClass(final Class<T> screenClass);
}
