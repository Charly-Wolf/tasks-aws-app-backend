SHELL := /bin/bash

.PHONY: import-mockdata-to-mongo
import-mockdata-to-mongo:
	./scripts/import_mockdata.sh

.PHONY: test
test:
	./gradlew test