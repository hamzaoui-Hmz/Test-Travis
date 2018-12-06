package io.github.oliviercailloux.y2018.hamzaouiHmz;

/**
 * Hello world!
 *
 */
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import com.sun.mail.util.MailLogger;

import java.util.logging.Logger;

@SuppressWarnings("serial")
@WebServlet("additioner/add")
public class AdditionerServlet extends HttpServlet {
	int resultat, num1, defaut = 0; /*crt shift F*/
	private final static Logger LOGGER = Logger.getLogger(MailLogger.class.getName());
	Integer defautParam;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType(MediaType.TEXT_PLAIN);
		resp.setLocale(Locale.FRENCH);
		@SuppressWarnings("resource")
		final ServletOutputStream out = resp.getOutputStream();
		try {

			int num1 = Integer.parseInt(req.getParameter("param1"));
			int num2 = Integer.parseInt(req.getParameter("param2"));
			int result = num1 + num2;
			out.print(result);
			LOGGER.fine(num1 + ":" + num2);

		} catch (NumberFormatException e) {
			try {
				int num1 = Integer.parseInt(req.getParameter("param1"));
				if (this.defautParam != null) {
					int num2 = this.defautParam;
				} else {
					
					throw new NumberFormatException("Exception");

				}
				resultat = num1 + defautParam;
				out.print(resultat);
			} catch (NumberFormatException ee) {
				resp.setStatus(400);
				LOGGER.warning("paramètre manquant.");
				out.println("Exécution impossible, paramètre manquant.");
			}
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
		resp.setContentType(MediaType.TEXT_PLAIN);

		resp.setContentType("text/plain");
		try {

			int num2 = Integer.parseInt(req.getParameter("param2"));
			this.defautParam = num2;
			resp.getWriter().println("OK");

		} catch (NumberFormatException e) {
			resp.setStatus(400);
			LOGGER.warning("paramètre manquant.");
			resp.getWriter().println("Exécution impossible, paramètre manquant.");
		}
	}

}
