##Instructions to run application locally

MockXqueryDelayServerApplication -> right click -> Run

##Instructions to send request to mockXqueryDelayServer application from script

* We declare on the script a method that sends the request to mockXqueryDelayServer (provoking a delay that is determined from property waitingTime). 
  Following is an example: 

declare function obligations:httpget() {
  let $data := http:send-request(<http:request method='get' status-only='true'/>, 'http://localhost:8080/api/test')
  return $data
};

* Next we call the method that we created earlier

e.g. obligations:httpget()


### SSL key generation for demo alias
keytool -genkeypair -alias demo -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore demo.p12 -validity 3650

## Docker commands and actions

### Build image and deploy to a docker repo 
- inside app root directory run the following commands:

$ mvn clean install 
$ docker build -t eworxeea/mockxquerydelayserver:2020-27-10T1500 .
$ docker push eworxeea/mockxquerydelayserver:2020-27-10T1500 

### Run a docker container locally
 docker run -p 8080:8080 --name mockxquery -d eworxeea/mockxquerydelayserver:2020-27-10T1500
 
 ### Test the local docker container
 $  curl localhost:8080/api/sparql?query=someQuery <br>
 After the execution of the above command, in the container logs you should see the query value printed. <br>
 $ docker logs mockxquery 
 