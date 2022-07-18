package ddd.core;

import java.util.ArrayList;

/**
 * Represents an aggregate-root of a domain aggregate (DDD). An aggregate-root is always an entity.
 * @param <TId> The type of the Id of the entity.
 */
public abstract class AggregateRoot<TId> extends Entity<TId> {


    private final ArrayList<DomainEvent> events;

    /**
     * The list of events that occurred while handling commands.
     */
    public ArrayList<DomainEvent> getEvents() {
        return events;
    }


    /**
     * Indication whether the aggregate is replaying events (true) or not (false).
     */
    private boolean isRelaying = false;

    protected boolean isRelaying() {
        return isRelaying;
    }

    /**
     * The current version after handling any commands.
     */
    private int version;

    public int getVersion() {
        return version;
    }

    /**
     * The original version of the aggregate after replaying all events in the event-store.
     */
    private int originalVersion;

    public int getOriginalVersion() {
        return originalVersion;
    }

    /**
     * Constructor for creating an empty aggregate.
     * @param id The unique id of the aggregate-root.
     */
    public AggregateRoot(TId id)  {
        super(id);
        originalVersion = 0;
        version = 0;
        events = new ArrayList<DomainEvent>();
    }

    /**
     * Constructor for creating an aggregate of which the state is intialized by
     * replaying the list of events specified.
     * @param id The unique Id of the aggregate.
     * @param events The events to replay.
     */
    public AggregateRoot(TId id, ArrayList<DomainEvent> events) {
        this(id);
        isRelaying = true;
        for (DomainEvent evt : this.events) {
            when(evt);
            originalVersion++;
            version++;
        }
        isRelaying = false;
    }

    /**
     * Let the aggregate handle an event and save it in the list of events
     * so it can be used outside the aggregate (persisted, published on a bus, ...).
     * Use GetEvents to retrieve the list of events.
     * @param domainEvent The event to handle.
     */
    protected void raiseEvent(DomainEvent domainEvent) {
        // let the derived aggregate handle the event
        when(domainEvent);

        // save the event so it can be published outside the aggregate
        events.add(domainEvent);
        version += 1;
    }

    /**
     * Clear the list of events that occurred while handling a command.
     */
    public void clearEvents() {
        events.clear();
    }

    /**
     * Handle a specific event. Derived classes should  implement this method
     * for every event type.
     *
     * Because the parameter type of the specified event is dynamic,
     * the appropriate overload of the When method is called.
     * @param domainEvent The event to handle.
     */
    protected abstract void when(DomainEvent domainEvent);
}
