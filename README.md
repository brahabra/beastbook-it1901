[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2021/gr2132/gr2132)

# BeastBook

For in-depth description of documentation see `beastBook/README.md`

The core components are stored under `beastBook/core/src/main/java/beastbook`

The UI components are found under `beastBook/fxui/src/main/java/beastbook`

Documentation for the different releases are found in the `docs` folder

# Developer installation
To install the project, type `git clone https://gitlab.stud.idi.ntnu.no/it1901/groups-2021/gr2132/gr2132.git`

# How to build and run the project
We use maven to build and run this project
To build the project, type `cd beastBook` and then `mvn install`.
To run the project, type `cd fxui` and then `mvn javafx:run`, or `mvn javafx:run -f fxui/pom.xml`
