package application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BankApplication {

    private static Logger LOGGER = LoggerFactory.getLogger(BankApplication.class);

    public static void run(String[] args) throws Exception {
        LOGGER.info("[Application] Starting Bank application");
        Teller.depositMoney(Integer.parseInt(args[2]));
        Thread.sleep(Long.valueOf(args[1]));
        Teller.depositMoney(Integer.parseInt(args[3]));
    }

}
