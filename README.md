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
