cd ..
if not exist "bin/server" mkdir "bin/server"
cd src
javac ServerMain.java -d ../bin/server
java -cp ../bin/server ServerMain