package resistation;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class Resistation extends HttpServlet {
	Connection con;
	public void init(ServletConfig config) throws ServletException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			 con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","PROJECT","PROJECT");
		}catch(Exception e) {
			System.out.println(e);
		}
	}

	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		try {
			String s1 = request.getParameter("fname");
			String s2 = request.getParameter("fmobileno");
			String s3 = request.getParameter("femail");
			String s4 = request.getParameter("flockclosed");
			
			
			PreparedStatement pstm = con.prepareStatement("INSERT INTO resistation VALUES(?,?,?,?)");
			pstm.setString(1, s1);
			pstm.setString(2, s2);
			pstm.setString(3, s3);
			pstm.setString(4, s4);
			
			
			pstm.executeUpdate();
			PrintWriter pw = response.getWriter();
			pw.println("<html><body bgcolor=red text=yellow><center>");
			pw.println("<h1>You registration has been don Successfully</h1>");
			pw.println("<h1><a href=\"TravlingForm.html\">Submit your Travling form... here</a></h1>");
			pw.println("<html><body>");
		
		}catch(Exception e) {
			PrintWriter pw = response.getWriter();
			pw.println("<html><body bgcolor=red text=yellow><center>");
			pw.println("<h1>You have allready registred...</h1>");
			System.out.println(e);
		}
	}

}
