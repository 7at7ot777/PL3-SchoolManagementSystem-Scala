object SchoolManagementSystem extends App {

  var choice = 1
  while (choice != 0) {
    println(
      """
        |Please Choose The Operation
        |1-Open Student Module
        |2-Open Teacher Module
        |3-Open Course Module
        |4-Exam
        |5-To Terminate
        ================================================================================================""".stripMargin)

    choice = scala.io.StdIn.readLine().toInt
    choice match {
      case 1 => studentCruds()
      case 2 => teacherCruds()
      case 3 => courseCruds()
      case 4 => examCruds()
      case 5 => choice = 0
    }
  }
  def studentCruds(): Unit = {
    var choice = 1
    while(choice != 0)
      {
        println(
          """
            |Please Choose The Operation
            |1-Create new student
            |2-Read a student
            |3-Update student
            |4-Delete student
            |5-Show all students
            |6-To Go Back
            =============================================================================================""".stripMargin)

    choice = scala.io.StdIn.readLine().toInt
        choice match {
        case 1 => Student.createNewStudentWindow()
        case 2 => Student.readStudentWindow()
        case 3 => Student.updateStudentWindow()
        case 4 => Student.deleteStudentWindow()
        case 5 => Student.index()
        case 6 => choice = 0
        case _ => choice = 1
      }
      }
}

  def teacherCruds(): Unit = {
    var choice = 1
    while (choice != 0) {
      println(
        """
          |Please Choose The Operation
          |1-Create new teacher
          |2-Read a teacher
          |3-Update teacher
          |4-Delete teacher
          |5-Show all teachers
          |6-To Go Back
          ========================================================================================================""".stripMargin)

      choice = scala.io.StdIn.readLine().toInt
      choice match {
        case 1 => Teacher.createNewTeacherWindow()
        case 2 => Teacher.readTeacherWindow()
        case 3 => Teacher.updateTeacherWindow()
        case 4 => Teacher.deleteTeacherWindow()
        case 5 => Teacher.index()
        case 6 => choice = 0
        case _ => choice = 1

      }
    }
  }

  def courseCruds(): Unit = {
    var choice = 1
    while (choice != 0) {
      println(
        """
          |Please Choose The Operation
          |1-Create new course
          |2-Read a course
          |3-Update course
          |4-Delete course
          |5-Show All Courses
          |6-To Go Back
          ========================================================================================================""".stripMargin)

      choice = scala.io.StdIn.readLine().toInt
      choice match {
        case 1 => Course.createNewCourseWindow()
        case 2 => Course.readCourseWindow()
        case 3 => Course.updateCourseWindow()
        case 4 => Course.deleteCourseWindow()
        case 5 => Course.index()
        case 6 => choice = 0
        case _ => choice = 1
      }
    }
  }

  def examCruds(): Unit = {
    var choice = 1
    while (choice != 0) {
      println(
        """
          |Please Choose The Operation
          |1-Create new exam
          |2-Read an exam
          |3-Update exam
          |4-Delete exam
          |5-Show All Exams
          |6-To Go Back
          ========================================================================================================""".stripMargin)

      choice = scala.io.StdIn.readLine().toInt
      choice match {
        case 1 => Exam.createNewExamWindow()
        case 2 => Exam.readExamWindow()
        case 3 => Exam.updateExamWindow()
        case 4 => Exam.deleteExamWindow()
        case 5 => Exam.index()
        case 6 => choice = 0
        case _ => choice = 1
      }
    }
  }


}
//  Student.create(1, "Mohamed", "1 E3dady")
//  Student.create(2, "Ahmed", "2 E3dady")//  //  val result: Array[String] = Student.read(5).asInstanceOf[Array[String]]
//  val result: Array[String] = Student.read(5) match {
//    case arr: Array[String] => arr
//    case _ =>Array("0", "No Name", "No Grade")
//  }
//  result.foreach(value => println(s"Found value: $value"))
//  println(s"\nThe result id = ${result(0)} name = ${result(1)} grade is ${result(2)}")

//  Student.deleteLineFromFile(2);
//
//  Student.update(1,"Hathout","Kolya")