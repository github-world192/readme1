package game.servlet;
import game.components.*;
import helper.MessageLogger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/KeepAliveServlet")
public class KeepAliveServlet extends HttpServlet {
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    request.getSession(); // Accessing session keeps it alive
    response.setStatus(HttpServletResponse.SC_OK);
  }
}