package Controllers;
import Server.Main;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
@Path("AnswerTypeTwo/")
public class AnswerTypeTwoController {
    //Answer(Type2) create
    @POST
    @Path("Create")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String insertAnswer2(@FormDataParam("QuestionID") Integer QuestionID, @FormDataParam("CorrectAnswerText") String CorrectAnswerText, @FormDataParam("WrongAnswerText1") String WrongAnswerText1, @FormDataParam("WrongAnswerText2") String WrongAnswerText2 , @FormDataParam("WrongAnswerText3") String WrongAnswerText3 ){
        try{
            if (QuestionID == null || CorrectAnswerText == null || WrongAnswerText1 == null || WrongAnswerText2 == null || WrongAnswerText3 == null  ) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("thing/new QuestionID=" + QuestionID);

            PreparedStatement ps = Main.db.prepareStatement(
                    " INSERT INTO  ANSWERTYPETWO( QuestionID, CorrectAnswerText, WrongAnswerText1, WrongAnswerText2, WrongAnswerText3) VALUES (?,?,?,?,?)");//sql code to insert values into the fields
            ps.setInt(1,QuestionID);
            ps.setString(2,CorrectAnswerText);
            ps.setString(3,WrongAnswerText1);
            ps.setString(4,WrongAnswerText2);
            ps.setString(5,WrongAnswerText3);
            ps.execute();//executes the prepared statements
            return "{\"status\": \"OK\"}";

        }catch (Exception exception){ //if the parameters don't work or an database error
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to create new item, please see server console for more info.\"}";
        }
    }



    //Update correct Answer
    @POST
    @Path("Update")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String  updateCorrectAnswer(@FormDataParam("CorrectAnswerText") String CorrectAnswerText, @FormDataParam("QuestionID") Integer QuestionID){
        try{
            if (CorrectAnswerText == null || QuestionID == null ) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("thing/update QuestionID=" + QuestionID);

            PreparedStatement ps = Main.db.prepareStatement("UPDATE ANSWERTYPETWO Set CorrectAnswerText= ? Where QuestionID=?");//update SQL query
            ps.setString(1,CorrectAnswerText);
            ps.setInt(2,QuestionID);

            ps.execute();//executes the prepared statements
            return "{\"status\": \"OK\"}";

        }catch (Exception exception){
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to update item, please see server console for more info.\"}";
            //If SQL query doesn't work outputs this message
        }
    }

    //Update correct wrongAnswer1
    @POST
    @Path("Update1")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateWrongAnswer1(@FormDataParam("WrongAnswerText1") String WrongAnswerText1, @FormDataParam("QuestionID") Integer QuestionID){
        try{
            if (WrongAnswerText1 == null || QuestionID == null ) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("thing/update QuestionID=" + QuestionID);

            PreparedStatement ps = Main.db.prepareStatement("UPDATE ANSWERTYPETWO Set WrongAnswerText1= ? Where QuestionID=?");//update SQL query
            ps.setString(1,WrongAnswerText1);
            ps.setInt(2,QuestionID);

            ps.execute();//executes the prepared statements
            return "{\"status\": \"OK\"}";

        }catch (Exception exception){
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to update item, please see server console for more info.\"}";
//If SQL query doesn't work outputs this message
        }
    }
    //Update correct wrongAnswer2
    @POST
    @Path("Update2")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateWrongAnswer2(@FormDataParam("WrongAnswerText2") String WrongAnswerText2, @FormDataParam("QuestionID") Integer QuestionID){
        try{
            if (WrongAnswerText2 == null || QuestionID == null ) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("thing/update QuestionID=" + QuestionID);
            PreparedStatement ps = Main.db.prepareStatement("UPDATE ANSWERTYPETWO Set WrongAnswerText2= ? Where QuestionID=?");//update SQL query
            ps.setString(1,WrongAnswerText2);
            ps.setInt(2,QuestionID);

            ps.execute();//executes the prepared statements
            return "{\"status\": \"OK\"}";
        }catch (Exception exception){
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to update item, please see server console for more info.\"}";//If SQL query doesn't work outputs this message
        }
    }
    //Update correct wrongAnswer3
    @POST
    @Path("Update3")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateWrongAnswer3(@FormDataParam("WrongAnswerText3") String WrongAnswerText3, @FormDataParam("QuestionID") Integer QuestionID){
        try{
            if (WrongAnswerText3 == null || QuestionID == null ) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("thing/update QuestionID=" + QuestionID);
            PreparedStatement ps = Main.db.prepareStatement("UPDATE ANSWERTYPETWO Set WrongAnswerText3= ? Where QuestionID=?");//update SQL query
            ps.setString(1,WrongAnswerText3);
            ps.setInt(2,QuestionID);

            ps.execute();//executes the prepared statements
            return "{\"status\": \"OK\"}";
        }catch (Exception exception){
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to update item, please see server console for more info.\"}";//If SQL query doesn't work outputs this message
        }
    }
    //Select Answer
    @GET
    @Path("Select/{QuestionID}")
    @Produces(MediaType.APPLICATION_JSON)
    public String SelectAnswer2(@PathParam("QuestionID") Integer QuestionID) {//It will calls for these parameters
        try{
            if (QuestionID == null) {
                throw new Exception("Thing's 'id' is missing in the HTTP request's URL.");
            }
            System.out.println("thing/get/" + QuestionID);
            JSONObject item = new JSONObject();

            PreparedStatement ps = Main.db.prepareStatement("SELECT AnswerID,CorrectAnswerText, WrongAnswerText1, WrongAnswerText2, WrongAnswerText3 From AnswerTypeTwo Where QuestionID=?  ");
            ps.setInt(1,QuestionID);
            ResultSet results = ps.executeQuery();
            if (results.next()) {
                item.put("QuestionID", QuestionID);
                item.put("AnswerID", results.getInt(1));
                item.put("CorrectAnswerText", results.getString(2));
                item.put("WrongAnswerText1", results.getString(3));
                item.put("WrongAnswerText2", results.getString(4));
                item.put("WrongAnswerText3", results.getString(5));

            }
            return item.toString();
        }catch (Exception exception){ //if the parameters don't work or an database error
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to get item, please see server console for more info.\"}";

        }
    }

    //Delete with QuestionID
    @POST
    @Path("Delete1")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteUser1(@FormDataParam("QuestionID") Integer  QuestionID){//delete player method
        try{
            if (QuestionID == null) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("thing/delete QuestionID=" + QuestionID);

            PreparedStatement ps = Main.db.prepareStatement("DELETE from ANSWERTYPETWO Where QuestionID=?");
            ps.setInt(1,QuestionID);
            ps.execute();//executes
            return "{\"status\": \"OK\"}";

        }catch (Exception exception){
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to delete item, please see server console for more info.\"}";

        }
    }
    //Delete with AnswerID
    @POST
    @Path("Delete2")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteUser2(@FormDataParam("AnswerID") Integer AnswerID){//delete player method
        try{
            PreparedStatement ps = Main.db.prepareStatement("DELETE from ANSWERTYPETWO Where AnswerID=?");
            ps.setInt(1,AnswerID);
            ps.execute();//executes
            return "{\"status\": \"OK\"}";
        }catch (Exception exception){
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to delete item, please see server console for more info.\"}";
        }
    }
}

