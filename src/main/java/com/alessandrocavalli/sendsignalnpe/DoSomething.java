package com.alessandrocavalli.sendsignalnpe;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.Execution;

public class DoSomething implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		

	    ProcessEngine processEngine = execution.getProcessEngine();
	    
	    RuntimeService runtimeService = processEngine.getRuntimeService();
	    
	    Execution exec =  runtimeService.createExecutionQuery().activityId("IdActAlex")
	    		.active().singleResult();
	   
		execution.setVariable("FOOO", "FOOO");

		if(!exec.isEnded()) {
		//	TimeUnit.MINUTES.sleep(6);	

		//	TimeUnit.MINUTES.sleep(3);
			
		    try {
				runtimeService.createSignalEvent("effectCreatedSignal").executionId(exec.getId()).setVariables(execution.getVariables()).send();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
		    
		    
		}
		
		
	}

	
	
}
