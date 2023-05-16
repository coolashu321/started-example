package com.taulia.example.client

import com.taulia.example.core.Widget
import com.taulia.example.server.IntapiExampleApplication
import com.taulia.example.services.WidgetService
import com.taulia.example.tdo.WidgetRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*

@SpringBootTest(
  classes = [IntapiExampleApplication, ExampleClientAutoConfiguration],
  webEnvironment = DEFINED_PORT,
  properties = [
    'taulia.example.client.url=http://localhost:${server.port}/'
  ]
)
class ExampleClientIntegrationSpec extends Specification {

  @Autowired
  ExampleClient exampleClient

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
    widget = exampleClient.getWidget(widget.id)

    then:
    widget.name == 'Widget'
    widget.id
  }

  void 'Widget save'() {
    when:
    def widget = exampleClient.saveWidget(new Widget(name: 'Widget'))

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
    exampleClient.deleteWidget(widget.id)

    then:
    !widgetRepository.existsById(widget.id)
  }

  void 'Widget list'() {
    setup:
    (1..3).each {
      widgetService.save(new Widget(name: "Widget $it"))
    }

    when:
    def widgets = exampleClient.listWidgets()

    then:
    widgets.size() == 3
    widgets.name.sort() == ['Widget 1', 'Widget 2', 'Widget 3']
  }

}
