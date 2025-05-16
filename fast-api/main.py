from fastapi import FastAPI

app = FastAPI()

@app.get("/")
def read_info():
    return {"message":"Hello FastAPI"}