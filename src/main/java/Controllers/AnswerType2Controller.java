package Controllers;

import Server.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class AnswerType2Controller {
    //Answer(Type2) create
    public static void insertAnswer2(int QuestionID, String CorrectAnswerText,String WrongAnswerText1,String WrongAnswerText2,String WrongAnswerText3){
        try{
            PreparedStatement ps = Main.db.prepareStatement(
                    " INSERT INTO  ANSWERTYPETWO( QuestionID, CorrectAnswerText, WrongAnswerText1, WrongAnswerText2, WrongAnswerText3) VALUES (?,?,?,?,?)");//sql code to insert values into the fields
            ps.setInt(1,QuestionID);
            ps.setString(2,CorrectAnswerText);
            ps.setString(3,WrongAnswerText1);
            ps.setString(4,WrongAnswerText2);
            ps.setString(5,WrongAnswerText3);
            ps.execute();//executes the prepared statements
        }catch (Exception exception){ //if the parameters don't work or an database error
            System.out.println("Database Error:"+exception.getMessage());
        }
    }
    //Update correct Answer
    public static void updateCorrectAnswer(String CorrectAnswerText,int QuestionID){
        try{
            PreparedStatement ps = Main.db.prepareStatement("UPDATE ANSWERTYPETWO Set CorrectAnswerText= ? Where QuestionID=?");//update SQL query
            ps.setString(1,CorrectAnswerText);
            ps.setInt(2,QuestionID);

            ps.execute();//executes the prepared statements

        }catch (Exception exception){
            System.out.println("Database Error:");//If SQL query doesn't work outputs this message
        }
    }
    //Update correct wrongAnswer1
    public static void updateWrongAnswer1(String WrongAnswerText1,int QuestionID){
        try{
            PreparedStatement ps = Main.db.prepareStatement("UPDATE ANSWERTYPETWO Set WrongAnswerText1= ? Where QuestionID=?");//update SQL query
            ps.setString(1,WrongAnswerText1);
            ps.setInt(2,QuestionID);

            ps.execute();//executes the prepared statements

        }catch (Exception exception){
            System.out.println("Database Error:");//If SQL query doesn't work outputs this message
        }
    }
    //Update correct wrongAnswer2
    public static void updateWrongAnswer2(String WrongAnswerText2,int QuestionID){
        try{
            PreparedStatement ps = Main.db.prepareStatement("UPDATE ANSWERTYPETWO Set WrongAnswerText2= ? Where QuestionID=?");//update SQL query
            ps.setString(1,WrongAnswerText2);
            ps.setInt(2,QuestionID);

            ps.execute();//executes the prepared statements

        }catch (Exception exception){
            System.out.println("Database Error:");//If SQL query doesn't work outputs this message
        }
    }
    //Update correct wrongAnswer3
    public static void updateWrongAnswer3(String WrongAnswerText3,int QuestionID){
        try{
            PreparedStatement ps = Main.db.prepareStatement("UPDATE ANSWERTYPETWO Set WrongAnswerText3= ? Where QuestionID=?");//update SQL query
            ps.setString(1,WrongAnswerText3);
            ps.setInt(2,QuestionID);

            ps.execute();//executes the prepared statements

        }catch (Exception exception){
            System.out.println("Database Error:");//If SQL query doesn't work outputs this message
        }
    }
    //Select Answer2
    public static void SelectAnswer2( int QuestionID){//It will calls for these parameters
        try{
            PreparedStatement ps = Main.db.prepareStatement("SELECT AnswerID,CorrectAnswerText, WrongAnswerText1, WrongAnswerText2, WrongAnswerText3 From AnswerTypeTwo Where QuestionID=?  ");
            ps.setInt(1,QuestionID);
            ResultSet results = ps.executeQuery();
            while (results.next()) {
                int AnswerID = results.getInt(1);
                String CorrectAnswerText = results.getString(2);
                String WrongAnswerText1 = results.getString(3);
                String WrongAnswerText2 = results.getString(4);
                String WrongAnswerText3 = results.getString(5);
                System.out.print("AnswerID: " + AnswerID +" , ");
                System.out.print("CorrectAnswerText: " + CorrectAnswerText +" , ");
                System.out.print("WrongAnswerText1: " + WrongAnswerText1 +" , ");
                System.out.print("WrongAnswerText2: " + WrongAnswerText2 +" , ");
                System.out.print("WrongAnswerText3: " + WrongAnswerText3 +" , ");
            }




            ps.execute();//executes the prepared statements
        }catch (Exception exception){ //if the parameters don't work or an database error
            System.out.println("Database Error:"+exception.getMessage());
        }
    }
    //Delete with QuestionID
    public static void deleteUser1(int QuestionID){//delete player method
        try{
            PreparedStatement ps = Main.db.prepareStatement("DELETE from ANSWERTYPETWO Where QuestionID=?");
            ps.setInt(1,QuestionID);
            ps.execute();//executes

        }catch (Exception exception){
            System.out.println("Database Error:");
        }
    }
    //Delete with AnswerID
    public static void deleteUser2(int AnswerID){//delete player method
        try{
            PreparedStatement ps = Main.db.prepareStatement("DELETE from ANSWERTYPETWO Where AnswerID=?");
            ps.setInt(1,AnswerID);
            ps.execute();//executes

        }catch (Exception exception){
            System.out.println("Database Error:");
        }
    }
}

