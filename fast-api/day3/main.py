

from fastapi import FastAPI, Depends, HTTPException, status


server = FastAPI()


#
# we have known how to create get method and post method.
# as we know, there are some other methods in HTTP: delete, put
#
# get : query information
# post : submit a form
# put : update a record
# delete : delete a record
#  
# get, put, delete are idempotent
# post is not
# I will not develop those methods today, because there are too easy.
#

# Error handling
# we may use try except finally block to dealwith exceptions
# and we can also raise an Exception in our method to the invoker
# the keyword raise is the same like keyword throw in Java
#
@server.delete("/delete/{id}")
def get(id:int):
    if(id >= 100):
        raise HTTPException(status_code=status.HTTP_501_NOT_IMPLEMENTED,
                            detail="id should be less than 100")

    return {"message": "ok"}

#
# Response Model
# BaseModel will provide constructor as the order when we delare the variables.
# we do not need to provide __init__ method as the constructor.
#
from typing import Optional
from pydantic import BaseModel
class ResponseModel(BaseModel):
    name:str
    message:Optional[str]

@server.get("/read", response_model=ResponseModel)
async def read(id:int):
    return ResponseModel(name="123", message=None)



#
# basic async methods
#
import asyncio
async def process(name: str):
    await asyncio.sleep(1)
    # f"" means this is a formatter string
    # which is quite the same with `` in javascript and s"" in scala
    return f"Proccessd {name}"

@server.post("/async_get")
async def basic_async(names: list[str]):
    res = await asyncio.gather(*[process(i) for i in names])
    return {"res": res}