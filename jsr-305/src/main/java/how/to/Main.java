package how.to;

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

        // Object holder which allows null objects
        final NullableObjectHolder nullableObjectHolder = new NullableObjectHolder();

        // Attempt to set null object into the method which is marked with Nullable annotation
        nullableObjectHolder.setObject(null);

        // Attempt to call method on the object which is marked with Nullable, without null check
        nullableObjectHolder.getObject().toString();
    }

}
