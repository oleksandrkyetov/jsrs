package how.to;

import javax.annotation.Nonnull;

/**
 * This implementation neither accepts nor provides object which is null
 */
public class NonnullObjectHolder implements ObjectHolder {

    private Object object;

    public void setObject(@Nonnull final Object object) {
        this.object = object;
    }

    public @Nonnull Object getObject() {
        return object;
    }

}
