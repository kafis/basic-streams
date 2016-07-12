package three

/**
  * Created by kfischer on 12.07.16.
  */
class AddMapper {

  def apply(element: Int) = {
    println(s"MAP: $element + 1")
    element +1
  }

}
