package Controllers;

import Server.main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class FlashCardSetController {
    //Create FlashCardSet
    public static void insertFlashCardSets(String Username, String FlashCardSetName){//It will calls for these parameters
        try{
            PreparedStatement ps = main.db.prepareStatement("INSERT INTO  FlashcardSets ( Username, FlashCardSetName) VALUES (?,?)");//sql code to insert values into the fields
            ps.setString(1,Username);
            ps.setString(2,FlashCardSetName);

            ps.execute();//executes the prepared statements
        }catch (Exception exception){ //if the parameters don't work or an database error
            System.out.println("Database Error:"+exception.getMessage());
        }
    }
    // update name using id
    public static void updateName1(int FlashCardSetID,String FlashCardSetName){ //update username in user

        try{
            PreparedStatement ps = main.db.prepareStatement("UPDATE FlashCardSets SET FlashCardSetName= ? Where FlashCardSetID=?");//update SQL queryps.setInt(1,FlashCardSetID);
            ps.setString(1,FlashCardSetName);
            ps.setInt(2,FlashCardSetID);
            ps.execute();//executes the prepared statements

        }catch (Exception exception){
            System.out.println("Database Error:");//If SQL query doesn't work outputs this message
        }
    }
    // update name using old name
    public static void updateName2(String OldFlashCardSetName,String FlashCardSetName){ //update username in user

        try{
            PreparedStatement ps = main.db.prepareStatement("UPDATE FlashCardSets SET FlashCardSetName= ? Where FlashCardSetName=?");//update SQL queryps.setInt(1,FlashCardSetID);
            ps.setString(1,FlashCardSetName);
            ps.setString(2,OldFlashCardSetName);
            ps.execute();//executes the prepared statements

        }catch (Exception exception){
            System.out.println("Database Error:");//If SQL query doesn't work outputs this message
        }
    }
    //Select using ID
    public static void SelectFlashCardSet1( int FlashCardSetID){//It will calls for these parameters
        try{
            PreparedStatement ps = main.db.prepareStatement("SELECT Username, FlashCardSetName From FLASHCARDSETS Where FlashCardSetID=?  ");
            ps.setInt(1,FlashCardSetID);
            ResultSet results = ps.executeQuery();
            while (results.next()) {
                String Username = results.getString(1);
                String FlashCardSetName = results.getString(2);
                System.out.print("Username: " + Username +" , ");
                System.out.print("FlashCardSetName: " + FlashCardSetName +" , ");
            }
            ps.execute();//executes the prepared statements
        }catch (Exception exception){ //if the parameters don't work or an database error
            System.out.println("Database Error:"+exception.getMessage());
        }
    }
    //Select using Name
    public static void SelectFlashCardSet2( String FlashCardSetName){//It will calls for these parameters
        try{
            PreparedStatement ps = main.db.prepareStatement("SELECT FlashCardSetID, Username From FLASHCARDSETs Where FlashCardSetName = ? ");
            ps.setString(1,FlashCardSetName);
            ResultSet results = ps.executeQuery();
            while (results.next()) {
                int FlashCardSetID = results.getInt(1);
                String Username = results.getString(2);
                System.out.print("Username: " + Username +" , ");
                System.out.print("FlashCardSetID: " + FlashCardSetID +" , ");
            }
            ps.execute();//executes the prepared statements
        }catch (Exception exception){ //if the parameters don't work or an database error
            System.out.println("Database Error:"+exception.getMessage());
        }
    }
    //Select using UserName
    public static void SelectFlashCardSet3( String Username){//It will calls for these parameters
        try{
            PreparedStatement ps = main.db.prepareStatement("SELECT FlashCardSetID, FlashCardSetName From FLASHCARDSETS Where Username=? ");
            ps.setString(1,Username);
            ResultSet results = ps.executeQuery();
            while (results.next()) {
                int FlashCardSetID = results.getInt(1);
                String FlashCardSetName = results.getString(2);
                System.out.print("FlashCardSetName: " + FlashCardSetName +" , ");
                System.out.print("FlashCardSetID: " + FlashCardSetID +" , ");
            }
            ps.execute();//executes the prepared statements
        }catch (Exception exception){ //if the parameters don't work or an database error
            System.out.println("Database Error:"+exception.getMessage());
        }
    }
    //Delete Flashcardset
    public static void deleteFlashCardSet(int FlashCardSetID){//delete player method
        try{
            PreparedStatement ps = main.db.prepareStatement("DELETE from FLASHCARDSETs Where FlashCardSetID=?");
            ps.setInt(1,FlashCardSetID);
            ps.execute();//executes

        }catch (Exception exception){
            System.out.println("Database Error:");
        }
    }

}
