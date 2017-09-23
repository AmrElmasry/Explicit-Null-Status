package com.amrelmasry.processorly;

import java.util.Arrays;
import java.util.List;

/**
 * These values are copied from :
 * https://github.com/JetBrains/kotlin/blob/master/core/descriptor.loader.java/src/org/jetbrains/kotlin/load/java/JvmAnnotationNames.kt
 * <p>
 * Created by Amr Elmasry on 18/09/17.
 */
final class SupportedAnnotations {

    static final List<String> NULLABLE_ANNOTATIONS = Arrays.asList(
            "org.jetbrains.annotations.Nullable",
            "android.support.annotation.Nullable",
            "com.android.annotations.Nullable",
            "org.eclipse.jdt.annotation.Nullable",
            "org.checkerframework.checker.nullness.qual.Nullable",
            "javax.annotation.Nullable",
            "javax.annotation.CheckForNull",
            "edu.umd.cs.findbugs.annotations.CheckForNull",
            "edu.umd.cs.findbugs.annotations.Nullable",
            "edu.umd.cs.findbugs.annotations.PossiblyNull",
            "io.reactivex.annotations.Nullable");
    static final List<String> NOT_NULL_ANNOTATIONS = Arrays.asList(
            "org.jetbrains.annotations.NotNull",
            "edu.umd.cs.findbugs.annotations.NonNull",
            "android.support.annotation.NonNull",
            "com.android.annotations.NonNull",
            "org.eclipse.jdt.annotation.NonNull",
            "org.checkerframework.checker.nullness.qual.NonNull",
            "lombok.NonNull",
            "io.reactivex.annotations.NonNull");

    private SupportedAnnotations() {
        throw new AssertionError("No Instances");
    }

}
