CelestialWizardry
=================

##Welcome to CelestialWizardry GitHub repository!
**LATEST OFFICIAL VERSION**: [0.1-DEV.11 for Minecraft 1.7.2](http://copy.com/RUcI6qxo3BXp/CelestialWizardry-1.7.2-0.1-DEV.11.jar)

[Compiling Mod](#compiling-celestial-wizardry) - For those that want the latest unreleased features.

[Contributing](#contributing) - For those that want to help out.

###Compiling Celestial Wizardry
IMPORTANT: Please report any issues you have, there might be some problems with the documentation!
Also make sure you know EXACTLY what you're doing!  It's not any of our faults if your OS crashes, becomes corrupted, etc.
***
[Setup Java](#setup-java)

[Setup Git](#setup-git)

[Setup Mod](#setup-celestial-wizardry)

[Compile Mod](#compile-celestial-wizardry)

[Updating Your Repository](#updating-your-repository)

####Setup Java
The Java JDK is used to compile Celestial Wizardry.

1. Download and install the Java JDK.
	* [Windows/Mac download link](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html).  Scroll down, accept the `Oracle Binary Code License Agreement for Java SE`, and download it (if you have a 64-bit OS, please download the 64-bit version).
	* Linux: Installation methods for certain popular flavors of Linux are listed below.  If your distribution is not listed, follow the instructions specific to your package manager or install it manually [here](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html).
		* Gentoo: `emerge dev-java/oracle-jdk-bin`
		* Archlinux: `pacman -S jdk7-openjdk`
		* Ubuntu/Debian: `apt-get install openjdk-7-jdk`
		* Fedora: `yum install java-1.7.0-openjdk`
2. Windows: Set environment variables for the JDK.
    * Go to `Control Panel\System and Security\System`, and click on `Advanced System Settings` on the left-hand side.
    * Click on `Environment Variables`.
    * Under `System Variables`, click `New`.
    * For `Variable Name`, input `JAVA_HOME`.
    * For `Variable Value`, input something similar to `C:\Program Files\Java\jdk1.7.0_55` exactly as shown (or wherever your Java JDK installation is), and click `Ok`.
    * Scroll down to a variable named `Path`, and double-click on it.
    * Append `;%JAVA_HOME%\bin` EXACTLY AS SHOWN and click `Ok`.  Make sure the location is correct; double-check just to make sure.
3. Open up your command line and run `javac`.  If it spews out a bunch of possible options and the usage, then you're good to go.  If not, try the steps again.

####Setup Git
Git is used to clone Celestial Wizardry and update your local copy.

1. Download and install Git [here](http://git-scm.com/download/).
	* *Optional*: Download and install a Git GUI client, such as Github for Windows/Mac, SmartGitHg, TortoiseGit, etc.  A nice list is available [here](http://git-scm.com/downloads/guis).

####Setup Celestial Wizardry
This section assumes that you're using the command-line version of Git.

1. Open up your command line.
2. Navigate to a place where you want to download Celestial Wizardry's source (eg `C:\Github\CelestialWizardry\`) by executing `cd [folder location]`.  This location is known as `mcdev` from now on.
3. Execute `git clone https://github.com/PizzAna/CelestialWizardry.git`.  This will download Celestial Wizardry's source into `mcdev`.
4. Right now, you should have a directory that looks something like:

***
	mcdev
	\-CelestialWizardry
		\-Celestial Wizardry's files (should have `build.gradle`)
***

####Compile Celestial Wizardry
**Please replace `gradle` with `gradlew.bat` if you are on Windows and with `gradlew` if you are on Linux**

1. Execute `gradle setupCiWorkspace`. This sets up Forge and downloads the necessary libraries to build Celestial Wizardry.  This might take some time, be patient.
	* You will generally only have to do this once until the Forge version in `build.properties` changes.
2. Execute `gradle build`. If you did everything right, `BUILD SUCCESSFUL` will be displayed after it finishes.  This should be relatively quick.
    * If you see `BUILD FAILED`, check the error output (it should be right around `BUILD FAILED`), fix everything (if possible), and try again.
3. Navigate to `mcdev\CelestialWizardry\build\libs`.
    *  You should see `.jar` files named `CelestialWizardry-1.7.2-*.jar`, `CelestialWizardry-1.7.2-*-api.jar`, `CelestialWizardry-1.7.2-*-deobf.jar` and `CelestialWizardry-1.7.2-*-src.jar`.
4. Copy the `CelestialWizardry-1.7.2-*.jar` jar into your Minecraft mods folder, and you are done!

####Updating Your Repository
In order to get the most up-to-date builds, you'll have to periodically update your local repository.

1. Open up your command line.
2. Navigate to `mcdev` in the console.
3. Make sure you have not made any changes to the local repository, or else there might be issues with Git.
	* If you have, try reverting them to the status that they were when you last updated your repository.
4. Execute `git pull origin`.  This pulls all commits from the official repository that do not yet exist on your local repository and updates it.

###Contributing
***
####General guidelines for contributing
You think you can improve our code? Good! Just make sure you follow these few simple rules in your code.

1. Always keep your code clean and well documented, so it's easier for us to see what you have done.
2. It is discouraged to copy other people's code. Believe it or not, we don't need you to do that.
3. While it is not very important, you should follow the formatting settings located [here](https://github.com/PizzAna/CelestialWizardry/blob/master/settings.jar). They are exported from PizzAna's IntelliJ Idea workspace and I'm not sure if they work in Eclipse. If they don't work for you or you don't want to use them, PizzAna does clean the code up pretty frequently.

####Submitting a PR
So you found a bug in our code? Think you can make it more efficient? Want to help in general? Great!

1. If you haven't already, create a GitHub account.
2. Click the `Fork` icon located at the top-right of this page (below your username).
3. Make the changes that you want to and commit them.
	* If you're making changes locally, you'll have to execute `git commit -a` and `git push` in your command line.
4. Click `Pull Request` at the right-hand side of the gray bar directly below your fork's name.
5. Click `Click to create a pull request for this comparison`, enter your PR's title, and create a detailed description telling us what you changed.
6. Click `Send pull request`, and wait for feedback!

####Creating an Issue
Celestial Wizardry crashes every time? Have a suggestion? Found a bug? Create an issue now!

1. Make sure your issue hasn't already been answered or fixed. Also think about whether your issue is a valid one before submitting it.
2. Go to [the issues page](https://github.com/PizzAna/CelestialWizardry/issues).
3. Click `New Issue` right below `Star` and `Fork`.
4. Enter your Issue's title (something that summarizes your issue), and then create a detailed description ("Hey, could you add/change xxx?" or "Hey, found an exploit:  stuff").
	* If you are reporting a bug report from an unofficial version, make sure you include the following:
		* Commit SHA (usually located in a changelog or the jar name itself)
		* ForgeModLoader log
		* Server log if applicable
		* Detailed description of the bug (steps to reproduce possibly) and pictures if applicable
5. Click `Submit new issue`, and wait for feedback!
