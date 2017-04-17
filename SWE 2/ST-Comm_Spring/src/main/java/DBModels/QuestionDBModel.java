package DBModels;

import java.util.Vector;

import Entities.Question;

public class QuestionDBModel {

  public static Vector<Question> fetchQuestions(String gameName) {
  return new Vector<Question>();
  }

  public static boolean saveQuestion(Question question, String gameName) {
  return true;
  }

}