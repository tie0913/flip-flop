
from fastapi import Depends, FastAPI
from typing import Optional

server = FastAPI()

def query_executor(q:Optional[str] = None):
    print("Now we have received the parameter q=" + str(q))
    return q is None

# we defined a method which depends query_executor
# when we invoke this http method, fast-api will invoke the query_executor method automatically
# and it will treat the http parameters as the parametes of query_executor method.
@server.get("/get")
async def get(res: bool = Depends(query_executor)):
    return {"q":res}




# if we have another service which will be depended by the http method
# but becareful, the method providing dependecies should not have parameters
# if we want to declare a provider like this, fast-api will try to find a parameter with the same name from the request.
class Service:

    def __init__(self):
        self.name = "Service"

    def get(self):
        return self.name

def get_service(n:str):
    if n == "Service":
        return Service()
    else:
        return None

@server.get("/get_service")
async def get_service_name(name:str, provider:Service = Depends(get_service)):
    print(name);
    if provider is not None:
        msg = provider.get()
    else:
        msg = "Empty Service"

    return {"msg": msg}



# async middleware.
# this looks like filters or interceptors in Spring
# we may use it to implement basic functions, such as user validation, authentication , authorization and so forth.
# and we may return the request before executing acutual method.
#
# if we want to execute next method in the chain we may invoke call_next method
#      response = call_next(request)

# Startlette provides these features which can be async
# so we need to import
from starlette.requests import Request

# 1. add time comsumed by the method in the response header

import time
@server.middleware("http")
async def add_time_consumed(req:Request, call_next):
    start = time.monotonic()
    resp = await call_next(req)
    duration = time.monotonic() - start
    resp.headers["X-Process-Time"] = str(duration)
    return resp


# 2. adding headers (CORS/Meta)
@server.middleware("http")
async def add_custom_header(req:Request, call_next):
    resp = await call_next(req)
    resp.headers["X-Custom-Header"] = "FastAPI-Rocks"
    return resp


# 3.coroutine-safe logging
import logging
@server.middleware("http")
async def log(req:Request, call_next):
    logging.info(f"Request: {req.method} {req.url}")
    resp = await call_next(req)
    logging.info(f"Response status:{resp.status_code}")
    return resp
    

# now launch the uvicorn and visit a http method
# when we define a middleware it will apply to all the http method
# if we want a middleware method only apply on specific method, we need to do custom logic in that middleware method.

# the fast api will invoke all the middleware methods in the reverse order of definition.
# if you want to specify orders of the middleware methods. you may use 'server.addMiddleware' method

