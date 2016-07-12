package two

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Sink, Source}

/**
  * Created by kfischer on 12.07.16.
  */
object Operations extends App {
  implicit val system = ActorSystem("actor-system")
  implicit val materializer = ActorMaterializer()
  
  val source = Source.fromIterator( () => {
    new RandomNumberIterator(
      lowerBound = 0,
      upperBound = 100,
      delay = 5000)
  })

  val sink = Sink.foreach({ element:Int =>
    println(s"SINK: Received $element from Upstream")
  })

  val stream = source.map({ element  => {
    println(s"MAP: $element + 1")
    element + 1
  }}).filter({element =>
    println(s"FILTER: check if $element is even")
    element %2 == 0
  }).to(sink)

  stream.run()

}
