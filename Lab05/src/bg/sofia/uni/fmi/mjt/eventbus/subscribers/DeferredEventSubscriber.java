package bg.sofia.uni.fmi.mjt.eventbus.subscribers;

import java.util.Iterator;

import bg.sofia.uni.fmi.mjt.eventbus.events.Event;

public class DeferredEventSubscriber<T extends Event<?>> implements Subscriber<T>, Iterable<T> {

    /**
     * Store an event for processing at a later time.
     *
     * @param event the event to be processed
     * @throws IllegalArgumentException if the event is null
     */
    @Override
    public void onEvent(T event) {
        throw new UnsupportedOperationException("Still not implemented");
    }

    /**
     * Get an iterator for the unprocessed events. The iterator should provide the events sorted
     * by priority, with higher-priority events first (lower priority number = higher priority).
     * For events with equal priority, earlier events (by timestamp) come first.
     *
     * @return an iterator for the unprocessed events
     */
    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException("Still not implemented");
    }

    /**
     * Check if there are unprocessed events.
     *
     * @return true if there are no unprocessed events, false otherwise
     */
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Still not implemented");
    }

}