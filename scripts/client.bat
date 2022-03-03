cd ..
if not exist bin/client mkdir bin
cd src
javac ClientMain.java -d ../bin/client
java -cp ../bin/client ClientMain