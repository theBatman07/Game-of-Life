# Conway's Game of Life Java Implementation

Conway's Game of Life is a cellular automaton devised by mathematician John Conway. It's a zero-player game, meaning that its evolution is determined by its initial state, requiring no further input. This project provides a Java implementation of Conway's Game of Life with a graphical user interface using Swing.

## Table of Contents

- [Features](#features)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
- [Usage](#usage)
- [How to Play](#how-to-play)
- [Conway's Game of Life Rules](#conways-game-of-life-rules)
- [Contributing](#contributing)
- [License](#license)

## Features

- Graphical user interface (GUI) for interactive gameplay.
- Start, pause, reset, and step-by-step control of the simulation.
- Click to toggle cells and create custom patterns.
- Observe the dynamic evolution of the cell population based on Conway's rules.

## Getting Started

### Prerequisites

Before you begin, ensure you have met the following requirements:

- Java Development Kit (JDK) installed on your system.
- An Integrated Development Environment (IDE) such as Eclipse or IntelliJ IDEA is recommended but not required.

### Installation

1. Clone the repository to your local machine:

  ```bash
  git clone https://github.com/yourusername/game-of-life-java.git
  ```


2. Open the project in your preferred IDE.

3. Build and run the `GameOfLife.java` file to start the application.


## Usage

Once the application is running, you can:

- Click on cells to toggle them on and off.
- Use the "Play" button to start the simulation.
- Use the "Pause" button to pause the simulation.
- Use the "Step" button to advance the simulation one generation at a time.
- Use the "Reset" button to clear the board and start over.
- Observe the evolution of cells according to Conway's rules.

## How to Play

1. Launch the application.
2. Click on cells to create your initial configuration.
3. Click the "Play" button to start the simulation.
4. Observe how the cells evolve over time.
5. Experiment with different initial configurations to see how they affect the simulation.

## Conway's Game of Life Rules

Conway's Game of Life operates on a grid of cells, where each cell can be in one of two states: alive or dead. The rules for the simulation are as follows:

1. **Birth**: A dead cell with exactly three live neighbors becomes alive in the next generation.

2. **Survival**: A live cell with two or three live neighbors remains alive in the next generation.

3. **Death**: A live cell with fewer than two live neighbors dies of loneliness, and a live cell with more than three live neighbors dies of overcrowding in the next generation.


These simple rules lead to complex and fascinating patterns and behaviors in the evolving grid of cells.