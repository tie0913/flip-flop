
# Today we are going to build a fast-api development enviroment
#
# 1. create a your project directory give it a name like 'fast-api'
#
# 2. prepare a virtual environment for your project by executing 'python -m venv venv' in the directory
#       '-m venv' tells python to prepare a virtual environment
#       the last 'venv' is the name of the virtual environment
#       now you have a 'venv' directory in your 'fast-api' directory     
#       virtual environment is a sandbox, which has no any modules but your dependencies
#
# 3. execute 'source venv/bin/activate'
#       this will activate the virtual environment 
#       now you may install dependencies by pip in this virtual environment
#       if you want to exit the virtual environment, you may execute 'deactivate' in it.
#      
#       if you are working on Windows you need to use command '.\venv\Scripts\activate to activate your venv and use 'deactivate' to exit the virtual enviroment
#
# 4. execute 'pip install fastapi uvicorn'
#       fastapi is a framework based on ASGI providing coroutine system which allows us to develop asynchronized program by async and await
#       uvicorn is a webserver the only thing about it I knew so far is how to spell it.
#
# 5. if you want to record all your dependencies in this virtual envrionment you may execute 'pip freeze > requirements'
#       all the dependencies will be writtern into the file 'requirements' you may use it when deploy your code to another environment or sandbox
#       when you want to work on your project by another computer, you may use 'pip install -r requirements' to recover your dependencies in your virtual envrionment
#
# 6. create a python file my file's name is main.py you can read code and comment in this file 
#
# 7. double check whether you entered the virtual environment.
#       if the prompt of your terminal displays (venv) in the front of the line, you are in the correct environment
#       otherwise, execute step 3.
#
# 8. in your project directory execute 'uvicorn file_name:app --reload'
#       file_name is your python file's name, mine is day1.main, so my command should be 'uvicorn day1.main:app --reload
#       app is the instance of FastAPI which we have created in this file
#       --reload tells the server that it needs to reload the code when it is modified.
#
#       If you are working on Windows, when starting the uvicorn, you may have errors 
#            Program 'uvicorn.exe' failed to run: Access is deniedAt line:1 char:1
#       you may start your uvicorn by command 'python -m uvicorn file_name:app --reload'
#
# 9. open your browser and visit http://localhost:8000 see what happens.
#    you may find swagger page by visiting http://localhost:8080/docs
#


# import fastapi
from fastapi import FastAPI

# create an instance of FastAPI
app = FastAPI()

#declare this method will provide response for the root path of this server
#the method should be get and notice the async symbol this means the method uses coroutine system instead of original sync way.
#try to visit http://localhost:8000  you can see the JSON printed in your browser
#8000 is the default port for uvicorn
@app.get("/")
async def read_info():
    return {"message":"Hello FastAPI"}


# About coroutine system
# if you have developed web service in traditional Java, you may know that each request would be executed by a thread in server, and the server holds a threadpool exeucting those threads.
# however, this means the os will switch threads on one core, and the switching operation costs high (move the stack frames, restore variables and so forth)
# in coroutine system(Python: ASGI, Go: Goroutine, Scala: Actor, Java: Vritual Thread) each request will be executed by coroutine
# and one thread can have thousands of coroutines, which means the os will not switch thread frequently.
# instead, the coroutine system will switch coroutine based on events (for example if we invoke await, that means we send an event telling the coroutine system that we need to wait a conditioin)
# the system will exeucte other coroutines.
# so that in this kind of applications, the os rarely switches threads and the cores of CPU will be busy forever(hopefully)
# It will boost up effeciency dramatically in IO intensive applications by promoting the throughout.