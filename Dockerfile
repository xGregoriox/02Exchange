FROM maven:3-alpine
WORKDIR /appmavenjenkins
ADD . /appmavenjenkins
EXPOSE 3000
RUN chmod 755 jenkins/scripts/deliver.sh
CMD jenkins/scripts/deliver.sh
