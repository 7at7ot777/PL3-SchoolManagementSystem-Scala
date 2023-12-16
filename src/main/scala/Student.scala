import java.io.{File, FileWriter, PrintWriter}
import scala.io.Source
import scala.util.Using

object Student {
  private var id :Int = 0



  def read(id: Int): Any = {

    val source = Source.fromFile("students.txt")

    for (line <- source.getLines()) {
      // Assuming records are comma-separated values
      val recordFields = line.split(",")

      // Process the fields as needed
      val studentId: Int = recordFields(0).toInt
      if (id == studentId) {
        source.close()
        return recordFields;
      }

    }

    source.close()
  }


  def create(id: Int, name: String, grade: String /*,attendance: Map[String, Boolean]*/): Unit = {

    val writer = new FileWriter("students.txt", true)
    writer.write(s"${id},${name},${grade}\n")
    writer.close()
    print("Student Created Successfully")

  }

  def deleteLineFromFile(id:Int): Unit = {

    val filePath = "students.txt"

    // Read the file and filter out the line to delete
    val source = Source.fromFile(filePath)
    val lines = source.getLines().filterNot { line =>
      val recordFields = line.split(",")
      val studentId = recordFields(0).toInt
      id == studentId
    }.toList
    source.close()

    // Write the updated content back to the file
    val writer = new PrintWriter(new File(filePath))
    lines.foreach(writer.println)
    writer.close()
  }

  def update(id: Int, name: String, grade: String): Unit = {
    deleteLineFromFile(id);
    create(id, name, grade)
    println("\nStudent Updated Successfully")
  }

  def createNewStudentWindow(): Unit = {
    println("please enter student name ")
    var name = scala.io.StdIn.readLine()
    println("please enter student grade ")
    var grade = scala.io.StdIn.readLine()
    this.id +=1
    Student.create(this.id,name,grade)
  }

  def readStudentWindow(): Unit = {
    println("please enter student id ")
    val id = scala.io.StdIn.readLine().toInt
    val result: Array[String] = Student.read(id) match {
      case arr: Array[String] => arr
      case _ => Array("0", "No Name", "No Grade")
    }
    println(s"\nThe result id = ${result(0)} name = ${result(1)} grade is ${result(2)}")
//    result
  }

  def updateStudentWindow(): Unit = {
    println("please enter student id ")
    val id = scala.io.StdIn.readLine().toInt
    println("please enter student name ")
    val name = scala.io.StdIn.readLine()
    println("please enter student grade ")
    val grade = scala.io.StdIn.readLine()
    this.update(id, name, grade)



  }

  def deleteStudentWindow(): Unit = {

    println("please enter student id ")
    val id: Int = scala.io.StdIn.readLine().toInt
    this.deleteLineFromFile(id)

  }
}




