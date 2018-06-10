# bankslip-services

For run application first you'll need build it:

    mvn clean install

And then:

    mvn spring-boot:run

With the step below the application will run in develop mode, go to:

    http://localhost:8080/bank-slip-services/swagger-ui.html

And test api.

You can build docker image to be deployed in some service, running:

    docker build -t bankslip-services .

Push the image to some repository like Elastic Container Service eg.