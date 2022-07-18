package ddd.core;

/**
 * Represents a ValueObject in the domain (DDD).
 */
public abstract class ValueObject {
    public abstract boolean equals(Object obj);
    public abstract int hashCode();
}
