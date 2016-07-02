package AdminEnd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import Global.MySQL;

/**
 * @author Gursimran Singh
 * @email  gursimran14041@iiitd.ac.in
 * @rollno 2014041
 */

@WebServlet("/CSV_Importer")
@MultipartConfig (fileSizeThreshold = 1024*1024*2,
					maxFileSize = 1024*1024*10,
					maxRequestSize = 1024*1024*50)
public class CSV_Importer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final Logger LOG = Logger.getLogger (CSV_Importer.class.getName());
	
	private final String databaseDirectory = "D:\\IIIT-Delhi\\Courses\\Advanced_Programming\\BitBucket\\AP_CP_localRepo\\PhD Registration Form (Servlet)\\src\\temp DB directory";
	
	/* *********************************************************************************************/
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CSV_Importer() {
        super();
    }
    
    /* *********************************************************************************************/
    
    private void dataImporter_1 (String filepath, String filename) {
    	LOG.entering (CSV_Importer.class.getName(), "dataImporter_1");
    	
    	BufferedReader br = null;
    	Statement STM = null;
    	String line;
    	String[] brokenLine = null;
    	String sqlQuery = null;
    	String NA = "NULL", q = "\"", c = ",", qc = "\",";
    	
    	String[] data = new String[60];
    	
    	try
    	{
    		STM = MySQL.getStatement();
    		br = new BufferedReader (new FileReader (filepath + "\\" + filename));
    		
    		br.readLine();		// Skip the headers
    		
    		while ((line = br.readLine()) != null)
    		{
    			ArrayList<String> parsedData = new ArrayList<>();
    			int ind = 0;
    			brokenLine = line.split (",");
    			
    			// Handling the Addresses
    			for (int i = 0; i < brokenLine.length; i ++)
    			{
    				brokenLine[i] = brokenLine[i].trim();
    				
    				if (!brokenLine[i].isEmpty() && brokenLine[i].charAt(0) == '\"')
    				{
    					String TEMP;
    					TEMP = brokenLine[i].split("\"")[1];
    					
    					i ++;
    					brokenLine[i] = brokenLine[i].trim();
    					
    					while (brokenLine[i].charAt(brokenLine[i].length() -1) != '\"')
    					{
    						TEMP += brokenLine[i];
    						i ++;
    					}
    					
    					TEMP += brokenLine[i].split("\"")[0];
    					
    					parsedData.add (TEMP);
    				}
    				else
    					parsedData.add (brokenLine[i]);
    			}
    			
    			// Putting it in data[]
    			parsedData.toArray (data);
    			
//    			for (String TEMP: data)
//    				System.out.println (TEMP);
    			
    			// sqlQuery creation
    			sqlQuery = "INSERT INTO " + MySQL.getSQLtable() + " VALUES (";
    			
    			/* ** Actual Data Parsing ***********/
    			
    			// 0 - 10 varchar
    			for (; ind <= 10; ind ++)
    			{
    				if (data[ind].isEmpty())
    					sqlQuery += NA + c;
    				else
    					sqlQuery += q + data[ind] + qc;
    			}
    			
    			// 11 date
    			if (data[ind].isEmpty())
    				sqlQuery += NA + c;
    			else
    			{
    				String[] TEMP = data[ind].split("[/-]");
    				if (TEMP.length == 3)
    				{
    					data[ind] = TEMP[2] + "-" + TEMP[1] + "-" + TEMP[0];	// YYYY-MM-DD
    					sqlQuery += q + data[ind] + qc;
    				}
    				else
    					sqlQuery += NA + c;
    			}
    			ind ++;
    			
    			// 12 - 17 varchar
    			for (; ind <= 17; ind ++)
    			{
    				if (data[ind].isEmpty())
    					sqlQuery += NA + c;
    				else
    					sqlQuery += q + data[ind] + qc;
    			}
    			
    			// 18 - 19 numeric
    			for (; ind <= 19; ind ++)
    			{
    				if (data[ind].isEmpty())
    					sqlQuery += NA + c;
    				else
    					sqlQuery += data[ind] + c;
    			}
    			
    			// 20 varchar
    			if (data[ind].isEmpty())
					sqlQuery += NA + c;
				else
					sqlQuery += q + data[ind] + qc;
    			ind ++;
    			
    			// 21 - 22 numeric
    			for (; ind <= 22; ind ++)
    			{
    				if (data[ind].isEmpty())
    					sqlQuery += NA + c;
    				else
    					sqlQuery += data[ind] + c;
    			}
    			
    			// 23 - 28 varchar
    			for (; ind <= 28; ind ++)
    			{
    				if (data[ind].isEmpty())
    					sqlQuery += NA + c;
    				else
    					sqlQuery += q + data[ind] + qc;
    			}
    			
    			// 29 numeric
    			if (data[ind].isEmpty())
					sqlQuery += NA + c;
				else
					sqlQuery += data[ind] + c;
    			ind ++;
    			
    			// 30 CGPA/Marks
    			if (data[ind].isEmpty())
    			{
    				sqlQuery += NA + c;
    				sqlQuery += NA + c;
    				sqlQuery += NA + c;
    				sqlQuery += NA + c;
    			}
    			else
    			{
    				double Percent = -1;
    				String[] TEMP = data[ind].split (":");
    				
    				if (TEMP.length == 2)
    				{
    					sqlQuery += q + TEMP[0] + qc;
    					
    					if (TEMP[0].compareToIgnoreCase ("CGPA") == 0)
    					{
    						sqlQuery += TEMP[1] + c;
    						Percent = Double.parseDouble(TEMP[1]) * 10;		// Assuming CGPA out of 10
    					}
    					else
    						sqlQuery += NA + c;
    					
    					if (TEMP[0].compareToIgnoreCase ("Marks") == 0)
    					{
    						sqlQuery += TEMP[1] + c;
    						Percent = Double.parseDouble (TEMP[1]);			// Assuming Marks out of 100
    					}
    					else
    						sqlQuery += NA + c;
    					
    					if (Percent != -1)
    						sqlQuery += Percent + c;
    					else
    						sqlQuery += NA + c;
    				}
    				else
    				{
    					sqlQuery += NA + c;
        				sqlQuery += NA + c;
        				sqlQuery += NA + c;
        				sqlQuery += NA + c;
    				}
    			}
    			ind ++;
    			
    			// 31 - 42 varchar
    			for (; ind <= 42; ind ++)
    			{
    				if (data[ind].isEmpty())
    					sqlQuery += NA + c;
    				else
    					sqlQuery += q + data[ind] + qc;
    			}
    			
    			// 43 numeric
    			if (data[ind].isEmpty())
					sqlQuery += NA + c;
				else
					sqlQuery += data[ind] + c;
    			ind ++;
    			
    			// 44 CGPA/Marks
    			if (data[ind].isEmpty())
    			{
    				sqlQuery += NA + c;
    				sqlQuery += NA + c;
    				sqlQuery += NA + c;
    				sqlQuery += NA + c;
    			}
    			else
    			{
    				double Percent = -1;
    				String[] TEMP = data[ind].split (":");
    				
    				if (TEMP.length == 2)
    				{
    					sqlQuery += q + TEMP[0] + qc;
    					
    					if (TEMP[0].compareToIgnoreCase ("CGPA") == 0)
    					{
    						sqlQuery += TEMP[1] + c;
    						Percent = Double.parseDouble(TEMP[1]) * 10;		// Assuming CGPA out of 10
    					}
    					else
    						sqlQuery += NA + c;
    					
    					if (TEMP[0].compareToIgnoreCase ("Marks") == 0)
    					{
    						sqlQuery += TEMP[1] + c;
    						Percent = Double.parseDouble (TEMP[1]);			// Assuming Marks out of 100
    					}
    					else
    						sqlQuery += NA + c;
    					
    					if (Percent != -1)
    						sqlQuery += Percent + c;
    					else
    						sqlQuery += NA + c;
    				}
    				else
    				{
    					sqlQuery += NA + c;
        				sqlQuery += NA + c;
        				sqlQuery += NA + c;
        				sqlQuery += NA + c;
    				}
    			}
    			ind ++;
    			
    			// 45 - 47 varchar
    			for (; ind <= 47; ind ++)
    			{
    				if (data[ind].isEmpty())
    					sqlQuery += NA + c;
    				else
    					sqlQuery += q + data[ind] + qc;
    			}
    			
    			// 48 - 50 numeric
    			for (; ind <= 50; ind ++)
    			{
    				if (data[ind].isEmpty())
    					sqlQuery += NA + c;
    				else
    					sqlQuery += data[ind] + c;
    			}
    			
    			// 51 - 52 varchar
    			for (; ind <= 52; ind ++)
    			{
    				if (data[ind].isEmpty())
    					sqlQuery += NA + c;
    				else
    					sqlQuery += q + data[ind] + qc;
    			}
    			
    			// 53 - 56 numeric
    			for (; ind <= 56; ind ++)
    			{
    				if (data[ind].isEmpty())
    					sqlQuery += NA + c;
    				else
    					sqlQuery += data[ind] + c;
    			}
    			
    			// 57 varchar
    			if (data[ind].isEmpty())
					sqlQuery += NA + c;
				else
					sqlQuery += q + data[ind] + qc;
    			ind ++;
    			
    			// 58 timestamp
    			if (data[ind].isEmpty())
    				sqlQuery += NA + c;
    			else
    			{
    				String[] TEMP = data[ind].split(" ");
    				DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern ("uuuu-MMM-dd");
    				DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern ("uuuu-MM-dd");
    				
    				if (TEMP.length == 2)
    				{
    					LocalDate dt = LocalDate.parse (TEMP[0], dtf1);
    					dt.format (dtf2);
    					data[ind] = dt.toString() + " " + TEMP[1];
    					sqlQuery += q + data[ind] + qc;
    				}
    				else
    					sqlQuery += NA + c;
    			}
    			ind ++;
    			
    			// 59 varchar
    			if (data[ind].isEmpty())
					sqlQuery += NA;
				else
					sqlQuery += q + data[ind] + q;
    			ind ++;
 
    			sqlQuery += ")";
//    			System.out.println ("SQL_QUERY: " + sqlQuery);
    			
    			STM.executeUpdate (sqlQuery);
    		}
    		
    		LOG.log (Level.INFO, "DATA IMPORTED SUCCESSFULLY");
    		
    		br.close();
    		STM.close();
    	}
    	catch (FileNotFoundException fnfexc)
    	{
    		LOG.log (Level.SEVERE, "FILE NOT FOUND", fnfexc);
    	}
    	catch (SQLException se)
    	{
    		LOG.log (Level.SEVERE, "", se);
    	}
    	catch (Exception e)
    	{
    		LOG.log (Level.SEVERE, "", e);
    	}
    	
    	LOG.exiting (CSV_Importer.class.getName(), "dataImporter_1");
    }
    
    /* *********************************************************************************************/
    
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tempFileName = "tempDB.csv";
		
		File fileSaveDir = new File (databaseDirectory);
		if (!fileSaveDir.exists())
			fileSaveDir.mkdir();
		
		for (Part part: request.getParts()) {
			String fileName = extractFileName(part);
			part.write (databaseDirectory + File.separator + tempFileName);
		}
		
		File fileReference = new File (databaseDirectory + File.separator + tempFileName);
		
		MySQL.connect();
		
		MySQL.createTable_overwrite (MySQL.getSQLtable());
		dataImporter_1 (databaseDirectory, tempFileName);
		
		MySQL.disconnect();
		
		RequestDispatcher view = request.getRequestDispatcher("/Admin Homepage.html");
		view.forward (request, response);
		
		if (fileReference.exists())
			fileReference.delete();
		
		if (fileSaveDir.exists())
			fileSaveDir.delete();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
