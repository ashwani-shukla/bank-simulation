package model;

import lombok.extern.slf4j.Slf4j;

/**
 * This class is a sub class of Event, it represents an event in which a customer finishes interaction with the Teller and departs from the bank
 */
@Slf4j
public class CustomerDepartureEvent extends Event {
    private Teller teller;

    public CustomerDepartureEvent(Double eventTime, Teller teller) {
        super(eventTime);
        this.teller = teller;
    }

    /**
     *
     * @param bank - Bank
     */
    @Override
    public void action(Bank bank) {
        teller.finishedServing();
        eventLog("\nCustomer #" + teller.getCustomer().getCustomerId() + " departed :" + getEventTime());
        bank.removeTeller(teller);
    }

    /**
     *
     * @param text - Text
     */
    public void eventLog(String text) {
        log.debug(text);
    }

    /**
     *
     * @return - Event type
     */
    @Override
    public String eventType() {
        return ("Departure Event");
    }
}
