package cn.com.yusys.yusp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
//import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.ulisesbocchio.jasyptspringboot.JasyptSpringBootAutoConfiguration;

import cn.com.yusys.yusp.commons.config.ApplicationProperties;
import cn.com.yusys.yusp.commons.util.AppStartMessageUtil;
import tk.mybatis.spring.annotation.MapperScan;


/**
 * <h2>模板启动类</h2>
 * 主要为目前项目中所使用的到注解解释及使用场景
 * <p>以下为启动类注解的使用场景</p>
 * <table>
 *  <tr>
 *      <td align="center"><strong>注解</strong></td>
 *      <td align="center"><strong>属性</strong></td>
 *      <td align="center"><strong>说明</strong></td>
 *  </tr>
 *  <tr>
 *      <td align="center">{@link @EnableDiscoveryClient}</td>
 *      <td></td>
 *      <td>SpringCloud中discovery service实现</br>
 *          {@link @EnableDiscoveryClient}基于spring-cloud-commons，并不区分实际的注册中心是使用的eureka、consul、zookeeper等</td>
 *  </tr>
 *  <tr>
 *      <td align="center">{@link @EnableApolloConfig}</td>
 *      <td></td>
 *      <td>使用Apollo配置中心后，需要开启的注解，如不使用Apollo配置中心，请将该注解去除</td>
 *  </tr>
 *  <tr>
 *      <td align="center">{@link @EnableConfigurationProperties}</td>
 *      <td></td>
 *      <td>{@link @ConfigurationProperties}注解主要用来把properties配置文件转化为bean来使用的，</br>
 *          而{@link @EnableConfigurationProperties}注解的作用是{@link @ConfigurationProperties}注解生效。</br>
 *          如果只配置{@link @ConfigurationProperties}注解，在IOC容器中是获取不到properties配置文件转化的bean的</td>
 *  </tr>
 *  <tr>
 *      <td align="center">{@link @EnableFeignClients}</td>
 *      <td>value属性与basePackages意义相同</td>
 *      <td>扫描标记了@FeignClient的接口并创建实例bean，默认扫描并创建所在工程下的包，可使用通配符方式，并支持多路径配置</br>
 *          <strong>注意此注解并不管理bean的依赖的关系</strong></br>
 *          如该项目中没有调用其他微服务接口应用，请将该注解去除</br>
 *          配合改注解使用的相关配置如下：
 *          <li>配置属性:在配置中添加以ribbon.*和hystrix.*的相关配置</li>
 *          <li>POM引用:在POM中添加SpringCloud中的spring-cloud-starter-openfeign和spring-cloud-starter-netflix-hystrix两个jar</li></td>
 *  </tr>
 *  <tr>
 *      <td align="center" rowspan="3">{@link @SpringBootApplication}</td>
 *      <td></td>
 *      <td>标记由该类启动的工程为SpringBoot应用，注解可不用配置ComponentScan和SpringBootConfiguration和EnableAutoConfiguration注解</td>
 *  </tr>
 *  <tr>
 *      <td>scanBasePackages</td>
 *      <td>该属性的作用与{@link @ComponentScan}注解相同，均为配置扫描路径，可使用通配符方式，并支持多路径配置</td>
 *  </tr>
 *  <tr>
 *      <td>exclude</td>
 *      <td>该属性的为剔除某些自动配置类，在使用spring-boot-autoconfigure包后，会存在许多自动配置类启动，
 *          标记了{@link @Conditional}*开头的注解除外，可剔除某些不用的启动类使应用启用变快，一些简单剔除的启动类如下：
 *          <li>{@link ThymeleafAutoConfiguration}:Thymeleaf应用的自动配置类</li>
 *          <li>{@link JmxAutoConfiguration}:开启JMX配置</li>
 *          <li>{@link WebSocketServletAutoConfiguration}:webSocket servlet应用的自动配置类</li>
 *          <li>{@link JasyptSpringBootAutoConfiguration}:properties使用加解密自动配置类，如使用，请配合属性配置
 *              application.decrypt.enabled，jasypt.encryptor.bootstrap，application.decrypt.type一起使用</li>
 *          <li>{@link RedisAutoConfiguration}:redis自动配置类</li>
 *          <li>{@link CacheAutoConfiguration}:Spring cache自动配置类</li>
 *          <li>其他配置类，请见spring-boot-autoconfigure包中*{@link AutoConfiguration}结尾的
 *              并且未使用{@link @Conditional}*开头注解的配置类，如不使用且觉得应用启动过慢，可通过此种方式删除</li></td>
 *  </tr>
 *  <tr>
 *      <td align="center">{@link @MapperScan}</td>
 *      <td></td>
 *      <td><strong>如使用yusp-common-mapper请使用tk包下的{@link @MapperScan}注解</strong></br>
 *          该注解主要用于mybatis接口实现代理类的扫描包路径，可使用通配符方式，并支持多路径配置
 *      </td>
 *  </tr>
 * </table>
 * 其他相关说明如下：
 * <li>SpringBoot 2.x的版本连接Redis的客户端建议使用Lettuce，改项目的配置文件中连接spring redis的相关配置已经改为使用lettuce，如使用lettuce，请在POM中添加如下jar引用方可使用</br>
 * &lt;dependency&gt;</br>
 * &nbsp;&nbsp;&lt;groupId&gt;org.apache.commons&lt;/groupId&gt;</br>
 * &nbsp;&nbsp;&lt;artifactId&gt;commons-pool2&lt;/artifactId&gt;</br>
 * &lt;/dependency&gt;</li>
 * 
 * @version 1.0
 */
@EnableConfigurationProperties({ApplicationProperties.class})
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"cn.com.yusys.yusp"},
    exclude = {JmxAutoConfiguration.class, ThymeleafAutoConfiguration.class, 
            SecurityAutoConfiguration.class, JasyptSpringBootAutoConfiguration.class})
@MapperScan({"cn.com.yusys.yusp.repository.mapper"})
@EnableFeignClients("cn.com.yusys.yusp")
@EnableTransactionManagement
public class ShchBookkeepingApp {
    
    private static final Logger logger = LoggerFactory.getLogger(ShchBookkeepingApp.class);

    public static void main(String[] args) {
        Environment env = SpringApplication.run(ShchBookkeepingApp.class, args).getEnvironment();
        logger.info(AppStartMessageUtil.updServiceStartMessage(env));
    }
}
