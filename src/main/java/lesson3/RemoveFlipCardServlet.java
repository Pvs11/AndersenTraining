package lesson3;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lesson2.MemorizeService;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "deleteServlet", urlPatterns = "/delete/*")
public class RemoveFlipCardServlet extends HttpServlet {
	private final MemorizeService service = ServletConfiguration.getService();
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getPathInfo().substring(1));
		if (service.removeFlipCard(id)) {
			resp.setContentType("text/html");
			PrintWriter pr = resp.getWriter();
			pr.println("<html><body><p>Delete successful!");
			pr.println("</body></html>");
			pr.close();
		} else {
			resp.setContentType("text/html");
			PrintWriter pr = resp.getWriter();
			pr.println("<html><body><p>Delete failed! There is no such flipcard");
			pr.println("</body></html>");
			pr.close();
		}
	}
}
