package cn.segema.cloud.filecenter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import cn.segema.cloud.filecenter.domain.Filecenter;
import cn.segema.cloud.filecenter.repository.FilecenterRepository;
import cn.segema.cloud.filecenter.vo.FilecenterVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 合同接口
 */
@RestController
public class FilecenterController {
	@Autowired
	private DiscoveryClient discoveryClient;
	@Autowired
	private FilecenterRepository filecenterRepository;
	@Autowired
	private RestTemplate restTemplate;
	
//	@Autowired
//	private UserRepository userRepository;
	
	
	
	
	
	

	/**
	 * @param id
	 * @return 合同信息
	 */
	@GetMapping("/{id}")
	public Filecenter findById(@PathVariable String id) {
		Filecenter findOne = this.filecenterRepository.findOne(id);
		//User user = userRepository.findOne("1");
		
		return findOne;
	}
	
	
	@PostMapping
    @ApiOperation(value = "上传文件")
    public String fileUpload(@ApiParam(value = "文件", required = true) @RequestParam("file") MultipartFile multipartFile,
            @ApiParam(value = "usage(目录)", required = false) @RequestParam(value = "usage", required = false) String usage,
            @ApiParam(value = "同步(可选,默认false)") @RequestParam(value = "sync", required = false, defaultValue = "false") boolean sync) {
        if (multipartFile == null) {
            throw new IllegalArgumentException("参数异常");
        }
       // String url = map.get(key).doUpload(multipartFile, usage, sync);
        return UploadResult.builder().url(url).build();
    }
	

	/**
	 * 本地服务实例的信息
	 * 
	 * @return
	 */
	@GetMapping("/instance-info")
	public ServiceInstance showInfo() {
		ServiceInstance localServiceInstance = this.discoveryClient.getLocalServiceInstance();
		return localServiceInstance;
	}

	/**
	 * 通过参数生成Pageable对象
	 * @param page
	 * @param size
	 * @return
	 */
	@GetMapping("/listByPageable")
	public Page<Filecenter> listByPageable(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "5") Integer size) {
		Sort sort = new Sort(Direction.DESC, "contractId");
		Pageable pageable = new PageRequest(page, size, sort);
		return filecenterRepository.findAll(pageable);
	}
	
	/**
	 * 直接获取Pageable对象
	 * @param pageable
	 * @return
	 */
	@GetMapping("/listByPageableDefault")
	public Page<Filecenter> listByPageableDefault(@PageableDefault(value = 15, sort = { "contractId" }, direction = Sort.Direction.DESC) Pageable pageable) {
		return filecenterRepository.findAll(pageable);
	}
	
	/**
	 * @param id
	 * @return 合同信息
	 */
	@GetMapping("/contractdetail/{id}/{userId}")
	public Filecenter findContractDetailById(@PathVariable String id,@PathVariable String userId) {
		FilecenterVO userVO = (FilecenterVO)restTemplate.getForObject("http://microservice-system/user/{userId}", FilecenterVO.class, userId);
		Filecenter findOne = this.filecenterRepository.findOne(id);
		return findOne;
	}
	

}
