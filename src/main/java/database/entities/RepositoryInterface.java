package database.entities;

import java.util.List;

public interface RepositoryInterface <T> {

    void add(T t);
    T getById(int id);
    T getByString( String address);
    List getByText(String text);
    List getAll();

}
