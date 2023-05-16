package com.taulia.example.services.converter

import com.taulia.example.core.Widget
import com.taulia.example.tdo.WidgetTdo
import org.springframework.stereotype.Component

@Component
class WidgetConverter {

  Widget fromDomain(WidgetTdo tdo) {
    new Widget(
      id: tdo.id,
      name: tdo.name
    )
  }

  WidgetTdo toDomain(Widget core) {
    new WidgetTdo(
      id: core.id,
      name: core.name
    )
  }

}
