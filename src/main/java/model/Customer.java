package model;

import lombok.Getter;
import lombok.Setter;

/**
 * This class represents a customer.
 * Holds a customer id which is auto incremented.
 * Holds an arrival time indicating when the customer arrived in the bank, and service time indicating how long this customer needs to interact with a teller.
 */
public class Customer {

    private static Integer count = 0;

    Customer(Double arrivalTime, Double serviceTime) {
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.customerId = ++count;
    }

    @Getter
    @Setter
    private Integer customerId;

    @Getter
    @Setter
    private Double serviceTime;

    @Getter
    @Setter
    private Double arrivalTime;
}
