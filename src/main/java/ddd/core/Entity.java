package ddd.core;

/**
 * Represents an Entity in the domain (DDD).
 * @param <TId> The type of the Id of the entity.<
 */
public abstract class Entity<TId> {

    private final TId id;

    public Entity(TId id) {
        this.id = id;
    }

    public TId getId() {
        return id;
    }
}