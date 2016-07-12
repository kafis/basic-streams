package one

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Sink, Source}

/**
  * Created by kfischer on 12.07.16.
  */
object Simple extends App {
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

  val stream = source.to(sink)

  stream.run()

}
