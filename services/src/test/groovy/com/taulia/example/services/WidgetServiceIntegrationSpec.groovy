package com.taulia.example.tdo

import com.taulia.example.core.Widget
import com.taulia.example.services.ServicesConfiguration
import com.taulia.example.services.WidgetService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest(classes = ServicesConfiguration)
class WidgetServiceIntegrationSpec extends Specification {

  @Autowired
  WidgetService widgetService

  @Autowired
  WidgetRepository widgetRepository

  void cleanup() {
    widgetRepository.deleteAll()
  }

  void 'Widget get'() {
    when:
    def widget = widgetService.save(new Widget(name: 'Widget'))
    widget = widgetService.get(widget.id)

    then:
    widget.name == 'Widget'
    widget.id
  }

  void 'Widget save'() {
    when:
    def widget = widgetService.save(new Widget(name: 'Widget'))

    then:
    widget.name == 'Widget'
    widget.id != null
    widget.id ==~ /[0-9a-z]{32}/
  }

  void 'Widget delete'() {
    setup:
    def widget = widgetService.save(new Widget(name: 'Widget'))

    expect:
    widgetRepository.existsById(widget.id)

    when:
    widgetService.delete(widget.id)

    then:
    !widgetRepository.existsById(widget.id)
  }

  void 'Widget list'() {
    when:
    (1..3).each {
      widgetService.save(new Widget(name: "Widget $it"))
    }

    def widgets = widgetService.list()

    then:
    widgets.size() == 3
    widgets.name.sort() == ['Widget 1', 'Widget 2', 'Widget 3']
  }

}
