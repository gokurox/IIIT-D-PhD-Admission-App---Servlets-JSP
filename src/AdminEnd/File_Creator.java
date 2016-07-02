package AdminEnd;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Global.MySQL;

/**
 * Servlet implementation class PDF_Creator
 */

@WebServlet("/PDF_Creator")
public class File_Creator extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/* *********************************************************************************************/
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public File_Creator() {
        super();
    }

    /* *********************************************************************************************/
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Statement SQLstaement = null;
		ResultSet SQLresultset = null;
		ResultSetMetaData SQLmeta = null;
		String sqlQuery;
		String enrollmentNumber = request.getParameter ("enrol");
		
		MySQL.connect();
		
		try
		{
			sqlQuery = "SELECT * FROM " + MySQL.getSQLtable() + " WHERE ENROLLMENT_NUMBER = \"" + enrollmentNumber + "\"";  
			SQLstaement = MySQL.getStatement();
			SQLresultset = SQLstaement.executeQuery (sqlQuery);
			SQLmeta = SQLresultset.getMetaData();
			
			if (SQLresultset.next())
			{
				PrintWriter out = response.getWriter();
				
				out.println ("<!DOCTYPE html>");
				out.println ("<html>");
				out.println ("  <head>");
				out.println ("    <title>  APPLICATION DETAILS  </title>");
				out.println ("    <link href=\"./css/bootstrap.min.css\" rel=\"stylesheet\">");
				out.println ("  </head>");
				out.println ("  <body>");
				out.println ("    <h1><center> Application Details: " + enrollmentNumber.toUpperCase() + " </center></h1>");
				out.println ("    <br />");
				out.println ("    <table class=\"table table-hover table-responsive\">");
				out.println ("    <tr>");
				out.println ("      <th><center> Attribute </center></th>");
				out.println ("      <th><center> Value </center></th>");
				out.println ("    </tr>");
				
				for (int i = 1; i <= SQLmeta.getColumnCount(); i++)
				{
					out.println ("    <tr>");
					out.println ("      <td>" + SQLmeta.getColumnName (i) + "</td>");
					out.println ("      <td>" + SQLresultset.getString(i) + "</td>");
					out.println ("    </tr>");
				}
				
				out.println ("    <table>");
				out.println ("  </body>");
				out.println ("</html>");
				
			}
		}
		catch (SQLException se) {
			se.printStackTrace();
		}
		
		MySQL.disconnect();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
