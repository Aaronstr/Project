package Controllers;

import Server.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class QuestionController {
    //Question Create
    public static void insertQuestion(int QuizID, int Timer, String QuestionText, int QuestionTypeID) {//It will calls for these parameters
        try {
            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO  QUESTION(QuizID, Timer, QuestionText, QuestionTypeID)  VALUES (?,?,?,?)");//sql code to insert values into the fields
            ps.setInt(1, QuizID);
            ps.setInt(2, Timer);
            ps.setString(3, QuestionText);
            ps.setInt(4, QuestionTypeID);

            ps.execute();//executes the prepared statements
        } catch (Exception exception) { //if the parameters don't work or an database error
            System.out.println("Database Error:" + exception.getMessage());
        }
    }
    //update timer
        public static void updateTimer(int Timer, int QuestionID){
            try{
                PreparedStatement ps = Main.db.prepareStatement("UPDATE QUESTION Set Timer = ? Where QuestionID=?");//update SQL query
                ps.setInt(1,Timer);
                ps.setInt(2, QuestionID);

                ps.execute();//executes the prepared statements

            }catch (Exception exception){
                System.out.println("Database Error:");//If SQL query doesn't work outputs this message
            }
        }
        //update text
    public static void updateText(String QuestionText, int QuestionID){
        try{
            PreparedStatement ps = Main.db.prepareStatement("UPDATE QUESTION Set QuestionText = ? Where QuestionID=?");//update SQL query
            ps.setString(1,QuestionText);
            ps.setInt(2, QuestionID);

            ps.execute();//executes the prepared statements

        }catch (Exception exception){
            System.out.println("Database Error:");//If SQL query doesn't work outputs this message
        }
    }
    //select question
    public static void SelectQuestion( int QuizID){//It will calls for these parameters
        try{
            PreparedStatement ps = Main.db.prepareStatement("SELECT QuestionID, Timer, QuestionText, QuestionTypeID From QUESTION Where QuizID=? ");
            ps.setInt(1,QuizID);
            ResultSet results = ps.executeQuery();
            while (results.next()) {
                int QuestionID = results.getInt(1);
                int Timer = results.getInt(2);
                String QuestionText = results.getString(3);
                int QuestionTypeID = results.getInt(4);
                System.out.print("QuestionID: " + QuestionID +" , ");
                System.out.print("Timer: " + Timer +" , ");
                System.out.print("QuestionText: " + QuestionText +" , ");
                System.out.print("QuestionTypeID: " + QuestionTypeID +" , ");

            }




            ps.execute();//executes the prepared statements
        }catch (Exception exception){ //if the parameters don't work or an database error
            System.out.println("Database Error:"+exception.getMessage());
        }
    }
    // Delete using QuizID
    public static void deleteQuestion1(int QuizID){
        try{
            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Question WHERE QuizID =?");//SQL Statement saying to delete
            ps.setInt(1,QuizID);//TheUserId to remove
            ps.execute();//executes

        }catch (Exception exception){
            System.out.println("Database Error:");//if there is no Username with that value
        }
    }
    //Delete quiz using questionID
    public static void deleteQuestion2(int QuestionID){
        try{
            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Question WHERE QuestionID =?");//SQL Statement saying to delete
            ps.setInt(1,QuestionID);//TheUserId to remove
            ps.execute();//executes

        }catch (Exception exception){
            System.out.println("Database Error:");//if there is no Username with that value
        }
    }

    }

