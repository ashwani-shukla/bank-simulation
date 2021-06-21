package model;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import utils.Utils;

import java.util.*;

/**
 * This class represents a bank.
 * Holds a customer queue, a teller queue, an event queue and other items for statistics
 */
@Slf4j
public class Bank {

    private Queue<Customer> customerQueue;
    private Queue<Teller> tellerQueue;
    private PriorityQueue<Event> eventQueue;
    private Integer totalCustomersArrived = 0;
    private Integer totalCustomersServed = 0;
    private Double totalTime = 0.0;
    private final Integer noOfTellers;
    private final Statistics statistics;


    public Bank(Integer noOfTellers, Double averageArrivalTime, Integer p, Integer a, Integer b, Statistics statistics) {
        this.noOfTellers = noOfTellers;
        this.averageArrivalTime = averageArrivalTime;
        this.p = p;
        this.a = a;
        this.b = b;
        this.customerQueue = new ArrayDeque<>();
        this.tellerQueue = new ArrayDeque<>();
        this.eventQueue = new PriorityQueue<>();
        this.statistics = statistics;
    }

    @Getter
    @Setter
    private Double averageArrivalTime;

    @Getter
    @Setter
    private Integer p;

    @Getter
    @Setter
    private Integer a;

    @Getter
    @Setter
    private Integer b;

    @Getter
    @Setter
    private Double currentTime;

    /**
     * Add an event to the event queue
     * @param evt - Event
     */
    public void addEventToQueue(Event evt) {
        eventQueue.add(evt);
    }

    /**
     * Assigns a customer to a teller and adds the teller to the teller queue and schedules an customer arrival event
     * @param c - Customer
     */
    public void assignCustomer(Customer c) {
        totalCustomersArrived++;
        var tellerBusy = false;
        for (var i = 0; !tellerBusy && i < noOfTellers; i++) {
            var teller = new Teller(i + 1, c);
            if (tellerQueue.stream().noneMatch(x -> x.getTellerId().equals(teller.getTellerId())) && getTellersInQueue() == 0) {
                addTeller(teller);
                tellerBusy = true;
            }
        }

        //if the teller is busy, add customer to the waiting queue
        if (!tellerBusy) {
            addCustomerToQueue(c);
        }
        addEventToQueue(new CustomerArrivalEvent(getCurrentTime() + Utils.getRandomNumber(getAverageArrivalTime())));
    }

    /**
     * Adds a teller to the teller queue and schedules a customer departure event
     * @param teller - Teller
     */
    private void addTeller(Teller teller) {
        addTellerToQueue(teller);
        addEventToQueue(new CustomerDepartureEvent(teller.getCustomer().getServiceTime(), teller));
    }

    /**
     * Frees the customer from a teller and removes a teller from the tellers queue
     * @param teller - Teller
     */
    public void removeTeller(Teller teller) {
        var customer = teller.getCustomer();
        removeTellerFromQueue(teller);

        totalCustomersServed++;
        totalTime = totalTime + customer.getServiceTime();

        if (getCustomersInQueue() > 0) {
            var queuedCustomer = removeCustomerFromQueue();

            statistics.updateWaitTimeStatistics(queuedCustomer, getCurrentTime());
            statistics.updateQueueStatistics(getCurrentTime(), getCustomersInQueue());

            teller.setCustomer(queuedCustomer);
            addTeller(teller);
        }
    }

    /**
     * Adds a customer to a customer queue
     * @param customer - Customer
     */
    public void addCustomerToQueue(Customer customer) {
        customerQueue.add(customer);
    }

    /**
     * Removes a customer from a customer queue
     * @return Customer
     */
    public Customer removeCustomerFromQueue() {
        return customerQueue.remove();
    }

    /**
     * Adds a teller to the teller queue
     * @param teller - Teller
     */
    public void addTellerToQueue(Teller teller) {
        tellerQueue.add(teller);
    }

    /**
     * Removes a teller from the teller queue
     * @param teller - Teller
     */
    public void removeTellerFromQueue(Teller teller) {
        tellerQueue.remove(teller);
    }

    /**
     *
     * @return Customer queue size
     */
    public Integer getCustomersInQueue() {
        return customerQueue.size();
    }

    /**
     *
     * @return Teller queue size
     */
    public Integer getTellersInQueue() {
        return tellerQueue.size();
    }

    /**
     * Performs simulation based on start and end time and displays statistics
     * @param startTime -  Simulation start time
     * @param endTime - Simulation end time
     */
    public void simulate(Double startTime, Double endTime) {
        currentTime = startTime;
        addEventToQueue(new CustomerArrivalEvent(startTime));
        while (!eventQueue.isEmpty() && currentTime <= endTime) {
            var event = eventQueue.remove();
            currentTime = event.getEventTime();
            event.action(this);
        }
        statistics.calculateStatistics(totalCustomersServed, totalTime);
        statistics.displayStatistics(totalCustomersArrived, totalCustomersServed);
    }
}