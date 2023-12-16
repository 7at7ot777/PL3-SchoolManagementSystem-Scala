import java.io.{File, FileWriter, PrintWriter}
import scala.io.Source
import scala.util.control.Breaks.break

object Teacher {
  private var id: Int = 0

  def index(): Unit = {
    val filePath = "teachers.txt"
    val source = Source.fromFile(filePath)

    try {
      println("Teachers Index:")
      for (line <- source.getLines()) {
        val recordFields = line.split(",")
        println(s"ID: ${recordFields(0)}, Teacher Name: ${recordFields(1)}, Subject: ${recordFields(2)}")
      }
    } finally {
      source.close()
    }
  }

  def read(id: Int)= {
    val source = Source.fromFile("teachers.txt")
    var flag = 0
    for (line <- source.getLines()) {
      // Assuming records are comma-separated values
      val recordFields = line.split(",")

      // Process the fields as needed
      val studentId: Int = recordFields(0).toInt
      if (id == studentId) {
        flag = 1
        println(s"Teacher with ID $id: ID=${recordFields(0)}, Name=${recordFields(1)}, Subject=${recordFields(2)}")
        source.close()
        break
      }

  }
    if(flag == 0 ){println("Teacher Not Found")}
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
   read(id)


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
