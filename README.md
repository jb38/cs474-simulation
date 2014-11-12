# cs474-simulation

## Set Up Build Environment

### Install Java Development Kit 8

http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

### Install Apache Maven 3

http://maven.apache.org/download.cgi

#### Add MASON to the Local Maven Repository

 1. Download the [MASON library](http://cs.gmu.edu/~eclab/projects/mason/mason.18.jar) (.jar file)

 1. Execute the following command:

        mvn install:install-file -Dfile=mason.18.jar -DartifactId=mason -DgroupId=edu.gmu.cs -Dversion=18.0 -Dpackaging=jar

### Install Eclipse Luna

 1. Download the **Eclipse IDE for Java Developers** package from the [Eclipse Downloads Page](http://www.eclipse.org/downloads)

 1. Unzip the file and place the **eclipse** directory... ...somewhere (you pick).

### Clone the Git Repository

 1. If you don't have a **git** client installed, [follow this guide](https://help.github.com/articles/set-up-git/#setting-up-git).

 1. Execute the following command

        git clone https://github.com/jb38/cs474-simulation.git

### Import the `cs474-simulation` project into Eclipse

 1. Open eclipse (double-click on eclipse.exe (Windows) or eclipse.app (OS X))

 1. In the **Project Explorer** window:

   * Right-click > Import...
   * Maven > Existing Maven Projects > Next
   * Browse... > Look for the parent folder of where you cloned the git repository
   * Check the box next to `cs474-simulation`
   * Finish

### Edit code

  Hint: be awesome

### Commit changes

  1. In **Eclipse**:

    * Right-click on the `cs474-simulation` project
    * Team > Commit
    * Enter a message describing the updates you just made
    * Commit
    * Right-click on the `cs474-simulation` project
    * Team > Commit to Upstream

### Compile and Execute

  1. To build (to a jar):

        mvn clean package

  1. To execute (NOTE: you should have flights.db saved from Google Drive to your current directory):

        java -jar ./target/cs474-simulation-0.0.1-SNAPSHOT.jar
