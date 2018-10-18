package com.betterup.codingexercise.utilities;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * A collection of static utility methods related to Java's Reflection system.
 * <p>
 * Created by rob on 10/20/16.
 */
public class ReflectionUtility {
    //region Constants

    private final static String TAG = ReflectionUtility.class.getSimpleName();

    //endregion

    //region Methods

    /**
     * Returns the class of <c>objectSource</c>'s <c>parameterIndex</c>-nth type parameter.
     *
     * @param objectSource   The object containing the type parameters to evaluate.
     *                       It must be the subclass of a generic type.
     * @param parameterIndex The 0-based index of the type parameter to evaluate.
     * @return The class of <c>objectSource</c>'s <c>parameterIndex</c>-nth type parameter.
     */
    public static Class<?> getParameterClass(@NonNull Object objectSource,
                                             @IntRange(from = 0) int parameterIndex) {
        Type superClass = objectSource.getClass().getGenericSuperclass();
        if ((null == superClass) || !(superClass instanceof ParameterizedType)) {
            // TODO: Use a Log Manager so it can be mocked.
//            Log.e(TAG, String.format(Locale.ENGLISH,
//                    "Object %s has no valid generic super class.", objectSource));
            return null;
        }

        Type[] typeArguments = ((ParameterizedType) superClass).getActualTypeArguments();

        if (parameterIndex >= typeArguments.length) {
            // TODO: Use a Log Manager so it can be mocked.
//            Log.e(TAG, String.format(Locale.ENGLISH,
//                    "Object %s has no type parameter at index %d.", objectSource, parameterIndex));
            return null;
        }

        return (Class<?>) typeArguments[parameterIndex];
    }

    /**
     * Sets a field name to a value via reflection.
     *
     * @param srcObject is the source {@link Object} that contains the field to be set.
     * @param fieldName is the name of the field within the source {@link Object}.
     * @param value     is the value to set for the given field name.
     */
    public static void setField(@NonNull Object srcObject, @NonNull String fieldName, @NonNull Object value) {

        Class<?> classType = srcObject.getClass();
        Field field;

        try {
            field = classType.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(srcObject, value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    //endregion
}
