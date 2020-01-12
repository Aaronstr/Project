package Controllers;
import Server.Main;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;
// This interacts with the user table
@Path("User/")
public class UserController {
    //user log in
    @POST
    @Path("login")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String loginUser(@FormDataParam("Username") String Username, @FormDataParam("Password") String Password) {

        try {

            PreparedStatement ps1 = Main.db.prepareStatement("SELECT Password FROM User WHERE Username = ?");
            ps1.setString(1, Username);
            ResultSet loginResults = ps1.executeQuery();
            if (loginResults.next()) {

                String correctPassword = loginResults.getString(1);

                if (Password.equals(correctPassword)) {

                    String Token = UUID.randomUUID().toString();

                    PreparedStatement ps2 = Main.db.prepareStatement("UPDATE User SET Token = ? WHERE Username = ?");
                    ps2.setString(1, Token);
                    ps2.setString(2, Username);
                    ps2.executeUpdate();

                    return "{\"token\": \""+ Token + "\"}";

                } else {

                    return "{\"error\": \"Incorrect password!\"}";

                }

            } else {

                return "{\"error\": \"Unknown user!\"}";

            }

        }catch (Exception exception){
            System.out.println("Database error during /user/login: " + exception.getMessage());
            return "{\"error\": \"Server side error!\"}";
        }


    }
    //logout
    @POST
    @Path("logout")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public String logoutUser(@CookieParam("token") String token) {

        try {

            System.out.println("user/logout");

            PreparedStatement ps1 = Main.db.prepareStatement("SELECT Username FROM Users WHERE Token = ?");
            ps1.setString(1, token);
            ResultSet logoutResults = ps1.executeQuery();
            if (logoutResults.next()) {

                int id = logoutResults.getInt(1);

                PreparedStatement ps2 = Main.db.prepareStatement("UPDATE Users SET Token = NULL WHERE Username = ?");
                ps2.setInt(1, id);
                ps2.executeUpdate();

                return "{\"status\": \"OK\"}";
            } else {

                return "{\"error\": \"Invalid token!\"}";

            }

        } catch (Exception exception){
            System.out.println("Database error during /user/logout: " + exception.getMessage());
            return "{\"error\": \"Server side error!\"}";
        }

    }

    public static boolean validToken(String token) {
        try {
            PreparedStatement ps = Main.db.prepareStatement("SELECT Username FROM Users WHERE Token = ?");
            ps.setString(1, token);
            ResultSet logoutResults = ps.executeQuery();
            return logoutResults.next();
        } catch (Exception exception) {
            System.out.println("Database error during /user/logout: " + exception.getMessage());
            return false;
        }
    }









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
    @Path("Select/{Username}")
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
    public String deleteUser(@FormDataParam("Username") String Username){//delete User method
        try{
            if (Username == null) {
                throw new Exception("One or more form data parameters are missing in the HTTP request.");
            }
            System.out.println("User/delete Username=" + Username);
            PreparedStatement ps = Main.db.prepareStatement("DELETE FROM USER WHERE Username =?");//SQL Statement saying to delete everything with the Username
            ps.setString(1,Username);//TheUser to remove
            ps.execute();//executes
            return "{\"status\": \"OK\"}";
        }catch (Exception exception){
            System.out.println("Database error: " + exception.getMessage());
            return "{\"error\": \"Unable to delete item, please see server console for more info.\"}";

        }
    }


}




