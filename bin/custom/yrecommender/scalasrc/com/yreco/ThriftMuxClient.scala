package com.yreco

import com.twitter.finagle.ThriftMux
import org.springframework.stereotype.Service
import thrift.RecommenderEngine.FutureIface

/**
 * This is an example how to do Spring Dependency Injection in Scala service using annotations.
 */
@Service("thriftMuxClientService")
class ThriftMuxClientService {

  var client: Option[FutureIface] = None
  def getClient():FutureIface={client.getOrElse(ThriftMux.newIface[FutureIface](":12000"))}
  //TODO: create three customers with Int customerIDs, real test.
  /** example codes.

  getClient().getSimilarProducts("104176_violet")
  getClient().getRecommendations(0,2,null)

  //groovy
  import com.twitter.finagle.ThriftMux
  import com.twitter.util.Await
  import com.twitter.util.Duration
  import org.springframework.stereotype.Service
  import thrift.RecommenderEngine.FutureIface
  Await.result(spring.getBean("thriftMuxClientService").getClient().reLoadDataAndBuildModel(3,0.07),Duration.fromSeconds(60))
  //Await.result(spring.getBean("thriftMuxClientService").getClient().getRecommendations(0,2,null),Duration.fromSeconds(60))
  Await.result(spring.getBean("thriftMuxClientService").getClient().getSimilarProducts("104176_violet"),Duration.fromSeconds(60))

  */

}
