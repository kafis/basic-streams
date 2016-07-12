package three

import scala.concurrent.forkjoin.ThreadLocalRandom

/**
  * Created by kfischer on 12.07.16.
  */
class RandomNumberIterator(lowerBound: Int, upperBound: Int, delay: Int ) extends Iterator[Int]{
  override def hasNext: Boolean = true

  override def next(): Int =  {
    println("------")
    println("SOURCE: demand signaled, generating a random nr...")
    Thread.sleep(delay)
    val number = ThreadLocalRandom.current().nextInt(lowerBound, upperBound + 1)
    println(s"SOURCE: pushing $number downstream")
    number
  }
}
