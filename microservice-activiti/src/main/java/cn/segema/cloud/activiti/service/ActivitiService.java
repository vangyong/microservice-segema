package cn.segema.cloud.activiti.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivitiService {

	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private HistoryService historyService;
	
	
	public Deployment deploy(String wokflowId){
		
		Deployment deployment = repositoryService.createDeployment()
			.addClasspathResource("diagrams/leaveBill.bpmn")
			.addClasspathResource("diagrams/leaveBill.png")
			.deploy();
			System.out.println("发布成功:"+deployment.getId()+" "+deployment.getName());
			return deployment;
	}
	

	// 发起流程实例
	public ProcessInstance startProcess(String wokflowId, Map<String, Object> variables){
		
		ProcessInstance processInstance =  runtimeService.startProcessInstanceByKey(wokflowId);
				//runtimeService.startProcessInstanceByKey(wokflowId, variables);
		return processInstance;
	}
	
	// 获得所有任务别表
	public List<Task> getTasks() {
		return taskService.createTaskQuery().list();
	}

	// 获得某个人的任务别表
	public List<Task> getTasks(String assignee) {
		return taskService.createTaskQuery().taskCandidateUser(assignee).list();
	}

	// 完成任务
	public void completeTasks(Boolean joinApproved, String taskId) {
		Map<String, Object> taskVariables = new HashMap<String, Object>();
		taskVariables.put("joinApproved", joinApproved);
		taskService.complete(taskId, taskVariables);
	}

}
