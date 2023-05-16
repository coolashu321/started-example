package com.taulia.example.tdo


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest(classes = TdoConfiguration)
class WidgetRepositoryIntegrationSpec extends Specification {

  @Autowired
  WidgetRepository widgetRepository

  void cleanup() {
    widgetRepository.deleteAll()
  }

  void 'Widget save'() {
    when:
    def widget = widgetRepository.save(new WidgetTdo(name: 'Widget'))

    then:
    widget.name == 'Widget'
    widget.id != null
    widget.id ==~ /[0-9a-z]{32}/
  }

  void 'Widget delete'() {
    setup:
    def widget = widgetRepository.save(new WidgetTdo(name: 'Widget'))

    expect:
    widgetRepository.existsById(widget.id)

    when:
    widgetRepository.delete(widget)

    then:
    !widgetRepository.existsById(widget.id)
  }

  void 'Widget findAll'() {
    when:
    widgetRepository.saveAll((1..3).collect {
      new WidgetTdo(name: "Widget $it")
    })

    def widgets = widgetRepository.findAll()

    then:
    widgets.size() == 3
    widgets.name.sort() == ['Widget 1', 'Widget 2', 'Widget 3']
  }

}
