package cn.segema.cloud.demo;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import cn.segema.cloud.demo.test.Product;
import cn.segema.cloud.demo.test.ProductMapper;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("cn.segema.cloud.demo.mapper.**")
public class DemoMybatisApplication {
  public static void main(String[] args) {
    SpringApplication.run(DemoMybatisApplication.class, args);
    
//    Configuration
//    XMLConfigBuilder
//    Configuration;
//    SqlSessionFactory;
//    SqlSessionFactoryBuilder;
//    DefaultSqlSessionFactory;
//    MappedStatement;
//    SqlSource;
//    BoundSql;
//    
//    ApplicationContext;
//    BeanFactory;
//    BeanFactoryPostProcessor;
//    BeanPostProcessor;
//    
//    InstantiationAwareBeanPostProcessor;
//    InstantiationAwareBeanPostProcessorAdapter
//    BeanPostProcessor;
//    BeanNameAware;
//    BeanFactoryAware;
//    InitializingBean;
//    BeanPostProcessor
    
    
    String resource = "mybatis.xml";
    InputStream inputStream =null;
	try {
		inputStream = Resources.getResourceAsStream(resource);
	} catch (IOException e) {
		e.printStackTrace();
	}
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

    SqlSession sqlSession = sqlSessionFactory.openSession();
    try {
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
        List<Product> productList = productMapper.selectProductList();
        for (Product product : productList) {
            System.out.printf(product.toString());
        }
    } finally {
        sqlSession.close();
    }

    
    
  }
}
