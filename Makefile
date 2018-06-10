OS := $(shell uname)

# Variables
MAVEN=mvn
JAVA=java


#::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

install:
	$(MAVEN) clean install

#::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

run:
	$(JAVA) -jar target/bankslip-services.jar
