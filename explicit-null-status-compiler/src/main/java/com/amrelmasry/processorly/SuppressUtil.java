package com.amrelmasry.processorly;

import com.amrelmasry.processly.annotations.Ignore;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;

/**
 * Created by Amr Elmasry on 22/09/17.
 */

final class SuppressUtil {
    private SuppressUtil() {
        throw new RuntimeException("Non Instances");
    }

    private static boolean isAnnotatedWithIgnore(Element element) {
        return element.getAnnotation(Ignore.class) != null;
    }

    static boolean shouldSkipField(VariableElement field) {
        return isAnnotatedWithIgnore(field);
    }

    static boolean shouldSkipConstructor(ExecutableElement element) {
        return isAnnotatedWithIgnore(element);
    }

    static boolean shouldSkipMethod(ExecutableElement element) {
        return isAnnotatedWithIgnore(element);
    }
}
