SHELL := /bin/bash

.PHONY: import-mockdata-to-mongo
import-mockdata-to-mongo:
	./scripts/import_mockdata.sh

.PHONY: test
test:
	./gradlew test

.PHONY: build-without-tests
build-without-tests:
	./gradlew bootJar

## TERRAFORM
.PHONY: init
init:
	bash .github/scripts/init.sh

.PHONY: plan
plan:
	bash .github/scripts/plan.sh

.PHONY: apply
apply:
	bash .github/scripts/apply.sh

.PHONY: destroy
destroy:
	bash .github/scripts/destroy.sh