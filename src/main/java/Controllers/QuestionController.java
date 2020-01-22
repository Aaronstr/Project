package Controllers;

import Server.Main;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Path("Question/")
public class QuestionController {
    //Question Create

    @POST
    @Path("Create")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String insertQuestion(@FormDataParam("QuizID") Integer QuizID, @FormDataParam("Timer") Integer Timer, @FormDataParam("QuestionText") String QuestionText, @FormDataParam("QuestionType") Integer QuestionType) {//It will calls for these parameters
        try {
            if (QuizID == null || Timer == null || QuestionText == null || QuestionType == null) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("quiz/Create id=" + QuizID);

            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO  QUESTION(QuizID, Timer, QuestionText, QuestionType)  VALUES (?,?,?,?)");//sql code to insert values into the fields
            ps.setInt(1, QuizID);
            ps.setInt(2, Timer);
            ps.setString(3, QuestionText);
            ps.setInt(4, QuestionType);
            ps.execute();//executes the prepared statements
            return "{\"status\": \"OK\"}"; //Tells ypu that it has  executed
        } catch (Exception exception) { //if the parameters don't work or an database error
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to create new item, please see server console for more info.\"}";
        }
    }


    //update timer

    @POST
    @Path("Update1")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateTimer(@FormDataParam("Timer") Integer Timer, @FormDataParam("QuestionID") Integer QuestionID) {

        try {
            if (Timer == null || QuestionID == null) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("thing/update QuestionID=" + QuestionID);
            PreparedStatement ps = Main.db.prepareStatement("UPDATE QUESTION Set Timer = ? Where QuestionID=?");//update SQL query
            ps.setInt(1, Timer);
            ps.setInt(2, QuestionID);
            ps.execute();//executes the prepared statements
            return "{\"status\": \"OK\"}";//Tells ypu that it has  executed
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to update item, please see server console for more info.\"}";//If SQL query doesn't work outputs this message
        }
    }

    //update text

    @POST
    @Path("Update2")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateText(@FormDataParam("QuestionText") String QuestionText, @FormDataParam("QuestionID") Integer QuestionID) {
        try {
            if (QuestionText == null || QuestionID == null) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("!uiz/update2 id=" + QuestionID);


            PreparedStatement ps = Main.db.prepareStatement("UPDATE QUESTION Set QuestionText = ? Where QuestionID=?");//update SQL query
            ps.setString(1, QuestionText);
            ps.setInt(2, QuestionID);
            ps.execute();//executes the prepared statements
            return "{\"status\": \"OK\"}";//Tells ypu that it has  executed
        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to update item, please see server console for more info.\"}";//If SQL query doesn't work outputs this message
        }
    }
    //select question

    @GET
    @Path("Select/{QuizID}")
    @Produces(MediaType.APPLICATION_JSON)
    public String SelectQuestion(@PathParam("QuizID") Integer QuizID) {//It will calls for these parameters
        JSONArray list = new JSONArray();
        try {
            if (QuizID == null) {
                throw new Exception("Thing's QuizID is missing in the HTTP request's URL.");
            }
            System.out.println("quiz/Select/" + QuizID);

            PreparedStatement ps = Main.db.prepareStatement("SELECT QuestionID, Timer, QuestionText, QuestionType From QUESTION Where QuizID=? ORDER BY QuestionID ASC");
            ps.setInt(1, QuizID);
            ResultSet results = ps.executeQuery();
            while (results.next()) {
                JSONObject item = new JSONObject();
                item.put("QuizID", QuizID);
                item.put("QuestionID", results.getInt(1));
                item.put("Timer", results.getInt(2));
                item.put("QuestionText", results.getString(3));
                item.put("QuestionType", results.getInt(4));
                list.add(item);
            }
            return list.toString();
        } catch (Exception exception) { //if the parameters don't work or an database error
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to get item, please see server console for more info.\"}";
        } //Database error message
    }

    // Delete using QuizID

    @POST
    @Path("Delete1")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteQuestion1(@FormDataParam("QuizID") Integer QuizID) {
        try {
            if (QuizID == null) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("quiz/delete1 QuizID=" + QuizID);

            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Question WHERE QuizID =?");//SQL Statement saying to delete
            ps.setInt(1, QuizID);//TheUserId to remove
            ps.execute();//executes

            return "{\"status\": \"OK\"}";// tells you it exuted

        } catch (Exception exception) {//lets you know about datbase errors
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to delete item, please see server console for more info.\"}";
        }
    }
    //Delete quiz using questionID
    @POST
    @Path("Delete2")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteQuestion2(@FormDataParam("QuestionID") Integer QuestionID) {
        try {
            if (QuestionID == null) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("quiz/delete2 QuestionID=" + QuestionID);
            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM Question WHERE QuestionID =?");//SQL Statement saying to delete
            ps.setInt(1, QuestionID);//TheUserId to remove
            ps.execute();//executes

            return "{\"status\": \"OK\"}";// tells you it exuted

        } catch (Exception exception) {//lets you know about datbase errors
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to delete item, please see server console for more info.\"}";
        }
    }

    }

