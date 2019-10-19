import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class QuizController {
    // create quiz
    public static void insertQuiz(String Username, String QuizName){//It will calls for these parameters
        try{
            PreparedStatement ps = main.db.prepareStatement("INSERT INTO  QUIZ(UserName, QuizName) VALUES  (?,?)");//sql code to insert values into the fields
            ps.setString(1,Username);
            ps.setString(2,QuizName);

            ps.execute();//executes the prepared statements
        }catch (Exception exception){ //if the parameters don't work or an database error
            System.out.println("Database Error:"+exception.getMessage());
        }
    }
    public static void updateQuiz(String QuizName, int QuizID){ //update username in user
        try{
            PreparedStatement ps = main.db.prepareStatement("UPDATE Quiz Set QuizName = ? Where QuizID=?");//update SQL query
            ps.setString(1,QuizName);
            ps.setInt(2,QuizID);

            ps.execute();//executes the prepared statements

        }catch (Exception exception){
            System.out.println("Database Error:");//If SQL query doesn't work outputs this message
        }
    }
  //Quiz Select with quizName
  public static void SelectQuiz1( String QuizName) {//It will calls for these parameters
      try {
          PreparedStatement ps = main.db.prepareStatement("Select QuizID, Username From QUIZ Where QuizName = ?  ");
          ps.setString(1, QuizName);
          ResultSet results = ps.executeQuery();
          while (results.next()) {
              int QuizID = results.getInt(1);
              String Username = results.getString(2);
              System.out.print("password: " + QuizID +" , ");
              System.out.print("Token: " + Username +" , ");
          }


          ps.execute();//executes the prepared statements
      } catch (Exception exception) { //if the parameters don't work or an database error
          System.out.println("Database Error:" + exception.getMessage());
      }
  }
  //SelectWithUsername
    public static void SelectQuiz2( String Username){//It will calls for these parameters
        try{
            PreparedStatement ps = main.db.prepareStatement("Select  QuizID, QuizName From QUIZ Where Username = ?  ");
            ps.setString(1,Username);
           ResultSet results = ps.executeQuery();
           while (results.next()) {
                int QuizID = results.getInt(1);
                String QuizName = results.getString(2);
                System.out.print("QuizID: " + QuizID +" , ");
                System.out.print("QuizName: " + QuizName +" , ");
           }




           ps.execute();//executes the prepared statements
        }catch (Exception exception){ //if the parameters don't work or an database error
            System.out.println("Database Error:"+exception.getMessage());
        }
    }
    //Select with QuizID
    public static void SelectQuiz3( int QuizID){//It will calls for these parameters
        try{
            PreparedStatement ps = main.db.prepareStatement("Select  Username, QuizName From QUIZ Where QuizID = ?  ");
            ps.setInt(1,QuizID);
            ResultSet results = ps.executeQuery();
            while (results.next()) {
                String Username = results.getString(1);
                String QuizName = results.getString(2);
                System.out.print("Username: " + Username +" , ");
                System.out.print("QuizName: " + QuizName +" , ");
            }




            ps.execute();//executes the prepared statements
        }catch (Exception exception){ //if the parameters don't work or an database error
            System.out.println("Database Error:"+exception.getMessage());
        }
    }
    //Select all quizzes
    public static void SelectQuiz4(){//It will calls for these parameters
        try{
            PreparedStatement ps = main.db.prepareStatement("Select  Username, QuizName,QuizID From QUIZ   ");
            ResultSet results = ps.executeQuery();
            while (results.next()) {
                String Username = results.getString(1);
                String QuizName = results.getString(2);
                String QuizID = results.getString(3);
                System.out.print("Username: " + Username +" , ");
                System.out.print("QuizName: " + QuizName +" , ");
                System.out.print("QuizID: " + QuizID +" , ");
            }




            ps.execute();//executes the prepared statements
        }catch (Exception exception){ //if the parameters don't work or an database error
            System.out.println("Database Error:"+exception.getMessage());
        }
    }
    //Deete a quiz from its quizID
    public static void deleteQuiz(int QuizID){//delete  method
        try{
            PreparedStatement ps = main.db.prepareStatement("DELETE FROM Quiz WHERE QuizID=?");
            ps.setInt(1,QuizID);//TheUserId to remove
            ps.execute();//executes

        }catch (Exception exception){
            System.out.println("Database Error:");
        }
    }
}
