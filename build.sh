#!/bin/bash

PROJECT=$1
VERSION=$(date +v%y.%m.%d%H%M%S)

git tag ${VERSION}
git push origin ${VERSION}

cd  ..

mvn versions:set -DnewVersion=${VERSION} -X && \
mvn versions:commit -X && \
mvn clean deploy -X

docker build -t ${PROJECT} .
