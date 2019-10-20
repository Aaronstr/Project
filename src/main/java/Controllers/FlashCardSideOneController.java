package Controllers;

import Server.main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class FlashCardSideOneController {
    //Create FlashCardSideOne
    public static void insertFlashCardSideOne(int FlashCardSetID, String Text){//It will calls for these parameters
        try{
            PreparedStatement ps = main.db.prepareStatement("INSERT INTO  FlashCardSIDEONE( FlashCardSetID, Text)  VALUES (?,?)");//sql code to insert values into the fields
            ps.setInt(1,FlashCardSetID);
            ps.setString(2,Text);

            ps.execute();//executes the prepared statements
        }catch (Exception exception){ //if the parameters don't work or an database error
            System.out.println("Database Error:"+exception.getMessage());
        }
    }
    // update sideOne
    public static void updateSideOne(int SideOneID,String Text){

        try{
            PreparedStatement ps = main.db.prepareStatement("UPDATE FlashCardSIDEONE Set Text= ? Where SideOneID=?");
            ps.setString(1,Text);
            ps.setInt(2,SideOneID);
            ps.execute();//executes the prepared statements

        }catch (Exception exception){
            System.out.println("Database Error:");//If SQL query doesn't work outputs this message
        }
    }
    //Select using FlashCardSetID
    public static void SelectFlashCardSideOne1( int FlashCardSetID){//It will calls for these parameters
        try{
            PreparedStatement ps = main.db.prepareStatement("SELECT SideOneID, Text From FlashCardSIDEONE Where FlashCardSetID=? ");
            ps.setInt(1,FlashCardSetID);
            ResultSet results = ps.executeQuery();
            while (results.next()) {
                int SideOneID = results.getInt(1);
                String Text = results.getString(2);
                System.out.print("SideOneID: " + SideOneID +" , ");
                System.out.print("Text: " + Text +" , ");
            }
            ps.execute();//executes the prepared statements
        }catch (Exception exception){ //if the parameters don't work or an database error
            System.out.println("Database Error:"+exception.getMessage());
        }
    }
    //Select using SideOneID
    public static void SelectFlashCardSideOne2( int SideOneID){//It will calls for these parameters
        try{
            PreparedStatement ps = main.db.prepareStatement("SELECT FlashCardSetID, Text From FlashCardSIDEONE Where SideOneID=? ");
            ps.setInt(1,SideOneID);
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
    //Delete FlashCardSideOne with  flashCardSetID
    public static void deleteFlashSideOne1(int FlashCardSetID){//delete player method
        try{
            PreparedStatement ps = main.db.prepareStatement("DELETE from FlashCardSIDEONE Where FlashCardSetID=?");
            ps.setInt(1,FlashCardSetID);
            ps.execute();//executes

        }catch (Exception exception){
            System.out.println("Database Error:");
        }
    }
    public static void deleteFlashSideOne2(int SideOneID){//delete player method
        try{
            PreparedStatement ps = main.db.prepareStatement("DELETE from FlashCardSIDEONE Where SideOneID=?");
            ps.setInt(1,SideOneID);
            ps.execute();//executes

        }catch (Exception exception){
            System.out.println("Database Error:");
        }
    }




}
