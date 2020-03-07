Student Name: Kaushik Nadimpalli
Class: CS3345.003
Project 5


***********I did not use an IDE for this project. I used the Command Prompt(Terminal on Mac which I use) to compile and run the program.*********

Steps:
1) First Download File and save it in on the computer - Desktop or elsewhere(Assuming Desktop in this case)
2) Let’s say the .java downloaded file is in C directory, we must first set the path and right tools in the case the computer does not already have the set path.
3) Run prompt: C:\> cd \Desktop
4) This makes desktop the current directory
5) Run following command then: C:\> cd \Desktop
6)This displays the directory contents. You should see ProjectFive.java.
7) From there, run the following command: C:\Desktop> set path=%path%;C:\Program Files\Java\jdk1.5.0_09\bin
8) This tells the system where to find JDK programs.
9) Then run the following command: C:\Desktop> javac ProjectFive.java
10) Above command runs javac.exe, the compiler. You should see nothing but the next system prompt...
11)You should see C:\Desktop> dir which means that the javac has created the ProjectFive.class file. You should see ProjectFive.java and ProjectFive.class among the files.


Easier Instructions(IF all correct paths and SDK tools already are installed)
1) Download .java file
2) go to terminal and direct yourself to the directory that contains the ProjectFive.java file
3) Type: "javac ProjectFive.java" which will build the object file.
4) Run the program with following command: "java ProjectFive" 



Program Structure and way to run it:

The purpose of this program is to use in-place quick sort and sort different sized lists with different choices of how the pivot is chosen. NOTE - In my program, I gave the option to the user even when it comes to which way he wants to determine the pivot. The instructors were not too clear on this therefore I suggest using the same size and running the program multiple times. Since they are all randomly chosen arrays, the ONLY issue in my program that might hinder the representation is that we can't use the same list and sort it with the different choices. HOWEVER, I tested my methods using different sizes, and they all work like they are supposed to. THE PROGRAM ALSO CALCULATES THE RUNTIME OF THE EXECUTION of the sorting in nanoseconds. Again, in order to compare the different options, the program has to be run multiple times with similar sizes. It also prints the lists into unsorted.txt and sorted.txt(for clarity, depending on the choice user picked it prints it with different name). Sample output is provided below.

Size 100 - OPTION 1 - First element as pivot

Runtime is:PT0.000003985S

Size 1000 - OPTION 1 - First element as pivot

Runtime is:PT0.000000873S

Size 5000 - OPTION 1 - First element as pivot

Runtime is:PT0.0000005S



Size 50000 - OPTION 1 - First element as pivot

Runtime is:PT0.000000145S


Size 100 - OPTION 2 - First element as pivot

Runtime is:PT0.000000498S

Size 5000 - OPTION 2 - First element as pivot

Runtime is:PT0.000000441S

Size 50000 - OPTION 2 - First element as pivot

Runtime is:PT0.000000219S

Size 100 - OPTION 3 - First element as pivot

Runtime is:PT0.000000655S

Size 5000 - OPTION 3 - First element as pivot

Runtime is:PT0.000000421S

Size 50000 - OPTION 3 - First element as pivot

Runtime is:PT0.000000126S

Size 100 - OPTION 4 - First element as pivot

Runtime is:PT0.000000468S

Size 5000 - OPTION 4 - First element as pivot

Runtime is:PT0.000000482S

Size 50000 - OPTION 4 - First element as pivot

Runtime is:PT0.000000291S

As we can see there is a noticeable difference depending on the way the pivot is chosen. We can also analyze the fact that option 1 and 4 are faster but only with certain sizes. We can look at the text files to also check how there are some errors with sorting when we sort using certain option instead of another.
