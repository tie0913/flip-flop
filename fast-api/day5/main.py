
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

