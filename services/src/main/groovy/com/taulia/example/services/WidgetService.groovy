package com.taulia.example.services

import com.taulia.example.core.Widget
import com.taulia.example.services.converter.WidgetConverter
import com.taulia.example.tdo.WidgetRepository
import io.micrometer.core.annotation.Timed

import javax.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@Transactional
class WidgetService {

  @Autowired
  WidgetRepository widgetRepository

  @Autowired
  WidgetConverter widgetConverter

  @Timed
  Widget get(String widgetId) {
    widgetConverter.fromDomain(
      widgetRepository.getOne(widgetId)
    )
  }

  @Timed
  List<Widget> list() {
    widgetRepository.findAll().collect { tdo ->
      widgetConverter.fromDomain(tdo)
    }
  }

  @Timed
  Widget save(Widget widget) {
    widgetConverter.fromDomain(
      widgetRepository.save(
        widgetConverter.toDomain(widget)
      )
    )
  }

  @Timed
  void delete(String widgetId) {
    widgetRepository.deleteById(widgetId)
  }

}
