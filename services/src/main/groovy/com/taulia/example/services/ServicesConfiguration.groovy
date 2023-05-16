package com.taulia.example.services

import com.taulia.example.tdo.TdoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(TdoConfiguration)
class ServicesConfiguration {

}
