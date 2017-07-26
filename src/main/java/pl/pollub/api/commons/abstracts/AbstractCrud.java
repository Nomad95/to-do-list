package pl.pollub.api.commons.abstracts;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class AbstractCrud<T extends AbstractIdentityObject> extends AbstractIdentityObject {

    protected List<T> elements = new ArrayList<>();

    protected AtomicInteger counter = new AtomicInteger();

    /**
     * Saves new item or updates existing if id matches item in list
     */
    public T save(T object){
        T foundItem = findOne(object.getId());
        if(foundItem != null)
            remove(foundItem);
        elements.add(object);
        return object;
    }

    public boolean remove(T object){
        return elements.remove(object);
    }

    /**
     * Returns null if id isnt present in list
     */
    public T findOne(Integer id){
        Optional<T> element = elements.stream().peek(el -> {
        }).filter(el -> el.getId().equals(id)).findFirst();
        return element.orElse(null);
    }

    public boolean removeById(Integer id) {
        return remove(findOne(id));
    }

    public List<T> findAll(){
        return elements;
    }

    public Integer generateId(){
        return counter.incrementAndGet();
    }

}
