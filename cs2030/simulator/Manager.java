package cs2030.simulator;

/**
 * The Manager class takes in the values needed to generate the random values.
 */
public class Manager {
    int seed;
    double lamda;
    double mu;
    double restingRate;
    static int maxQ = 1;
    static RandomGenerator rgen;
    double probRR;
    static int numOfServers = 1;

    /**
     * Constructor that creates a Manager.
     * 
     * @param seed         - base seed for random generator
     * @param lamda        - arrival rate for random generator
     * @param mu           - service rate for random generator
     * @param restingRate  - resting rate for random generator
     * @param probRR       - probability of random rest
     * @param maxQ         - maximum queue per server
     * @param numOfServers - number of human servers
     * 
     */

    public Manager(int seed, double lamda, double mu, double restingRate, 
                    double probRR, int maxQ, int numOfServers) {
        rgen = new RandomGenerator(seed, lamda, mu, restingRate);
        this.probRR = probRR;
        this.maxQ = maxQ;
        this.numOfServers = numOfServers;
    }

    public double getArrivalTime() {
        double timeInterval = rgen.genInterArrivalTime();
        return timeInterval;
    }

    public double getServiceTime() {
        double timeInterval = rgen.genServiceTime();
        return timeInterval;
    }

    public double getRR() {
        return probRR;
    }

    public double getRandomRest() {
        double rest = rgen.genRandomRest();
        return rest;
    }

    public double getRestPeriod() {
        double restTime = rgen.genRestPeriod();
        return restTime;
    }

    public double getGreedyCust() {
        double prob = rgen.genCustomerType();
        return prob;
    }

}