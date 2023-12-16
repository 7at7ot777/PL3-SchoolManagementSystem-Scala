import akka.actor.{Actor, ActorLogging, ActorSystem, Props}

// Define messages for communication
case class CreateStudent(id: Int, name: String, grade: String)
case class ReadStudent(id: Int)
case class UpdateStudent(id: Int, name: String, grade: String)
case class DeleteStudent(id: Int)
case class IndexStudents()

class StudentActor extends Actor with ActorLogging {


  override def receive: Receive = {
    case CreateStudent(id, name, grade) =>
      Student.create(id, name, grade)
      log.info(s"Student with ID $id created successfully.")

    case ReadStudent(id) =>
//      val result = Student.read(id) match {
//        case arr: Array[String] => arr
//        case _ => Array("0", "No Name", "No Grade")
//      }
      Student.read(id)
      log.info(s"Student with ID $id has beean read")

    case UpdateStudent(id, name, grade) =>
      Student.update(id, name, grade)
      log.info(s"Student with ID $id updated successfully.")

    case DeleteStudent(id) =>
      Student.deleteLineFromFile(id)
      log.info(s"Student with ID $id deleted successfully.")

    case IndexStudents() =>
      Student.index()
      log.info(s"Student index list is displayed.")

  }
}
