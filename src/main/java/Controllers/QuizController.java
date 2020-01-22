package Controllers;

import Server.Main;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Path("Quiz/")
public class QuizController {


    // create quiz
    @POST
    @Path("Create")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String insertQUIZ
    (@FormDataParam("Username") String Username,@FormDataParam("QuizName") String QuizName){//It will calls for these parameters
        try{
            if (Username == null || QuizName == null ) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            // checks if empty
            System.out.println("Quiz/new Quiz=" + QuizName);
            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO  QUIZ(UserName, QuizName) VALUES  (?,?)");//sql code to insert values into the fields
            ps.setString(1,Username);
            ps.setString(2,QuizName);
            ps.execute();//executes the prepared statements
            return "{\"status\": \"OK\"}";
        }catch (Exception exception){ //if the parameters don't work or an database error
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to create new item, please see server console for more info.\"}";
            //Database error message
        }
    }

    //update quizname using quizID

    @POST
    @Path("Update")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String updatePassword(@FormDataParam("QuizName") String QuizName, @FormDataParam("QuizID") Integer QuizID ){
        try{
            if (QuizName == null || QuizID == null ) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            // checks if empty
            System.out.println("thing/update QuizID=" + QuizID);

            PreparedStatement ps = Main.db.prepareStatement("UPDATE Quiz Set QuizName = ? Where QuizID=?");//update SQL query
            ps.setString(1,QuizName);
            ps.setInt(2,QuizID);
            ps.execute();//executes the prepared statements
            return "{\"status\": \"OK\"}";


        }catch (Exception exception){
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to update item, please see server console for more info.\"}";
        } //Database error message
    }



    //Quiz Select with quizName

    @GET
    @Path("Select1/{QuizName}")
    @Produces(MediaType.APPLICATION_JSON)
    public String SelectQuiz1(@PathParam("QuizName") String QuizName) {//It will calls for these parameters//It will calls for these parameters
        try{
            if (QuizName == null) {
                throw new Exception("Thing's 'QuizName' is missing in the HTTP request's URL.");
            }
            // checks if empty
            System.out.println("Quiz/Select1/" + QuizName);
            JSONObject item = new JSONObject();
            PreparedStatement ps = Main.db.prepareStatement("Select QuizID, Username From QUIZ Where QuizName = ?  ");
            ps.setString(1, QuizName);
            ResultSet results = ps.executeQuery();
            if (results.next()) {
                item.put("QuizName", QuizName);
                item.put("QuizID", results.getString(1));
                item.put("Username", results.getString(2));
            }
            return item.toString();
        }catch (Exception exception){ //if the parameters don't work or an database error
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to get item, please see server console for more info.\"}";
        } //Database error message
    }


    //SelectWithUsername


    @GET
    @Path("Select2/{Username}")
    @Produces(MediaType.APPLICATION_JSON)
    public String SelectQuiz2(@PathParam("Username") String Username) {//It will calls for these parameters
        try{
            if (Username == null) {
                throw new Exception("Thing's 'Username' is missing in the HTTP request's URL.");
            }
            // checks if empty
            System.out.println("Quiz/Select2/" + Username);
            JSONArray list = new JSONArray();
            PreparedStatement ps = Main.db.prepareStatement("Select  QuizID, QuizName From QUIZ Where Username = ?  ");
            ps.setString(1,Username);
            ResultSet results = ps.executeQuery();
            while  (results.next()) {
                JSONObject item = new JSONObject();
                item.put("Username", Username);
                item.put("QuizID", results.getInt(1));
                item.put("QuizName", results.getString(2));
                list.add(item);
            }
            return list.toString();
        }catch (Exception exception){ //if the parameters don't work or an database error
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to list items, please see server console for more info.\"}";

        } //Database error message
    }


    //Select with QuizID


    @GET
    @Path("Select3/{QuizID}")
    @Produces(MediaType.APPLICATION_JSON)
    public String SelectQuiz3(@PathParam("QuizID") Integer QuizID) {//It will calls for these parameters
        try{
            if (QuizID == null) {
                throw new Exception("Thing's 'Username' is missing in the HTTP request's URL.");
            }
            // checks if empty
            System.out.println("Quiz/Select3/" + QuizID);
            JSONObject item = new JSONObject();
            PreparedStatement ps = Main.db.prepareStatement("Select  Username, QuizName From QUIZ Where QuizID = ?  ");
            ps.setInt(1,QuizID);
            ResultSet results = ps.executeQuery();
            if (results.next()) {
                item.put("QuizID", QuizID);
                item.put("Username", results.getString(1));
                item.put("QuizName", results.getString(2));
            }
            return item.toString();
        }catch (Exception exception){ //if the parameters don't work or an database error
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to get item, please see server console for more info.\"}";
        } //Database error message
    }



    //Select all quizzes


    @GET
    @Path("Select")
    @Produces(MediaType.APPLICATION_JSON)
    public String SelectQuiz(){
        System.out.println("Select/list");
        JSONArray list = new JSONArray();
        try{
            PreparedStatement ps = Main.db.prepareStatement("Select  Username, QuizName,QuizID From QUIZ   ");
            ResultSet results = ps.executeQuery();//executes the prepared statements
            while (results.next()) {
                JSONObject item = new JSONObject();
                item.put("Username", results.getString(1));
                item.put("QuizName", results.getString(2));
                item.put("QuizID", results.getInt(3));
                list.add(item);
            }
            return list.toString();
        }catch (Exception exception){ //if the parameters don't work or an database error
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to list items, please see server console for more info.\"}";
            //Database error message
        }
    }


    //Delete a quiz from its quizID


    @POST
    @Path("Delete")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteQuiz1(@FormDataParam("QuizID") Integer QuizID) { // id which is being deleted
        try{
            if (QuizID == null) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            // checks if empty
            System.out.println("Quiz/delete QuizID=" + QuizID);
            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Quiz WHERE QuizID=?");
            ps.setInt(1,QuizID);//TheUserId to remove
            ps.execute();//executes
            return "{\"status\": \"OK\"}";
        }catch (Exception exception){
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to delete item, please see server console for more info.\"}";
        }
        //Database error message
    }






}

