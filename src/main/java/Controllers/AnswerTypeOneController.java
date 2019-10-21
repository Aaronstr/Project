package Controllers;

import Server.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class AnswerTypeOneController {
    //Answer creator for typing
    public static void insertAnswer1(int QuestionID, String CorrectAnswerText) {//It will calls for these parameters
        try {
            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO ANSWERTYPEONE( QuestionID, CorrectAnswerText) VALUES (?,?)");
            ps.setInt(1, QuestionID);
            ps.setString(2, CorrectAnswerText);

            ps.execute();//executes the prepared statements
        } catch (Exception exception) { //if the parameters don't work or an database error
            System.out.println("Database Error:" + exception.getMessage());
        }
    }

    //update the answer
    public static void updateCorrectAnswerText(String CorrectAnswerText, int QuestionID) {
        try {
            PreparedStatement ps = Main.db.prepareStatement("UPDATE ANSWERTYPEONE Set  CorrectAnswerText = ? Where QuestionID=?");//update SQL query
            ps.setString(1, CorrectAnswerText);
            ps.setInt(2, QuestionID);

            ps.execute();//executes the prepared statements

        } catch (Exception exception) {
            System.out.println("Database Error:");//If SQL query doesn't work outputs this message
        }
    }

    // select the answer
    public static void SelectAnswer1(int QuestionID) {//It will calls for these parameters
        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT AnswerID, CorrectAnswerText From ANSWERTYPEONE Where QuestionID=?  ");
            ps.setInt(1, QuestionID);
            ResultSet results = ps.executeQuery();
            while (results.next()) {
                String AnswerID = results.getString(1);
                String CorrectAnswerText = results.getString(2);
                System.out.print("AnswerID: " + AnswerID + " , ");
                System.out.print("CorrectAnswerText: " + CorrectAnswerText + " , ");
            }


            ps.execute();//executes the prepared statements
        } catch (Exception exception) { //if the parameters don't work or an database error
            System.out.println("Database Error:" + exception.getMessage());
        }
    }

    //Delete answer using answerID
    public static void deleteAnswer1(int AnswerID) {
        try {
            PreparedStatement ps = Main.db.prepareStatement("DELETE from ANSWERTYPEONE Where AnswerID=?");//SQL Statement saying to delete everything
            ps.setInt(1, AnswerID);
            ps.execute();//executes

        } catch (Exception exception) {
            System.out.println("Database Error:");
        }
    }
    //Delete answer using QuestionID
    public static void deleteAnswer2(int QuestionID) {
        try {
            PreparedStatement ps = Main.db.prepareStatement("DELETE from ANSWERTYPEONE Where QuestionID=?");//SQL Statement saying to delete everything
            ps.setInt(1, QuestionID);
            ps.execute();//executes

        } catch (Exception exception) {
            System.out.println("Database Error:");
        }
    }
}
