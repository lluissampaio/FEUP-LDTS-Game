## LDTS_T06_G08 - <AirHockey>

AirHockey is a classic arcade game for 1 or 2 players where each one of them control a disk and their goal is to score on the opponents net.
Best of 7 goals decide the winner. If, once the timer hits 1 minute of playing, the players hold the same score, the game proceeds to an "Overtime" where the winner is the first one to score.

This game was developed by João Morete (up202403406@up.pt), Luís Sampaio (up202404078@up.pt) and Tomás Silva (up202403435@up.pt).

### IMPLEMENTED FEATURES

- **Smooth in-game inputs** - Disks feel responsive, allowing for multiple key presses at the same time and key holding
- **Ball collision with walls** - When the ball hits a wall, it will bounce off the wall, while losing some speed
- **Disk bounds** - Disks aren't able to leave their halfcourts

### PLANNED FEATURES

- **Singleplayer mode** - One of the disks is controller by a bot, instead of another player
- **Pause Menu** - Players will be able to pause the game
- **Ball collision with disks** - When a disk hits a ball, the ball will bounce off it, with accurate velocity and direction, depending on how the disk hits it
- **Customization** - The player may use the options menu to change things like the ball, disk and wall colors

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

#### KNOWN CODE SMELLS

1. Hard-coded values: Throughout our code, at times we found the need to use some constant primitive data types to be able to build the game. 
    The problem is we started with one, and then another, and soon enough we got a lot of values that are hard-coded. 
    Some examples would be the colors of the game, such as the background or elements' colors, also the Menu's options' positions or the 
    constant that we use to simulate friction. We will eventually solve this problem by creating a class that holds all the constants of the game.

2. Position.java: We changed the primitive data type that holds the position from int to double so that it would be easier to calculate the balls 
    movement. The problem is that we need the position in int data type to be able to represent in the LanternaGUI.java class, so we got a fast 
    solution by rounding the values. We plan to implement functions in Position.class that allow us to solve this problem. 

3. Disk classes: We think that Disk1 and Disk2 are very similar classes and this is normally not a good sign. On the other hand
    this was the way we thought was best for our game to work. As opposed to the previous code smells, we do not plan to make changes
    on this one.

4. The menu inputs: Since replacing the Lanterna's keystrokes for the java's key listeners as the way our game reads inputs we think that the 
    menu inputs are not as responsive as we intended to. We changed the menu's frames per second so that it would be easier for the user to 
    navigate. We plan to improve this.

5. The game window: We found very difficult to create a game that does not break when we resize it. We would like to be able to fullscreen the game 
    in any machine.

### TESTING

### Screenshot of coverage report
![CoverageReport screenshot](docs/images/screenshots/coverageReport.png)

### Link to mutation testing report
[Mutation tests](../build/reports/pitest/index.html)

### SELF-EVALUATION

Everyone in the group contributed equally to this project.

- João Morete: 33.3%
- Luís Sampaio: 33.3%
- Tomás Vieira: 33.3%
