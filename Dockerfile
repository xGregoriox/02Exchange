FROM maven:3-alpine
WORKDIR /appmavenjenkins
ADD . /appmavenjenkins
EXPOSE 3000
RUN ["chmod", "+x", "/jenkins/scripts/deliver.sh"]
CMD jenkins/scripts/deliver.sh
