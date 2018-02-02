package how.to;

import javax.annotation.Nullable;

/**
 * This implementation allows and can provide object which is null
 */
public class NullableObjectHolder implements ObjectHolder {

    private Object object;

    public void setObject(@Nullable final Object object) {
        this.object = object;
    }

    public @Nullable Object getObject() {
        return object;
    }

}
