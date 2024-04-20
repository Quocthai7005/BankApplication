package application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents a bank teller who is in charge of processing deposited money.
 */
public class Teller {
    private static Logger LOGGER = LoggerFactory.getLogger(Teller.class);

    private static int balance = 0;

    public static void depositMoney(int amount) throws InterruptedException {
        Thread.sleep(1000l);
        LOGGER.info("[Application] Successful Deposit of [{}] Dollars!", amount);
        balance += amount;
        LOGGER.info("[Application] Balance amount is [{}] Dollars!", balance);
    }
}
