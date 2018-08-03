# Description #
----------
I have an WSException that extends WebApplicationException.

```
public class WSException extends WebApplicationException
{
    private String message;

    public WSException as(Response.Status status)
    {
        this.statusCode = status;
        return this;
    }
}
```

Until Payara 5.181 those exceptions were thrown to the client, so I made a Json wrapper to catch with the same structure any response from my Web services. Any response is intercepted and if is an application/json response it is wrapped inside the data field.

HTTP 200: `{ success:true, message:"", data:{...}  } `
Any Error: `{ success:false, message:"Exception Message", data:{...}  } `
_(look at the 'success' boolean flag)_

but on Payara 182,183 those Exceptions are converted with the toString() mehtod and returned as

Any Error: `{ success:true, message:"", data:{"String representation of the Exception object"}  } `



## Expected Outcome

WebApplication Exceptions must be thrown to the client unless a ExceptionMapper is configured.

## Current Outcome

WebApplication Exceptions are swallowed (may be by `fish.payara.microprofile.opentracing.jaxrs.JaxrsContainerRequestTracingFilter` and converted to String

## Steps to reproduce (Only for bug reports) 


### Samples

- clone this repo -> 
- run `gradlew war` to get an Application.war

**deploy it on payara micro 5 181** 
- test with  `localhost:8080/Application/api?fail=false` and should get this json 
   `{"data":"Hello World!","message":"","success":true}`
- test with  `localhost:8080/Application/api?fail=true` and should get this json 
   `{"message":"Error","success":false}`

**deploy it on payara micro 5 182,183** 
- test with  `localhost:8080/Application/api?fail=false` and should get this json 
   `{"data":"Hello World!","message":"","success":true}`
- test with  `localhost:8080/Application/api?fail=true` and should get this json 
   `{"data":"WSException(message=Error, errors=null, statusCode=Bad Request)","message":"","success":true}`

## Context 

WebApplication Exceptions are used to tell to the client what happened in a controlled way, with those Exceptions muted, we need to refactor many huge projects to a new error management proccess.

## Environment ##

- **Payara Version**: 5.181 | 5.182 | 5.183.20180803.000239-63
- **Edition**: Payara Micro
- **JDK Version**: Oracle JDK 1.8.0_171 
- **Operating System**: Windows 8.1u1

