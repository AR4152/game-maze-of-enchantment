# User Stories

For rough schedule, see [Milestones]() tab. We have created issues for all the below user stories and have given a due date. Most have been split up into different sprints.

## User Stories for "Movement" Feature

1. Basic Movement 
   - Story: As a player, I want my character to move within the maze, exploring different paths. 
   - Acceptance Criteria:
     - The player should be able to move their character using keyboard inputs (W, A, S, D). 
     - The character should move one step at a time. 
     - The character's movement should be constrained within the boundaries of the maze.

2. Multi-Level Navigation 
   - Story: As a player, I want my character to move between different levels of the maze, adding depth to the gameplay. 
   - Acceptance Criteria:
     - The player should be able to move one level up or one level down when applicable. 
     - The character's movement between levels should be seamless and constrained by the maze's structure.

3. Collision Detection 
   - Story: As a player, I want the game to detect collisions with walls and obstacles to prevent unintended movement. 
   - Acceptance Criteria:
     - The game should prevent the character from moving through walls or obstacles. 
     - When the character attempts to move into a wall or obstacle, the game should display an appropriate message.

### User Stories for "Inventory System" Feature

1. Item Collection
   - Story: As a player, I want to be able to collect items I find in the maze to aid my journey. 
   - Acceptance Criteria: 
     - The player should be able to interact with items found in the maze by moving onto their location. 
     - Collected items should be added to the player's inventory.

2. Inventory Management
   - Story: As a player, I want to manage the items in my inventory efficiently. 
   - Acceptance Criteria: 
     - The player should be able to open the inventory screen to view collected items. 
     - Items in the inventory should be displayed with their names and descriptions. 
     - The player should be able to select items to use, drop, or examine them.

3. Inventory Capacity
   - Story: As a player, I want my inventory to have a limited capacity, adding a strategic element to item collection. 
   - Acceptance Criteria: 
     - The player's inventory should have a maximum capacity (e.g., 10 slots). 
     - When the inventory is full, the player should receive a clear message when attempting to pick up additional items.

4. Item Use and Effects
   - Story: As a player, I want to use items from my inventory and see their effects on my character and the game world. 
   - Acceptance Criteria:
     - The player should be able to select and use items from the inventory. 
     - Using items should have visible effects, such as restoring health or providing temporary boosts.


### User Stories for "Maze/Map/Level" Feature

1. Maze feature
   - User Story: As a player who seeks adventure, I want to experience a variety of maze layouts so that the game  is more challenging. 
   - Acceptance Criteria: Include different maze themes like forest, futuristic, etc. 

2. Maze feature
   - User Story: As a player who enjoys surprise, I want to encounter interactive objects within the maze that affect gameplay so that the process would be more interesting. 
   - Acceptance Criteria: Include objects like teleporters or special powers that can be collected to impact the player's progress and help them to advance further.

3. Map feature
   - User Story: As a curious player, I want to discover hidden passageways within the maze so that there are surprises and shortcuts available. 
   - Acceptance Criteria: Include secret passages and doors that are not too easily found which could lead to hidden treasures or shortcuts

4. Level feature
   - User Story: As a determined player, I want to explore different levels of the game so that I can play in unique mazes or maps. 
   - Acceptance Criteria: Multiple levels are available for gameplay. Each level has a unique design so that players remain engaged.

5. Level feature
   - User Story: As a level designer for the maze, I want access to a maze editor tool so that I can create my custom levels. 
   - Acceptance Criteria: Have a user-friendly interface for creating, editing, and testing levels so that players can cater to their own interests and level of difficulties/


### User Stories for "Enemies" Feature

1. Types of enemies
   - User Story: As a player who seeks thrill, I want to encounter various types of enemies in the game so that it is more exciting.
   - Acceptance Criteria: Have different enemy types like monsters, zombies, etc.

2. Behaviors of enemies
   - User Story: As a game designer, I want enemies to have different behaviors so that the game is dynamic and requires strategic thinking from the players..
   - Acceptance Criteria: Allow enemies to have unique behaviors like flying, patrolling and chasing. Some enemies may be in groups to coordinate attacks while others attack individually.

3. Health of Ememies 
   - User Story: As a game designer, I want enemies to have varying health levels and inflict different amounts of damage for added challenges so that players can be more invested in the game.
   - Acceptance Criteria: Enemies can harm the player upon contact and have visible health bars. Different enemy types then cause varying damage to the player.

4. Boss enemies
   - User Story: As an experienced player, I want to encounter boss enemies at certain levels for bigger battles so that I can look forward to advancing in the game.
   - Acceptance Criteria: Have special boss characters that are larger and more powerful to make the boss fights memorable. 

5. Incentives to kill enemies
   - User Story: As a player, I want enemies to drop items upon defeat so that the rewards can incentivize me to fight them instead of avoiding them.
   - Acceptance Criteria: Make some enemies drop rewards like blood to improve the players’ health, or other useful combat items like swords and guns. Ensure that such reward drops are well-balanced to enhance gameplay but do not shift the focus of the game from advancement in the maze level to combating enemies. 


### User Stories for "Saving and loading the game" Feature

1. Manually save game
   - User Story: As a player with other commitments, I want to be able to save my progress in the game and load it later so that I can continue where I stopped whenever I am free. 
   - Acceptance Criteria: Players can manually save their game at any point in the game and the saved game data can be loaded and resumed.

2. Auto-save game 
   - User Story: As a responsible game designer, I want the game to auto-save at designated checkpoints so that players won't lose their progress. 
   - Acceptance Criteria: The game would automatically save at the start of each level. 

3. Loading the game
   - User Story: As a player who wants to enter the game fast, I want the game to load my last saved game to resume where I left off quickly.
   - Acceptance Criteria: Include a "Quick Load" option on the main menu which loads the most recently saved file. This feature should be made accessible without entering the main load menu.


### User Stories for additional features 

1. Multiple difficulties 
   - User Story: As a player who wants to enter the game fast, I want to choose from different difficulty levels to cater to my skills level. 
   - Acceptance Criteria: Provide 3 different difficulty levels for the game (Easy, Normal, Hard) and adjust the complexity of the maze and enemy behaviors accordingly.

2. Tutorial mode
   - User story: As a new player to the game, I want to have an interactive tutorial so that it can guide and help me to understand and navigate through the game. 
   - Acceptance criteria: Include on-screen prompts for basic actions when a new player just started the game and provide a manual for players to read to understand the basics of the game such as how to level up, combat enemies, use rewards, etc.

3. Achievements system
   - User story: As a player, I want to earn achievements after accomplishing certain tasks so that I am motivated to keep playing.
   - Acceptance Criteria: Include a list of achievable milestones and goals for players to accomplish and provide badges upon completion. 

4. Power-up abilities
   - User Story: As a player, I want to be able to have power-ups that grant special abilities so that I can have advantages to advance during the game. 
   - Acceptance Criteria: Have a variety of power-ups like speed boost, invisibility, teleportation to enhance the players’ abilities.

5. Leaderboards
   - User Story: As a competitive player, I want to see leaderboards so that I can compare my performance with other players. 
   - Acceptance Criteria: Display the top 20 player levels and rankings for both global and local leaderboards. Global leaderboard includes all players of the game from around the whole and local leaderboards only include friends that the player added personally.

6. Environmental changes
   - User Story: As a game designer, I want the game to have dynamic environmental changes so that it can be more realistic. 
   - Acceptance Criteria: Weather conditions like having  rain, snow, or fog could change randomly based on in-game events. There could also be day-night cycles to match with the actual timings that the player are playing the game, or changing seasons to enhance the visuals of the game.

7. Character customization
   - User Story: As a player, I want to customize my character's appearance so that I can have a personalized gaming experience. 
   - Acceptance Criteria: There should be a character creation option for selecting the appearance, clothing, and accessories for the character to be more aesthetic and personalized.

8. Interactive NPCs
   - User Story: As a game designer, I want the NPCS like enemies or bosses to offer information for quests or instructions to the players so that the game is interactive. 
   - Acceptance Criteria: NPCs can have dialogues and provide choices to the players when choosing their quests and side missions which could affect the game's story.

9. Multiplayer mode
   - User Story: As a team player who often plays games with friends, I want the game to have a multiplayer mode so that I can play with friends or other online players.
   - Acceptance Criteria: The game could support 2-4 players in a cooperative game mode where there are shared objectives and challenges for the teams.

10. Soundtrack and audio
    - User Story: As a game designer , I want the game to have soundtracks and audio effects that adapt to the game as its story unfolds so that players can have a more immersive experience. 
    - Acceptance Criteria: Include dynamic music that changes based on the player's actions or the game's events and audio of dialogues during the game.

### Newly Added User Stories for DevOps
1. As a developer, I want a version control system (e.g., Git) integrated with our CI/CD pipeline so that I can easily track changes, collaborate with the team, and trigger automated builds and deployments.
2. As a developer, I want to set up a new Java Maven project with a predefined project structure and dependencies so that I can quickly start working on new features without spending time on project setup.
3. As a QA engineer, I want automated testing integrated into our CI/CD pipeline so that I can run tests on every code change and receive immediate feedback on the quality of the application.
4. As a developer, I want to generate comprehensive reports (e.g., code coverage, test results) during the Maven build process, enabling us to assess the quality of our codebase and identify areas for improvement.
5. As a release manager, I want to use Maven to create distributable artifacts (e.g., JAR, WAR) and store them in a repository for version control and deployment, ensuring that each release is properly documented.
