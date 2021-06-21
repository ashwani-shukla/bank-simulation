package simulator;

import lombok.extern.slf4j.Slf4j;
import model.Bank;
import model.Statistics;

/**
 * Class to hold a main method that starts the simulation.
 */
@Slf4j
public class Simulator {

    private static final Double START_TIME = 9 * 60.0;
    private static final Double END_TIME = 17 * 60.0;
    private static final Integer NO_OF_TELLERS = 1;
    private static final Double AVERAGE_ARRIVAL_TIME = 100.0;
    private static final Integer P_SERVICE_TIME = 200;
    private static final Integer A_SERVICE_TIME_YELLOW_CUSTOMERS = 2;
    private static final Integer B_SERVICE_TIME_YELLOW_CUSTOMERS = 5;
    private static final Integer A_SERVICE_TIME_RED_CUSTOMERS = 2;
    private static final Integer B_SERVICE_TIME_RED_CUSTOMERS = 2;
    private static final Integer A_SERVICE_TIME_BLUE_CUSTOMERS = 5;
    private static final Integer B_SERVICE_TIME_BLUE_CUSTOMERS = 1;
    private static final String YELLOW_CUSTOMER = "Yellow Customer";
    private static final String RED_CUSTOMER = "Red Customer";
    private static final String BLUE_CUSTOMER = "Blue Customer";
    private static final String AVERAGE_WAIT_TIME = "Average WaitTime";
    private static final String MAX_WAIT_TIME = "Max Wait Time";
    private static final String AVERAGE_QUEUE_LENGTH = "Average Queue Length";
    private static final String MAX_QUEUE_LENGTH = "Max Queue Length";
    public static final String FORMAT = "%s: %s";
    private Double averageWaitTimeYellowCustomers;
    private Double maxWaitTimeYellowCustomers;
    private Double averageQueueLengthYellowCustomers;
    private Integer maxQueueLengthYellowCustomers;

    /**
     * Runs simulation for yellow type customers
     */
    public void runSimulationForYellowCustomers() {
        log.info("----------------------------------------------------");
        log.info("Running simulation for Yellow Customers");
        var statistics = new Statistics();
        var bank = new Bank(NO_OF_TELLERS, AVERAGE_ARRIVAL_TIME, P_SERVICE_TIME, A_SERVICE_TIME_YELLOW_CUSTOMERS, B_SERVICE_TIME_YELLOW_CUSTOMERS, statistics);
        bank.simulate(START_TIME, END_TIME);
        setAndDisplayYellowCustomerStatistics(statistics);
    }

    /**
     * Sets and displays statistics for yellow customers
     * @param statistics - Statistics
     */
    private void setAndDisplayYellowCustomerStatistics(Statistics statistics) {
        averageWaitTimeYellowCustomers = statistics.getAverageWaitTime();
        maxWaitTimeYellowCustomers = statistics.getMaxWaitTime();
        averageQueueLengthYellowCustomers = statistics.getAverageQueueLength();
        maxQueueLengthYellowCustomers = statistics.getMaxQueueLength();
        displayStatistics(YELLOW_CUSTOMER, averageWaitTimeYellowCustomers, maxWaitTimeYellowCustomers, averageQueueLengthYellowCustomers, maxQueueLengthYellowCustomers);
    }

    /**
     * Runs simulation for red type customers
     */
    public void runSimulationForRedCustomers() {
        log.info("----------------------------------------------------");
        log.info("Running simulation for Red Customers");
        var statistics = new Statistics();
        var bank = new Bank(NO_OF_TELLERS, AVERAGE_ARRIVAL_TIME, P_SERVICE_TIME, A_SERVICE_TIME_RED_CUSTOMERS, B_SERVICE_TIME_RED_CUSTOMERS, statistics);
        bank.simulate(START_TIME, END_TIME);
        setAndDisplayRedCustomerStatistics(statistics);
    }

    /**
     * Sets and displays statistics for red customers
     * @param statistics - Statistics
     */
    private void setAndDisplayRedCustomerStatistics(Statistics statistics) {
        averageWaitTimeYellowCustomers = statistics.getAverageWaitTime();
        maxWaitTimeYellowCustomers = statistics.getMaxWaitTime();
        averageQueueLengthYellowCustomers = statistics.getAverageQueueLength();
        maxQueueLengthYellowCustomers = statistics.getMaxQueueLength();
        displayStatistics(RED_CUSTOMER, averageWaitTimeYellowCustomers, maxWaitTimeYellowCustomers, averageQueueLengthYellowCustomers, maxQueueLengthYellowCustomers);
    }

    /**
     * Runs simulation for blue type customers
     */
    public void runSimulationForBlueCustomers() {
        log.info("----------------------------------------------------");
        log.info("Running simulation for Blue Customers");
        var statistics = new Statistics();
        var bank = new Bank(NO_OF_TELLERS, AVERAGE_ARRIVAL_TIME, P_SERVICE_TIME, A_SERVICE_TIME_BLUE_CUSTOMERS, B_SERVICE_TIME_BLUE_CUSTOMERS, statistics);
        bank.simulate(START_TIME, END_TIME);
        setAndDisplayBlueCustomerStatistics(statistics);
    }

    /**
     * Sets and displays statistics for blue customers
     * @param statistics - Statistics
     */
    private void setAndDisplayBlueCustomerStatistics(Statistics statistics) {
        averageWaitTimeYellowCustomers = statistics.getAverageWaitTime();
        maxWaitTimeYellowCustomers = statistics.getMaxWaitTime();
        averageQueueLengthYellowCustomers = statistics.getAverageQueueLength();
        maxQueueLengthYellowCustomers = statistics.getMaxQueueLength();
        displayStatistics(BLUE_CUSTOMER, averageWaitTimeYellowCustomers, maxWaitTimeYellowCustomers, averageQueueLengthYellowCustomers, maxQueueLengthYellowCustomers);
    }

    /**
     * Displays statistics
     */
    public void displayStatistics(String customerType, Double averageWaitTime, Double maxWaitTime, Double averageQueueLength, Integer maxQueueLength){
        log.info("----------------------------------------------------");
        log.info(String.format("Statistics for: %s", customerType));
        log.info(String.format(FORMAT, AVERAGE_WAIT_TIME, averageWaitTime));
        log.info(String.format(FORMAT, MAX_WAIT_TIME, maxWaitTime));
        log.info(String.format(FORMAT, AVERAGE_QUEUE_LENGTH,Math.round(averageQueueLength)));
        log.info(String.format(FORMAT, MAX_QUEUE_LENGTH, maxQueueLength));
    }

    public static void main(String[] args) {
        var runBankSimulator = new Simulator();
        runBankSimulator.runSimulationForYellowCustomers();
        runBankSimulator.runSimulationForRedCustomers();
        runBankSimulator.runSimulationForBlueCustomers();
    }
}
