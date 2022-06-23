package com.alessandrocavalli.sendsignalnpe;

import java.util.concurrent.TimeUnit;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;

import com.oracle.truffle.regex.tregex.util.DebugUtil.Timer;


@SpringBootApplication
@EnableProcessApplication("mainProcess")
public class SendSignalApplication {
	
	private RuntimeService runtimeService;

	public SendSignalApplication(RuntimeService runtimeService) {
		this.runtimeService = runtimeService;
	}
	
  public static void main(String... args) {
	 
	  LogFactory.useSlf4jLogging();
    
	  SpringApplication.run(SendSignalApplication.class, args);
  }
  
  
  @EventListener
	private void processPostDeploy(PostDeployEvent event) throws InterruptedException {
	  
		runtimeService.startProcessInstanceByKey("signalStuff", "keySignalStuff");
		
		TimeUnit.SECONDS.sleep(2);
		      
		runtimeService.startProcessInstanceByKey("mainProcess");

	}
  
}
