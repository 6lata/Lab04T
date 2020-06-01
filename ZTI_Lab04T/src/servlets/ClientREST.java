package servlets;
 
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
 
/**
 * Servlet implementation class ClientREST
 */
@WebServlet("/ClientREST")
public class ClientREST extends HttpServlet {
    private static final long serialVersionUID = 1L;
        
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientREST() {
        super();
        // TODO Auto-generated constructor stub
    }
 
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        //response.getWriter().append("Served at: ").append(request.getContextPath());
        Client client = ClientBuilder.newClient();
        String url = "http://localhost:8081/ZTI_Lab04T/rest/jpa/person/";       
        resp.setContentType("application/json");
        Response res ;
        // WebTarget target;
        if (req.getParameterMap().containsKey("id")) {
            //String id = req.getParameterValues("id")[0] ;
            String id = req.getParameter("id");
            res = client.target(url).path("/{id}").resolveTemplate("id", id).request("application/json").get();
        } else {
            res = client.target(url).request("application/json").get();
        }   
        resp.getWriter().append(res.readEntity(String.class));
    }
 
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
 
}