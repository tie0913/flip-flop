
# Today we are going to learn basic get and post method.
from fastapi import FastAPI

# This is the instance of FastAPI framework
# and this main file exists in package day2
# so if we want to launch uvicorn we need to use command 'uvicorn day2.main:server --reload
# however, before launching the uvicorn, do not forget switch to the virtual environment 
#   by 'source venv/bin/activate' in macOS and Linux
#   by '.\venv\Scripts\activate'  in Windows
server = FastAPI()



# This is a get method with simple parameters
# 1. parameters must be declared in the parameter list in the brace after method name
# 2. if we do not want parameter existing in query string, we may add it to the http path like the '{name}' in this example
# 3. the fastapi makes all the parameter mondatory.
# 4. if we want a parameter to be optional we neend to use Optional in typing like this
from typing import Optional
#    however, in this example we can not make the parameter name optional, because it is part of the http path

@server.get("/get_info/{name}")
async def get_with_simple_parameters(name:str, gender:str, age:int, book_name:Optional[str]=None):
    if book_name is not None:
        return {"message":{"name": name, "gender":gender, "age":age, "book_reading": book_name}}
    else:
        return {"message":{"name": name, "gender":gender, "age":age}}



# We want to receive parameters by an instance of a class, instead of some variables
# for this purpose, we are going to use Pydantic model
# 1. import BaseModel in pydantic
from pydantic import BaseModel
# 2. we define a class representing all the parameters of a http method
#   by the way, this is how we declare inheritance in Python.
#   one class may have multiple super classes.
class Person(BaseModel):
    name:str
    age:int
    income:float
#   if we provide default value for a parameter, it would be an optional one.
#   otherwise, it is mondatory.
    description:Optional[str] = None

# 3. we may use this class to receive the parameters being sent in the http method
# 4. now go to http://localhost:8000/docs reading the interface description in swagger
@server.post("/save_person")
async def save_person(p: Person):
    return {"person_recieved": p}


# what if we want have two instances of parameter classes in one http method?
# let's define another parameter class and method
class Pet(BaseModel):
    name:str
    age:int

@server.post("/save_person_with_pet")
async def save_person_with_pet(person: Person, pet: Pet):
    return {"person": person, "pet": pet}

# now goto http://localhost:8000/docs reading the interface description in swagger
#
# quick question: what if one person has multiple pets? 
# you never know unless trying once. 
# tips: a list of Pet can be declared as list[Pet] in Python


# Ok at last, we want to have a get method which recieves an instance of parameter class
# 1. we need to import Depends from fastapi
from fastapi import Depends

# 2. provide default value for this parameter when defining the method
@server.get("/save_person_by_get")
async def save_person_by_get(p:Person = Depends()):
    return {"person": p}
# 3. we cannot add another parameter of Pet, because both Pet and person have the same attribute name and age. 
#       get method can not recogonize which parameter the name belongs to