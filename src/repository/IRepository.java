package repository;
import domain.Identifiable;

public interface IRepository <ID, T extends Identifiable<ID>> {
    ///THE METHODS OF THE INTERFACE ARE PUBLIC BY DEFAULT///
    void addEntity(ID id, T entity);
    void deleteEntity(ID id);
    void modifyEntity(ID id, T entity);
    T findEntityById(ID id);
    Iterable<T> getAllEntities();
}
