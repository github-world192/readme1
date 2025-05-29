package helper;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class MessageLogger {
    public static void add(HttpServletRequest request, String message) {
        HttpSession session = request.getSession();
        List<String> history = (List<String>) session.getAttribute("gameMessages");
        if (history == null) {
            history = new ArrayList<>();
        }
        history.add(message);
        session.setAttribute("gameMessages", history);
    }
}