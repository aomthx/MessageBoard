package com.messageboard.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.messageboard.mapper")
public class MybatisConfig {
}
