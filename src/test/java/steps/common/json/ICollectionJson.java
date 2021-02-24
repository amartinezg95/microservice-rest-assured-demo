package steps.common.json;

import java.util.List;
@FunctionalInterface
public interface ICollectionJson<T> {

    List<T> getList();
}
