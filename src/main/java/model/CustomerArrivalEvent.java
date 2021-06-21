package model;

import lombok.extern.slf4j.Slf4j;
import utils.Utils;

/**
 * This class is a sub class of Event, it represents an event in which a customer arrives at the bank and joins the customer queue
 */
@Slf4j
public class CustomerArrivalEvent extends Event {

    public CustomerArrivalEvent(Double eventTime) {
        super(eventTime);
    }

    /**
     * Performs the customer arrival event action
     * @param bank - Bank
     */
    @Override
    public void action(Bank bank) {
        var customer = new Customer(bank.getCurrentTime(), bank.getCurrentTime() + Utils.getServiceTime(bank.getP(), bank.getA(), bank.getB()));
        bank.assignCustomer(customer);
        eventLog("\nCustomer #" + customer.getCustomerId() + " arrived with arrival time: " + customer.getArrivalTime() + " service time: " + customer.getServiceTime());
    }

    /**
     * Logs the event
     * @param text - Text
     */
    public void eventLog(String text) {
        log.debug(text);
    }

    /**
     *
     * @return Event type
     */
    @Override
    public String eventType() {
        return ("Arrival Event");
    }
}
