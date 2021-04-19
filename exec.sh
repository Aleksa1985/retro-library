#!/bin/sh

echo 'Executing...'
java -jar app.jar db migrate config.yml
echo 'Executed db migration'
echo 'Running app'
java -jar app.jar server config.yml