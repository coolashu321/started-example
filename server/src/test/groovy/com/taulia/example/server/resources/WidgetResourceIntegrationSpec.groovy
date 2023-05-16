package com.taulia.example.server.resources

import com.taulia.example.core.Widget
import com.taulia.example.server.IntapiExampleApplication
import com.taulia.example.services.WidgetService
import com.taulia.example.tdo.WidgetRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import spock.lang.Specification

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*

@SpringBootTest(classes = IntapiExampleApplication, webEnvironment = RANDOM_PORT)
class WidgetResourceIntegrationSpec extends Specification {

  @Autowired
  TestRestTemplate restTemplate

  @Autowired
  WidgetService widgetService

  @Autowired
  WidgetRepository widgetRepository

  void cleanup() {
    widgetRepository.deleteAll()
  }

  void 'Widget get'() {
    setup:
    def widget = widgetService.save(new Widget(name: 'Widget'))

    when:
    widget = restTemplate.getForObject("/widget/{id}", Widget, widget.id)

    then:
    widget.name == 'Widget'
    widget.id
  }

  void 'Widget save'() {
    when:
    def widget = restTemplate.postForObject('/widget', new Widget(name: 'Widget'), Widget)

    then:
    widget.name == 'Widget'
    widget.id != null
    widget.id ==~ /[0-9a-z]{32}/

    widgetRepository.existsById(widget.id)
  }

  void 'Widget delete'() {
    setup:
    def widget = widgetService.save(new Widget(name: 'Widget'))

    expect:
    widgetRepository.existsById(widget.id)

    when:
    restTemplate.delete("/widget/{id}", widget.id)

    then:
    !widgetRepository.existsById(widget.id)
  }

  void 'Widget list'() {
    setup:
    (1..3).each {
      widgetService.save(new Widget(name: "Widget $it"))
    }

    when:
    def widgets = restTemplate.exchange(
      '/widget', HttpMethod.GET, null,
      new ParameterizedTypeReference<List<Widget>>(){}
    ).body

    then:
    widgets.size() == 3
    widgets.name.sort() == ['Widget 1', 'Widget 2', 'Widget 3']
  }

}
