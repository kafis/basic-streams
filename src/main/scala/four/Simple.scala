package four

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Flow, Sink, Source}

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

  val stream = source
      .via(mapperFlow)
      .via(filterFlow)
      .to(sink)

  stream.run()



  def filterFlow = Flow[Int]
      .map({element =>
        println("Entering FilterFlow")
        element
      })
      .filter(new IsEvenFilter().apply)
      .map({element =>
        println("Leaving FilterFlow")
        element
      })

  def mapperFlow = Flow[Int]
      .map({element =>
        println("Entering Mapper Flow")
        element
      })
      .map(new AddMapper(2).apply)
      .map({ element =>
        println("Leaving Mapper Flow")
        element
      })

}
