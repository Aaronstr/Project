import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class UserController {
    //User create
    public static void insertUSER(String Username, String Password){//It will calls for these parameters
        try{
            PreparedStatement ps = main.db.prepareStatement(
                    "INSERT INTO  USER(UserName, Password) VALUES (?,?)");//sql code to insert values into the fields
            ps.setString(1,Username);//prepared statement 1 is the value going to the first "?" it is th username
            ps.setString(2,Password);//prepared statement 2 is the value going to the second "?" therefore the playerIGN

            ps.execute();//executes the prepared statements
        }catch (Exception exception){ //if the parameters don't work or an database error
            System.out.println("Database Error:"+exception.getMessage());
        }
    }
    //Update Username
   // public static void updateUsername(String Username,String OldUsername){ //update username in user
     //   try{
         //   PreparedStatement ps = main.db.prepareStatement("UPDATE USER Set Username = ? Where OldUsername=?");//update SQL query
         //   ps.setString(1,Username);//prepared statement updates Username to what is requested
          //  ps.setString(2,OldUsername);

         //   ps.execute();//executes the prepared statements

     //   }catch (Exception exception){
      //      System.out.println("Database Error:");//If SQL query doesn't work outputs this message
     //   }
  //  }
    //Update Password
    public static void updatePassword(String Password,String Username){ //update username in user
        try{
            PreparedStatement ps = main.db.prepareStatement("UPDATE USER Set Password = ? Where Username=?");//update SQL query
            ps.setString(1,Password);//prepared statement updates Username to what is requested
            ps.setString(2,Username);

            ps.execute();//executes the prepared statements

        }catch (Exception exception){
            System.out.println("Database Error:");//If SQL query doesn't work outputs this message
        }
    }
    // select user
    public static void SelectUser( String Username){//It will calls for these parameters
        try{
            PreparedStatement ps = main.db.prepareStatement("Select  Password, Token From USER Where Username = ?  ");
            ps.setString(1,Username);
            ResultSet results = ps.executeQuery();
            while (results.next()) {
                String Password = results.getString(1);
                String Token = results.getString(2);
                System.out.print("password: " + Password +" , ");
                System.out.print("Token: " + Token +" , ");
            }
            ps.execute();//executes the prepared statements
        }catch (Exception exception){ //if the parameters don't work or an database error
            System.out.println("Database Error:"+exception.getMessage());
        }
    }
    public static void deleteUser(String Username){//delete player method
        try{
            PreparedStatement ps = main.db.prepareStatement("DELETE FROM USER WHERE Username =?");//SQL Statement saying to delete everything with the Username
            ps.setString(1,Username);//TheUserId to remove
            ps.execute();//executes

        }catch (Exception exception){
            System.out.println("Database Error:");
        }
    }

}




