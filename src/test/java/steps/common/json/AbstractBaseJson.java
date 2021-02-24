package steps.common.json;

import java.lang.reflect.Field;

public abstract class AbstractBaseJson {

    public String getFieldValue(String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = this.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        Object o = field.get(this);
        if (o != null)
            return field.get(this).toString();
        return null;
    }

}
