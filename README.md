# SendSignalThrowsNPE

[SUPPORT-13932]

## To reproduce:

As soon as the application starts, two process instrances will start automatically.

Put a breakpoint in the class org.camunda.bpm.engine.impl.cmd.sendSignalToExecution line checkAuthorizationOfCatchSignal

``` 
protected void sendSignalToExecution(CommandContext commandContext, String signalName, String executionId) {
    ExecutionManager executionManager = commandContext.getExecutionManager();
    ExecutionEntity execution = executionManager.findExecutionById(executionId);
    ensureNotNull("Cannot find execution with id '" + executionId + "'", "execution", execution);
    EventSubscriptionManager eventSubscriptionManager = commandContext.getEventSubscriptionManager();
    List<EventSubscriptionEntity> signalEvents = eventSubscriptionManager.findSignalEventSubscriptionsByNameAndExecution(signalName, executionId);
    ensureNotEmpty("Execution '" + executionId + "' has not subscribed to a signal event with name '" + signalName + "'.", signalEvents);

    checkAuthorizationOfCatchSignals(commandContext, signalEvents);
    notifyExecutions(signalEvents);
  }
```

Claim and execute task "signalStuff". The application will stop in debug on the line above...

Claim and execute task mainProcess. It will end properly.

Continue debug. You will get the error.


[SUPPORT-13932]: <https://jira.camunda.com/browse/SUPPORT-13932>

