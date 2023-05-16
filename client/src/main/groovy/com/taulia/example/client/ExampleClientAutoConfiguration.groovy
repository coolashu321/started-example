package com.taulia.example.client

import com.taulia.starters.client.ClientProperties
import com.taulia.starters.client.feign.FeignClientAutoConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(ExampleClientProperties)
class ExampleClientAutoConfiguration extends FeignClientAutoConfiguration<ExampleClient> {

  @ConfigurationProperties('taulia.example.client')
  static class ExampleClientProperties extends ClientProperties {
  }

  @Autowired
  ExampleClientProperties clientProperties

  @Bean
  ExampleClient exampleClient() {
    createClient()
  }

}
