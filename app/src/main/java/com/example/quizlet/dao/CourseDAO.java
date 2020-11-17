package com.example.quizlet.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.quizlet.model.Answers;
import com.example.quizlet.model.Courses;
import com.example.quizlet.model.JoinedCourses;
import com.example.quizlet.model.Question;
import com.example.quizlet.model.User;
import com.example.quizlet.model.customModel.Course_AnswerCount;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface CourseDAO {
    @Insert
    public void insertCourse(Courses course);

    @Insert
    public void insertQuestion(Question question);

    @Insert
    public void insertAnswer(Answers course);

    @Insert
    public void insertJoinedCourse(JoinedCourses joinedCourses);

    @Update
    public int updateCourse(Courses courses);

    @Update
    public int updateQuestion(Question question);

    @Update
    public int updateAnswer(Answers answers);

    @Delete
    public int deleteQuestion(Question question);

    @Delete
    public int deleteAnswer(Answers answers);

    @Delete
    public int deleteCourse(Courses courses);

    @Query("SELECT * FROM Courses")
    public List<Courses> getCourses();

    @Query("SELECT * FROM Courses ORDER BY Courses.id DESC LIMIT 1;")
    public Courses getLastCourses();

    @Query("SELECT * FROM Question Where Question.courseId=(Select MAX(id) from Courses);")
    public List<Question> getQuestionOfLastCourse();

    @Query("SELECT * FROM Question")
    public List<Question> getQuestion();

    @Query("SELECT * FROM Question ORDER BY Question.id DESC LIMIT 1;")
    public Question getLastQuestion();

    @Query("DELETE FROM Question Where Question.id=(Select MAX(id) from Question);")
    public int delLastQuestion();

    @Query("DELETE FROM Courses Where Courses.id=(Select MAX(id) from Courses);")
    public int delLastCourse();

    @Query("Select Count(*) from Courses;")
    public int getCourseCount();

    @Query("SELECT * FROM Courses ORDER BY Courses.id DESC LIMIT 1;")
    public Courses getLastCourse();

    @Query("SELECT * FROM Question Where Question.courseId=:id;")
    public List<Question> getQuestionByCourseID(long id);

    @Query("SELECT * FROM Answers Where Answers.questionId=:id;")
    public List<Answers> getAnswerByQuestionID(long id);


    @Query("SELECT * FROM Courses Where Courses.id=:id;")
    public Courses getCourseByID(long id);

    @Query("SELECT * FROM JoinedCourses Where userId=:userID AND courseId=:courseID;")
    public List<JoinedCourses> checkJoined(long userID, long courseID);

    @Query("DELETE FROM Courses Where Courses.id=:id;")
    public int delCourseByID(long id);

    @Query("SELECT Courses.id as id,Courses.name as courseName, Courses.createDate as creatorDate, Count(Question.id) as answerNum FROM Courses, Question where Question.courseId=Courses.id Group by Question.courseId")
    public List<Course_AnswerCount> getCoursesSearchView();

    @Query("SELECT Courses.id as id,Courses.name as courseName, Courses.createDate as creatorDate, Count(Question.id) as answerNum FROM Courses, Question, JoinedCourses where Question.courseId=Courses.id And JoinedCourses.courseId=Courses.id And JoinedCourses.userID=:userID Group by Question.courseId")
    public List<Course_AnswerCount> getCoursesSearchViewByUserID(long userID);

    @Query("SELECT Courses.id as id,Courses.name as courseName, Courses.createDate as creatorDate, Count(Question.id) as answerNum FROM Courses, Question where Question.courseId=Courses.id AND Courses.creatorID=:userID Group by Question.courseId;")
    public List<Course_AnswerCount> getMyCourse(long userID);

    //    @Query("select * from Courses where ")
//    public List<Courses> getAllCoursesByUser(int idUser);
    @Query("Select * from Courses where id = :idCourse")
    public Courses getUserByCourse(long idCourse);
}
