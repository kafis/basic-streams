package four

/**
  * Created by kfischer on 12.07.16.
  */
class AddMapper(nr: Int) {

  def apply(element: Int) = {
    println(s"MAP: $element + $nr")
    element + nr
  }

}
