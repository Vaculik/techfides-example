package cz.muni.fi.config;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by Vaculik on 02/02/2016.
 */
@Configuration
@Import(PersistenceApplicationContext.class)
@ComponentScan(basePackageClasses = {cz.muni.fi.facade.BeanMapper.class})
public class ServiceApplicationContext {

    @Bean
    public Mapper mapper() {
        return new DozerBeanMapper();
    }
}
