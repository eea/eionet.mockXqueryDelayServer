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
