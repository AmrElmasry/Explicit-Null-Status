package com.amrelmasry.processly.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Include the annotated target to the explicit-null-status-check (this means it will be forced to
 * use null-status annotations on all its constructors, fields and methods (except ignored ones).
 * <p>
 * if the target is a package, all enclosed classes will be checked, but enclosed packages won't.
 *
 * @see Ignore
 * @see IgnoreConstructors
 * @see IgnoreFields
 * @see IgnoreMethods
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.PACKAGE, ElementType.TYPE})
@Inherited
public @interface ExplicitNullStatus {
}
