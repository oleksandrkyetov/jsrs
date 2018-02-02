## JSR 305: Annotations for Software Defect Detection

### Notes

##### General
At the moment, [JSR-305](https://jcp.org/en/jsr/detail?id=305) is in **Dormant** status, so it is not advised for usage

##### Description
Below you can see useful application of `@Nonnull` and `@Nullable` annotations and hints provided by [IntelliJ IDEA](https://www.jetbrains.com/idea/)

##### Prerequisites
1. [Maven](https://maven.apache.org/)
2. [IntelliJ IDEA](https://www.jetbrains.com/idea/)

### Use cases

##### Non-null reference hints
We have a class with two methods, `setObject` which does not allow `null` references and `getObject` which always provide not a `null` reference to an object
```java
public class NonnullObjectHolder implements ObjectHolder {

    private Object object;

    public void setObject(@Nonnull final Object object) {
        this.object = object;
    }

    public @Nonnull Object getObject() {
        return object;
    }

}
```
Marking method parameters and/or return type with `@Nonnull` annotation does not prevent developers from passing `null` references to methods or calling methods on references which might be `null`, but [IntelliJ IDEA](https://www.jetbrains.com/idea/) highlights such cases and signal developers that something is potentially wrong. For example, for code below
```java
public class Main {

    public static void main(final String[] arguments) {
    // Object holder which does not allow null objects
        final NonnullObjectHolder nonnullObjectHolder = new NonnullObjectHolder();
    
        // Attempt to set null object into the method which is marked with Nonnull annotation
        nonnullObjectHolder.setObject(null);
    
        // Attempt to perform a null check on the object returned from the method, which is marked with Nonnull annotation
        if (nonnullObjectHolder.getObject() != null) {
            // ...
        }
        
    }

}
```
Case when we try to pass `null` as an argument to the of the method which is marked `@Nonnull`

![jsr-305-nonnull-check-1.png](screenshots/jsr-305-nonnull-check-1.png)

Case when we try to perform `null` check on the object returned by method which return type is marked `@Nonnull`

![jsr-305-nonnull-check-2.png](screenshots/jsr-305-nonnull-check-2.png)

##### Nullable reference hints
Similar to the cases above, we have a class with two methods, `setObject` which allows `null` references and `getObject` which might provide a `null` reference to an object
```java
public class NullableObjectHolder implements ObjectHolder {

    private Object object;

    public void setObject(@Nullable final Object object) {
        this.object = object;
    }

    public @Nullable Object getObject() {
        return object;
    }

}
``` 
As previously, marking method parameters and/or return type with `@Nullable` annotation does not prevent developers from calling methods on `null` references, but again, [IntelliJ IDEA](https://www.jetbrains.com/idea/) highlights such cases and give useful hints. For example, for code below
```java
public class Main {

    public static void main(final String[] arguments) {
        // Object holder which allows null objects
        final NullableObjectHolder nullableObjectHolder = new NullableObjectHolder();

        // Attempt to set null object into the method which is marked with Nullable annotation
        nullableObjectHolder.setObject(null);

        // Attempt to call method on the object which is marked with Nullable, without null check
        nullableObjectHolder.getObject().toString();
    }

}

```
Case when we try to call a method on a reference which is marked `@Nullable` (potentially `null` reference)

![jsr-305-nullable-check-1.png](screenshots/jsr-305-nullable-check-1.png)

##### Conclusion
There is much more in JSR-305, but unfortunately, other annotations are not supported by [IntelliJ IDEA](https://www.jetbrains.com/idea/) out of the box. Moreover, as it mentioned above, JSR status is **Dormant**, thus it is not recommended to use. In general, from what is observed, it would have been a useful addition for the developers and tools to prevent mistakes and errors on early stage of deevlopment
