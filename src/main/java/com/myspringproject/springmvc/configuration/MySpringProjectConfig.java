package com.myspringproject.springmvc.configuration;

import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

@Configuration
@ComponentScan("com.myspringproject.springmvc")
@EnableWebMvc
@EnableTransactionManagement
@Import({ SecurityConfig.class })
public class MySpringProjectConfig {
	
    @Bean
    public SessionFactory sessionFactory() {
            LocalSessionFactoryBuilder builder = 
		new LocalSessionFactoryBuilder(dataSource());
            builder.scanPackages("com.myspringproject.springmvc.model")
                  .addProperties(getHibernateProperties());

            return builder.buildSessionFactory();
    }
    
	private Properties getHibernateProperties() {
        Properties prop = new Properties();
        prop.put("hibernate.format_sql", "true");
        prop.put("hibernate.show_sql", "true");
        prop.put("hibernate.dialect", 
            "org.hibernate.dialect.MySQL5Dialect");
        return prop;
}

    
	@Bean(name = "dataSource")
	public BasicDataSource dataSource() {
		
		BasicDataSource ds = new BasicDataSource();
	        ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/spring_db");
		ds.setUsername("root");
		return ds;
	}
	
	@Bean
    public HibernateTransactionManager txManager() {
        return new HibernateTransactionManager(sessionFactory());
    }
	
	@Bean
	public UrlBasedViewResolver setupViewResolver() {
		   UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		   resolver.setPrefix("/WEB-INF/views/");
		   resolver.setSuffix(".jsp");
		   resolver.setViewClass(JstlView.class);
		   return resolver;
		 }
	
	
}
