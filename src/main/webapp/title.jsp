<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Monopoly Title</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- P5.js -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/p5.js/1.4.0/p5.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/p5.js/1.4.0/addons/p5.sound.min.js"></script>

    <style>
    * {
     box-sizing: border-box;
}
 html, body {
     margin: 0;
     padding: 0;
     height: 100%;
     width: 100%;
     font-family: 'Segoe UI', sans-serif;
     background-color: #f7f7f7;
     display: flex;
     justify-content: center;
     align-items: center;
     background-image: url('images/skyBackground.jpg');
     background-size: cover;
     background-position: center;
     background-repeat: no-repeat;
}
 .container {
     width: 90vw;
     max-width: 500px;
     padding: 2em;
     display: flex;
     flex-direction: column;
     align-items: center;
     justify-content: center;
}
 .title-box {
     padding: 1.2em 2.2em;
     border: 3px solid black;
     font-size: 2.2em;
     margin-bottom: 1em;
     text-align: center;
     background-color: white;
     box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
     width: 100%;
}
 .start-button {
     padding: 12px 28px;
     font-size: 1.2rem;
     background-color: #4CAF50;
     color: white;
     border: none;
     border-radius: 12px;
     cursor: pointer;
     transition: background-color 0.3s ease;
     width: 60%;
     max-width: 250px;
}
 .start-button:hover {
     background-color: #15cf34;
}
 .fade-form {
     display: none;
     flex-direction: column;
     align-items: center;
     margin-top: 20px;
     animation: fadeIn 0.5s ease forwards;
     width: 100%;
}
 .player-inputs {
     display: grid;
     grid-template-columns: repeat(auto-fit, minmax(140px, 1fr));
     gap: 1rem;
     width: 100%;
}
 .player-inputs input, .player-inputs select {
     padding: 0.8em;
     font-size: 1em;
     border: 2px solid black;
     border-radius: 12px;
     text-align: center;
     width: 100%;
}
 .confirm-button {
     margin-top: 20px;
     padding: 12px 24px;
     font-size: 1.1rem;
     background-color: #6c5ce7;
     color: white;
     border: none;
     border-radius: 8px;
     cursor: pointer;
     transition: background-color 0.3s;
     width: 80%;
     max-width: 300px;
}
 .confirm-button:hover {
     background-color: #5a4bcf;
}
 @keyframes fadeIn {
     from {
         opacity: 0;
         transform: scale(0.95);
    }
     to {
         opacity: 1;
         transform: scale(1);
    }
}
 @media (max-width: 600px) {
     .title-box {
         font-size: 1.8em;
         padding: 1em;
    }
     .start-button, .confirm-button {
         font-size: 1rem;
    }
}
        /* {
            box-sizing: border-box;
        }

        html, body {
            margin: 0;
            padding: 0;
            height: 100%;
            width: 100%;
            font-family: 'Segoe UI', sans-serif;
            background-color: #f7f7f7;
        }

        body {
    		display: flex;
    		justify-content: center;
    		align-items: center;
    		background-image: url('images/skyBackground.jpg');
    		background-size: cover;
    		background-position: center;
    		background-repeat: no-repeat;
		}

        .container {
            width: 80vw;
            max-width: 500px;
            padding: 3em;
            
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
        }

        .title-box {
    		padding: 1.5em 3em;
    		border: 3px solid black;
    		font-size: 3em;
    		margin-bottom: 2em;
    		text-align: center;
    		background-color: white;
    		box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
		}

        .start-button {
    		padding: 12px 28px;
    		font-size: 1.2rem;
    		background-color: #4CAF50;
    		color: white;
    		border: none;
    		border-radius: 12px;
    		cursor: pointer;
    		transition: background-color 0.3s ease;
		}

		.start-button:hover {
		    background-color: #15cf34;
		}

        @media (max-width: 600px) {
            .title-box {
                font-size: 1.5em;
            }

            .start-button {
                font-size: 1em;
                padding: 0.6em 1.2em;
            }

            .container {
                width: 90vw;
                padding: 2em;
            }
        }
        
        .fade-form {
    		display: none;
    		flex-direction: column;
    		align-items: center;
    		margin-top: 20px;
    		animation: fadeIn 0.5s ease forwards;
		}

		.player-row {
    		margin: 10px 0;
    		display: flex;
    		gap: 10px;
    		flex-wrap: wrap;
    		justify-content: center;
		}

		.confirm-button {
    		margin-top: 20px;
    		padding: 12px 24px;
    		font-size: 1.1rem;
    		background-color: #6c5ce7;
    		color: white;
    		border: none;
    		border-radius: 8px;
    		cursor: pointer;
    		transition: background-color 0.3s;
		}

		.confirm-button:hover {
		    background-color: #5a4bcf;
		}

		.player-inputs {
  			display: grid;
  			grid-template-columns: repeat(4, 1fr);
  			gap: 1.5rem;
  			width: 100%;
  			max-width: 900px;
		}

		.player-inputs input,
		.player-inputs select {
  			padding: 0.8em;
  			font-size: 1em;
  			border: 2px solid black;
 			border-radius: 12px;
  			text-align: center;
		}

@keyframes fadeIn {
    from { opacity: 0; transform: scale(0.95); }
    to { opacity: 1; transform: scale(1); }
}*/

    </style>
</head>

<body>
<audio id="turnStartSound" src="sounds/switchTurn.wav" preload="auto"></audio>
<script>
  // Ask for sound permission on any first user interaction
  document.addEventListener("click", () => {
    const sound = document.getElementById("turnStartSound");
    if (sound && !localStorage.getItem("soundUnlocked")) {
      sound.play().then(() => {
        sound.pause(); // unlock the context, then pause immediately
        sound.currentTime = 0;
        localStorage.setItem("soundUnlocked", "true");
      }).catch(err => {
        console.warn("Sound permission not granted yet.", err);
      });
    }
  });
</script>

<!-- Main container -->
<div class="container">
    <div class="title-box">Monopoly</div>
    <audio id="bgm" src="sounds/springSky.mp3" autoplay loop></audio>

    <!-- Start Button -->
    <button class="start-button" onclick="showPlayerForm()">Start</button>

    <!-- Player Form (initially hidden) -->
    <form id="playerForm" action="StartGameServlet" method="post" class="fade-form">
        <div class="player-inputs">
            <input type="text" name="playerName1" placeholder="Player 1 Name" required />
    		<select name="playerColor1" required onchange="changeSelectColor(this)">
        		<option value="">Color</option>
        		<option value="#ff4d4d">Red</option>
        		<option value="#d9d204">Yellow</option>
        		<option value="#4da6ff">Blue</option>
        		<option value="#228922">Green</option>
        		<option value="#4bdbc6">Cyan</option>
        		<option value="#ed791a">Orange</option>
    		</select>
    		<input type="text" name="playerName2" placeholder="Player 2 Name" required />
    		<select name="playerColor2" required onchange="changeSelectColor(this)">
        		<option value="">Color</option>
        		<option value="#ff4d4d">Red</option>
        		<option value="#d9d204">Yellow</option>
        		<option value="#4da6ff">Blue</option>
        		<option value="#228922">Green</option>
        		<option value="#4bdbc6">Cyan</option>
        		<option value="#ed791a">Orange</option>
    		</select>
    		<input type="text" name="playerName3" placeholder="Player 3 Name" required />
    		<select name="playerColor3" required onchange="changeSelectColor(this)">
        		<option value="">Color</option>
        		<option value="#ff4d4d">Red</option>
        		<option value="#d9d204">Yellow</option>
        		<option value="#4da6ff">Blue</option>
        		<option value="#228922">Green</option>
        		<option value="#4bdbc6">Cyan</option>
        		<option value="#ed791a">Orange</option>
    		</select>
    		<input type="text" name="playerName4" placeholder="Player 4 Name" required />
    		<select name="playerColor4" required onchange="changeSelectColor(this)">
        		<option value="">Color</option>
        		<option value="#ff4d4d">Red</option>
        		<option value="#d9d204">Yellow</option>
        		<option value="#4da6ff">Blue</option>
        		<option value="#228922">Green</option>
        		<option value="#4bdbc6">Cyan</option>
        		<option value="#ed791a">Orange</option>
    		</select>
        </div>

        <audio id="clickSound" src="sounds/modernTechnologySelect.wav"></audio>

        <button type="submit" class="confirm-button" onclick="playClickSound()">Confirm and Start Game</button>
    </form>
</div>
    <script>
    function showPlayerForm() {
        const form = document.getElementById('playerForm');
        form.style.display = 'flex'; // trigger flex layout + fade-in
        playClickSound();
    }

    function playClickSound() {
        const sound = new Audio("sounds/modernTechnologySelect.wav");
        sound.play();
    }
    document.addEventListener('click', function () {
    	  const bgm = document.getElementById('bgm');
    	  if (bgm.paused) {
    	    bgm.play();
    	  }
    });
    function changeSelectColor(select) {
    	  select.style.color = select.value || 'black';
    }
    </script>
</body>
</html>
