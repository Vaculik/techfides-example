package cz.muni.fi.facade;

import java.util.Collection;
import java.util.List;

/**
 * Created by Vaculik on 02/02/2016.
 */
public interface BeanMapper {

    <T> List<T> map(Collection<?> objectsToMap, Class<T> clazz);

    <T> T map(Object objectToMap, Class<T> clazz);
}
