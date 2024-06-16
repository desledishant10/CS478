# Gopher Hunting

Gopher Hunting is an Android game where two bots play against each other to find a hidden gopher in a 10x10 grid. The first bot to find the gopher wins the game.

## Table of Contents

- [Project Description](#project-description)
- [Project Structure](#project-structure)
- [Installation](#installation)
- [Usage](#usage)
- [Permissions](#permissions)
- [How it Works](#how-it-works)
- [Contributing](#contributing)
- [License](#license)

## Project Description

The game is played by two worker threads, each running different algorithms to find the gopher. The game provides feedback on each guess, indicating if it was a success, a near miss, a close guess, or a complete miss. The app features a continuous-play mode, where the two threads play without interruption until one thread wins.


## Installation

1. Clone the repository:
https://github.com/desledishant10/CS478.git

2. Open the project in Android Studio.
3. Sync the project with Gradle files.
4. Build and run the project on an emulator or physical device.

## Usage

1. Open the app.
2. On the main screen, click "Start Game" to begin.
3. The game screen will show two 10x10 grids, one for each player.
4. The bots will play against each other until one of them finds the gopher.
5. Click "Stop Game" to end the game at any time.

## Permissions

The app requires the following permissions:
- `com.example.gopherhunting.PERMISSION_GAME_PLAYER`: This is a dangerous-level permission that must be granted to start the game.

## How it Works

- **MainActivity**: The entry point of the app. It has buttons to start and stop the game.
- **GameActivity**: Displays the game progress with two grids and the game status.
- **WorkerThread**: Represents a player in the game. Each thread runs a different algorithm to find the gopher.
- **CustomAdapter**: A custom adapter to display the grid items.

### Game Logic

- **Success**: The thread guesses the hole containing the gopher and wins the game.
- **Near miss**: The thread guesses one of the 8 holes adjacent to the gopher’s hole. The game continues with the other thread making a guess.
- **Close guess**: The thread guesses a hole that’s 2 holes away from the gopher’s hole in any direction.
- **Complete miss**: The thread gets this response in all other cases.

### UI Thread Responsibilities

- Setting up the game.
- Starting the worker threads.
- Showing the guesses of the two players on two separate tables.
- Providing feedback for each guess.



## Contributing

1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Make your changes.
4. Commit your changes (`git commit -m 'Add some feature'`).
5. Push to the branch (`git push origin feature-branch`).
6. Open a pull request.

