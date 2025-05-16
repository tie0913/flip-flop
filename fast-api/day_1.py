
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
#
# 4. execute 'pip install fastapi uvicorn'
#       fastapi is a framework based on ASGI providing coroutine system which allows us to develop asynchronized program by async and await
#       uvicorn is a webserver the only thing about it I knew so far is how to spell it.
#
# 5. if you want to record all your dependencies in this virtual envrionment you may execute 'pip freeze > requirements'
#       all the dependencies will be writtern into the file 'requirements' you may use it when deploy your code to another environment or sandbox
#
# 6. create a python file my file's name is day_1.py you can read code and comment in this file 
#
# 7. double check whether you entered the virtual environment.
#       if the prompt of your terminal displays (venv) in the front of the line, you are in the correct environment
#       otherwise, execute step 3.
#
# 8. in your project directory execute 'uvicorn file_name:app --reload'
#       file_name is your python file's name, mine is day_1, so my command should be 'uvicorn day_1:app --reload
#       app is the instance of FastAPI which we have created in this file
#       --reload tells the server that it needs to reload the code when it is modified.
# 9. open your browser and visit http://localhost:8000 see what happens.
#


# import fastapi
from fastapi import FastAPI

# create an instance of FastAPI
app = FastAPI()

#declare this method will provide response for the root path of this server
#the method should be get
#try to visit http://localhost:8000  you can see the JSON printed in your browser
#8000 is the default port for uvicorn
@app.get("/")
def read_info():
    return {"message":"Hello FastAPI"}