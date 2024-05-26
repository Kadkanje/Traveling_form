package travling;



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServlet;


public class Travling extends HttpServlet {
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
			String s2 = request.getParameter("fclassno");
			String s3 = request.getParameter("fmobileno");
			String s4 = request.getParameter("fpmobileno");
			String s5 = request.getParameter("faddress");
			String s6 = request.getParameter("femail");
			String s7 = request.getParameter("flockclosed");
			
			PreparedStatement pstm = con.prepareStatement("INSERT INTO TFORM VALUES(?,?,?,?,?,?,?)");
			pstm.setString(1, s1);
			pstm.setString(2, s2);
			pstm.setString(3, s3);
			pstm.setString(4, s4);
			pstm.setString(5, s5);
			pstm.setString(6, s6);
			pstm.setString(7, s7);
			
			pstm.executeUpdate();
			PrintWriter pw = response.getWriter();
			pw.println("<html><body bgcolor=red text=yellow><center>");
			pw.println("<h1>You travling form has been submitted Successfully</h1>");
			pw.println("<html><body>");
		
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}
