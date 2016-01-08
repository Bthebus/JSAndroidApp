package jewellerystore.com.example.jewellerystore.services.implementation;

import java.util.List;

import jewellerystore.com.example.jewellerystore.model.Item;
import jewellerystore.com.example.jewellerystore.repositories.RestAPI;
import jewellerystore.com.example.jewellerystore.repositories.rest.RestItemAPI;
import jewellerystore.com.example.jewellerystore.services.ItemService;

/**
 * Created by Braedy Thebus on 2015-11-16.
 */
public class ItemServiceImpl implements ItemService{

    final RestAPI<Item, Long> rest = new RestItemAPI();

    @Override
    public Item findById(Long id)
    {
        return rest.get(id);
    }

    @Override
    public String save(Item entity)
    {
        return rest.post(entity);
    }

    @Override
    public String update(Item entity)
    {
        return rest.put(entity);
    }

    @Override
    public void delete(Item entity)
    {
         rest.delete(entity);
    }

    @Override
    public List<Item> findAll()
    {
        return rest.getAll();
    }
}
