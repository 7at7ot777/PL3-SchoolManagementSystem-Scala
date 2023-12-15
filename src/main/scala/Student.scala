import java.io.{File, FileWriter, PrintWriter}
import scala.io.Source
import scala.util.Using

object Student {

  //  def listAll(): List[Student] = {
  //
  //    val file = new File("students.txt")
  //    val lines = Source.fromFile(file).getLines()
  //    lines.map(line => {
  //      val Array(studentId, name, grade) = line.split(",")
  //
  //      Student(studentId.toInt, name, grade)
  //    }).toList
  //  }

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
    print("write to file successfully")

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
    println("Student Updated Successfully")
  }
}




