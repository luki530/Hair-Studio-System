package pl.edu.agh.persistence;

import java.util.List;

public interface PersistenceManager {
    public void create(Persistable persistable);

    public void update(Persistable persistable);

    public Persistable delete(Persistable persistable);

    public Persistable find(Persistable persistable, Object id);

    public List<Persistable> findAll(Persistable persistable);
}
