## JSR 305: Annotations for Software Defect Detection

### Notes

##### General
At the moment, [JSR-305](https://jcp.org/en/jsr/detail?id=305) is in **Dormant** state, so it is advised for usage

##### Description
Below you can see when and why to use `@Nonnull` and `@Nullable` annotations. Also, hints provided by [IntelliJ IDEA](https://www.jetbrains.com/idea/) for these annotations are described

##### Prerequisites
1. [Maven](https://maven.apache.org/)
2. [IntelliJ IDEA](https://www.jetbrains.com/idea/)

### Use cases

##### Non-null reference hints
We have a class with two methods, `setObject` which does not allow `null` reference, and `getObject` which always return not a `null` reference
```java
public class NonnullObjectHolder implements ObjectHolder {

    private Object object;

    public void setObject(final @Nonnull Object object) {
        this.object = object;
    }

    public @Nonnull Object getObject() {
        return object;
    }

}
```
Marking method parameters and/or return type with `@Nonnull` annotation does not prevent developers from passing `null` references to methods or calling methods on references which might be `null`, but [IntelliJ IDEA](https://www.jetbrains.com/idea/) highlights such cases and signals that something is potentially wrong using hints. For example, for the code below
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
Case when we are passing `null` as an argument to a method, parameter of which is marked with `@Nonnull`

![jsr-305-nonnull-check-2.png](screenshots/jsr-305-nonnull-check-2.png)

Case when we are performing `null` check on the reference returned by a method, return type of which is marked with `@Nonnull`

![jsr-305-nonnull-check-1.png](screenshots/jsr-305-nonnull-check-1.png)

##### Nullable reference hints
Similar to cases above, we have a class with two methods, `setObject` which allows `null` reference and `getObject` which might return a `null` reference
```java
public class NullableObjectHolder implements ObjectHolder {

    private Object object;

    public void setObject(final @Nullable Object object) {
        this.object = object;
    }

    public @Nullable Object getObject() {
        return object;
    }

}
``` 
As noted earlier, marking method parameters and/or return type with `@Nullable` annotation does not prevent developers from calling methods on `null` references, but again, [IntelliJ IDEA](https://www.jetbrains.com/idea/) highlights such cases and gives useful hints. For example, for code below
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
Case when we are calling a method on a reference which is marked `@Nullable` (potentially `null` reference)

![jsr-305-nullable-check-1.png](screenshots/jsr-305-nullable-check-1.png)

##### Conclusion
There is much more in JSR-305, but unfortunately, other annotations are not supported by [IntelliJ IDEA](https://www.jetbrains.com/idea/) out of the box. Moreover, as it mentioned above, JSR status is **Dormant**, thus it is not recommended for usage at the moment

From what is observed, it would have been a useful addition for tools and developers and would have helped to prevent mistakes and errors on the early stage of development
