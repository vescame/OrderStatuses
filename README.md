# Order Statuses
The customer updates it's orders status by sending it to our endpoint

# Build
```shell
./gradlew build
```

# Start App
```shell
./gradlew bootRun
```

## Endpoints
### List items endpoint
```shell
curl -X GET -H 'Content-Type: application/json' http://localhost:8080/items
```
### Create new order
```shell
curl -X POST -H 'Content-Type: application/json' localhost:8080/orders -d \
'{
    "customerId": 1,
    "orderLines": [
        {
            "itemId": 1,
            "quantity": 1
        }
    ]
}'
```
### Get existing order
```shell
curl -X GET -H 'Content-Type: application/json' localhost:8080/orders/1
```

### Update existing order status
```shell
curl -X PUT -H 'Content-Type: application/json' localhost:8080/orders/1/status -d \
'{
    "status": "CONFIRMED"
}'
```
