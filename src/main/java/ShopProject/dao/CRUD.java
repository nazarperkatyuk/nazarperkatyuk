package ShopProject.dao;

import java.util.List;

public interface CRUD<T> {
    T create(T t);
    T read(int id);
    void update(T t);
    void delete(int id);
    List<T> readAll();
}