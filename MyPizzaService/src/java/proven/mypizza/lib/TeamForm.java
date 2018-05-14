package proven.mypizza.lib;
 
import javax.servlet.http.HttpServletRequest;
 
public class TeamForm {
 
    /**
     * gets and validates data sent by client.
     * @param request http request object to get data from
     * @return friend object with data sent from client or null in case of error.
     */
    /*public static Team getData(HttpServletRequest request) {
        Team team = null;
        try {
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            if ((id == null) || (name == null) || (description==null)) {
                team = null;
            } else {
                int idInt = Integer.parseInt(id);
                team = new Team(idInt,name,description);                
            }            
        } catch (NumberFormatException e) {
            team = null;
        }
        return team;
    }*/
 
}