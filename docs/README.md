## LDTS_T06_G08 - <AirHockey>

AirHockey is a classic arcade game for 1 or 2 players where each one of them control a disk and their goal is to score on the opponents net.
Best of 7 goals decide the winner. If, once the timer hits 1 minute of playing, the players hold the same score, the game proceeds to an "Overtime" where the winner is the first one to score.

This game was developed by João Morete (up202403406@up.pt), Luís Sampaio (up202404078@up.pt) and Tomás Silva (up202403435@up.pt).

### IMPLEMENTED FEATURES

- **Smooth in-game inputs** - Disks feel responsive, allowing for multiple key presses at the same time and key holding
- **Disk bounds** - Disks aren't able to leave their halfcourts
- **Overtime** - Once the countdown timer hits 0, either the game ends, in case a player is already winning, or, if there is a draw, an overtime starts - wins the first player to score.
- **Options Menu** - A new menu that allows players to customize the colors of the game elements such as the ball, disks and walls.
- **End Game Menu** - A new menu that shows the result of the game that just finished and gives players plenty of options on what to do next.
- **Keybinds** - During the game phase we added 2 new keybinds: <br> - **"q"** quit the game into the main menu; <br> - **"r"** reset the ball to the central position.
- **Game settings** - Players are allowed to change the colors of the game freely to what they like the most.

### PLANNED FEATURES

We were able to implement all the pretended features apart from the pause menu.

### DESIGN

#### GENERAL DEVELOPMENT PATTERN - MVC

**Problem in Context** 

The game required the Model-View-Controller design pattern. Mainly to ensure easier maintaining of the code in the future and also more concise testability.

**The Pattern**

MVC is a very used pattern that separates the game into three components:

- M (Model) - is responsible for the changes in the game such as creating the instances of the classes.
- V (View) - is responsible for the visual representation of the objects created by Model.
- C (Controller) - is responsible for the processing the elements movements during the game.

**Implementation**

Our implementation of this pattern was done as follows: 

- Model -> (model) Position;
            (model.game.elements) Ball, Disk, Element, Goal, Halfline, Wall;
            (model.game.field) Field, FieldBuilder, LoaderFieldBuilder;
            (model.menu) Menu, Options.

- View -> (viewer) Viewer;
           (viewer.game) BallViewer, DiskViewer, ElementViewer, GameViewer, HalflineViewer, WallViewer;
           (viewer.menu) MenuViewer, OptionsViewer.

- Controller -> (controller) Controller;
                   (controller.game) BallController, Disk1Controller, Disk2Controller, FieldController, GameController;
                   (controller.menu) MenuController, OptionsController.   

**Consequences**

The implementation of the MVC design pattern brought us the following benefits:

- Ease of maintaining and testing;
- Separate responsibilities - also, it makes it more difficult the for us to break the Single Responsibility Principle from SOLID;

#### STATE PATTERN

**Problem in Context**

States allow for better context in a point of (run)time and also allow us to have more control over what classes are being used and if they should be used in that state of the game. It also allows for better transitioning between game and all the menus.

**The Pattern**

The state pattern is a widely used pattern to address these types of problems. It is simple to implement and improves the quality of the development of the game.

**Implementation**

The Game class holds various state such as the GameState class and the MenuState class, and more. When the game starts, the game proceeds to the menu state and, from there, states change accordingly (as it is not possible to have more than one running at the same time).

**Consequences**

A clear, organized code that also allows for expansion with ease. It happened when we wanted to implement both que Options and the End screens to our game. We just created new states and called them in the right moment.

#### FACADE PATTERN

**Problem in Context**

Lanterna provides good methods to manipulate the terminal, and we used it as base to our game. It is though not perfect and, most of the time, is way too low-level and seems to overcomplicate.

**The Pattern**

We used the Facade design pattern to address this problem. It consists of an interface that simplify some of the low-level tasks that are needed.

**Implementation**

In our implementation, there were some classes where we applied this design pattern. Both the GUI and the LanternaGUI classes constitute a good example of the use of this design pattern.

**Consequences**

With the implementation of this design pattern we saw our work get simplified as we didn't have to deal with the low-level Lanterna modules.

#### KNOWN CODE SMELLS

All the code smells mentioned before were solved.

2. Position.java: We changed the primitive data type that holds the position from int to double so that it would be easier to calculate the balls 
    movement. The problem is that we need the position in int data type to be able to represent in the LanternaGUI.java class, so we got a fast 
    solution by rounding the values. We plan to implement functions in Position.class that allow us to solve this problem. 

### TESTING

### Screenshot of coverage report
![CoverageReport screenshot](docs/images/screenshots/final_coverageReport.png)

### Link to mutation testing report
[Mutation tests](../build/reports/pitest/index.html)

### SELF-EVALUATION

The work was split evenly among the three of us: 

- João Morete: 33.3%
- Luís Sampaio: 33.3%
- Tomás Vieira: 33.3%
