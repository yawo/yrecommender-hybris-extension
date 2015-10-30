package com.yreco

import com.twitter.finagle.ThriftMux
import org.springframework.stereotype.Service
import thrift.RecommenderEngine.FutureIface
import scala.beans.BeanProperty

/**
 * This is an example how to do Spring Dependency Injection in Scala service using annotations.
 */
@Service("thriftMuxClientService")
class ThriftMuxClientService {

  @BeanProperty lazy val client: FutureIface = ThriftMux.newIface[FutureIface](":12000")
  /** example codes.
  client.getSimilarProducts("104176_violet")
  client.getRecommendations(0,2,null)
  // ----------- And in hybris groovy console ----------- :
  import com.twitter.finagle.ThriftMux
  import com.twitter.util.*
  import org.springframework.stereotype.Service
  import thrift.RecommenderEngine.FutureIface
  client = spring.getBean("thriftMuxClientService").getClient()
  Await.result(client.reLoadDataAndBuildModel(3,0.07)
  Await.result(client.getRecommendations(0,2,null))
  Await.result(client.getSimilarProducts("104176_violet"),Duration.fromSeconds(60))

  */

}
