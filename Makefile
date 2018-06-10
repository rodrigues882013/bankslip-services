OS := $(shell uname)

# Variables
MAVEN=mvn
JAVA=java


#::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

install:
	$(MAKE) clean install

#::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

run:
    $(JAVA) -jar target/bankslip-services.jar