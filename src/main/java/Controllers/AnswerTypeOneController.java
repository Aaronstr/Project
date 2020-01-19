package Controllers;
import Server.Main;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
@Path("AnswerTypeOne/")

public class AnswerTypeOneController {
    //Answer creator for typing
    @POST
    @Path("Create")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String insertAnswer(
            @FormDataParam("QuestionID") Integer QuestionID, @FormDataParam("CorrectAnswerText") String CorrectAnswerText) { //It will calls for these parameters
        try {
            if (QuestionID == null || CorrectAnswerText == null) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("thing/new id=" + QuestionID);
            PreparedStatement ps = Main.db.prepareStatement("INSERT INTO ANSWERTYPEONE( QuestionID, CorrectAnswerText) VALUES (?,?)");
            ps.setInt(1, QuestionID);
            ps.setString(2, CorrectAnswerText);
            ps.execute();//executes the prepared statements
            return "{\"status\": \"OK\"}";
        } catch (Exception exception) { //if the parameters don't work or an database error
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to create new item, please see server console for more info.\"}";

        }
    }

    //update the answer
    @POST
    @Path("Update")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateCorrectAnswerText(
            @FormDataParam("CorrectAnswerText") String CorrectAnswerText, @FormDataParam("QuestionID") Integer QuestionID) {
        try {
            if (CorrectAnswerText == null || QuestionID == null) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("thing/update id=" + QuestionID);

            PreparedStatement ps = Main.db.prepareStatement("UPDATE ANSWERTYPEONE Set  CorrectAnswerText = ? Where QuestionID=?");//update SQL query
            ps.setString(1, CorrectAnswerText);
            ps.setInt(2, QuestionID);
            ps.execute();//executes the prepared statements
            return "{\"status\": \"OK\"}";

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to update item, please see server console for more info.\"}";
//If SQL query doesn't work outputs this message
        }
    }

    // select the answer
    @GET
    @Path("Select/{QuestionID}")
    @Produces(MediaType.APPLICATION_JSON)
    public String SelectAnswer(@PathParam("QuestionID") Integer QuestionID) {//It will calls for these parameters
        try {
            if (QuestionID == null) {
                throw new Exception("Thing's 'QuestionID' is missing in the HTTP request's URL.");
            }
            System.out.println("thing/get/" + QuestionID);
            JSONObject item = new JSONObject();
            PreparedStatement ps = Main.db.prepareStatement("SELECT AnswerID, CorrectAnswerText From ANSWERTYPEONE Where QuestionID=?  ");
            ps.setInt(1, QuestionID);
            ResultSet results = ps.executeQuery();
            if (results.next()) {
                item.put("QuestionID", QuestionID);
                item.put("AnswerID", results.getInt(1));
                item.put("CorrectAnswerText", results.getString(2));

            }
            return item.toString();
            // ps.execute();//executes the prepared statements
        } catch (Exception exception) { //if the parameters don't work or an database error
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to get item, please see server console for more info.\"}";

        }
    }

    //Delete answer using answerID
    @POST
    @Path("Delete1")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteAnswer1(@FormDataParam("AnswerID") Integer AnswerID) {
        try {
            if (AnswerID == null) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("thing/delete AnswerID=" + AnswerID);

            PreparedStatement ps = Main.db.prepareStatement("DELETE from ANSWERTYPEONE Where AnswerID=?");//SQL Statement saying to delete everything
            ps.setInt(1, AnswerID);
            ps.execute();//executes
            return "{\"status\": \"OK\"}";

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to delete item, please see server console for more info.\"}";

        }
    }

    //Delete answer using QuestionID
    @POST
    @Path("Delete2")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteAnswer2(@FormDataParam("QuestionID") Integer QuestionID) {
        try {
            if (QuestionID == null) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("thing/delete QuestionID=" + QuestionID);
            PreparedStatement ps = Main.db.prepareStatement("DELETE from ANSWERTYPEONE Where QuestionID=?");//SQL Statement saying to delete everything
            ps.setInt(1, QuestionID);
            ps.execute();//executes
            return "{\"status\": \"OK\"}";

        } catch (Exception exception) {
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to delete item, please see server console for more info.\"}";

        }
    }


}