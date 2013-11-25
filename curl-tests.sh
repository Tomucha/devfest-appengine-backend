curl --include --header "Content-Type: application/json; charset=UTF-8" \
     --request POST \
     --data-binary "{
        \"key\":\"prvni.klic\",
        \"message\": \"První klíč je vždy nejlepší\!\"
    }" http://localhost:8080/api/messages;


curl --include --header "Content-Type: application/json" \
     --request POST \
     --data-binary "{
        \"key\":\"druhy.klic\",
        \"message\": \"Ahoj vsichni, tohle je druhý super message.\"
    }" http://localhost:8080/api/messages;

curl --include --header "Content-Type: application/json; charset=UTF-8" \
     --request POST \
     --data-binary "{
        \"key\":\"vyherce\",
        \"message\": \"Tomucha\!\"
    }" http://matelier-sandbox.appspot.com/api/messages;

curl --include --request GET http://localhost:8080/api/messages/prvni.klic;

curl --include --request GET http://matelier-sandbox.appspot.com/api/messages/vyherce;

curl --include --request GET --header "Authorization: Ja su Franta" http://localhost:8080/api/messages;

curl --include --request DELETE http://localhost:8080/api/messages/prvni.klic;



-------------------

curl --include --header "Content-Type: application/json; charset=UTF-8"      --request POST      --data-binary "{
        \"key\":\"prvni.klic\",
        \"message\": \"První klíč je vždy nejlepší\"
    }" http://localhost:8080/_ah/api/messages/v1/createMessage
