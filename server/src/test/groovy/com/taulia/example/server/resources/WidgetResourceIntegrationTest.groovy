package com.taulia.example.server.resources

import com.taulia.example.core.Widget
import com.taulia.example.server.BaseServerIntegrationTest
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate

class WidgetResourceIntegrationTest extends BaseServerIntegrationTest {

  @Autowired
  TestRestTemplate restTemplate

  @Test
  void 'Widget save'() {
    def widget = restTemplate.postForObject('/widget', new Widget(name: 'Widget'), Widget)

    assert widget.name == 'Widget'
    assert widget.id != null
    assert widget.id ==~ /[0-9a-z]{32}/
  }

}
