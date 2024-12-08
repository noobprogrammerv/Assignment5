package repository;

import domain.Identifiable;
import java.util.HashMap;

public class MemoryRepository <ID, T extends Identifiable<ID>> implements IRepository<ID, T> {
    protected HashMap<ID, T> elements = new HashMap<>();
    @Override
    public void addEntity(ID id, T element) { elements.put(id, element); }
    @Override
    public void deleteEntity(ID id){ elements.remove(id); }
    @Override
    public void modifyEntity(ID id, T t) { elements.replace(id, t); }
    @Override
    public T findEntityById(ID id) { return elements.get(id); }
    @Override
    public Iterable<T> getAllEntities(){ return elements.values(); }

}
