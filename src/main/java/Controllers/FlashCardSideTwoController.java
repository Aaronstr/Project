package Controllers;

import Server.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class FlashCardSideTwoController {
    //Create FlashCardSideTwo
    public static void insertFlashCardSideTwo(int FlashCardSetID, String Text){//It will calls for these parameters
        try{
            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO  FlashCardSIDETwo( FlashCardSetID, Text)  VALUES (?,?)");//sql code to insert values into the fields
            ps.setInt(1,FlashCardSetID);
            ps.setString(2,Text);

            ps.execute();//executes the prepared statements
        }catch (Exception exception){ //if the parameters don't work or an database error
            System.out.println("Database Error:"+exception.getMessage());
        }
    }
    // update sideTwo
    public static void updateSideTwo(int SideTwoID,String Text){

        try{
            PreparedStatement ps = Main.db.prepareStatement("UPDATE FlashCardSIDETwo Set Text= ? Where SideTwoID=?");
            ps.setString(1,Text);
            ps.setInt(2,SideTwoID);
            ps.execute();//executes the prepared statements

        }catch (Exception exception){
            System.out.println("Database Error:");//If SQL query doesn't work outputs this message
        }
    }
    //Select using FlashCardSetID
    public static void SelectFlashCardSideTwo1( int FlashCardSetID){//It will calls for these parameters
        try{
            PreparedStatement ps = Main.db.prepareStatement("SELECT SideTwoID, Text From FlashCardSIDETwo Where FlashCardSetID=? ");
            ps.setInt(1,FlashCardSetID);
            ResultSet results = ps.executeQuery();
            while (results.next()) {
                int SideTwoID = results.getInt(1);
                String Text = results.getString(2);
                System.out.print("SideTwoID: " + SideTwoID +" , ");
                System.out.print("Text: " + Text +" , ");
            }
            ps.execute();//executes the prepared statements
        }catch (Exception exception){ //if the parameters don't work or an database error
            System.out.println("Database Error:"+exception.getMessage());
        }
    }
    //Select using SideTwoID
    public static void SelectFlashCardSideTwo2( int SideTwoID){//It will calls for these parameters
        try{
            PreparedStatement ps = Main.db.prepareStatement("SELECT FlashCardSetID, Text From FlashCardSIDETwo Where SideTwoID=? ");
            ps.setInt(1,SideTwoID);
            ResultSet results = ps.executeQuery();
            while (results.next()) {
                int FlashCardSetID = results.getInt(1);
                String Text = results.getString(2);
                System.out.print("FlashCardSetID: " + FlashCardSetID +" , ");
                System.out.print("Text: " + Text +" , ");
            }
            ps.execute();//executes the prepared statements
        }catch (Exception exception){ //if the parameters don't work or an database error
            System.out.println("Database Error:"+exception.getMessage());
        }
    }
    //Delete FlashCardSideTwo with  flashCardSetID
    public static void deleteFlashSideOne1(int FlashCardSetID){
        try{
            PreparedStatement ps = Main.db.prepareStatement("DELETE from FlashCardSIDETwo Where FlashCardSetID=?");
            ps.setInt(1,FlashCardSetID);
            ps.execute();//executes

        }catch (Exception exception){
            System.out.println("Database Error:");
        }
    }
    public static void deleteFlashSideTwo2(int SideTwoID){//delete player method
        try{
            PreparedStatement ps = Main.db.prepareStatement("DELETE from FlashCardSIDETwo Where SideTwoID=?");
            ps.setInt(1,SideTwoID);
            ps.execute();//executes

        }catch (Exception exception){
            System.out.println("Database Error:");
        }
    }
}
