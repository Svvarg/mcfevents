.PHONY: build

build:
	gradle clean build

# publish to local Maven Repository --> ~/.m2/repository/ ...
to-mvn-local:
	gradle publishToMavenLocal

# copy to dev env (to the server and client mod directory) (todo mod/1.7.10/)
deploy-to-local:
	mcpc dev mod d2l o l mcfevents
