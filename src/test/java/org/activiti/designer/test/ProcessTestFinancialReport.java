package org.activiti.designer.test;

import static org.junit.Assert.assertNotNull;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.junit.Rule;
import org.junit.Test;

public class ProcessTestFinancialReport {

	private static final String filename = "/diagrams/bestdog.bpmn";

	@Rule
	public ActivitiRule activitiRule = new ActivitiRule();

	@Test
	public void startProcess() throws Exception {
		RepositoryService repositoryService = activitiRule
				.getRepositoryService();
		repositoryService
				.createDeployment()
				.addInputStream("financialReport.bpmn20.xml",
						getClass().getResourceAsStream(filename)).deploy();
		RuntimeService runtimeService = activitiRule.getRuntimeService();
		Map<String, Object> variableMap = new HashMap<String, Object>();
		variableMap.put("name", "Activiti");
		ProcessInstance processInstance = runtimeService
				.startProcessInstanceByKey("financialReport", variableMap);
		assertNotNull(processInstance.getId());
		System.out.println("id " + processInstance.getId() + " "
				+ processInstance.getProcessDefinitionId());

		ProcessEngine processEngine = activitiRule.getProcessEngine();
		TaskService taskService = processEngine.getTaskService();
		List<Task> tasks = taskService.createTaskQuery()
				.taskCandidateGroup("accountancy").list();

		for (Task task : tasks) {
			System.out.println(task);
		}
	}

	public static void main(String[] args) throws Exception {

		// Create Activiti process engine
		ProcessEngineConfiguration createStandaloneProcessEngineConfiguration = ProcessEngineConfiguration
				.createStandaloneProcessEngineConfiguration();
		createStandaloneProcessEngineConfiguration.setJdbcUrl("jdbc:h2:tcp://localhost/~/activiti");
		System.err.println(createStandaloneProcessEngineConfiguration);
		System.err.println(createStandaloneProcessEngineConfiguration.getJdbcUrl());
		System.err.println(createStandaloneProcessEngineConfiguration.getDatabaseSchema());
		ProcessEngine processEngine = 	createStandaloneProcessEngineConfiguration.buildProcessEngine();

		// Get Activiti services
		RepositoryService repositoryService = processEngine
				.getRepositoryService();
		RuntimeService runtimeService = processEngine.getRuntimeService();

		// Deploy the process definition
		repositoryService
		.createDeployment()
		.addInputStream("financialReport.bpmn20.xml",
				new FileInputStream(filename)).deploy();
		// TODO
//		repositoryService.createDeployment()
//				.addClasspathResource("FinancialReportProcess.bpmn20.xml")
//				.deploy();

		// Start a process instance
		runtimeService.startProcessInstanceByKey("financialReport");
	}
}