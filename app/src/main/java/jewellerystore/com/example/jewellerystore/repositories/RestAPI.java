package jewellerystore.com.example.jewellerystore.repositories;

import java.util.List;

/**
 * Created by Braedy Thebus on 2015-11-16.
 */
public interface RestAPI<S, ID> {
    S get(ID id);

    String post(S entity);

    String put(S entity);

    String delete(S entity);

    List<S> getAll();
}
