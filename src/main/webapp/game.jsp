<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*, game.components.*, game.servlet.*, helper.GridHelper" %>
<%
    Monopoly game = (Monopoly) session.getAttribute("monopoly");
    List<Players> players = game.getPlayersList();
%>

<!DOCTYPE html>
<html>
<head>
<link href="https://fonts.googleapis.com/css2?family=EB+Garamond&display=swap" rel="stylesheet">

  <meta charset="UTF-8">
  <title>Monopoly Game</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <style>
    body {
  margin: 0;
  padding: 0;
  font-family: Arial, sans-serif;
  background-color: #f7f7f7;
  background-image: url('images/skyBackground.jpg');
}

.container {
  display: flex;
  flex-direction: column;
  gap: 1rem;
  padding: 1rem;
  max-width: 1400px;
  margin: auto;
}

/* Top section: player list + game board */
.top-layout {
  display: flex;
  flex-direction: row;
  gap: 1rem;
  align-items: flex-start;
}


.top-wrapper {
  display: flex;
  flex-direction: column;
  gap: 1rem;
  width: 1150px;
  margin: auto;
}

.player-column {
  display: flex;
  flex-direction: column;
  gap: 1rem;
  flex: 0 1 180px;
  min-width: 160px;
}

.player-box {
  background-color: white;
  border: 2px solid black;
  border-radius: 12px;
  padding: 1rem;
  font-size: 1.1rem;
  font-weight: bold;
  min-height: 100px;

  display: flex;
  flex-direction: column;
  justify-content: flex-start;  /* Keep content starting from top */
  align-items: center;          /* Center name */
  text-align: left;
}

.player-name {
  text-align: center;
  width: 100%;
  margin-bottom: 0.5rem;
}

.player-details {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: flex-start;  /* Align left */
  justify-content: center;
  flex-grow: 1;
}

@keyframes pulse-custom {
  0% { box-shadow: 0 0 10px 4px; }
  50% { box-shadow: 0 0 16px 8px; }
  100% { box-shadow: 0 0 10px 4px; }
}

.game-board { display: flex; align-items: center; justify-content: center; flex: 1 1 auto; min-width: 850px; min-height: 588px; background-color: white; border: 2px solid black; border-radius: 12px; font-size: 1.2rem; padding: 0.5rem; box-sizing: border-box; }

.monopoly-board { display: grid; grid-template-columns: repeat(11, 51px); grid-template-rows: repeat(11, 51px); gap: 2px; }

.grid-box { border: 1px solid black; font-size: 0.45rem; padding: 1px; text-align: center; overflow: hidden; background-color: white; display: flex; flex-direction: column; justify-content: space-between; word-wrap: break-word; word-break: break-word; }

.token { width: 7px; height: 7px; border-radius: 50%; margin-top: 1px; }

.grid-name { font-weight: bold; font-size: 0.64rem; padding: 0; margin: 0; }

.grid-price { font-size: 0.67rem; margin: 0; }

.player-tokens { display: flex; flex-wrap: wrap; justify-content: center; gap: 2px; }

.special-grid { background-color: #eef; }

.icon {
  width: 20px;
  height: 20px;
  vertical-align: middle;
  margin-right: 0.4rem;
}

.clickable-surrender {
  cursor: pointer;
  position: relative;
  transition: transform 0.2s ease;
}

.clickable-surrender:hover {
  transform: scale(1.02);
}

/* Surrender hover text box */
.surrender-hover {
  position: absolute;
  bottom: 5px;
  right: 5px;
  background-color: black;
  color: white;
  padding: 4px 8px;
  font-size: 0.8rem;
  border-radius: 6px;
  opacity: 0;
  transition: opacity 0.3s;
}

.clickable-surrender:hover .surrender-hover {
  opacity: 1;
}


/* Bottom section */
.bottom-layout {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  gap: 1rem;
  width: 100%;
}

.message-box {
  flex: 1 1 auto;
  height: 180px;
  min-height: 160px;
  max-height: 200px;
  background-color: white;
  border: 2px solid black;
  border-radius: 12px;
  padding: 1rem;
  overflow-y: auto;
  font-size: 1.4rem;
  line-height: 1.4;
  scroll-behavior: smooth;
  background-image: url('images/paper.png'); /* path to your image */
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
}

.message-content {
  display: flex;
  flex-direction: column;
  gap: 0.4rem;
  font-family: 'EB Garamond', serif;
}

.message-line {
  word-wrap: break-word;
}

.button-block {
  width: 150px; /* same width as before */
  display: flex;
  flex-direction: column;
  background-image: url('images/wood.jpg'); /* path to your image */
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
  gap: 1rem;
  background-color: white;
  border: 2px solid black;
  border-radius: 12px;
  padding: 1rem;
}

.action-buttons {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100%;
  gap: 1rem;
}

.roll-dice-button {
  width: 100px;
  height: 100px;
  font-family: 'EB Garamond', serif;
  border-radius: 50%;
  border: 2px solid black;
  background-image: url("images/diceButton.png");
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  font-size: 1.4rem;
  font-weight: bold;
  text-align: center;
  line-height: 1.2rem;
  cursor: pointer;
  transition: transform 0.2s ease;
}

.roll-dice-button:disabled {
  opacity: 0.4;
  cursor: not-allowed;
  pointer-events: none;
}

.roll-dice-button:hover {
  transform: scale(1.1);
}

.confirm-button {
  width: 80px;
  height: 80px;
  border-radius: 50%;
  border: 2px solid black;
  font-size: 0.9rem;
  font-weight: bold;
  cursor: pointer;
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  transition: transform 0.2s ease;
  color: transparent; /* or transparent if image has text */
}

.confirm-button:hover {
  background-color: #444;
  color: white;
}

.yes-btn {
  background-image: url('images/yesButton.png'); /* path to your image */
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
  color: white;
  border: none;
}

.no-btn {
  background-image: url('images/noButton.png'); /* path to your image */
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
  color: white;
  border: none;
}


.button-block button:hover {
  background-color: #ccc;
}

.winner-popup {
  display: none;
  position: fixed;
  top: 0; left: 0;
  width: 100vw;
  height: 100vh;
  background-color: rgba(0,0,0,0.7);
  justify-content: center;
  align-items: center;
  z-index: 9999;
}

.popup-content {
  background-color: white;
  padding: 3rem;
  border-radius: 20px;
  font-size: 2rem;
  text-align: center;
  box-shadow: 0 0 20px black;
}


.disabled-button {
  opacity: 0.5;
  cursor: not-allowed;
  pointer-events: none;
  filter: grayscale(60%);
}


@media (max-width: 768px) {
  .top-layout {
    flex-direction: column;
    align-items: center;
  }

  .top-wrapper {
    width: 100%;
    padding: 0 1rem;
  }

  .container {
    padding: 0.5rem;
    width: 95vw;
  }
  .player-column {
    flex: 1 1 100%;
    min-width: auto;
    align-items: center;
  }

  .player-box {
    width: 90%;
    font-size: 1rem;
    padding: 0.8rem;
  }
  .game-board {
    min-width: auto;
    min-height: auto;
    width: 100%;
    height: auto;
    overflow-x: auto;
    padding: 0.5rem;
  }

  .monopoly-board {
    grid-template-columns: repeat(11, 32px);
    grid-template-rows: repeat(11, 32px);
    gap: 1px;
  }

  .grid-box {
    font-size: 0.35rem;
    padding: 1px;
  }
  
  .token {
    width: 5px;
    height: 5px;
    margin-top: 0.5px;
  }

  .grid-name {
    font-size: 0.48rem;
  }

  .grid-price {
    font-size: 0.5rem;
  }

  .player-tokens {
    gap: 1px;
  }
  
  .icon { width: 16px; height: 16px; margin-right: 0.3rem; }
  .surrender-hover { padding: 3px 6px; font-size: 0.7rem; bottom: 4px; right: 4px; }
  .bottom-layout { flex-direction: column; align-items: center; }
  .message-box { width: 100%; font-size: 1rem; padding: 0.8rem; }
  .button-block { width: 90%; max-width: 300px; padding: 0.8rem; margin: auto; }
  .message-content { font-size: 1rem; }
   .roll-dice-button { width: 70px; height: 70px; font-size: 1rem; line-height: 1rem; }
   .confirm-button { width: 60px; height: 60px; font-size: 0.7rem; }
   .popup-content { padding: 1.5rem; font-size: 1.2rem; } }
   .disabled-button { opacity: 0.5; cursor: not-allowed; pointer-events: none; filter: grayscale(60%);
}
  </style>
</head>

<body>
<audio id="turnStartSound" src="sounds/switchTurn.wav" autoplay></audio>

<audio id="game-bgm" src="sounds/gameBGM.mp3" autoplay loop preload="auto" style="display: none;"></audio>
<script>
  window.addEventListener("DOMContentLoaded", () => {
    let bgm = document.getElementById("game-bgm");

    if (!bgm) {
      bgm = document.createElement("audio");
      bgm.id = "game-bgm";
      bgm.src = "sounds/gameBGM.mp3";
      bgm.loop = true;
      bgm.preload = "auto";
      bgm.style.display = "none";
      document.body.appendChild(bgm);
    }

    // Restore playback position if available
    const lastTime = sessionStorage.getItem("bgmTime");
    if (lastTime && !isNaN(lastTime)) {
      bgm.currentTime = parseFloat(lastTime);
    }

    // Try to play
    const tryPlay = () => {
      bgm.play().then(() => {
        sessionStorage.setItem("bgmStarted", "true");
      }).catch(() => {
        const unlock = () => {
          bgm.play().then(() => {
            sessionStorage.setItem("bgmStarted", "true");
          });
          document.removeEventListener("click", unlock);
        };
        document.addEventListener("click", unlock);
      });
    };

    tryPlay();

    // Save currentTime periodically
    setInterval(() => {
      if (!bgm.paused) {
        sessionStorage.setItem("bgmTime", bgm.currentTime);
      }
    }, 0);
  });
</script>

<div class="container">
  <!-- Top: Player List + Game Board -->
  <div class="top-wrapper">
  <div class="top-layout">
    <div class="player-column">
    <% Players currentPlayer = game.getCurrentPlayer(); %>
      <% for (Players p : players) { %>
      
    
<% boolean isActive = p.equals(currentPlayer); boolean canClick = isActive && !p.isSurrendering(); %>
<div class="player-box <%= canClick ? "clickable-surrender" : "" %>"
style="color: <%= p.getColor() %>; border: 3px solid
<%= isActive ? p.getColor() : "black" %>;
<%= isActive ? "animation: pulse-custom 1.2s ease-in-out infinite; box-shadow: 0 0 10px 4px " + p.getColor() + "99;" : "" %>" 
<%= canClick ? "onclick=\"triggerSurrender('" + p.getName() + "')\"" : "" %>>

      <div class="player-name"><%= p.getName() %></div>
      <div class="player-details">
        <div><img src="images/dollars.png" class="icon"/> <%= p.getMoney() %></div>
  <div><img src="images/land.png" class="icon"/> <%= p.getNumOfLandsOwn() %></div>
  <div><img src="images/freedom.png" class="icon"/> <%= p.getNumOfReleaseCard() %></div>
      <%-- surrender hover text --%>
      <% if (p.equals(currentPlayer)) { %>
        <div class="surrender-hover">Surrender</div>
      <% } %>
      </div>
    </div>
  <% } %>
    </div>
    <div class="game-board">
      <div class="monopoly-board"> 
      <% List<Grids> grids = game.getGridsList(); 
      List<Players> playersList = game.getPlayersList();
      for (int i = 0; i < grids.size(); i++) {
    	    Grids g = grids.get(i);
    	    boolean isSpecial = !(g instanceof Lands);

    	    String ownerColor = "";
    	    if (g instanceof Lands) {
    	      Players owner = ((Lands) g).getOwner();
    	      if (owner != null) {
    	        ownerColor = owner.getColor();
    	      }
    	    }

    	    // Collect all players on this grid
    	    List<Players> playersOnGrid = new ArrayList<>();
    	    for (Players p : playersList) {
    	      if (!p.getBankrupt() && p.getLocation() == i) {
    	        playersOnGrid.add(p);
    	      }
    	    }

    	    String gridStyle = GridHelper.getGridPositionStyle(i);
    	    if (!ownerColor.isEmpty()) {
    	      gridStyle += "background-color:" + ownerColor + "22;";
    	    }
    	%>

    	<div class="grid-box <%= isSpecial ? "special-grid" : "" %>" style="<%= gridStyle %>">
    	  <div class="grid-name"><%= g.getGridName() %></div>

    	  <% if (!isSpecial) { %>
    	    <div class="grid-price">$<%= ((Lands) g).getPrice() %></div>
    	  <% } %>

    	  <% if (!playersOnGrid.isEmpty()) { %>
    	    <div class="player-tokens">
    	      <% for (Players p : playersOnGrid) { %>
    	        <div class="token" style="background-color: <%= p.getColor() %>;" title="<%= p.getName() %>"></div>
    	      <% } %>
    	    </div>
    	  <% } %>
    	</div>

    	<% } // end for %>
		</div> 
    </div>
  </div>

  <!-- Bottom: History + Buttons -->
  <div class="bottom-layout">
    <div class="message-box">
  <div class="message-content">
    <%
      List<String> messages = (List<String>) session.getAttribute("gameMessages");
      if (messages != null) {
        for (String msg : messages) {
    %>
          <div class="message-line"><%= msg %></div>
    <%
        }
      }
    %>
  </div>
</div>
    <div class="button-block">
      <div class="action-buttons">
  <c:choose>
    <c:when test="${not empty confirmAction}">
      <form action="${confirmYesAction}" method="post">
        <button name="decision" type="submit" class="confirm-button yes-btn" value="yes"></button>
      </form>
      <form action="${confirmYesAction}" method="post">
        <button name="decision" type="submit" class="confirm-button no-btn" value="no"></button>
      </form>
    </c:when>
    <c:otherwise>
      <% Boolean canRoll = (Boolean) request.getAttribute("canRollDice"); %>
<form action="RollDiceServlet" method="post">
  <button 
    type="submit" 
    class="roll-dice-button" 
    <% if (canRoll != null && !canRoll) { %> disabled <% } %>>
    Roll<br>Dice
  </button>
</form>
    </c:otherwise>
  </c:choose>
</div>
    </div>
  </div>
  </div>
</div>
<script>
  function triggerSurrender(playerName) {
    if (confirm(playerName + " will surrender?")) {
      // Disable further interaction
      const box = document.querySelector(`.player-box[onclick*="${playerName}"]`);
      if (box) {
        box.onclick = null;
        box.classList.remove("clickable-surrender");
        box.style.pointerEvents = "none";
        box.style.opacity = "0.6"; // optional visual feedback

        // Optional: remove hover label
        const hoverText = box.querySelector(".surrender-hover");
        if (hoverText) hoverText.remove();

        // Optional: show feedback
        const status = document.createElement("div");
        status.textContent = "Surrendering...";
        status.style.fontSize = "0.9rem";
        status.style.color = "gray";
        box.appendChild(status);
      }

      // Submit form
      const form = document.createElement("form");
      form.method = "post";
      form.action = "SurrenderServlet";

      const input = document.createElement("input");
      input.type = "hidden";
      input.name = "playerName";
      input.value = playerName;

      form.appendChild(input);
      document.body.appendChild(form);
      form.submit();
    }
  }
</script>


<!-- for surrender p5js -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/p5.js/1.4.0/p5.min.js"></script>
<script src="js/SurrenderEffect.js"></script>

<!-- for history message block -->
<script>
  window.onload = function () {
    const box = document.querySelector('.message-box');
    if (box) {
      box.scrollTop = box.scrollHeight;
    }
  };
</script>

<form id="applyGridForm" action="ApplyGridEffectServlet" method="post" style="display: none;"></form>
<% Boolean apply = (Boolean) request.getAttribute("applyGridEffect"); %>
<script>
  <% if (apply != null && apply) { %>
      document.getElementById("applyGridForm").submit();
  <% } %>
</script>

<form id="autoNextTurnForm" action="NextTurnServlet" method="post" style="display: none;"></form>
<% Boolean nextTurn = (Boolean) request.getAttribute("nextTurn"); %>
<script>
  <% if (nextTurn != null && nextTurn) { %>
    setTimeout(() => {
      document.getElementById('autoNextTurnForm').submit();
    }, 1500); // delay in milliseconds
  <% } %>
</script>

<script>
  // Ping server every 4 seconds to keep session alive
  setInterval(() => {
    fetch('KeepAliveServlet');
  }, 4000);
</script>


<% Boolean ended = (Boolean) session.getAttribute("gameEnded"); %>
<% String winnerName = (String) session.getAttribute("winnerName"); %>
<script>
  <% if (ended != null && ended==true) { %>
    document.addEventListener("DOMContentLoaded", () => {
      // Show winner popup
      const popup = document.getElementById("winner-popup");
      if (popup) popup.style.display = "flex";

      // Disable all buttons
      const buttons = document.querySelectorAll("button");
      buttons.forEach(btn => {
        btn.disabled = true;
        btn.classList.add("disabled-button");
      });
    });
  <% } %>
</script>

<script>
	window.addEventListener("DOMContentLoaded", () => {
		  if (localStorage.getItem("soundUnlocked")) {
		    const sound = document.getElementById("turnStartSound");
		    sound?.play().catch(e => console.warn("Autoplay failed:", e));
		  }
		});
</script>

<div id="winner-popup" class="winner-popup">
  <div class="popup-content">
    🎉 <span id="winner-name"><%= winnerName %></span> wins the game! 🎉
  </div>
</div>

</body>
</html>
