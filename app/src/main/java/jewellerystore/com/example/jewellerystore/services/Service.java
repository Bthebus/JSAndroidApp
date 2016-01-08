package jewellerystore.com.example.jewellerystore.services;

import java.util.List;

/**
 * Created by Braedy Thebus on 2015-11-16.
 */
public interface Service<S, ID> {

    public S findById(ID id);

    public String save(S entity);

    public String update(S entity);

    public void delete(S entity);

    public List<S> findAll();

}

