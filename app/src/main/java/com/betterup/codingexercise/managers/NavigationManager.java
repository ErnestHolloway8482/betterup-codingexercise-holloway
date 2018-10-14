package com.betterup.codingexercise.managers;

import android.support.annotation.NonNull;

import com.betterup.codingexercise.views.Screen;
import com.betterup.codingexercise.views.ViewContainer;

public interface NavigationManager {
    /**
     * Sets the main view container that all other views will be swapped into as navigation requests are made.
     *
     * @param viewContainer is the main view container provided by the {@link com.betterup.codingexercise.activities.MainActivity}
     */
    public void setViewContainer(final ViewContainer viewContainer);

    /**
     * Pushes the next view to be displayed onto the view stack.
     *
     * @param view is {@link Screen} next up to be displayed.
     * @return returns the {@link Screen} that was just pushed onto the view stack.
     */
    public Screen push(@NonNull final Screen view);

    /**
     * Pops(Removes) the current view that is being displayed off of the top of the view stack. If there are no more items to pop, then no operation will be performed.
     */
    public void pop();

    /**
     * @return the {@link Screen} that is at the top of the view stack.
     */
    public Screen peek();

    /**
     * Removes all {@link Screen} from the view stack.
     */
    public void clearAllViewsFromStack();

    /**
     * This method will show the screen that is currently at the top of the view stack. The reason why this method exists, is to allow the developer
     * to setup the navigational view stack the way they want first before displaying anything to the user granting them more control in the event that
     * exceptional navigational cases need to be covered. This avoids the scenario as well as having a screen be automatically displayed when a screen is pushed/poped to/from the stack.
     */
    public void showScreen();

    /**
     * @return true if the user is on the last screen, false otherwise. This can be utilized to allow the app to exit when the user taps back on the last screen.
     */
    public boolean isOnLastScreen();
}
