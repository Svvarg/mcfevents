.PHONY: build

build:
	gradle clean build

# publish to local Maven Repository --> ~/.m2/repository/ ...
to-mvn-local:
	gradle publishToMavenLocal
