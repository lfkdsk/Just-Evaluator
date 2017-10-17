#!/bin/bash
echo "mvn package assembly:single -DskipTests"
mvn package assembly:single -DskipTests