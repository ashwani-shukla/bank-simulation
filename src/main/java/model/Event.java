package model;

import lombok.Getter;
import lombok.Setter;

/**
 * This abstract class represents an event and holds the time when it is scheduled to happen
 * Implements Comparable and overrides compareTo() to order Events according to the event time
 */
public abstract class Event implements Comparable<Event> {

    Event(Double eventTime) {
        this.eventTime = eventTime;
    }

    @Getter
    @Setter
    private Double eventTime;

    /**
     * Compares the event time
     * @param event - Event
     * @return integer
     */
    public int compareTo(Event event) {
        return eventTime.compareTo(event.eventTime);
    }

    /**
     * Performs the event action
     * @param bank - Bank
     */
    public void action(Bank bank) {
    }

    /**
     * Logs the event
     */
    public void eventLog() {
    }

    /**
     *
     * @return Event type
     */
    public String eventType() {
        return null;
    }
}
