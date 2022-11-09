Fetch Rewards Coding Exercise - Backend Software Engineering

The Project is built using Java Spring Boot Framework.

Requirements:
 1. Install appropriate Java JDK 8
 2. Install IntelliJ Ultimate Edition or any appropriate Spring Boot IDE.
 3.  Use Spring Boot  intializr(https://start.spring.io/) to download required dependencies.

Run the Project:
1. Download the project and open it with the IntelliJ IDE.
2. Next run the FetchRewardsApplication.java file.
3. To test the API calls we will use generated-requests.http file (Scratches and Consoles/scratches/generated-requests.http)
4. Or to test API calls we can use PostMan.

Assumptions :
For transaction timestamp whenever the transaction is added the local/current date and time are considered.

**********   REST API ************
-------------------------------------

The REST API to the app is described below.

Add Transactions:
-----------------
1. EndPoint to add transactions

POST http://localhost:8080/api/v1/rewards/addtransaction

Content-Type: application/json

{
"payer": "DANNON",
"points": 300
}

Response:



HTTP/1.1 200

Content-Length: 0

Date: Tue, 08 Nov 2022 23:31:58 GMT

Keep-Alive: timeout=60

Connection: keep-alive


Response code: 200; Time: 334ms (334 ms); Content length: 0 bytes (0 B)



GET All Transactions :
-----------------
1. Endpoint  to add all transactions

   GET http://localhost:8080/api/v1/rewards/gettransactions

Response:

HTTP/1.1 200

Content-Type: application/json

Transfer-Encoding: chunked

Date: Tue, 08 Nov 2022 23:37:41 GMT

Keep-Alive: timeout=60

Connection: keep-alive


[
{
"payer": "DANNON",
"points": 300,
"timestamp": "2022-11-08T18:31:58.189425",
"updatedPoints": 100
},

{
"payer": "UNILEVER",
"points": 200,
"timestamp": "2022-11-08T18:37:30.935267",
"updatedPoints": 200
},

{
"payer": "DANNON",
"points": -200,
"timestamp": "2022-11-08T18:37:33.218531",
"updatedPoints": -200
},

{
"payer": "MILLER COORS",
"points": 10000,
"timestamp": "2022-11-08T18:37:35.964172",
"updatedPoints": 10000
},

{
"payer": "DANNON",
"points": 1000,
"timestamp": "2022-11-08T18:37:38.324312",
"updatedPoints": 1000
}
]

Response file saved.



GET Payer Data:
-

1. EndPoint to get all payers data


   GET http://localhost:8080/api/v1/rewards/getuserdata

Response :

HTTP/1.1 200

Content-Type: application/json

Transfer-Encoding: chunked

Date: Tue, 08 Nov 2022 23:41:13 GMT

Keep-Alive: timeout=60

Connection: keep-alive

[
{
"userName": "UNILEVER",
"totalPoints": 200
},

{
"userName": "MILLER COORS",
"totalPoints": 10000
},

{
"userName": "DANNON",
"totalPoints": 1100
}
]


Spend Points
-
1. EndPoint to spend points

PUT http://localhost:8080/api/v1/rewards/spendpoints/5000

Response:

HTTP/1.1 200

Content-Type: application/json

Transfer-Encoding: chunked

Date: Tue, 08 Nov 2022 23:42:58 GMT

Keep-Alive: timeout=60

Connection: keep-alive


[
{
"userName": "DANNON",
"totalPoints": 100
},

{
"userName": "UNILEVER",
"totalPoints": 200
},

{
"userName": "MILLER COORS",
"totalPoints": 4700
}
]



