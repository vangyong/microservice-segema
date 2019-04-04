package cn.segema.cloud.system.controller;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.segema.cloud.common.constants.ApiConstant;
import cn.segema.cloud.common.utils.DateTimeUtil;
import cn.segema.cloud.common.utils.IdGeneratorUtil;
import cn.segema.cloud.system.domain.Option;
import cn.segema.cloud.system.repository.OptionRepository;
import cn.segema.cloud.system.vo.OptionVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags="配置参数")
@RestController
@RequestMapping(value = ApiConstant.API_VERSION + "/option")
public class OptionController {

	@Autowired
	private OptionRepository optionRepository;

	@ApiOperation(value = "新增参数", notes = "新增参数")
	@PostMapping
	public ResponseEntity add(@RequestBody Option option) {
		if (option.getOptionId() == null) {
			option.setOptionId(BigInteger.valueOf(IdGeneratorUtil.generateSnowFlakeId()));
		}
		option.setCreateTime(DateTimeUtil.getCurrentUnixTime());
		optionRepository.save(option);
		return new ResponseEntity(option,HttpStatus.OK);
	}

	@ApiOperation(value = "修改参数", notes = "修改参数")
	@PutMapping
	public ResponseEntity edit(@RequestBody Option option) {
		Option oldOption = optionRepository.findOne(option.getOptionId());
		if(oldOption!=null) {
		    BeanUtils.copyProperties(option, oldOption,"createTime");
	        optionRepository.save(oldOption);
	        return new ResponseEntity(oldOption,HttpStatus.OK);
		}else {
		    return new ResponseEntity("can't find Option",HttpStatus.BAD_REQUEST);
		}
		
	}

	@ApiOperation(value = "删除参数", notes = "删除参数")
	@DeleteMapping("/{optionId}")
	public ResponseEntity delete(@PathVariable BigInteger optionId) {
		Option option = optionRepository.findOne(optionId);
		if(option!=null) {
			optionRepository.delete(option);
		}
		return new ResponseEntity(option,HttpStatus.OK);
	}

	@ApiOperation(value = "根据id获取参数", notes = "根据id获取参数")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "optionId", value = "参数id", required = true, paramType = "path") })
	@GetMapping("/{optionId}")
	public ResponseEntity findById(@PathVariable BigInteger optionId) throws Exception {
		Option option = optionRepository.findOne(optionId);
		return new ResponseEntity(option,HttpStatus.OK);
	}

	@ApiOperation(value = "根据参数type获取参数值", notes = "根据参数key获取参数值")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "optionType", value = "参数Type", required = true, paramType = "path") })
	@GetMapping("/value/{optionType}")
	public ResponseEntity findValueByOptionType(@PathVariable String optionType) {
		List<Option> optionList = optionRepository.findValueByOptionType(optionType);
		return new ResponseEntity(optionList,HttpStatus.OK);
	}
	
	@ApiOperation(value = "根据参数type、key获取参数值", notes = "根据参数type、key获取参数值")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "optionType", value = "参数Type", required = true, paramType = "path"),
			@ApiImplicitParam(name = "optionKey", value = "参数key", required = true, paramType = "path")})
	@GetMapping("/value/{optionType}/{optionKey}")
	public ResponseEntity findValueByTypeKey(@PathVariable String optionType,@PathVariable String optionKey) {
		List<Option> optionList = optionRepository.findValueByTypeKey(optionType,optionKey);
		return new ResponseEntity(optionList,HttpStatus.OK);
	}

	@ApiOperation(value = "分页获取参数", notes = "分页获取参数")
	@ApiImplicitParams({@ApiImplicitParam(name = "page", value = "当前页", required = true, paramType = "query"),
		@ApiImplicitParam(name = "limit", value = "每页数", required = true, paramType = "query"),
		@ApiImplicitParam(name = "sort", value = "排序列", required = false, paramType = "query")})
	@GetMapping("/page")
	public ResponseEntity findByPage(@RequestParam(defaultValue ="1") int page, 
			@RequestParam(defaultValue = "20") int limit, 
			@RequestParam(defaultValue = "option_key") String sort,OptionVO option) {
		Sort sortOrder = new Sort(Sort.Direction.DESC, sort);
		Pageable pageable = new PageRequest(page - 1, limit, sortOrder);
		Page<Option> optionPage = optionRepository.findByPage(option,pageable);
		return new ResponseEntity(optionPage,HttpStatus.OK);
	}

}
