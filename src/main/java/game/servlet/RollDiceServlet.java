package game.servlet;
import game.components.*;
import enums.EffectEnum;
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

@WebServlet("/RollDiceServlet")
public class RollDiceServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Monopoly game = Monopoly.getInstance();
        Players currentPlayer = game.getCurrentPlayer();

        // Step 1: Player rolls the dice
        int steps = currentPlayer.rollDice();

        // Step 2: Player moves
        EffectEnum result=currentPlayer.move(steps);
        String message=currentPlayer.getName()+" rolled "+steps;
        
        // Step 3: Set the message into session
        MessageLogger.add(request, message);
        
        if(result.equals(EffectEnum.PASS_START)) {
        	MessageLogger.add(request, "You pass the start grid. Get 5000 bonus money");
        	currentPlayer.setMoney(currentPlayer.getMoney()+5000);
        }
        // Step 4: Send this info to JSP (to show dice roll + new location)
        HttpSession session = request.getSession();
        session.setAttribute("gameEnded", false);
        request.setAttribute("steps", steps);
        request.setAttribute("applyGridEffect", true);
        request.setAttribute("canRollDice", false);
        request.getRequestDispatcher("/game.jsp").forward(request, response);
    }
}