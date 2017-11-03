package cn.segema.cloud.flowable.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.flowable.engine.DynamicBpmnService;
import org.flowable.engine.FormService;
import org.flowable.engine.HistoryService;
import org.flowable.engine.IdentityService;
import org.flowable.engine.ManagementService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngines;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlowableWebService {
	
	private ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private ManagementService managementService;
	@Autowired
	private IdentityService identityService;
	@Autowired
	private FormService formService;
	//@Autowired
	//private DynamicBpmnService dynamicBpmnService;
	
	
	/**
	 * 部署流程
	 * @param wokflowId
	 * @return
	 */
	public Deployment deploy(String wokflowId){
			Deployment deployment = repositoryService.createDeployment()
					  .addClasspathResource("processes/holiday-request.bpmn20.xml")
					  .deploy();
			System.out.println("发布成功:"+deployment.getId()+" "+deployment.getName());
			return deployment;
			
	}
	
	
	/**
	 * 启动流程
	 * @param personId
	 * @param compId
	 * @return
	 */
	// 发起流程实例
	public ProcessInstance startProcess(String wokflowId, Map<String, Object> variables){
		ProcessInstance processInstance =  runtimeService.startProcessInstanceByKey(wokflowId);
				//runtimeService.startProcessInstanceByKey(wokflowId, variables);
		return processInstance;
	}
	
	
	public ProcessInstance startProcess( Long personId, Long compId){
	 Map<String, Object> variables = new HashMap<String, Object>();
	 variables.put("personId", personId);
	 variables.put("compId", compId);
	 return runtimeService.startProcessInstanceByKey("joinProcess", variables);
	}

	// 获得某个人的任务别表
//	public List<Task> getTasks(String assignee) {
//		return taskService.createTaskQuery().taskCandidateUser(assignee).list();
//	}

	// 完成任务
	public void completeTasks(Boolean joinApproved, String taskId) {
		Map<String, Object> taskVariables = new HashMap<String, Object>();
		taskVariables.put("joinApproved", joinApproved);
		taskService.complete(taskId, taskVariables);
	}

}
