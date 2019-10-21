package Controllers;
import Server.Main;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Path("User/")
public class UserController {

    //User create

    @POST
    @Path("Create")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String insertUSER
    (@FormDataParam("Username") String Username,@FormDataParam("Password") String Password){//It will calls for these parameters
        try{
            if (Username == null || Password == null ) { //ask steve about if there is a way to check for unique
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("thing/new Username=" + Username);// Ask Steve

            PreparedStatement ps = Main.db.prepareStatement(
                    "INSERT INTO  USER(UserName, Password) VALUES (?,?)");
            ps.setString(1,Username);
            ps.setString(2,Password);
            ps.execute();//executes the prepared statements
            return "{\"status\": \"OK\"}";
        }catch (Exception exception){ //if the parameters don't work or an database error
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to create new item, please see server console for more info.\"}";

        }
    }

    //Update Password
    @POST
    @Path("Update")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String updatePassword(@FormDataParam("Password") String Password, @FormDataParam("Username") String Username){ //update username in user
        try{
            if (Password == null || Username == null ) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("thing/update Username=" + Username);
            PreparedStatement ps = Main.db.prepareStatement("UPDATE USER Set Password = ? Where Username=?");//update SQL query
            ps.setString(1,Password);//prepared statement updates Username to what is requested
            ps.setString(2,Username);
            ps.execute();//executes the prepared statements
            return "{\"status\": \"OK\"}";


        }catch (Exception exception){
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to update item, please see server console for more info.\"}";

        }
    }


    // select user
    @GET
    @Path("Get/{Username}")
    @Produces(MediaType.APPLICATION_JSON)
    public String SelectUser(@PathParam("Username") String Username) {//It will calls for these parameters

        try{

        if (Username == null) {
            throw new Exception("Thing's 'id' is missing in the HTTP request's URL.");
        }
        System.out.println("User/get/" + Username);



            JSONObject item = new JSONObject();
            PreparedStatement ps = Main.db.prepareStatement("Select  Password, Token From USER Where Username = ?  ");
            ps.setString(1,Username);
            ResultSet results = ps.executeQuery();
            if (results.next()) {
                item.put("password", results.getString(1));
                item.put("Token", results.getString(2));
            }
            return item.toString();
        }catch (Exception exception){ //if the parameters don't work or an database error
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to get item, please see server console for more info.\"}";
        }
    }



    //Delete User
    @POST
    @Path("Delete")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteUser(@FormDataParam("Username") String Username){//delete player method
        try{
            if (Username == null) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("thing/delete Username=" + Username);
            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM USER WHERE Username =?");//SQL Statement saying to delete everything with the Username
            ps.setString(1,Username);//TheUserId to remove
            ps.execute();//executes
            return "{\"status\": \"OK\"}";
        }catch (Exception exception){
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to delete item, please see server console for more info.\"}";

        }
    }


}




