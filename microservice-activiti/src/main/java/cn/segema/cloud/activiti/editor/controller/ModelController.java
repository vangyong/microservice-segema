package cn.segema.cloud.activiti.editor.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 懒得写，直接参照了：http://www.jianshu.com/p/cf766a713a86
 * 自己增加的controller，用于对model进行操作，返回值随便弄的，需要修改 Created by chenhai on 2017/6/6.
 */
@Controller
@RequestMapping("/model")
public class ModelController {

	private Logger logger = LoggerFactory.getLogger(ModelController.class);

	@Autowired
	ProcessEngine processEngine;
	@Autowired
	ObjectMapper objectMapper;

	/**
	 * 新建一个空模型
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("newModel")
	public String newModel() throws UnsupportedEncodingException {
		RepositoryService repositoryService = processEngine.getRepositoryService();
		// 初始化一个空模型
		Model model = repositoryService.newModel();

		// 设置一些默认信息
		String name = "new-process";
		String description = "";
		int revision = 1;
		String key = "process";

		ObjectNode modelNode = objectMapper.createObjectNode();
		modelNode.put(ModelDataJsonConstants.MODEL_NAME, name);
		modelNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
		modelNode.put(ModelDataJsonConstants.MODEL_REVISION, revision);

		model.setName(name);
		model.setKey(key);
		model.setMetaInfo(modelNode.toString());

		repositoryService.saveModel(model);
		String id = model.getId();

		// 完善ModelEditorSource
		ObjectNode editorNode = objectMapper.createObjectNode();
		editorNode.put("id", "canvas");
		editorNode.put("resourceId", "canvas");
		ObjectNode stencilSetNode = objectMapper.createObjectNode();
		stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
		editorNode.put("stencilset", stencilSetNode);
		repositoryService.addModelEditorSource(id, editorNode.toString().getBytes("utf-8"));
		return "success";
	}

	@RequestMapping(value = "/create/{name}/{key}/{description}")
	public void create(@PathVariable("name") String name, @PathVariable("key") String key,
			@PathVariable("description") String description, HttpServletRequest request, HttpServletResponse response) {
		RepositoryService repositoryService = processEngine.getRepositoryService();

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			ObjectNode editorNode = objectMapper.createObjectNode();
			editorNode.put("id", "canvas");
			editorNode.put("resourceId", "canvas");
			ObjectNode stencilSetNode = objectMapper.createObjectNode();
			stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
			editorNode.put("stencilset", stencilSetNode);
			Model modelData = repositoryService.newModel();

			ObjectNode modelObjectNode = objectMapper.createObjectNode();
			modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, name);
			modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
			description = StringUtils.defaultString(description);
			modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
			modelData.setMetaInfo(modelObjectNode.toString());
			modelData.setName(name);
			modelData.setKey(StringUtils.defaultString(key));

			repositoryService.saveModel(modelData);
			repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));

			response.sendRedirect(request.getContextPath() + "/modeler.html?modelId=" + modelData.getId());
		} catch (Exception e) {
			logger.error("创建模型失败：", e);
		}
	}

	/**
	 * 获取所有模型
	 * 
	 * @return
	 */
	@RequestMapping("/modelList")
	@ResponseBody
	public List<Model> modelList() {
		RepositoryService repositoryService = processEngine.getRepositoryService();
		List<Model> models = repositoryService.createModelQuery().list();
		return models;
	}

	/**
	 * 删除模型
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("{id}")
	public String deleteModel(@PathVariable("id") String id) {
		RepositoryService repositoryService = processEngine.getRepositoryService();
		repositoryService.deleteModel(id);
		return "success";
	}

	/**
	 * 发布模型为流程定义
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@PostMapping("{id}/deployment")
	public String deploy(@PathVariable("id") String id) throws Exception {

		// 获取模型
		RepositoryService repositoryService = processEngine.getRepositoryService();
		Model modelData = repositoryService.getModel(id);
		byte[] bytes = repositoryService.getModelEditorSource(modelData.getId());

		if (bytes == null) {
			return ("模型数据为空，请先设计流程并成功保存，再进行发布。");
		}

		JsonNode modelNode = new ObjectMapper().readTree(bytes);

		BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
		if (model.getProcesses().size() == 0) {
			return ("数据模型不符要求，请至少设计一条主线流程。");
		}
		byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(model);

		// 发布流程
		String processName = modelData.getName() + ".bpmn20.xml";
		Deployment deployment = repositoryService.createDeployment().name(modelData.getName())
				.addString(processName, new String(bpmnBytes, "UTF-8")).deploy();
		modelData.setDeploymentId(deployment.getId());
		repositoryService.saveModel(modelData);

		return "success";
	}
}
