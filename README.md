# shape-interview
This is my solution to the shape-interview

The endpoint is now more generic. 

It is now simply a receiver of types.

The endpoint is called **```receiver```**, and it accepts **```type```** of work in JSON format.

This single, generic endpoint is quite powerful now as the same endpoint can accept any type of work described in its details section.

I have implemented the Shape interface for the following:
```
Square
Circle
Doughnut
Poly
```

## To Run
```Application::main()```
### URL
http://localhost:8080/receiver
### Request format
```
{
    "type" : "RequestType",
    "details" : { Details }
}
```
### POST test
#### Square
```curl --header "Content-Type: application/json" --request POST --data ' { "type" : "square", "details" : { "edge" : 5 } }' http://localhost:8080/receiver```
#### Circle
```curl --header "Content-Type: application/json" --request POST --data ' { "type" : "circle", "details" : { "radius" : 3.0 } }' http://localhost:8080/receiver```
#### Doughnut no.1 
##### Doughnut composed of two squares 15x15 and 5X5
```curl --header "Content-Type: application/json" --request POST --data ' { "type" : "doughnut", "details" : { "in" : { "type" : "square", "details" : { "edge" : 5 } }, "out" : { "type" : "square", "details" : { "edge" : 15 } } } }' http://localhost:8080/receiver```
#### Doughnut no.2 
#####Doughnut composed of two doughnuts: 
#####D1 = two squares 15x15 and 5X5  
#####D2 = two squares 10x10 and 3X3
```curl --header "Content-Type: application/json" --request POST --data ' {"type":"doughnut","details":{"in":{"type":"doughnut","details":{"in":{"type":"square","details":{"edge":3}},"out":{"type":"square","details":{"edge":10}}}},"out":{"type":"doughnut","details":{"in":{"type":"square","details":{"edge":5}},"out":{"type":"square","details":{"edge":15}}}}}} ' http://localhost:8080/receiver```
#### Poly
```curl --header "Content-Type: application/json" --request POST --data ' { "type" : "poly", "details" : { "pairs" : [ { "x" : 0, "y" : 0 }, { "x" : 0, "y" : 1 }, { "x" : 1, "y" : 1 }, { "x" : 1, "y" : 0 } ] } }' http://localhost:8080/receiver```


## To test
To run all the unit tests:
```
Run Tests in test.java.com.zephr.interview
```

##  Square Rest Test
### Request
```
Square Rest Test Request:  
{
    "type" : "square",
    "details" : {
        "edge" : 5
    }
}
```
### Response
```
Square Rest Test Response:
{
    "area" : 25.0,
    "perimeter" : 20.0
}
```
### Result
```
Square Area      Calculated Correctly: calculated:25.0 expected: 25.0
Square Perimeter Calculated Correctly: calculated:20.0 expected: 20.0
```

##Circle Rest Test
### Request
```
Circle Rest Test Request:  
{
    "type" : "circle",
    "details" : {
        "radius" : 3.0
    }
}
```
### Response
```
Circle Rest Test Response:
{
    "area" : 28.274333882308138,
    "perimeter" : 18.84955592153876
}
```
### Result
```
Circle Area      Calculated Correctly: calculated:28.274333882308138 expected: 28.274
Circle Perimeter Calculated Correctly: calculated:18.84955592153876 expected: 18.849
```

## Doughnut Rest Test No.1
### Doughnut composed of two squares 15x15 and 5X5
### Request
```
Doughnut Rest Test Request:  
{
    "type" : "doughnut",
    "details" : {
        "in" : {
            "type" : "square",
            "details" : {
                "edge" : 5
            }
    },
        "out" : {
            "type" : "square",
            "details" : {
                "edge" : 15
            }
        }
    }
}
```
### Response
```
Doughnut Rest Test Response:
{
"area" : 200.0,
"perimeter" : 80.0
}
```
### Result
```
Doughnut Area      Calculated Correctly: calculated:200.0 expected: 200.0
Doughnut Perimeter Calculated Correctly: calculated:80.0 expected: 80.0
```


## Doughnut Rest Test No.2
### Doughnut composed of two doughnuts 
### D1 = two squares 15x15 and 5X5
### D2 = two squares 10x10 and 3X3

### Request
```
{
  "type": "doughnut",
  "details": {
    "in": {
      "type": "doughnut",
      "details": {
        "in": {
          "type": "square",
          "details": {
            "edge": 3
          }
        },
        "out": {
          "type": "square",
          "details": {
            "edge": 10
          }
        }
      }
    },
    "out": {
      "type": "doughnut",
      "details": {
        "in": {
          "type": "square",
          "details": {
            "edge": 5
          }
        },
        "out": {
          "type": "square",
          "details": {
            "edge": 15
          }
        }
      }
    }
  }
}
```
### Response:
```
{
  "area" : 109.0,
  "perimeter" : 132.0
}
```
### Result
```
Poly Area      Calculated Correctly: calculated:109.0 expected: 109.0
Poly Perimeter Calculated Correctly: calculated:49.0 expected: 129.0
```

## Poly Rest Test
### Request
```
Poly Rest Test Request:  
{
  "type": "poly",
  "details": {
    "pairs": [
      {
        "x": 0,
        "y": 0
      },
      {
        "x": 0,
        "y": 1
      },
      {
        "x": 1,
        "y": 1
      },
      {
        "x": 1,
        "y": 0
      }
    ]
  }
}
```
### Response
```
Poly Rest Test Response:
{
    "area" : 1.0,
    "perimeter" : 4.0
}
```
### Result
```
Poly Area      Calculated Correctly: calculated:1.0 expected: 1.0
Poly Perimeter Calculated Correctly: calculated:4.0 expected: 4.0
```