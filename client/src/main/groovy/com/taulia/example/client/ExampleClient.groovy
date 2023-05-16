package com.taulia.example.client

import com.taulia.example.core.Widget
import javax.ws.rs.Consumes
import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path('/widget')
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
interface ExampleClient {

  @Path('/{id}')
  @GET
  Widget getWidget(@PathParam('id') String id)

  @Path('/{id}')
  @DELETE
  void deleteWidget(@PathParam('id') String id)

  @POST
  Widget saveWidget(Widget widget)

  @GET
  List<Widget> listWidgets()

}
