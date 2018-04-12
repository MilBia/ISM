# ISM
Draft of ISM laboratory asigment on PWr

##Example of endpoints requests:

###Table module

**POST**
 
URL: `localhost:8080/table`

BODY: `{
      	"name": "Ziutek",
      	"capacity": 3
      }`
      
**PUT**
 
URL: `localhost:8080/table`

BODY: `{
       	"id": 1,
       	"capacity": 4
       }`
       
**GET**
 
URL: `localhost:8080/table`
       
**GET**
 
URL: `localhost:8080/table/1`

###Reservation module

**POST**
 
URL: `localhost:8080/reservation`

BODY: `{
          "startDate": "2018-04-12T20:00:00.000+0000",
          "endDate": "2018-04-13T01:30:00.000+0000",
          "tables":
          [
          	{
          		"id": 1
          	}
          ]
      }`
      
**PUT**
 
URL: `localhost:8080/reservation`

BODY: `{
           "id": 1,
           "startDate": "2018-04-12T20:00:00.000+0000",
           "endDate": "2018-04-13T03:30:00.000+0000"
       }`
       
**GET**
 
URL: `localhost:8080/reservation`
       
**GET**
 
URL: `localhost:8080/reservation/1`