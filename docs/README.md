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

> This section should be organized in different subsections, each describing a different design problem that you had to solve during the project. Each subsection should be organized in four different parts:

- **Problem in Context.** The description of the design context and the concrete problem that motivated the instantiation of the pattern. Someone else other than the original developer should be able to read and understand all the motivations for the decisions made. When refering to the implementation before the pattern was applied, don’t forget to [link to the relevant lines of code](https://help.github.com/en/articles/creating-a-permanent-link-to-a-code-snippet) in the appropriate version.
- **The Pattern.** Identify the design pattern to be applied, why it was selected and how it is a good fit considering the existing design context and the problem at hand.
- **Implementation.** Show how the pattern roles, operations and associations were mapped to the concrete design classes. Illustrate it with a UML class diagram, and refer to the corresponding source code with links to the relevant lines (these should be [relative links](https://help.github.com/en/articles/about-readmes#relative-links-and-image-paths-in-readme-files)). When doing this, always point to the latest version of the code.
- **Consequences.** Benefits and liabilities of the design after the pattern instantiation, eventually comparing these consequences with those of alternative solutions.

#### GENERAL DEVELOPEMENT PATTERN - MVC

**Problem in Context** 

The game required the Model-View-Controller design pattern. Mainly to ensure easier maintaining of the code in the future and also more concise testability.

**The Pattern**

MVC is a very used pattern that separates the game into three components:
- M (Model) - is responsible for the changes in the game such as creating the elements

**Example of one of such subsections**:

------

#### THE JUMP ACTION OF THE KANGAROOBOY SHOULD BEHAVE DIFFERENTLY DEPENDING ON ITS STATE

**Problem in Context**

There was a lot of scattered conditional logic when deciding how the KangarooBoy should behave when jumping, as the jumps should be different depending on the items that came to his possession during the game (an helix will alow him to fly, driking a potion will allow him to jump double the height, etc.). This is a violation of the **Single Responsability Principle**. We could concentrate all the conditional logic in the same method to circumscribe the issue to that one method but the **Single Responsability Principle** would still be violated.

**The Pattern**

We have applied the **State** pattern. This pattern allows you to represent different states with different subclasses. We can switch to a different state of the application by switching to another implementation (i.e., another subclass). This pattern allowed to address the identified problems because […].

**Implementation**

The following figure shows how the pattern’s roles were mapped to the application classes.

![img](https://www.fe.up.pt/~arestivo/page/img/examples/lpoo/state.svg)

These classes can be found in the following files:

- [Character](https://web.fe.up.pt/~arestivo/page/courses/2021/lpoo/template/src/main/java/Character.java)
- [JumpAbilityState](https://web.fe.up.pt/~arestivo/page/courses/2021/lpoo/template/src/main/java/JumpAbilityState.java)
- [DoubleJumpState](https://web.fe.up.pt/~arestivo/page/courses/2021/lpoo/template/src/main/java/DoubleJumpState.java)
- [HelicopterState](https://web.fe.up.pt/~arestivo/page/courses/2021/lpoo/template/src/main/java/HelicopterState.java)
- [IncreasedGravityState](https://web.fe.up.pt/~arestivo/page/courses/2021/lpoo/template/src/main/java/IncreasedGravityState.java)

**Consequences**

The use of the State Pattern in the current design allows the following benefits:

- The several states that represent the character’s hability to jump become explicit in the code, instead of relying on a series of flags.
- We don’t need to have a long set of conditional if or switch statements associated with the various states; instead, polimorphism is used to activate the right behavior.
- There are now more classes and instances to manage, but still in a reasonable number.

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

- Screenshot of coverage report.
- Link to mutation testing report.

### SELF-EVALUATION

Everyone in the group contributed equally to this project.

- João Morete: 33.3%
- Luís Sampaio: 33.3%
- Tomás Vieira: 33.3%
