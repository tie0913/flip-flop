from fastapi import FastAPI

#today we are going to define routers in different packages
#this is the basic thing to divide whole system into different modules.
#Take a look at module_a and b
from day4.routers import module_a, module_b

server = FastAPI()

server.include_router(module_a.router)
server.include_router(module_b.router)
