import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class registerServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException,IOException {
		res.setContentType("text/html");
		String Name=req.getParameter("name");
		String Email=req.getParameter("email");
		String Password=req.getParameter("pass");
		String Contact=req.getParameter("contact");
	/* It provides the functionality to forward a request from a servlet to another resource 
		 * (such as a servlet, JSP file, or HTML file) on the server......*/
		RequestDispatcher dispatcher=null;
		/*  Scope Visibility  */
		try {
			String driver="com.mysql.cj.jdbc.Driver";
			String url="jdbc:mysql://localhost:3306/mywebsite";
			String username="root";
		 	String password="teja";
		 	/*Step-1*/
		 	Class.forName(driver);
		 	/*Step-2*/
		 	 Connection con=DriverManager.getConnection(url,username,password);
		 	/*Step-3*/
		 	String qry="insert into users(Name,Email,Password,Contact)values(?,?,?,?)";
		 	/*Step-4*/
			PreparedStatement stmt=con.prepareStatement(qry);
			stmt.setString(1,Name);
			stmt.setString(2,Email);
			stmt.setString(3,Password);
			stmt.setString(4,Contact);
			/*row count means Record is inserted or not*/
			int rowcount=stmt.executeUpdate();
			con.close();
			dispatcher=req.getRequestDispatcher("registration.jsp");
			if(rowcount>0) {
		/*Attribute: An attribute is a key-value pair stored in the request object. */
				req.setAttribute("status", "success");
			}
			else {
				req.setAttribute("status", "failed");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error"+e);
		}
	}
}
