object SchoolManagementSystem extends App {
  Student.create(1, "Mohamed", "1 E3dady")
  Student.create(2, "Ahmed", "2 E3dady")

  //  val result: Array[String] = Student.read(5).asInstanceOf[Array[String]]
  val result: Array[String] = Student.read(5) match {
    case arr: Array[String] => arr
    case _ =>Array("0", "No Name", "No Grade")
  }

  //  result.foreach(value => println(s"Found value: $value"))

  println(s"\nThe result id = ${result(0)} name = ${result(1)} grade is ${result(2)}")

  Student.deleteLineFromFile(2);

  Student.update(1,"Hathout","Kolya")


}
