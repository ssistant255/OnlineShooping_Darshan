package shopping;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.getWriter().println("<!DOCTYPE html>");
        resp.getWriter().println("<html>");
        resp.getWriter().println("<head>");
        resp.getWriter().println("<title>Background Image</title>");
        resp.getWriter().println("<style>");
        resp.getWriter().println("body {");
        resp.getWriter().println("    background-image:url('https://fonts.googleapis.com/css?family=Poppins:100,200,300,400,500,600,700,800,900&display=swap');");
        resp.getWriter().println("    background-size: cover;");
        resp.getWriter().println("}");
        resp.getWriter().println("form {");
        resp.getWriter().println("background-color: green");
        resp.getWriter().println("text-align: center;");
        resp.getWriter().println("}");
        resp.getWriter().println("</style>");
        resp.getWriter().println("</head>");
        resp.getWriter().println("<body>");
        resp.getWriter().println("</body>");
        resp.getWriter().println("</html>");
        PrintWriter out=resp.getWriter();
        String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/online_shooping_mini_project", "root",
					"darshan3009");
			Statement st = conn.createStatement();
			String Query = "SELECT password FROM users WHERE email = ?";
			PreparedStatement p = conn.prepareStatement(Query);
			p.setString(1, email);
			//int i = p.executeUpdate();
            ResultSet rs = p.executeQuery();
			
			if (rs.next())
	  		{
	  			String nPassword = rs.getString("password");
	  			if (password.equals(nPassword)) 
	  			{
	  				out.println("Login Successful..");
	  			} 
	  			else 
	  			{
	  				out.println("Incorrect Password..");
	  				out.print("Sorry, email or password is invalid<br>");
	  	            RequestDispatcher rd = req.getRequestDispatcher("/login.html");
	  	            rd.include(req, resp);
	  			}
	  			} 
	  			else
		  		{
	  				out.println("User not found..");
		  		}
		}
		catch (Exception e)
		{
			e.getStackTrace();
		
		}
	}
}