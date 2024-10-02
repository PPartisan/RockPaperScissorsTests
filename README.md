# How to Contribute

Contributing to open-source is a great way to improve your coding skills, and this project
was specifically set up to help people
like you improve your skills and become more familiar
with how to contribute to such projects.

### Prerequisites
1. Install Git
2. Set up a GitHub account
3. Install an IDE (preferably [IntelliJ](https://www.jetbrains.com/idea/) though VSCode may also be a good choice)
4. Kotlin comes installed with IntelliJ, if you are using another IDE you will need to install it

### Cloning the repo
1. Go to the repository on GitHub and click on the green Code button,
then copy the HTTPS address ending in ```.git```
2. In the terminal, ```cd``` into the IdeaProjects folder e.g. 
```C:\Users\person\IdeaProjects```, clone the repository with ```git clone ``` 
followed by the address you copied in step 1.

You will now have a copy of the repository in your own local system.
The changes you make will not directly affect the remote repository until
you have specifically requested this using git commands. 

3. Navigate to the branch you want to start from
with ```git checkout ``` followed by the name of the branch you want to be on.
Create a new branch to work in using ```git checkout -b ``` followed by
the name of whatever you want your branch to be called (preferably something
meaningful). 

Now make your contributions... When you are ready you can
submit your changes using the following steps.

4. Git will already be initialized so ```git init``` will not be needed to be
set up, neither should ```git remote add origin ``` be needed to be used. Add the files
to your local git repository using ```git add ``` followed by the name of files.
5. Add a commit message with ```git commit -m ``` followed by a short message explain your changes
as a string e.g. ```"Added cool new feature"```
6. Push the changes with ```git push origin ``` followed by the name of your new branch.

Now your changes should be visible in the remote repository on github.

If you want your changes to be introduced into the main repository you can open a pull request.
To do this:
1. go to pull requests and press the green pull request button
2. From the compare menu, select your branch
3. Fill out the PR template and submit

Your pull request will be reviewed and if any further changes
are needed you can look at the feedback, and return to your code in
your IDE to make those changes.

## Wait... What are good contributions? 
Good ideas for contribution include bug fixes which are normally
found in the issues area, documentation (such as working on this readme,
I probably missed some steps...), features (this is a bit more complex)...

Basically anything you can think of. A good idea
is to avoid making a pull request too big.

Also, when you are making contributions please can
you include tests.