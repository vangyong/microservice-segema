# 项目简介
本项目是《使用Spring Cloud与Docker实战微服务》：

采用springboot-1.x开发


内容主要包含：

| 微服务角色                 | 对应的技术选型                              |
| ---------------------    | ----------------------------------------- |
| 注册中心(Register Server)  | Eureka                                    |
| 服务提供者                 | spring mvc、spring-data-jpa、h2等           |
| 服务消费者                 | Ribbon/Feign消费服务提供者的接口              |
| 熔断器                    | Hystrix，包括Hystrix Dashboard以及Turbine    |
| 配置服务                  | Spring Cloud Config Server                  |
| API Gateway              | Zuul                                        |



# 准备

## 环境准备：

| 工具    | 版本或描述                          |
| ----- | --------------------- |
| JDK   | 1.8                   |
| IDE   | STS 或者 IntelliJ IDEA |
| Maven | 3.x                   |

## 主机名配置：

| 主机名配置（C:\Windows\System32\drivers\etc\hosts文件） |
| ---------------------------------------- |
| 127.0.0.1 discovery peer1 peer2 config api-gateway demo filecenter system contract |

## 主机规划：

| 项目名称                                     | 端口   | 描述                     | URL             |
| ---------------------------------------- | ---- | ---------------------------- | --------------- |
| microservice-api-gateway                 | 8040 | API Gateway                   | 详见文章        |           
| microservice-config                      | 8050 | 配置服务                       | 详见文章         |
| microservice-discovery                   | 8761 | 注册中心                       | /               |
| microservice-hystrix-turbine             | 8030 | hystrix-turbine监控            | /turbine.stream |
| microservice-hystrix-dashboard           | 8060 | hystrix-dashboard监控          | /hystrix.stream |
| microservice-demo                        | 8080 | 实例demo服务                    | /1              |
| microservice-demo-web                    | 8081 | 实例demo服务WEB                 | /1              |
| microservice-filecenter                  | 8082 | 文件中心服务                    | /1               |
| microservice-shiro                       | 8084 | 权限认证服务提供者               | /1               |
| microservice-auth-server                 | 8086 | 认证服务提供者                   | /1               |
| microservice-elasticsearch               | 8088 | 全文检索                        | /1               |
| microservice-mail                        | 8090 | 邮件服务                        | /1               |
| microservice-system                      | 12000 | 系统管理服务                   | /1               |
| microservice-system-web                  | 12001 | 系统管理服务WEB                 | /1              |
| microservice-contract                    | 12002 | 合同管理服务                    | /1              |
| microservice-contract-web                | 12003 | 合同管理服务WEB                 | /1              |
| microservice-activiti                    | 12006 | activiti服务                   | /1              |
| microservice-activiti-designer           | 12007 | activiti设计器服务WEB           | /1              |
| microservice-flowable                    | 12008 | flowable服务                   | /1              |
| microservice-flowable-web                | 12009 | flowable服务WEB                | /1              |
| microservice-performance                 | 12010 | 绩效管理服务                    | /1              |
| microservice-performance-web             | 12011 | 绩效管理服务                    | /1              |
| microservice-broadcast                   | 12012 | 编播系统服务提供者               | /1              |
| microservice-broadcast-web               | 12013 | 编播系统服务WEB                 | /1              |
| microservice-cms                         | 12014 | 内容管理服务提供者               | /1              |
| microservice-cms-web                     | 12015 | 内容管理服务WEB                 | /1              |
