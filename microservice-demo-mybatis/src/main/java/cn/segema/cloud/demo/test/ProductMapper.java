package cn.segema.cloud.demo.test;

import java.util.List;

public interface ProductMapper {
	 /**
     * 查询所有的产品
     * @return
     */
    List<Product> selectProductList();

}
