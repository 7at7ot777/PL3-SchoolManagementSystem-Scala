import java.io.{File, FileWriter, PrintWriter}
import scala.io.Source

object Course {
  private var id: Int = 0

  def index(): Unit = {
    val filePath = "courses.txt"
    val source = Source.fromFile(filePath)

    try {
      println("Courses Index:")
      for (line <- source.getLines()) {
        val recordFields = line.split(",")
        println(s"ID: ${recordFields(0)}, Course Name: ${recordFields(1)}, Instructor: ${recordFields(2)}")
      }
    } finally {
      source.close()
    }
  }
  def read(id: Int): Option[Array[String]] = {
    val source = Source.fromFile("courses.txt")

    val result = source.getLines().collectFirst {
      case line if line.startsWith(s"$id,") => line.split(",")
    }

    source.close()
    result
  }

  def create(id: Int, name: String, instructor: String): Unit = {
    val writer = new FileWriter("courses.txt", true)
    writer.write(s"$id,$name,$instructor\n")
    writer.close()
    println("Course Created Successfully")
  }

  def deleteLineFromFile(id: Int): Unit = {
    val filePath = "courses.txt"
    val source = Source.fromFile(filePath)
    val lines = source.getLines().filterNot { line =>
      val recordFields = line.split(",")
      val courseId = recordFields(0).toInt
      id == courseId
    }.toList
    source.close()

    val writer = new PrintWriter(new File(filePath))
    lines.foreach(writer.println)
    writer.close()
  }

  def update(id: Int, name: String, instructor: String): Unit = {
    deleteLineFromFile(id)
    create(id, name, instructor)
    println("\nCourse Updated Successfully")
  }

  def createNewCourseWindow(): Unit = {
    println("Please enter course name: ")
    val name = scala.io.StdIn.readLine()
    println("Please enter instructor name: ")
    val instructor = scala.io.StdIn.readLine()
    this.id += 1
    create(this.id, name, instructor)
  }

  def readCourseWindow(): Unit = {
    println("Please enter course id: ")
    val id = scala.io.StdIn.readLine().toInt
    val result: Option[Array[String]] = read(id)

    result match {
      case Some(arr) => println(s"\nThe result id = ${arr(0)} name = ${arr(1)} instructor = ${arr(2)}")
      case None      => println("Course not found.")
    }
  }

  def updateCourseWindow(): Unit = {
    println("Please enter course id: ")
    val id = scala.io.StdIn.readLine().toInt
    println("Please enter course name: ")
    val name = scala.io.StdIn.readLine()
    println("Please enter instructor name: ")
    val instructor = scala.io.StdIn.readLine()
    update(id, name, instructor)
  }

  def deleteCourseWindow(): Unit = {
    println("Please enter course id: ")
    val id: Int = scala.io.StdIn.readLine().toInt
    deleteLineFromFile(id)
  }
}
