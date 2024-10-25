#!/usr/bin/env bash
set -e

# Base directory of the project
BASE_DIR="$PROPHET_PLUGIN_HOME/test_projects/task-management-graphql"

# List of microservices
microservices=("user-service" "task-service")

# Build and run each microservice
for microservice in "${microservices[@]}"; do
    echo "Building $microservice..."
    (cd "$BASE_DIR/$microservice" && mvn clean package)

    echo "Running $microservice..."
    java -jar "$BASE_DIR/$microservice/target/${microservice}-0.1.jar" &
done

echo "All microservices are running."