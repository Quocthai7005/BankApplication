package application;

/**
 * Created by adi on 6/14/18.
 */
public class Launcher {
    public static void main(String[] args) throws Exception {
        if(args[0].equals("StartBankApplication")) {
            // For demonstration case of Static instrumentation.
            // Agent is called outside, to transform BankApplication.class before it is loaded by JVM.
            new BankApplication().run(args);
        } else if(args[0].equals("LoadAgent")) {
            // For demonstration case of Dynamic instrumentation.
            // Agent will be called after the BankApplication.class has been loaded
            new AgentLoader().run(args);
        }
    }
}
