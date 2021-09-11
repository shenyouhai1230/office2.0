package com.office;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 * 
 * @author office
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class OfficeApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(OfficeApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  OFFICE启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }
}
