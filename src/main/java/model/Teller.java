package model;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * This class represents a teller.
 * Holds a teller id and a customer
 */
@Slf4j
public class Teller {

    public Teller(Integer tellerId, Customer customer) {
        this.tellerId = tellerId;
        this.customer = customer;
    }

    @Getter
    @Setter
    private Integer tellerId;

    @Getter
    @Setter
    private Customer customer;

    /**
     * Method to print when the teller finishes serving a customer
     */
    public void finishedServing() {
        log.debug("\nTeller #" + getTellerId() + " has finished serving the the Customer #" + customer.getCustomerId());
    }

}
