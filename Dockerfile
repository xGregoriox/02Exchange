FROM adoptopenjdk/openjdk11:alpine-jre
WORKDIR /appmavenjenkins
ADD . /appmavenjenkins
EXPOSE 8090:8090
RUN chmod 755 jenkins/scripts/deliver.sh
CMD jenkins/scripts/deliver.sh
