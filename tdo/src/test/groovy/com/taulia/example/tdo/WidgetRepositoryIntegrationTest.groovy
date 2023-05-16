package com.taulia.example.tdo

import org.junit.After
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

class WidgetRepositoryIntegrationTest extends BaseTdoIntegrationTest {

  @Autowired
  WidgetRepository widgetRepository

  @After
  void cleanup() {
    widgetRepository.deleteAll()
  }

  @Test
  void 'Widget save'() {
    def widget = widgetRepository.save(new WidgetTdo(name: 'Widget'))

    assert widget.name == 'Widget'
    assert widget.id != null
    assert widget.id ==~ /[0-9a-z]{32}/
  }

  @Test
  void 'Widget delete'() {
    def widget = widgetRepository.save(new WidgetTdo(name: 'Widget'))
    assert widgetRepository.existsById(widget.id)

    widgetRepository.delete(widget)
    assert !widgetRepository.existsById(widget.id)
  }

  @Test
  void 'Widget findAll'() {
    widgetRepository.saveAll((1..3).collect {
      new WidgetTdo(name: "Widget $it")
    })

    def widgets = widgetRepository.findAll()

    assert widgets.size() == 3
    assert widgets.name.sort() == ['Widget 1', 'Widget 2', 'Widget 3']
  }

}
