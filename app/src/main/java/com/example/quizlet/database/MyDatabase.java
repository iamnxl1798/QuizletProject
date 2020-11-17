package com.example.quizlet.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.quizlet.dao.AnswerDAO;
import com.example.quizlet.dao.CourseDAO;
import com.example.quizlet.dao.ImportQuestionDAO;
import com.example.quizlet.dao.JoinedCouesesDAO;
import com.example.quizlet.dao.QuesstionDAO;
import com.example.quizlet.dao.TestDAO;
import com.example.quizlet.dao.UserDAO;
import com.example.quizlet.model.Answers;
import com.example.quizlet.model.Courses;
import com.example.quizlet.model.ImportantQuestions;
import com.example.quizlet.model.JoinedCourses;
import com.example.quizlet.model.Question;
import com.example.quizlet.model.User;


@Database(entities = {User.class, Question.class, JoinedCourses.class, Courses.class, Answers.class, ImportantQuestions.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {
    public abstract UserDAO createUserDAO();

    public abstract CourseDAO createCourseDAO();

    public abstract QuesstionDAO createQuesstionDAO();

    public abstract AnswerDAO createAnswerDAO();

    public abstract JoinedCouesesDAO createJoinCourseDAO();

    public abstract ImportQuestionDAO createImportQuestionDAO();

    public abstract TestDAO createTestDAO();

}
