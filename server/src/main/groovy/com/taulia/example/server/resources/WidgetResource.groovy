package com.taulia.example.server.resources

import com.taulia.example.core.Widget
import com.taulia.example.services.WidgetService
import javax.ws.rs.Consumes
import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
@Path('/widget')
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
class WidgetResource {

  @Autowired
  WidgetService widgetService

  @Path('/{id}')
  @GET
  Widget get(@PathParam('id') String id) {
    widgetService.get(id)
  }

  @Path('/{id}')
  @DELETE
  void delete(@PathParam('id') String id) {
    widgetService.delete(id)
  }

  @POST
  Widget save(Widget widget) {
    widgetService.save(widget)
  }

  @GET
  List<Widget> list() {
    widgetService.list()
  }

}
