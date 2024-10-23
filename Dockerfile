FROM openjdk:17
RUN export DISPLAY=:0.0
ADD target/comp-2120-assignment-3-workshop-09-group-c-1.0-SNAPSHOT.jar game.jar
ENTRYPOINT ["java", "-jar","game.jar", "comp2120.a3.GameLauncher"]

#FROM maven:3.8.3-openjdk-17
#WORKDIR /apps
#COPY . /apps
#RUN mvn clean install
#RUN export DISPLAY=:0.0
##RUN #setenv DISPLAY :0.0
## Don't forget to substitute the following with a real mainClass
#CMD mvn exec:java -Dexec.mainClass="comp2120.a3.GameLauncher"