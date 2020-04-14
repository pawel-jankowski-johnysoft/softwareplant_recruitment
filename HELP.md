# Getting Started

### Running app
App requires one property - base swapi url (see application.yml). 
Basically it is set to docker container url started on windows machine.

### Security
Application is secured (for now it is used in memory database with only one user). You can get access to application using 
credentials:

`test / test`

## frontend

To start frontend you have to have npm or yarn.

Go to: `src\front\ report-fronted`
and run:
``npm start`` or `yarn start`


## Rest documentation
After authenticate you could have an access to rest documentation - you can find it here:

YOUR_APP_CONTEXT_NAME/static/docs/index.html

e.g. 
http://localhost:8080/app/static/docs/index.html

where 'app' is application context name deployed on your server.
