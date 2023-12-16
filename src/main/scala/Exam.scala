import java.io.{File, FileWriter, PrintWriter}
import scala.io.Source

object Exam {
  private var id: Int = 0

  def index(): Unit = {
    val filePath = "exams.txt"
    val source = Source.fromFile(filePath)

    try {
      println("Exams Index:")
      for (line <- source.getLines()) {
        val recordFields = line.split(",")
        println(s"ID: ${recordFields(0)}, Course Name: ${recordFields(1)}, Exam Date: ${recordFields(2)}")
      }
    } finally {
      source.close()
    }
  }
  def read(id: Int): Option[Array[String]] = {
    val source = Source.fromFile("exams.txt")

    val result = source.getLines().collectFirst {
      case line if line.startsWith(s"$id,") => line.split(",")
    }

    source.close()
    result
  }

  def create(id: Int, course: String, date: String): Unit = {
    val writer = new FileWriter("exams.txt", true)
    writer.write(s"$id,$course,$date\n")
    writer.close()
    println("Exam Created Successfully")
  }

  def deleteLineFromFile(id: Int): Unit = {
    val filePath = "exams.txt"
    val source = Source.fromFile(filePath)
    val lines = source.getLines().filterNot { line =>
      val recordFields = line.split(",")
      val examId = recordFields(0).toInt
      id == examId
    }.toList
    source.close()

    val writer = new PrintWriter(new File(filePath))
    lines.foreach(writer.println)
    writer.close()
  }

  def update(id: Int, course: String, date: String): Unit = {
    deleteLineFromFile(id)
    create(id, course, date)
    println("\nExam Updated Successfully")
  }

  def createNewExamWindow(): Unit = {
    println("Please enter course name: ")
    val course = scala.io.StdIn.readLine()
    println("Please enter exam date: ")
    val date = scala.io.StdIn.readLine()
    this.id += 1
    create(this.id, course, date)
  }

  def readExamWindow(): Unit = {
    println("Please enter exam id: ")
    val id = scala.io.StdIn.readLine().toInt
    val result: Option[Array[String]] = read(id)

    result match {
      case Some(arr) => println(s"\nThe result id = ${arr(0)} course = ${arr(1)} date = ${arr(2)}")
      case None      => println("Exam not found.")
    }
  }

  def updateExamWindow(): Unit = {
    println("Please enter exam id: ")
    val id = scala.io.StdIn.readLine().toInt
    println("Please enter course name: ")
    val course = scala.io.StdIn.readLine()
    println("Please enter exam date: ")
    val date = scala.io.StdIn.readLine()
    update(id, course, date)
  }

  def deleteExamWindow(): Unit = {
    println("Please enter exam id: ")
    val id: Int = scala.io.StdIn.readLine().toInt
    deleteLineFromFile(id)
  }
}
