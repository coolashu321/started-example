package com.taulia.example.services

import com.taulia.example.core.Widget
import com.taulia.example.tdo.WidgetRepository
import org.junit.After
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

class WidgetServiceIntegrationTest extends BaseServicesIntegrationTest {

  @Autowired
  WidgetService widgetService

  @Autowired
  WidgetRepository widgetRepository

  @After
  void cleanup() {
    widgetRepository.deleteAll()
  }

  @Test
  void 'Widget get'() {
    def widget = widgetService.save(new Widget(name: 'Widget'))

    widget = widgetService.get(widget.id)

    assert widget.name == 'Widget'
    assert widget.id
  }

  @Test
  void 'Widget save'() {
    def widget = widgetService.save(new Widget(name: 'Widget'))

    assert widget.name == 'Widget'
    assert widget.id != null
    assert widget.id ==~ /[0-9a-z]{32}/
  }

  @Test
  void 'Widget delete'() {
    def widget = widgetService.save(new Widget(name: 'Widget'))
    assert widgetRepository.existsById(widget.id)

    widgetService.delete(widget.id)
    assert !widgetRepository.existsById(widget.id)
  }

  @Test
  void 'Widget list'() {
    (1..3).each {
      widgetService.save(new Widget(name: "Widget $it"))
    }

    def widgets = widgetService.list()

    assert widgets.size() == 3
    assert widgets.name.sort() == ['Widget 1', 'Widget 2', 'Widget 3']
  }

}
