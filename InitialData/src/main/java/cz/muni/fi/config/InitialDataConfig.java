package cz.muni.fi.config;

import cz.muni.fi.InitialData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

/**
 * Created by Vaculik on 02/02/2016.
 */
@Configuration
@Import(ServiceApplicationContext.class)
@ComponentScan(basePackageClasses = {InitialData.class})
public class InitialDataConfig {

    @Autowired
    private InitialData initialDataLoader;


    @PostConstruct
    public void loadData() {
        initialDataLoader.loadData();
    }
}
