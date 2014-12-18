Use the ant build file to compile and run this project.

Open up the command prompt (shell) and navigate to the directory where this project lives.

[Compile] :

type ====== ant compile ========= (or simply type "ant" as the compile target is the default target)

[Run] :

type ====== ant runapp -Doption=0 ========== to run unit tests
type ====== ant runapp -Doption=1 ========== to run the sample rotating code for a sample array [1,2,3,4,5,6] with three positions to the right.


If you want to play with it more, then open the ArrayRotator.java class and modify the value of the "array" variable (LINE 52) to anything you want, and then
modify the call to the rotate method with the value of position (LINE 58) you want to shift the array to the right.