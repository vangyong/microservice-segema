package cn.segema.cloud.contract.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import cn.segema.cloud.contract.vo.UserVO;
import cn.segema.cloud.contract.service.SystemFeignHystrixClient.SystemHystrixClientFallback;

@FeignClient(name="microservice-system",fallback=SystemHystrixClientFallback.class)
public interface SystemFeignHystrixClient {
	
	@RequestMapping("/user/{userId}")
	public UserVO findByIdFeign(@RequestParam("userId") String userId);

	/**
	 * SpringCloud官方做法， 将fallback类作为内部类放入Feign的接口中，
	 * 当然也可以单独写一个fallback类。
	 */
	@Component
	static class SystemHystrixClientFallback implements SystemFeignHystrixClient {
		private static final Logger LOGGER = LoggerFactory.getLogger(SystemHystrixClientFallback.class);

		@Override
		public UserVO findByIdFeign(String userId) {
			LOGGER.info("异常发生，进入fallback方法，接收的参数：userId = {}", userId);
			UserVO user = new UserVO();
			user.setUserId("1");
			user.setUserName("default username");
			return user;
		}
	}
	
}
