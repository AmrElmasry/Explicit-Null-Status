package com.amrelmasry.processly.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Amr Elmasry on 17/09/17.
 */

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.FIELD,
        ElementType.METHOD,
        ElementType.CONSTRUCTOR,
        ElementType.PARAMETER})
public @interface Ignore {
}
