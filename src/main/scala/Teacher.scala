import java.io.{File, FileWriter, PrintWriter}
import scala.io.Source

object Teacher {
  private var id: Int = 0

  def read(id: Int): Option[Array[String]] = {
    val source = Source.fromFile("teachers.txt")

    val result = source.getLines().collectFirst {
      case line if line.startsWith(s"$id,") => line.split(",")
    }

    source.close()
    result
  }

  def create(id: Int, name: String, subject: String): Unit = {
    val writer = new FileWriter("teachers.txt", true)
    writer.write(s"$id,$name,$subject\n")
    writer.close()
    println("Teacher Created Successfully")
  }

  def deleteLineFromFile(id: Int): Unit = {
    val filePath = "teachers.txt"
    val source = Source.fromFile(filePath)
    val lines = source.getLines().filterNot { line =>
      val recordFields = line.split(",")
      val teacherId = recordFields(0).toInt
      id == teacherId
    }.toList
    source.close()

    val writer = new PrintWriter(new File(filePath))
    lines.foreach(writer.println)
    writer.close()
  }

  def update(id: Int, name: String, subject: String): Unit = {
    deleteLineFromFile(id)
    create(id, name, subject)
    println("\nTeacher Updated Successfully")
  }

  def createNewTeacherWindow(): Unit = {
    println("Please enter teacher name: ")
    val name = scala.io.StdIn.readLine()
    println("Please enter teacher subject: ")
    val subject = scala.io.StdIn.readLine()
    this.id += 1
    create(this.id, name, subject)
  }

  def readTeacherWindow(): Unit = {
    println("Please enter teacher id: ")
    val id = scala.io.StdIn.readLine().toInt
    val result: Option[Array[String]] = read(id)

    result match {
      case Some(arr) => println(s"\nThe result id = ${arr(0)} name = ${arr(1)} subject = ${arr(2)}")
      case None      => println("Teacher not found.")
    }
  }

  def updateTeacherWindow(): Unit = {
    println("Please enter teacher id: ")
    val id = scala.io.StdIn.readLine().toInt
    println("Please enter teacher name: ")
    val name = scala.io.StdIn.readLine()
    println("Please enter teacher subject: ")
    val subject = scala.io.StdIn.readLine()
    update(id, name, subject)
  }

  def deleteTeacherWindow(): Unit = {
    println("Please enter teacher id: ")
    val id: Int = scala.io.StdIn.readLine().toInt
    deleteLineFromFile(id)
  }
}
