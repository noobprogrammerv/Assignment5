package filter;

public interface AbstractFilter<T> {
    boolean acceptEntity(T t);
}
