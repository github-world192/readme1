package game.components;
import game.servlet.*;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Monopoly extends Game{
    private static Monopoly instance; // Singleton instance
    protected List<Grids> gridsList; // List to store all grids
    protected Players currentPlayer;
    protected int numOfBankrupt;
    protected int currentPlayerIndex;
    protected Card lastDrawnCard;
    protected Players winner;
    protected boolean gameEnded;
    // Get the Singleton instance
    public static synchronized Monopoly getInstance() {
        if (instance == null) {
            instance = new Monopoly();
        }
        return instance;
    }

    // Private constructor to prevent external instantiation
    private Monopoly() {
    	super("Monopoly");
    	this.gameEnded=false;
    	this.winner=null;
    	this.numOfBankrupt=0;
    	this.lastDrawnCard=null;
    	String[][] landData = {
    		    {"Park Lane", "6000"},
    		    {"Mayfair", "9500"},
    		    {"Oxford Street", "4800"},
    		    {"Bond Street", "7200"},
    		    {"Regent Street", "3100"},
    		    {"Piccadilly", "8400"},
    		    {"Fleet Street", "2900"},
    		    {"Strand", "5600"},
    		    {"Trafalgar Square", "4000"},
    		    {"Leicester Square", "6800"},
    		    {"Coventry Street", "3600"},
    		    {"Pall Mall", "2700"},
    		    {"Whitehall", "5100"},
    		    {"Northumberland Avenue", "3900"},
    		    {"Bow Street", "4300"},
    		    {"Marlborough Street", "8700"},
    		    {"Vine Street", "1200"},
    		    {"The Angel Islington", "1800"},
    		    {"Euston Road", "2500"},
    		    {"Pentonville Road", "2100"},
    		    {"Whitechapel Road", "1400"},
    		    {"Old Kent Road", "1000"},
    		    {"Liverpool Street Station", "5500"},
    		    {"King's Cross Station", "5200"},
    		    {"Marylebone Station", "4700"},
    		    {"Fenchurch Street Station", "4900"},
    		    {"Baker Street", "7000"},
    		    {"Westminster", "3300"},
    		    {"Aldwych", "2300"},
    		    {"Holborn", "3100"}
    	};

        this.gridsList=new ArrayList<Grids>();
        
        //add lands data first
        for(String[] i : landData) {
        	gridsList.add(new Lands(i[0], Integer.parseInt(i[1])));
        }
        
        //insert special grids later
        gridsList.add(0, new StartGrid("start"));
        gridsList.add(2, new CommunityChest("community chest 1"));
        gridsList.add(7, new Chance("chance 1"));
        gridsList.add(10, new Jail("jail"));
        gridsList.add(17, new CommunityChest("community chest 2"));
        gridsList.add(20, new FreeParking("free parking"));
        gridsList.add(22, new Chance("chance 2"));
        gridsList.add(30, new GoToJail("go to jail"));
        gridsList.add(33, new CommunityChest("community chest 3"));
        gridsList.add(36, new Chance("chance 3"));
    }
    
    public void initializePlayers(String[] names, String[] colors) {
        playersList = new ArrayList<>();
        for (int i=0; i<names.length;i++) {
            playersList.add(new Players(names[i], colors[i]));
        }
        currentPlayerIndex=-1;
    }
    
    public void nextPlayer() {
    	int totalPlayers = playersList.size();
        currentPlayerIndex = (currentPlayerIndex + 1) % totalPlayers;
        System.out.println(currentPlayerIndex);
        currentPlayer = playersList.get(currentPlayerIndex);
    }
    
    @Override
    public void end() {
            // Find the non-bankrupt player
            for (Players player : playersList) {
                if (!player.getBankrupt()) {
                    this.winner = player;
                    this.gameEnded=true;
                    break;
                }
            }
    }
    
    public List<Grids> getGridsList() {
        return this.gridsList;
    }
    
    public Players getCurrentPlayer() {
    	return currentPlayer;
    }
    
    public Card getLastDrawnCard() {
        return lastDrawnCard;
    }
    
    public int getNumOfBankrupt() {
    	return numOfBankrupt;
    }
    
    public Players getWinner() {
    	return this.winner;
    }
}
