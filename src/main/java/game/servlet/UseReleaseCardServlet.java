package game.servlet;
import game.components.*;
import helper.MessageLogger;

import java.io.IOException;
import java.util.List;

import enums.EffectEnum;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/UseReleaseCardServlet")
public class UseReleaseCardServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Monopoly game = Monopoly.getInstance();
        String message="";
        Players currentPlayer = game.getCurrentPlayer();
        String decision = request.getParameter("decision");
        if("yes".equals(decision)) {
        	currentPlayer.useReleaseCard();
        	message=currentPlayer.getName()+" used a release card to escape jail";
        }
        else {
        	currentPlayer.reduceJailTime(1);
        	message=currentPlayer.getName()+" is in jail for "+currentPlayer.getInJailTurns()+" more turn";
        }
        
        MessageLogger.add(request, message);
        // Forward back to game.jsp
        request.setAttribute("nextTurn", true);
        //HttpSession session = request.getSession();
        //session.setAttribute("gameEnded", false);
        request.setAttribute("canRollDice", false);
        request.getRequestDispatcher("game.jsp").forward(request, response);
    }
}
