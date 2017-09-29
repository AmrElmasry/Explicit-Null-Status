# Explicit-Null-Status
[![](https://jitpack.io/v/AmrElmasry/explicit-null-status.svg)](https://jitpack.io/#AmrElmasry/explicit-null-status)

- Force types to be explicit about the **null-status** (nullable or non-null) of all enclosed constructors, fields and methods

# Motiviation

- In Java, the type system doesn't distinguish between nullable and non-null references,
so It's recommended to use **null-status** annotations `@NonNull` or `@Nullable` annotations. 
this library forces any type annotated with `@ExplicitNullStatus` to use the null-status annotations or it will fail the build

# Usage 

```java
@ExplicitNullStatus // => @NonNull @Nullable are required 
public class User {
    @NonNull
    private String email;
    @Nullable
    private String avatar;
    ...
}
```

```java
@ExplicitNullStatus // => @NonNull @Nullable are required 
public class User {
    private String email; // => compile-time error 
    @Nullable
    private String avatar;
    ...
}
```
# Suppression

- you can suppress the **null-status-check** using `@Ignore` on the ignore element or `@IgnoreConstructors`, `@IgnoreFields`, and ``@IgnoreMethods`` on a type to ignore multiple elements.

```java
@ExplicitNullStatus // => @NonNull @Nullable are required 
public class User {
    @Ignore
    private String email; // => will be ignored
    @Nullable
    private String avatar;
    ...
}
```

```java
@ExplicitNullStatus // => @NonNull @Nullable are required 
@IgnoreFields
public class User {
    private String email; // => will be ignored
    @Nullable
    private String avatar;
    ...
}
```

# Download

- Add it in your root build.gradle:
```groovy
repositories {
    maven { url 'https://jitpack.io' }
	}
```
- Add it in app build.gradle:
```groovy
dependencies {
    annotationProcessor 'com.github.AmrElmasry.explicit-null-status:explicit-null-status-compiler:1.0.0-beta4'
    compile 'com.github.AmrElmasry.explicit-null-status:explicit-null-status-annotations:1.0.0-beta4'
    }   
```

# Supported Annotations 

- check [here](https://github.com/AmrElmasry/Explicit-Null-Status/blob/master/explicit-null-status-compiler/src/main/java/com/amrelmasry/processorly/SupportedAnnotations.java) to show the full list


