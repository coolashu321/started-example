package com.taulia.example.server

import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*

@RunWith(SpringRunner)
@SpringBootTest(classes = IntapiExampleApplication, webEnvironment = RANDOM_PORT)
abstract class BaseServerIntegrationTest {

}
