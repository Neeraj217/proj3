package in.co.rays.proj3.ctl;

	import java.io.IOException;
	import java.util.HashMap;
	import java.util.Map;
	import java.util.ResourceBundle;

	import javax.servlet.ServletException;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	import javax.servlet.http.HttpSession;

import org.hibernate.internal.SessionImpl;

import in.co.rays.proj3.dto.UserDTO;
	import in.co.rays.proj3.util.HibernateDataSource;
	import in.co.rays.proj3.util.JDBCDataSource;
	import net.sf.jasperreports.engine.JasperCompileManager;
	import net.sf.jasperreports.engine.JasperExportManager;
	import net.sf.jasperreports.engine.JasperFillManager;
	import net.sf.jasperreports.engine.JasperPrint;
	import net.sf.jasperreports.engine.JasperReport;

	/**
	* Jasper functionality Controller. Performs operation for Print pdf of
	* MarksheetMeritList
	*
	* @author Neeraj
	*/
	@WebServlet(name = "JasperCtl", urlPatterns = { "/ctl/JasperCtl" })
	public class JasperCtl extends BaseCtl {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	try {
	 /*Compilation of jrxml file */
		System.out.println("into DoGet method");
	JasperReport jasperReport = JasperCompileManager.compileReport("F:\\Hib1\\proj3\\src\\main\\webapp\\jasper\\ReportMeritlist.jrxml");


	HttpSession session = request.getSession(true);
	UserDTO dto = (UserDTO) session.getAttribute("user");
	dto.getFirstName();
	dto.getLastName();
	System.out.println("Session created");
	
	Map<String, Object> map = new HashMap<String, Object>();
	map.put("ID", 1l);
	java.sql.Connection conn = null;

	ResourceBundle rb = ResourceBundle.getBundle("in.co.rays.proj3.bundle.System");
	System.out.println("Resource bundle");
	String Database = rb.getString("DATABASE");
	if ("HIBERNATE".equalsIgnoreCase(Database)) {
	conn = ((SessionImpl) HibernateDataSource.getSession()).connection();
	System.out.println("Connection created");
	}

	if ("JDBC".equalsIgnoreCase(Database)) {
	conn = JDBCDataSource.getConnection();
	}

	System.out.println("Started filling");
	 /*Filling data into the report */
	System.out.println("Data started filling into report" + jasperReport +"   " + map + "   "+ conn);
	JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, conn);
	System.out.println("Data filled into report");
	/* Export Jasper report*/ 
	byte[] pdf = JasperExportManager.exportReportToPdf(jasperPrint);
	System.out.println("Export to PDF");
	response.setContentType("application/pdf");
	response.getOutputStream().write(pdf);
	response.getOutputStream().flush();
	System.out.println("DoGet Ended");
	} catch (Exception e) {

	}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	@Override
	protected String getView() {
	return null;
	}

	

}
