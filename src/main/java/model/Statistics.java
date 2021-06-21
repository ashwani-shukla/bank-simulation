package model;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Class for gathering simulation stats
 */
@Slf4j
public class Statistics {

    @Getter
    @Setter
    private Double averageTime = 0.0;

    @Getter
    @Setter
    private Double averageWaitTime = 0.0;

    @Getter
    @Setter
    private Double maxWaitTime = 0.0;

    @Getter
    @Setter
    private Double totalWaitTime = 0.0;

    @Getter
    @Setter
    private ArrayList<Double> waitTimeList = new ArrayList<>();

    @Getter
    @Setter
    private Double averageQueueLength = 0.0;

    @Getter
    @Setter
    private Integer maxQueueLength = 0;

    /**
     * Calculates the queue statistics
     */
    public void updateQueueStatistics(Double currentTime, Integer customerQueueLength) {
        averageQueueLength = (currentTime * averageQueueLength + customerQueueLength) / (currentTime + 1);
        if (customerQueueLength > maxQueueLength)
            maxQueueLength = customerQueueLength;
    }

    /**
     * Updates the wait time statistics
     * @param queuedCustomer - Customer
     */
    public void updateWaitTimeStatistics(Customer queuedCustomer, Double currentTime) {
        Double waitTime = currentTime - queuedCustomer.getArrivalTime();
        waitTimeList.add(waitTime);
        totalWaitTime = totalWaitTime + waitTime;
    }

    /**
     * Calculates statistics
     */
    public void calculateStatistics(Integer totalCustomersServed, Double totalTime) {
        if (totalCustomersServed > 0) {
            averageTime = totalTime / totalCustomersServed;
            averageWaitTime = totalWaitTime / totalCustomersServed;
            maxWaitTime = !waitTimeList.isEmpty() ? Collections.max(waitTimeList) : 0.0;
        }
    }

    /**
     * Displays statistics
     */
    public void displayStatistics(Integer totalCustomersArrived, Integer totalCustomersServed) {
        log.debug("\n");
        log.debug(String.format("Total customers arrived: %d", totalCustomersArrived));
        log.debug(String.format("Total customers served: %d", totalCustomersServed));
        log.debug(String.format("Average service time: %s", averageTime));
    }
}
