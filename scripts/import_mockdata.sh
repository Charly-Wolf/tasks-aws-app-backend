#!/bin/bash

set -e

echo "Starting import to MongoDB..."

function import_collection {
  local collection=$1
  local file=$2

  echo "Importing $collection from $file..."
  if output=$(mongoimport --uri="$MONGO_URI" --collection="$collection" --drop --file="$file" --jsonArray 2>&1); then
    echo "Successfully imported $collection."
  else
    echo "ERROR importing $collection!"
    echo "$output"
    exit 1
  fi
}

import_collection "users" "mockData/mockUsers.json"
import_collection "lists" "mockData/mockLists.json"
import_collection "tasks" "mockData/mockTasks.json"

echo "All imports completed successfully!"
