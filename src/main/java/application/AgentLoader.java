package application;

import java.io.File;
import java.util.Optional;

import com.sun.tools.attach.VirtualMachine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Responsible for load an instrumentation agent to modify a class while there is processed associated with it.
 */
public class AgentLoader {
    private static Logger LOGGER = LoggerFactory.getLogger(AgentLoader.class);

    public static void run(String[] args) {
        String agentFilePath = "/path/to/agent_file.jar";
        String applicationName = "BankApplication";

        //iterate all jvms and get the first one that matches our application name
        Optional<String> jvmProcessOpt = Optional.ofNullable(VirtualMachine.list()
                .stream()
                .filter(jvm -> {
                    LOGGER.info("jvm:{}", jvm.displayName());
                    return jvm.displayName().contains(applicationName);
                })
                .findFirst().get().id());

        if(!jvmProcessOpt.isPresent()) {
            LOGGER.error("Target Application not found");
            return;
        }
        File agentFile = new File(agentFilePath);
        try {
            String jvmPid = jvmProcessOpt.get();
            LOGGER.info("Attaching to target JVM with PID: " + jvmPid);
            VirtualMachine jvm = VirtualMachine.attach(jvmPid);
            jvm.loadAgent(agentFile.getAbsolutePath());
            jvm.detach();
            LOGGER.info("Attached to target JVM and loaded Java agent successfully");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
