package ra.luyentap2bang.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "ra")
public class AppConfig implements WebMvcConfigurer, ApplicationContextAware {

    private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }
    @Bean
    public SpringResourceTemplateResolver templateResolver(){
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setPrefix("/views/");
        resolver.setSuffix(".html");
        resolver.setCharacterEncoding("utf-8");
        resolver.setTemplateMode(TemplateMode.HTML);
        return resolver;
    }
    @Bean
    public SpringTemplateEngine templateEngine(){
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(templateResolver());
        return engine;
    }

    @Bean
    public ThymeleafViewResolver thymeleafViewResolver(){
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("utf-8");
        return viewResolver;
    }

    @Bean
    public DataSource getDataSource(){
        DriverManagerDataSource source = new DriverManagerDataSource();
        source.setDriverClassName("com.mysql.cj.jdbc.Driver");
        source.setUrl("jdbc:mysql://localhost:1028/db_luyenTapJoin2Table?createDatabaseIfNotExist=true");
        source.setUsername("root");
        source.setPassword("123456");
        return source;
    }

    @Bean
    public SessionFactory sessionFactory(){
        LocalSessionFactoryBean sf = new LocalSessionFactoryBean();
        sf.setDataSource(getDataSource());
        sf.setPackagesToScan("ra.luyentap2bang.entity");
        Properties props = new Properties();
        props.put("hibernate.hbm2ddl.auto","update");
        props.put("hibernate.show_sql",true);
        props.put("hibernate.dialect","org.hibernate.dialect.MySQL8Dialect");
        sf.setHibernateProperties(props);
        try {
            sf.afterPropertiesSet();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sf.getObject();
    }
}
