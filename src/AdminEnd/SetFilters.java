package AdminEnd;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Global.MySQL;

/**
 * Servlet implementation class SetFilters
 */

@WebServlet("/SetFilters")
public class SetFilters extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/* *********************************************************************************************/
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetFilters() {
        super();
    }
    
    /* *********************************************************************************************/

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String resulttable = null;
		String sqlQuery = null;
		Filter filterObject = null;
		Statement SQLstatement = null;
		ResultSet SQLresultset = null;
		
		MySQL.connect();
		
		filterObject = new Filter();
		
		filterObject.initFilters();
		filterObject.setFilters (request);
		filterObject.applyFilters (request);
		
		if (MySQL.doesTableExist (Filter.temptable1))
			resulttable = Filter.temptable1;
		else if (MySQL.doesTableExist (Filter.temptable2))
			resulttable = Filter.temptable2;
		else
			resulttable = MySQL.getSQLtable();
		assert (resulttable != null);
		
		try
		{
			sqlQuery = "SELECT ENROLLMENT_NUMBER, NAME, EMAIL FROM " + resulttable;
			SQLstatement = MySQL.getStatement();
			assert (SQLstatement != null);
			SQLresultset = SQLstatement.executeQuery (sqlQuery);
		}
		catch (SQLException se) {
			se.printStackTrace();
		}
		
		
		PrintWriter out = response.getWriter();
		out.println ("<!DOCTYPE html>");
		out.println ("<html>");
		out.println ("  <head>");
		out.println ("    <title> FILTERED RESULTS </title>");
		out.println ("    <link href=\"./css/bootstrap.min.css\" rel=\"stylesheet\">");
		out.println ("  </head>");
		out.println ("  <body>");
		out.println ("    <h1> <center> TABLE </center> </h1>");
		out.println ("    <table class=\"table table-striped table-hover\">");
		out.println ("      <tr>");
		out.println ("       <th> ENROLLMENT NUMBER </td>");
		out.println ("       <th> NAME </td>");
		out.println ("       <th> EMAIL </td>");
		out.println ("       <th> PDF </td>");
		out.println ("      </tr>");
		
		try
		{
			while (SQLresultset.next()) {
				out.println ("      <tr>");
				out.println ("        <td> " + SQLresultset.getString (1) + " </td>");
				out.println ("        <td> " + SQLresultset.getString (2) + " </td>");
				out.println ("        <td> " + SQLresultset.getString (3) + " </td>");
				out.println ("        <td> <a target=\"_blank\" href=\"PDF_Creator?enrol=" + SQLresultset.getString(1) + "\" /> Details </td>");
				out.println ("      </tr>");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		out.println ("    </table>");
		out.println ("  </body>");
		out.println ("</html>");
		
		try
		{
			SQLresultset.close();
			SQLstatement.close();
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

class Filter {
	
	public static final String temptable1 = "temptable_1";
	public static final String temptable2 = "temptable_2";
	private final String q = "\"";
	private Statement GlobalSTM = null;
	
	/* ***********************************************************************************************************************/

	public Filter () {
		initFilters();
	}
	
	/* ************************************************************
	 *	Fields to Check if Filters are Set	
	 **************************************************************/
	
	private boolean PI_emailFilter;
	private boolean PI_nameFilter;
	private boolean PI_enrollmentNumberFilter;
	private boolean PI_categoryFilter;
	private boolean PI_genderFilter;
	private boolean PI_phyDisabilityFilter;
	private boolean PI_dobFilter;
	
	private boolean EI_phdStreamFilter;
	private boolean EI_gradDegreeFilter;
	private boolean EI_postGradDegreeFilter;
	private boolean EI_XBoardFilter;
	private boolean EI_XIIBoardFilter;
	private boolean EI_gradDisciplineFilter;
	private boolean EI_postGradDisciplineFilter;
	private boolean EI_gradUniversityFilter;
	private boolean EI_postGradUniversityFilter;
	private boolean EI_gradStateFilter;
	private boolean EI_postGradStateFilter;
	private boolean EI_XBoardPercFilter;
	private boolean EI_XIIBoardPercFilter;
	private boolean EI_gradPercFilter;
	private boolean EI_postGradPercFilter;
	private boolean EI_gateScoreFilter;
	
	private boolean Submit_datedFromFilter;
	private boolean Submit_datedUptoFilter;
	
	/* ***********************************************************************************************************************/
	
	public void initFilters ()
	{
		// Initially No Filter is Set
		PI_emailFilter = false;
		PI_nameFilter = false;
		PI_enrollmentNumberFilter = false;
		PI_categoryFilter = false;
		PI_genderFilter = false;
		PI_phyDisabilityFilter = false;
		PI_dobFilter = false;
		
		EI_phdStreamFilter = false;
		EI_gradDegreeFilter = false;
		EI_postGradDegreeFilter = false;
		EI_XBoardFilter = false;
		EI_XIIBoardFilter = false;
		EI_gradDisciplineFilter = false;
		EI_postGradDisciplineFilter = false;
		EI_gradUniversityFilter = false;
		EI_postGradUniversityFilter = false;
		EI_gradStateFilter = false;
		EI_postGradStateFilter = false;
		EI_XBoardPercFilter = false;
		EI_XIIBoardPercFilter = false;
		EI_gradPercFilter = false;
		EI_postGradPercFilter = false;
		EI_gateScoreFilter = false;
		
		Submit_datedFromFilter = false;
		Submit_datedUptoFilter = false;
		
		MySQL.dropTable_checked (temptable1);
		MySQL.dropTable_checked (temptable2);
	}
	
	public void setFilters (HttpServletRequest request)
	{
		String TEMP1, TEMP2;
		String[] TMP;
		
		// EMAIL FILTER
		TEMP1 = request.getParameter ("PI_eMail");
		if (TEMP1 != null)
		{
			TEMP1 = TEMP1.trim();
			if (!TEMP1.isEmpty())
				PI_emailFilter = true;
		}
		
		// NAME FILTER
		TEMP1 = request.getParameter ("PI_Name");
		if (TEMP1 != null)
		{
			TEMP1 = TEMP1.trim();
			if (!TEMP1.isEmpty())
				PI_nameFilter = true;
		}
		
		// ENROLLMENT_NUMBER FILTER
		TEMP1 = request.getParameter ("PI_enrollmentNumber");
		if (TEMP1 != null)
		{
			TEMP1 = TEMP1.trim();
			if (!TEMP1.isEmpty())
				PI_enrollmentNumberFilter = true;
		}
		
		// CATEGORY FILTER
		TEMP1 = request.getParameter ("PI_category");
		if (TEMP1 != null)
		{
			TEMP1 = TEMP1.trim();
			if (!TEMP1.isEmpty() && TEMP1.compareToIgnoreCase ("NULL") != 0)
				PI_categoryFilter = true;
		}
		
		// GENDER FILTER
		TEMP1 = request.getParameter ("PI_Gender");
		if (TEMP1 != null)
		{
			TEMP1 = TEMP1.trim();
			if (!TEMP1.isEmpty() && TEMP1.compareToIgnoreCase ("NULL") != 0)
				PI_genderFilter = true;
		}
		
		// PHYSICALLY_DISABLED FILTER
		TEMP1 = request.getParameter ("PI_phyDisabled");
		if (TEMP1 != null)
		{
			TEMP1 = TEMP1.trim();
			if (!TEMP1.isEmpty() && TEMP1.compareToIgnoreCase ("NULL") != 0)
				PI_phyDisabilityFilter = true;
		}
		
		// DATE_OF_BIRTH FILTER
		TEMP1 = request.getParameter ("PI_DoB_DatePicker");
		TEMP2 = request.getParameter ("PI_DoB");
		if (TEMP1 != null && TEMP2 != null)
		{
			TEMP1 = TEMP1.trim();
			if (!TEMP1.isEmpty() && TEMP1.compareToIgnoreCase ("NULL") != 0)
			{
				try {
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern ("dd-MM-uuuu");
					LocalDate.parse (TEMP1, dtf);
					PI_dobFilter = true;
				}
				catch (DateTimeParseException dte) {
					PI_dobFilter = false;
				}
			}
		}
		
		/* *********************************************************************************************************************/
		
		// PHD_STREAM FILTER
		TEMP1 = request.getParameter ("EI_phd_stream");
		if (TEMP1 != null)
		{
			TEMP1 = TEMP1.trim();
			if (!TEMP1.isEmpty() && TEMP1.compareToIgnoreCase ("NULL") != 0)
				EI_phdStreamFilter = true;
		}
		
		// GRAD_DEGREE FILTER
		TEMP1 = request.getParameter ("EI_grad_degree");
		if (TEMP1 != null)
		{
			TEMP1 = TEMP1.trim();
			if (!TEMP1.isEmpty() && TEMP1.compareToIgnoreCase ("NULL") != 0)
				EI_gradDegreeFilter = true;
		}

		// POSTGRAD_DEGREE FILTER
		TEMP1 = request.getParameter ("EI_postGradDegree");
		if (TEMP1 != null)
		{
			TEMP1 = TEMP1.trim();
			if (!TEMP1.isEmpty() && TEMP1.compareToIgnoreCase ("NULL") != 0)
				EI_postGradDegreeFilter = true;
		}
		
		// X_BOARD FILTER
		TEMP1 = request.getParameter ("EI_XBoard");
		if (TEMP1 != null)
		{
			TEMP1 = TEMP1.trim();
			if (!TEMP1.isEmpty() && TEMP1.compareToIgnoreCase ("NULL") != 0)
				EI_XBoardFilter = true;
		}
		
		// XII_BOARD FILTER
		TEMP1 = request.getParameter ("EI_XIIBoard");
		if (TEMP1 != null)
		{
			TEMP1 = TEMP1.trim();
			if (!TEMP1.isEmpty() && TEMP1.compareToIgnoreCase ("NULL") != 0)
				EI_XIIBoardFilter = true;
		}
		
		// GRAD_DEPARTMENT FILTER
		TEMP1 = request.getParameter ("EI_gradDiscipline");
		if (TEMP1 != null)
		{
			TEMP1 = TEMP1.trim();
			if (!TEMP1.isEmpty() && TEMP1.compareToIgnoreCase ("NULL") != 0)
				EI_gradDisciplineFilter = true;
		}
		
		// POSTGRAD_DEPARTMENT FILTER
		TEMP1 = request.getParameter ("EI_postGradDiscipline");
		if (TEMP1 != null)
		{
			TEMP1 = TEMP1.trim();
			if (!TEMP1.isEmpty() && TEMP1.compareToIgnoreCase ("NULL") != 0)
				EI_postGradDisciplineFilter = true;
		}
		
		// GRAD_UNIVERSITY FILTER
		TEMP1 = request.getParameter ("EI_gradUniversity");
		if (TEMP1 != null)
		{
			TEMP1 = TEMP1.trim();
			if (!TEMP1.isEmpty() && TEMP1.compareToIgnoreCase ("NULL") != 0)
				EI_gradUniversityFilter = true;
		}
		
		
		// POSTGRAD_UNIVESITY FILTER
		TEMP1 = request.getParameter ("EI_postGradUniversity");
		if (TEMP1 != null)
		{
			TEMP1 = TEMP1.trim();
			if (!TEMP1.isEmpty() && TEMP1.compareToIgnoreCase ("NULL") != 0)
				EI_postGradUniversityFilter = true;
		}
		
		// GRAD_STATE FILTER
		TEMP1 = request.getParameter ("EI_gradState");
		if (TEMP1 != null)
		{
			TEMP1 = TEMP1.trim();
			if (!TEMP1.isEmpty() && TEMP1.compareToIgnoreCase ("NULL") != 0)
				EI_gradStateFilter = true;
		}
		
		// POSTGRAD_STATE FILTER
		TEMP1 = request.getParameter ("EI_postGradState");
		if (TEMP1 != null)
		{
			TEMP1 = TEMP1.trim();
			if (!TEMP1.isEmpty() && TEMP1.compareToIgnoreCase ("NULL") != 0)
				EI_postGradStateFilter = true;
		}
		
		// X_MARKS FILTER
		{
			TEMP1 = request.getParameter ("EI_XBoardPerc");
			TMP = request.getParameterValues ("EI_XBoardPerc_CB");
			
			if (TEMP1 != null && TMP != null)
			{
				boolean GT = false, EQ = false, LT = false;
				for (int i = 0; i < TMP.length; i++)
				{
					if (!GT && TMP[i].compareToIgnoreCase ("GT") == 0)
						GT = true;
					if (!EQ && TMP[i].compareToIgnoreCase ("EQ") == 0)
						EQ = true;
					if (!LT && TMP[i].compareToIgnoreCase ("LT") == 0)
						LT = true;
				}

				if (!TEMP1.isEmpty())
					if (GT && EQ && LT)
						EI_XBoardPercFilter = false;
					else
						EI_XBoardPercFilter = true;
				else
					EI_XBoardPercFilter = false;
			}
		}
		
		// XII_MARKS FILTER
		{
			TEMP1 = request.getParameter ("EI_XIIBoardPerc");
			TMP = request.getParameterValues ("EI_XIIBoardPerc_CB");
			
			if (TEMP1 != null && TMP != null)
			{
				boolean GT = false, EQ = false, LT = false;
				for (int i = 0; i < TMP.length; i++)
				{
					if (!GT && TMP[i].compareToIgnoreCase ("GT") == 0)
						GT = true;
					if (!EQ && TMP[i].compareToIgnoreCase ("EQ") == 0)
						EQ = true;
					if (!LT && TMP[i].compareToIgnoreCase ("LT") == 0)
						LT = true;
				}

				if (!TEMP1.isEmpty())
					if (GT && EQ && LT)
						EI_XIIBoardPercFilter = false;
					else
						EI_XIIBoardPercFilter = true;
				else
					EI_XIIBoardPercFilter = false;
			}
		}
		
		// GRAD_PERCENT FILTER
		{
			TEMP1 = request.getParameter ("EI_gradPerc");
			TMP = request.getParameterValues ("EI_gradPerc_CB");
			
			if (TEMP1 != null && TMP != null)
			{
				boolean GT = false, EQ = false, LT = false;
				for (int i = 0; i < TMP.length; i++)
				{
					if (!GT && TMP[i].compareToIgnoreCase ("GT") == 0)
						GT = true;
					if (!EQ && TMP[i].compareToIgnoreCase ("EQ") == 0)
						EQ = true;
					if (!LT && TMP[i].compareToIgnoreCase ("LT") == 0)
						LT = true;
				}

				if (!TEMP1.isEmpty())
					if (GT && EQ && LT)
						EI_gradPercFilter = false;
					else
						EI_gradPercFilter = true;
				else
					EI_gradPercFilter = false;
			}
		}
		
		// POSTGRAD_PERCENT FILTER
		{
			TEMP1 = request.getParameter ("EI_postGradPerc");
			TMP = request.getParameterValues ("EI_postGradPerc_CB");
			
			if (TEMP1 != null && TMP != null)
			{
				boolean GT = false, EQ = false, LT = false;
				for (int i = 0; i < TMP.length; i++)
				{
					if (!GT && TMP[i].compareToIgnoreCase ("GT") == 0)
						GT = true;
					if (!EQ && TMP[i].compareToIgnoreCase ("EQ") == 0)
						EQ = true;
					if (!LT && TMP[i].compareToIgnoreCase ("LT") == 0)
						LT = true;
				}

				if (!TEMP1.isEmpty())
					if (GT && EQ && LT)
						EI_postGradPercFilter = false;
					else
						EI_postGradPercFilter = true;
				else
					EI_postGradPercFilter = false;
			}
		}
		
		// GATE_SCORE FILTER
		{
			TEMP1 = request.getParameter ("EI_gateScore");
			TMP = request.getParameterValues ("EI_gateScore_CB");
			
			if (TEMP1 != null && TMP != null)
			{
				boolean GT = false, EQ = false, LT = false;
				for (int i = 0; i < TMP.length; i++)
				{
					if (!GT && TMP[i].compareToIgnoreCase ("GT") == 0)
						GT = true;
					if (!EQ && TMP[i].compareToIgnoreCase ("EQ") == 0)
						EQ = true;
					if (!LT && TMP[i].compareToIgnoreCase ("LT") == 0)
						LT = true;
				}

				if (!TEMP1.isEmpty())
					if (GT && EQ && LT)
						EI_gateScoreFilter = false;
					else
						EI_gateScoreFilter = true;
				else
					EI_gateScoreFilter = false;
			}
		}
		
		/* *********************************************************************************************************************/
		
		// TIMESTAMP FILTER 1
		TEMP1 = request.getParameter ("Submit_datedFrom");
		if (TEMP1 != null)
		{
			TEMP1 = TEMP1.trim();
			if (!TEMP1.isEmpty() && TEMP1.compareToIgnoreCase ("NULL") != 0)
			{
				try {
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern ("dd-MM-uuuu");
					LocalDate.parse (TEMP1, dtf);
					Submit_datedFromFilter = true;
				}
				catch (DateTimeParseException dte) {
					Submit_datedFromFilter = false;
				}
			}
		}
		
		// TIMESTAMP FILTER 2
		TEMP1 = request.getParameter ("Submit_datedUpto");
		if (TEMP1 != null)
		{
			TEMP1 = TEMP1.trim();
			if (!TEMP1.isEmpty() && TEMP1.compareToIgnoreCase ("NULL") != 0)
			{
				try {
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern ("dd-MM-uuuu");
					LocalDate.parse (TEMP1, dtf);
					Submit_datedUptoFilter = true;
				}
				catch (DateTimeParseException dte) {
					Submit_datedUptoFilter = false;
				}
			}
		}
	}
	
	public void applyFilters (HttpServletRequest request)
	{
		GlobalSTM = MySQL.getStatement();
		assert (GlobalSTM != null);
		
		apply_PI_emailFilter (request);
		apply_PI_nameFilter (request);
		apply_PI_enrollmentNumberFilter (request);
		apply_PI_categoryFilter (request);
		apply_PI_genderFilter (request);
		apply_PI_phyDisabilityFilter (request);
		apply_PI_dobFilter (request);
		
		apply_EI_phdStreamFilter (request);
		apply_EI_gradDegreeFilter (request);
		apply_EI_postGradDegreeFilter (request);
		apply_EI_XBoardFilter (request);
		apply_EI_XIIBoardFilter (request);
		apply_EI_gradDisciplineFilter (request);
		apply_EI_postGradDisciplineFilter (request);
		apply_EI_gradUniversityFilter (request);
		apply_EI_postGradUniversityFilter (request);
		apply_EI_gradStateFilter (request);
		apply_EI_postGradStateFilter (request);
		apply_EI_XBoardPercFilter (request);
		apply_EI_XIIBoardPercFilter (request);
		apply_EI_gradPercFilter (request);
		apply_EI_postGradPercFilter (request);
		apply_EI_gateScoreFilter (request);	
		
		apply_Submit_datedFromFilter (request);
		apply_Submit_datedUptoFilter (request);
		
		try {
			GlobalSTM.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/* ***********************************************************************************************************************/

	class tableholder {
		String buffertable;
		String querytable;
	}
	
	private void prepFilterTables (tableholder th)
	{
		boolean exist_temptable1 = MySQL.doesTableExist (temptable1);
		boolean exist_temptable2 = MySQL.doesTableExist (temptable2);
		
		if (!exist_temptable1 && !exist_temptable2)
		{
			th.querytable = MySQL.getSQLtable();
			th.buffertable = temptable1;
		}
		else if (exist_temptable1 && !exist_temptable2)
		{
			th.querytable = temptable1;
			th.buffertable = temptable2;
		}
		else if (!exist_temptable1 && exist_temptable2)
		{
			th.querytable = temptable2;
			th.buffertable = temptable1;
		}
		else
		{
			System.out.println ("BOTH TEMPTABLES EXIST AT SAME TIME... SOMETHING IS VERY WRONG.. EXITING");
			System.exit (-1);
		}
		
		MySQL.createTable_unchecked (th.buffertable);
	}
	
	private void executeUpdate (String sqlQuery, tableholder th) {
		try 
		{
			GlobalSTM.executeUpdate (sqlQuery);
		}
		catch (SQLException se) {
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		if (th.querytable.compareToIgnoreCase (MySQL.getSQLtable()) != 0)
			MySQL.dropTable_unchecked (th.querytable);
	}
	
	/* ***********************************************************************************************************************/
	
	public void apply_PI_emailFilter (HttpServletRequest request)
	{
		if (!PI_emailFilter)
			return;
		System.out.println ("Applying PI_emailFilter");
		
		String filter = null;
		String sqlQuery = null;
		tableholder th = new tableholder();
		
		filter = request.getParameter ("PI_eMail");
		assert (filter != null && !filter.isEmpty());
		
		filter = filter.trim();
		
		prepFilterTables (th);
		
		sqlQuery = "INSERT INTO " + th.buffertable + " "
				 + "SELECT * FROM " + th.querytable + " "
				 + "WHERE EMAIL = " + q + filter + q;
		
		executeUpdate (sqlQuery, th);
	}
	
	public void apply_PI_nameFilter (HttpServletRequest request)
	{
		if (!PI_nameFilter)
			return;
		System.out.println ("Applying PI_nameFilter");
		
		String filter = null;
		String sqlQuery = null;
		tableholder th = new tableholder();
		
		filter = request.getParameter ("PI_Name");
		assert (filter != null && !filter.isEmpty());
		
		filter = filter.trim();
		
		prepFilterTables (th);
		
		sqlQuery = "INSERT INTO " + th.buffertable + " "
				 + "SELECT * FROM " + th.querytable + " "
				 + "WHERE NAME = " + q + filter + q;
		
		executeUpdate (sqlQuery, th);
	}
	
	private void apply_PI_enrollmentNumberFilter (HttpServletRequest request) {
		if (!PI_enrollmentNumberFilter)
			return;
		System.out.println ("Applying PI_enrollmentNumberFilter");
		
		String filter = null;
		String sqlQuery = null;
		tableholder th = new tableholder();
		
		filter = request.getParameter ("PI_enrollmentNumber");
		assert (filter != null && !filter.isEmpty());
		
		filter = filter.trim();
		
		prepFilterTables (th);
		
		sqlQuery = "INSERT INTO " + th.buffertable + " "
				 + "SELECT * FROM " + th.querytable + " "
				 + "WHERE ENROLLMENT_NUMBER = " + q + filter + q;
		
		executeUpdate (sqlQuery, th);
	}

	private void apply_PI_categoryFilter (HttpServletRequest request) {
		if (!PI_categoryFilter)
			return;
		System.out.println ("Applying PI_categoryFilter");
		
		String filter = null;
		String sqlQuery = null;
		tableholder th = new tableholder();
		
		filter = request.getParameter ("PI_category");
		assert (filter != null && !filter.isEmpty());
		
		filter = filter.trim();
		
		prepFilterTables (th);
		
		sqlQuery = "INSERT INTO " + th.buffertable + " "
				 + "SELECT * FROM " + th.querytable + " "
				 + "WHERE CATEGORY = " + q + filter + q;
		
		executeUpdate (sqlQuery, th);
	}

	private void apply_PI_genderFilter (HttpServletRequest request) {
		if (!PI_genderFilter)
			return;
		System.out.println ("Applying PI_genderFilter");
		
		String filter = null;
		String sqlQuery = null;
		tableholder th = new tableholder();
		
		filter = request.getParameter ("PI_Gender");
		assert (filter != null && !filter.isEmpty());
		
		filter = filter.trim();
		
		prepFilterTables (th);
		
		sqlQuery = "INSERT INTO " + th.buffertable + " "
				 + "SELECT * FROM " + th.querytable + " "
				 + "WHERE GENDER = " + q + filter + q;
		
		executeUpdate (sqlQuery, th);
	}

	private void apply_PI_phyDisabilityFilter (HttpServletRequest request) {
		if (!PI_phyDisabilityFilter)
			return;
		System.out.println ("Applying PI_phyDisabilityFilter");
		
		String filter = null;
		String sqlQuery = null;
		tableholder th = new tableholder();
		
		filter = request.getParameter ("PI_phyDisabled");
		assert (filter != null && !filter.isEmpty());
		
		filter = filter.trim();
		
		prepFilterTables (th);
		
		sqlQuery = "INSERT INTO " + th.buffertable + " "
				 + "SELECT * FROM " + th.querytable + " "
				 + "WHERE PHYSICALLY_DISABLED = " + q + filter + q;
		
		executeUpdate (sqlQuery, th);
	}

	private void apply_PI_dobFilter (HttpServletRequest request) {
		if (!PI_dobFilter)
			return;
		System.out.println ("Applying PI_dobFilter");
		
		String filter1 = null, filter2 = null;
		DateTimeFormatter dtf1, dtf2;
		dtf1 = DateTimeFormatter.ofPattern ("dd-MM-uuuu");
		dtf2 = DateTimeFormatter.ofPattern ("uuuu-MM-dd");
		
		String sqlQuery = null;
		tableholder th = new tableholder();
		
		filter1 = request.getParameter ("PI_DoB");
		filter2 = request.getParameter ("PI_DoB_DatePicker");
		assert (filter1 != null && !filter1.isEmpty());
		assert (filter2 != null && !filter2.isEmpty());
		
		filter1 = filter1.trim();
		filter2 = filter2.trim();
		
		LocalDate d1 = LocalDate.parse (filter2, dtf1);
		d1.format (dtf2);
		
		prepFilterTables (th);
		
		sqlQuery = "INSERT INTO " + th.buffertable + " "
				 + "SELECT * FROM " + th.querytable + " "
				 + "WHERE DATE_OF_BIRTH ";
		
		if (filter1.compareToIgnoreCase ("Before") == 0)
		{
			sqlQuery += "< ";
		}
		else if (filter1.compareToIgnoreCase ("On") == 0)
		{
			sqlQuery += "= ";
		}
		else if (filter1.compareToIgnoreCase ("After") == 0)
		{
			sqlQuery += "> ";
		}
		else
		{
			System.out.println ("DOB FILTER FAILED");
			return;
		}
		
		sqlQuery += q + d1.toString() + q;
		
		executeUpdate (sqlQuery, th);
	}

	private void apply_EI_phdStreamFilter (HttpServletRequest request) {
		if (!EI_phdStreamFilter)
			return;
		System.out.println ("Applying EI_phdStreamFilter");
		
		String filter = null;
		String sqlQuery = null;
		tableholder th = new tableholder();
		
		filter = request.getParameter ("EI_phd_stream");
		assert (filter != null && !filter.isEmpty());
		
		filter = filter.trim();
		
		prepFilterTables (th);
		
		sqlQuery = "INSERT INTO " + th.buffertable + " "
				 + "SELECT * FROM " + th.querytable + " "
				 + "WHERE PHD_STREAM = " + q + filter + q;
		
		executeUpdate (sqlQuery, th);
	}

	private void apply_EI_gradDegreeFilter (HttpServletRequest request) {
		if (!EI_gradDegreeFilter)
			return;
		System.out.println ("Applying EI_gradDegreeFilter");
		
		String filter = null;
		String sqlQuery = null;
		tableholder th = new tableholder();
		
		filter = request.getParameter ("EI_grad_degree");
		assert (filter != null && !filter.isEmpty());
		
		filter = filter.trim();
		
		prepFilterTables (th);
		
		sqlQuery = "INSERT INTO " + th.buffertable + " "
				 + "SELECT * FROM " + th.querytable + " "
				 + "WHERE GRAD_DEGREE = " + q + filter + q;
		
		executeUpdate (sqlQuery, th);
	}

	private void apply_EI_postGradDegreeFilter (HttpServletRequest request) {
		if (!EI_postGradDegreeFilter)
			return;
		System.out.println ("Applying EI_postGradDegreeFilter");
		
		String filter = null;
		String sqlQuery = null;
		tableholder th = new tableholder();
		
		filter = request.getParameter ("EI_postGradDegree");
		assert (filter != null && !filter.isEmpty());
		
		filter = filter.trim();
		
		prepFilterTables (th);
		
		sqlQuery = "INSERT INTO " + th.buffertable + " "
				 + "SELECT * FROM " + th.querytable + " "
				 + "WHERE POSTGRAD_DEGREE = " + q + filter + q;
		
		executeUpdate (sqlQuery, th);
	}

	private void apply_EI_XBoardFilter (HttpServletRequest request) {
		if (!EI_XBoardFilter)
			return;
		System.out.println ("Applying EI_XBoardFilter");
		
		String filter = null;
		String sqlQuery = null;
		tableholder th = new tableholder();
		
		filter = request.getParameter ("EI_XBoard");
		assert (filter != null && !filter.isEmpty());
		
		filter = filter.trim();
		
		prepFilterTables (th);
		
		sqlQuery = "INSERT INTO " + th.buffertable + " "
				 + "SELECT * FROM " + th.querytable + " "
				 + "WHERE X_BOARD = " + q + filter + q;
		
		executeUpdate (sqlQuery, th);
	}

	private void apply_EI_XIIBoardFilter (HttpServletRequest request) {
		if (!EI_XIIBoardFilter)
			return;
		System.out.println ("Applying EI_XIIBoardFilter");
		
		String filter = null;
		String sqlQuery = null;
		tableholder th = new tableholder();
		
		filter = request.getParameter ("EI_XIIBoard");
		assert (filter != null && !filter.isEmpty());
		
		filter = filter.trim();
		
		prepFilterTables (th);
		
		sqlQuery = "INSERT INTO " + th.buffertable + " "
				 + "SELECT * FROM " + th.querytable + " "
				 + "WHERE XII_BOARD = " + q + filter + q;
		
		executeUpdate (sqlQuery, th);
	}

	private void apply_EI_gradDisciplineFilter (HttpServletRequest request) {
		if (!EI_gradDisciplineFilter)
			return;
		System.out.println ("Applying EI_gradDisciplineFilter");
		
		String filter = null;
		String sqlQuery = null;
		tableholder th = new tableholder();
		
		filter = request.getParameter ("EI_gradDiscipline");
		assert (filter != null && !filter.isEmpty());
		
		filter = filter.trim();
		
		prepFilterTables (th);
		
		sqlQuery = "INSERT INTO " + th.buffertable + " "
				 + "SELECT * FROM " + th.querytable + " "
				 + "WHERE GRAD_DEPARTMENT = " + q + filter + q;
		
		executeUpdate (sqlQuery, th);
	}

	private void apply_EI_postGradDisciplineFilter (HttpServletRequest request) {
		if (!EI_postGradDisciplineFilter)
			return;
		System.out.println ("Applying EI_postGradDisciplineFilter");
		
		String filter = null;
		String sqlQuery = null;
		tableholder th = new tableholder();
		
		filter = request.getParameter ("EI_postGradDiscipline");
		assert (filter != null && !filter.isEmpty());
		
		filter = filter.trim();
		
		prepFilterTables (th);
		
		sqlQuery = "INSERT INTO " + th.buffertable + " "
				 + "SELECT * FROM " + th.querytable + " "
				 + "WHERE POSTGRAD_DEPARTMENT = " + q + filter + q;
		
		executeUpdate (sqlQuery, th);
	}

	private void apply_EI_gradUniversityFilter (HttpServletRequest request) {
		if (!EI_gradUniversityFilter)
			return;
		System.out.println ("Applying EI_gradUniversityFilter");
		
		String filter = null;
		String sqlQuery = null;
		tableholder th = new tableholder();
		
		filter = request.getParameter ("EI_gradUniversity");
		assert (filter != null && !filter.isEmpty());
		
		filter = filter.trim();
		
		prepFilterTables (th);
		
		sqlQuery = "INSERT INTO " + th.buffertable + " "
				 + "SELECT * FROM " + th.querytable + " "
				 + "WHERE GRAD_UNIVERSITY = " + q + filter + q;
		
		executeUpdate (sqlQuery, th);
	}

	private void apply_EI_postGradUniversityFilter (HttpServletRequest request) {
		if (!EI_postGradUniversityFilter)
			return;
		System.out.println ("Applying EI_postGradUniversityFilter");
		
		String filter = null;
		String sqlQuery = null;
		tableholder th = new tableholder();
		
		filter = request.getParameter ("EI_postGradUniversity");
		assert (filter != null && !filter.isEmpty());
		
		filter = filter.trim();
		
		prepFilterTables (th);
		
		sqlQuery = "INSERT INTO " + th.buffertable + " "
				 + "SELECT * FROM " + th.querytable + " "
				 + "WHERE POSTGRAD_COLLEGE = " + q + filter + q;		// Since there is no field POSTGRAD_UNIVERSITY
		
		executeUpdate (sqlQuery, th);
	}

	private void apply_EI_gradStateFilter (HttpServletRequest request) {
		if (!EI_gradStateFilter)
			return;
		System.out.println ("Applying EI_gradStateFilter");
		
		String filter = null;
		String sqlQuery = null;
		tableholder th = new tableholder();
		
		filter = request.getParameter ("EI_gradState");
		assert (filter != null && !filter.isEmpty());
		
		filter = filter.trim();
		
		prepFilterTables (th);
		
		sqlQuery = "INSERT INTO " + th.buffertable + " "
				 + "SELECT * FROM " + th.querytable + " "
				 + "WHERE GRAD_STATE = " + q + filter + q;
		
		executeUpdate (sqlQuery, th);
	}

	private void apply_EI_postGradStateFilter (HttpServletRequest request) {
		if (!EI_postGradStateFilter)
			return;
		System.out.println ("Applying EI_postGradStateFilter");
		
		String filter = null;
		String sqlQuery = null;
		tableholder th = new tableholder();
		
		filter = request.getParameter ("EI_postGradState");
		assert (filter != null && !filter.isEmpty());
		
		filter = filter.trim();
		
		prepFilterTables (th);
		
		sqlQuery = "INSERT INTO " + th.buffertable + " "
				 + "SELECT * FROM " + th.querytable + " "
				 + "WHERE POSTGRAD_STATE = " + q + filter + q;
		
		executeUpdate (sqlQuery, th);
	}

	private void apply_EI_XBoardPercFilter (HttpServletRequest request) {
		if (!EI_XBoardPercFilter)
			return;
		System.out.println ("Applying EI_XBoardPercFilter");
		
		String[] checkBox;
		String filter;
		String sqlQuery = null;
		tableholder th = new tableholder();
		
		checkBox = request.getParameterValues ("EI_XBoardPerc_CB");
		filter = request.getParameter ("EI_XBoardPerc");
		assert (checkBox != null);
		assert (filter != null);
		
		filter = filter.trim();
		
		prepFilterTables (th);
		
		sqlQuery = "INSERT INTO " + th.buffertable + " "
				 + "SELECT * FROM " + th.querytable + " "
				 + "WHERE X_MARKS ";
		
		boolean GT = false;
		boolean EQ = false;
		boolean LT = false;
		
		for (int i = 0; i < checkBox.length; i++)
		{
			if (!GT && checkBox[i].compareToIgnoreCase ("GT") == 0)
				GT = true;
			if (!EQ && checkBox[i].compareToIgnoreCase ("EQ") == 0)
				EQ = true;
			if (!LT && checkBox[i].compareToIgnoreCase ("LT") == 0)
				LT = true;
		}
		
		if (GT && EQ && LT)
			return;					// No filter set
		else if (GT && EQ)
			sqlQuery += ">= ";
		else if (GT && LT)
			sqlQuery += "= ";
		else if (LT && EQ)
			sqlQuery += "<= ";
		else if (GT)
			sqlQuery += "> ";
		else if (EQ)
			sqlQuery += "= ";
		else if (LT)
			sqlQuery += "< ";
		else
		{
			System.out.println ("ERROR: NO FLAG IS ON");
			System.exit (-1);
		}
		
		sqlQuery += filter;
		
		executeUpdate (sqlQuery, th);
	}

	private void apply_EI_XIIBoardPercFilter (HttpServletRequest request) {
		if (!EI_XIIBoardPercFilter)
			return;
		System.out.println ("Applying EI_XIIBoardPercFilter");
		
		String[] checkBox;
		String filter;
		String sqlQuery = null;
		tableholder th = new tableholder();
		
		checkBox = request.getParameterValues ("EI_XIIBoardPerc_CB");
		filter = request.getParameter ("EI_XIIBoardPerc");
		assert (checkBox != null);
		assert (filter != null);
		
		filter = filter.trim();
		
		prepFilterTables (th);
		
		sqlQuery = "INSERT INTO " + th.buffertable + " "
				 + "SELECT * FROM " + th.querytable + " "
				 + "WHERE XII_MARKS ";
		
		boolean GT = false;
		boolean EQ = false;
		boolean LT = false;
		
		for (int i = 0; i < checkBox.length; i++)
		{
			if (!GT && checkBox[i].compareToIgnoreCase ("GT") == 0)
				GT = true;
			if (!EQ && checkBox[i].compareToIgnoreCase ("EQ") == 0)
				EQ = true;
			if (!LT && checkBox[i].compareToIgnoreCase ("LT") == 0)
				LT = true;
		}
		
		if (GT && EQ && LT)
			return;					// No filter set
		else if (GT && EQ)
			sqlQuery += ">= ";
		else if (GT && LT)
			sqlQuery += "= ";
		else if (LT && EQ)
			sqlQuery += "<= ";
		else if (GT)
			sqlQuery += "> ";
		else if (EQ)
			sqlQuery += "= ";
		else if (LT)
			sqlQuery += "< ";
		else
		{
			System.out.println ("ERROR: NO FLAG IS ON");
			System.exit (-1);
		}
		
		sqlQuery += filter;
		
		executeUpdate (sqlQuery, th);
	}

	private void apply_EI_gradPercFilter (HttpServletRequest request) {
		if (!EI_gradPercFilter)
			return;
		System.out.println ("Applying EI_gradPercFilter");
		
		String[] checkBox;
		String filter;
		String sqlQuery = null;
		tableholder th = new tableholder();
		
		checkBox = request.getParameterValues ("EI_gradPerc_CB");
		filter = request.getParameter ("EI_gradPerc");
		assert (checkBox != null);
		assert (filter != null);
		
		filter = filter.trim();
		
		prepFilterTables (th);
		
		sqlQuery = "INSERT INTO " + th.buffertable + " "
				 + "SELECT * FROM " + th.querytable + " "
				 + "WHERE GRAD_PERCENT ";
		
		boolean GT = false;
		boolean EQ = false;
		boolean LT = false;
		
		for (int i = 0; i < checkBox.length; i++)
		{
			if (!GT && checkBox[i].compareToIgnoreCase ("GT") == 0)
				GT = true;
			if (!EQ && checkBox[i].compareToIgnoreCase ("EQ") == 0)
				EQ = true;
			if (!LT && checkBox[i].compareToIgnoreCase ("LT") == 0)
				LT = true;
		}
		
		if (GT && EQ && LT)
			return;					// No filter set
		else if (GT && EQ)
			sqlQuery += ">= ";
		else if (GT && LT)
			sqlQuery += "= ";
		else if (LT && EQ)
			sqlQuery += "<= ";
		else if (GT)
			sqlQuery += "> ";
		else if (EQ)
			sqlQuery += "= ";
		else if (LT)
			sqlQuery += "< ";
		else
		{
			System.out.println ("ERROR: NO FLAG IS ON");
			System.exit (-1);
		}
		
		sqlQuery += filter;
		
		executeUpdate (sqlQuery, th);
	}

	private void apply_EI_postGradPercFilter (HttpServletRequest request) {
		if (!EI_postGradPercFilter)
			return;
		System.out.println ("Applying EI_postGradPercFilter");
		
		String[] checkBox;
		String filter;
		String sqlQuery = null;
		tableholder th = new tableholder();
		
		checkBox = request.getParameterValues ("EI_postGradPerc_CB");
		filter = request.getParameter ("EI_postGradPerc");
		assert (checkBox != null);
		assert (filter != null);
		
		filter = filter.trim();
		
		prepFilterTables (th);
		
		sqlQuery = "INSERT INTO " + th.buffertable + " "
				 + "SELECT * FROM " + th.querytable + " "
				 + "WHERE POSTGRAD_PERCENT ";
		
		boolean GT = false;
		boolean EQ = false;
		boolean LT = false;
		
		for (int i = 0; i < checkBox.length; i++)
		{
			if (!GT && checkBox[i].compareToIgnoreCase ("GT") == 0)
				GT = true;
			if (!EQ && checkBox[i].compareToIgnoreCase ("EQ") == 0)
				EQ = true;
			if (!LT && checkBox[i].compareToIgnoreCase ("LT") == 0)
				LT = true;
		}
		
		if (GT && EQ && LT)
			return;					// No filter set
		else if (GT && EQ)
			sqlQuery += ">= ";
		else if (GT && LT)
			sqlQuery += "= ";
		else if (LT && EQ)
			sqlQuery += "<= ";
		else if (GT)
			sqlQuery += "> ";
		else if (EQ)
			sqlQuery += "= ";
		else if (LT)
			sqlQuery += "< ";
		else
		{
			System.out.println ("ERROR: NO FLAG IS ON");
			System.exit (-1);
		}
		
		sqlQuery += filter;
		
		executeUpdate (sqlQuery, th);
	}

	private void apply_EI_gateScoreFilter (HttpServletRequest request) {
		if (!EI_gateScoreFilter)
			return;
		System.out.println ("Applying EI_gateScoreFilter");
		
		String[] checkBox;
		String filter;
		String sqlQuery = null;
		tableholder th = new tableholder();
		
		checkBox = request.getParameterValues ("EI_gateScore_CB");
		filter = request.getParameter ("EI_gateScore");
		assert (checkBox != null);
		assert (filter != null);
		
		filter = filter.trim();
		
		prepFilterTables (th);
		
		sqlQuery = "INSERT INTO " + th.buffertable + " "
				 + "SELECT * FROM " + th.querytable + " "
				 + "WHERE GATE_SCORE ";
		
		boolean GT = false;
		boolean EQ = false;
		boolean LT = false;
		
		for (int i = 0; i < checkBox.length; i++)
		{
			if (!GT && checkBox[i].compareToIgnoreCase ("GT") == 0)
				GT = true;
			if (!EQ && checkBox[i].compareToIgnoreCase ("EQ") == 0)
				EQ = true;
			if (!LT && checkBox[i].compareToIgnoreCase ("LT") == 0)
				LT = true;
		}
		
		if (GT && EQ && LT)
			return;					// No filter set
		else if (GT && EQ)
			sqlQuery += ">= ";
		else if (GT && LT)
			sqlQuery += "= ";
		else if (LT && EQ)
			sqlQuery += "<= ";
		else if (GT)
			sqlQuery += "> ";
		else if (EQ)
			sqlQuery += "= ";
		else if (LT)
			sqlQuery += "< ";
		else
		{
			System.out.println ("ERROR: NO FLAG IS ON");
			System.exit (-1);
		}
		
		sqlQuery += filter;
		
		executeUpdate (sqlQuery, th);
	}

	private void apply_Submit_datedFromFilter (HttpServletRequest request) {
		if (!Submit_datedFromFilter)
			return;
		System.out.println ("Applying Submit_datedFromFilter");
		
		String filter = null;
		String sqlQuery = null;
		tableholder th = new tableholder();
		
		filter = request.getParameter ("Submit_datedFrom");
		assert (filter != null && !filter.isEmpty());
		
		filter = filter.trim();

		DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern ("dd-MM-uuuu");
		DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern ("uuuu-MM-dd");
		
		LocalDate d1 = LocalDate.parse (filter, dtf1);
		d1.format (dtf2);
		
		prepFilterTables (th);
		
		sqlQuery = "INSERT INTO " + th.buffertable + " "
				 + "SELECT * FROM " + th.querytable + " "
				 + "WHERE TIMESTAMP >= " + q + d1.toString() + " 00:00:00" + q;		// filter contains Date
		
		executeUpdate (sqlQuery, th);
	}

	private void apply_Submit_datedUptoFilter (HttpServletRequest request) {
		if (!Submit_datedUptoFilter)
			return;
		System.out.println ("Applying Submit_datedUptoFilter");
		
		String filter = null;
		String sqlQuery = null;
		tableholder th = new tableholder();
		
		filter = request.getParameter ("Submit_datedUpto");
		assert (filter != null && !filter.isEmpty());
		
		filter = filter.trim();

		DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern ("dd-MM-uuuu");
		DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern ("uuuu-MM-dd");
		
		LocalDate d1 = LocalDate.parse (filter, dtf1);
		d1.format (dtf2);
		
		prepFilterTables (th);
		
		sqlQuery = "INSERT INTO " + th.buffertable + " "
				 + "SELECT * FROM " + th.querytable + " "
				 + "WHERE TIMESTAMP <= " + q + d1.toString() + " 23:59:59" + q;		// filter contains Date
		
		executeUpdate (sqlQuery, th);
	}
}
