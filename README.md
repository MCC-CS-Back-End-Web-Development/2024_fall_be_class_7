<div align="center">
    <h2 align="center">Class 7 Java Lab</h2>
</div>

<div align="center">

[![License][license-badge]][license] [![Issues][issues-badge]][issues] [![PRs][prs-badge]][prs] [![Evan][evan-badge]][evan]

</div>

# Fantasy Football Lineup Optimizer

Alright class... I am struggling with my work fantasy football league and I need your help to write an algorithm to help me set my line up for next week. Please write a program that will take in a JSON object of my players. The program will then use the scores from the first three games of the season, average them out, and determine what my lineup should be.

## Console

```
Welcome to the Fantasy Football Lineup Generator
------------------------------------------------

Enter the player input file location: C:\\dev\\players.json

Input file not found

Enter the player input file location: C:\\dev\\code\\mcc\\players.json

Players Found:
- J. Allen
- A. Jones
- R. Penny
- D. Adams
- B. Cooks
- D. Goedert
- M. Evans
- R. Gould
- Packers
- T. Brady
- J. Conner
- D. Montgomery
- M. Hardman
- A. St. Brown
- D. Schultz


Man these players are awful you need all the help you can get!

Enter output Directory: C:\\dev\\code\\mcc

Successfully Generated Your Next Lineup Report!
```

## Sample Input File
```
[
    {
        "name": "J. Allen",
        "position": "QB",
        "scores":
                {
                    "game1": 31.48,
                    "game2": 29.68,
                    "game3": 26.7
                }
    },
    {
        "name": "A. Jones",
        "position": "RB",
        "scores":
                {
                    "game1": 10.6,
                    "game2": 32,
                    "game3": 5.7
                }
    },
    {
        "name": "R. Penny",
        "position": "RB",
        "scores":
                {
                    "game1": 8.7,
                    "game2": 1.5,
                    "game3": 7.9
                }
    },
    {
        "name": "D. Adams",
        "position": "WR",
        "scores":
                {
                    "game1": 30.10,
                    "game2": 9.2,
                    "game3": 14.2
                }
    },
    {
        "name": "B. Cooks",
        "position": "WR",
        "scores":
                {
                    "game1": 15.2,
                    "game2": 9.4,
                    "game3": 4.2
                }
    },
    {
        "name": "D. Goedert",
        "position": "TE",
        "scores":
                {
                    "game1": 9,
                    "game2": 13.2,
                    "game3": 11.6
                }
    },
    {
        "name": "M. Evans",
        "position": "WR",
        "scores":
                {
                    "game1": 18.1,
                    "game2": 9.1,
                    "game3": 0.0
                }
    },
    {
        "name": "R. Gould",
        "position": "K",
        "scores":
                {
                    "game1": 4,
                    "game2": 9,
                    "game3": 6
                }
    },
    {
        "name": "Packers",
        "position": "DEF",
        "scores":
                {
                    "game1": 1.0,
                    "game2": 9.0,
                    "game3": 11.0
                }
    },
    {
        "name": "T. Brady",
        "position": "QB",
        "scores":
                {
                    "game1": 10.38,
                    "game2": 9.4,
                    "game3": 14.74
                }
    },
    {
        "name": "J. Conner",
        "position": "RB",
        "scores":
                {
                    "game1": 16.5,
                    "game2": 7.1,
                    "game3": 8.70
                }
    },
    {
        "name": "D. Montgomery",
        "position": "RB",
        "scores":
                {
                    "game1": 8,
                    "game2": 15.6,
                    "game3": 1.1
                }
    },
    {
        "name": "M. Hardman",
        "position": "WR",
        "scores":
                {
                    "game1": 10.6,
                    "game2": 7.5,
                    "game3": 1.2
                }
    },
    {
        "name": "A. St. Brown",
        "position": "WR",
        "scores":
                {
                    "game1": 20.4,
                    "game2": 39.4,
                    "game3": 13.3
                }
    },
    {
        "name": "D. Schultz",
        "position": "TE",
        "scores":
                {
                    "game1": 13.2,
                    "game2": 1.8,
                    "game3": 7.93
                }
    }
]
```

## Sample Output File
```
Here is your lineup:
RB - A. Jones
RB - J. Conner
QB - J. Allen
TE - D. Goedert
DEF - Packers
WR - A. St. Brown
WR - D. Adams
K - R. Gould
FLEX - B. Cooks

------------------------------

Bench:
RB - R. Penny
WR - M. Evans
QB - T. Brady
RB - D. Montgomery
WR - M. Hardman
TE - D. Schultz

```

## Specifications
Follow these specifications for the project. This is your rubric.
* All exceptions need to be handled in a proper way. There should be no throws declaration in the main method signature.
* All File Readers/Writers should be closed in a finally block
* There should be a model object for our ```Player``` that has the following attributes:
    * Name
    * Position
    * A Map of Games to Scores: gameToScores
* The file provided will be valid JSON so no validation will be needed there
* Use lists/streams/arrays where applicable
* For those of you new to fantasy football:
    * The output file should display the following:
        * Top QB
        * Top two RBs
        * Top two WRs
        * Top TE
        * Top remaining WR, RB, or TE for the FLEX position
        * Top K
        * Top DEF
        * All remaining players will be on the Bench
* When the output file should be generated in the specified directory with the following name ```${todays-date}-lineup.txt```
* Output file should match the sample
    * Note positions can be in any order as long as the lineup is above the bench
* Console output should match the sample

## Starter Code
I wrote the below code that will parse JSON file and return a list of Players for you. You will need to call this method after you have read in the contents of the file.
```
private static List<Player> parsePlayerJson(String fileContent){
        Pattern names = Pattern.compile("\"name\": \"(.*)\"");
        Pattern positions = Pattern.compile("\"position\": \"(.*)\"");
        Pattern game1 = Pattern.compile("\"game1\": (.*)");
        Pattern game2 = Pattern.compile("\"game2\": (.*)");
        Pattern game3 = Pattern.compile("\"game3\": (.*)");
        Matcher matcher = names.matcher(fileContent);

        List<Player> players = new ArrayList<>();

        while(matcher.find()){
            Player player = new Player();
            player.setName(matcher.group(1));
            players.add(player);
        }

        int count = 0;

        matcher = positions.matcher(fileContent);
        while(matcher.find()){
            players.get(count).setPosition(matcher.group(1));
            count++;
        }
        count = 0;

        matcher = game1.matcher(fileContent);
        while(matcher.find()){
            Map<String, Double> gameToPoints = new HashMap<>();
            gameToPoints.put("game1", Double.valueOf(matcher.group(1).replace(",", "")));
            players.get(count).setGameToScores(gameToPoints);
            count++;
        }
        count = 0;

        matcher = game2.matcher(fileContent);
        while(matcher.find()){
            players.get(count).getGameToScores().put("game2", Double.valueOf(matcher.group(1).replace(",", "")));
            count++;
        }
        count = 0;

        matcher = game3.matcher(fileContent);
        while(matcher.find()){
            players.get(count).getGameToScores().put("game3", Double.valueOf(matcher.group(1).replace(",", "")));
            count++;
        }

        return players;
    }
```


<!--
Link References
-->
[license]: https://gitlab.mccinfo.net/code-school/cohort-4/pi1/java/-/blob/main/LICENSE "Our license"
[issues]: https://gitlab.mccinfo.net/code-school/cohort-4/pi1/java/-/issues "View or log an issue"
[prs]: https://gitlab.mccinfo.net/code-school/cohort-4/pi1/java/-/merge_requests "Feel free to submit a PR!"
[evan]: mailto:estohlmann.mcc@gmail.com "Email with any problems or concerns!"

<!--
Badge References
-->
[license-badge]: https://img.shields.io/badge/License-EULA-A31F34?&style=for-the-badge&labelColor=0057b8
[issues-badge]: https://img.shields.io/badge/issues-report-red.svg?style=for-the-badge&labelColor=0057b8
[prs-badge]: https://img.shields.io/badge/prs-welcomed-green.svg?style=for-the-badge&labelColor=0057b8
[evan-badge]: https://img.shields.io/badge/email-evanstohlmann-6463a9.svg?style=for-the-badge&labelColor=0057b8
