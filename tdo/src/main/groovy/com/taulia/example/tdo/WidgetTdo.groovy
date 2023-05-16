package com.taulia.example.tdo

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import org.hibernate.annotations.GenericGenerator

@Entity(name = 'Widget')
class WidgetTdo {

  @GeneratedValue(generator = 'system-uuid')
  @GenericGenerator(name = 'system-uuid', strategy = 'uuid')
  @Id
  String id

  String name

}
