package com.amrelmasry.processly.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Skip the annotated target while doing the explicit-null-status-check
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.FIELD,
        ElementType.METHOD,
        ElementType.CONSTRUCTOR,
        ElementType.PARAMETER})
public @interface Ignore {
}
