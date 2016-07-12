package three

/**
  * Created by kfischer on 12.07.16.
  */
class IsEvenFilter {

  def apply(element: Int) = {
    println(s"FILTER: check if $element is even")
    element %2 == 0
  }

}
