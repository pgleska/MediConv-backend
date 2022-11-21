app:
	rm -rf ./build
	gradle clean  build
	cp -f ./build/libs/MediConv-0.0.1.jar ./docker/backend/MediConv.jar
