# Accounts Controller API Documentation

## Update Account Information (PUT)

### Endpoint
`/api/update`

### Parameters
- No parameters

### Request body
- Media type: `application/json`
- Example Value:
```json
{
  "name": "string",
  "email": "string",
  "mobileNumber": "",
  "accountsDto": {
    "accountNumber": 0,
    "accountType": "string",
    "branchAddress": "string"
  }
}

Responses
Code: 200
Description: OK
Media type: application/json
Example Value:
json

{
  "statusCode": "string",
  "statusMessage": "string"
}
```

## Create a New Account (POST)
### Endpoint
`/api/create`

### Parameters
No parameters
### Request body
Media type: application/json
Example Value:
json
```json
{
  "name": "string",
  "email": "string",
  "mobileNumber": "",
  "accountsDto": {
    "accountNumber": 0,
    "accountType": "string",
    "branchAddress": "string"
  }
}
Responses
Code: 200
Description: OK
Media type: application/json
Example Value:
json

{
  "statusCode": "string",
  "statusMessage": "string"
}
```

## Fetch Account Information (GET)
### Endpoint
`/api/fetch`

### Parameters
Name: mobileNumber
Description: string (query)
Required: Yes
Responses
Code: 200
Description: OK
Media type: application/json
Example Value:
json
```json
{
  "name": "string",
  "email": "string",
  "mobileNumber": "2811583669",
  "accountsDto": {
    "accountNumber": 0,
    "accountType": "string",
    "branchAddress": "string"
  }
}
```

## Delete Account (DELETE)
### Endpoint
`/api/delete`

### Parameters
Name: mobileNumber
Description: string (query)
Required: Yes
Responses
Code: 200
Description: OK
Media type: application/json
Example Value:
json
```json
{
  "statusCode": "string",
  "statusMessage": "string"
}
```