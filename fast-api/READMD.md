# Overview

This is a tutorial about studying fast-api in 7 days created by ChatGPT

## Why we want to learn fast-api

Coroutine system provides extremely high throughout for IO-intensive application.
There is a rumor which says this kind of system can serve over 100,000 requests at the same time by one instance.

## Syllabus

### ✅ **Day 1: Environment Setup & Hello World**
- Install Python, create and activate `venv`
- Install FastAPI & Uvicorn:
  ```bash
  pip install fastapi uvicorn
  ```
- Setup VSCode debugging
- Create minimal app (`main.py`):
  ```python
  from fastapi import FastAPI

  app = FastAPI()

  @app.get("/")
  async def read_root():
      return {"message": "Hello, Async World!"}
  ```
- Run app:
  ```bash
  uvicorn main:app --reload
  ```
- Learn:
  - `async def` vs `def` in FastAPI
  - ASGI & event loop basics

---

### ✅ **Day 2: Path & Query Parameters + Pydantic**
- Define routes with path parameters: `/items/{item_id}`
- Use optional query parameters: `/items/?q=search`
- Async route + simulated I/O:
  ```python
  import asyncio

  @app.get("/items/{item_id}")
  async def read_item(item_id: int, q: str | None = None):
      await asyncio.sleep(0.2)
      return {"item_id": item_id, "q": q}
  ```
- Intro to **Pydantic**:
  - Define `BaseModel`
  - Input validation + type hints
- Test with Swagger UI

---

### ✅ **Day 3: HTTP Methods & Request Body**
- Add `@app.post`, `@app.put`, `@app.delete`
- Accept JSON with Pydantic models
- Return custom status codes, raise `HTTPException`
- Use response models
- Simulate concurrent processing:
  ```python
  import asyncio

  @app.post("/batch/")
  async def batch_processor(batch: list[str]):
      results = await asyncio.gather(*[process_item(i) for i in batch])
      return {"results": results}
  ```

---

### ✅ **Day 4: Routing & Modularization**
- Create submodules with `APIRouter`
- Folder structure:
  ```
  app/
    main.py
    routers/
      └── users.py
      └── items.py
  ```
- Organize by feature (separation of concerns)
- Use `async def` in all route handlers
- Discuss: how coroutine-based routing improves throughput

---

### ✅ **Day 5: Dependency Injection & Middleware**
- Use `Depends()` for injecting:
  - Query values
  - Auth logic
  - Config / DB session
- Add **async middleware** to:
  - Time requests
  - Add headers
  - Log coroutines
- Coroutine-safe example:
  ```python
  from starlette.requests import Request
  import time

  @app.middleware("http")
  async def add_process_time_header(request: Request, call_next):
      start = time.monotonic()
      response = await call_next(request)
      duration = time.monotonic() - start
      response.headers["X-Process-Time"] = str(duration)
      return response
  ```

---

### ✅ **Day 6: Database Integration (Async SQLAlchemy or Tortoise)**
- Choose:
  - `SQLAlchemy 2.0` + async engine
  - or `Tortoise ORM` (cleaner for async-first)
- Define models and schemas
- Create async session:
  - Use `async_sessionmaker`
  - Inject via `Depends()`
- Perform async CRUD ops
- Optional: Add Alembic for migrations
- Discuss: handling DB connection pooling + throughput

---

### ✅ **Day 7: Final Project (Async-First + High Throughput)**
- Choose a small project:
  - Async health metric tracker
  - Task queue (fan-out + gather pattern)
- Implement:
  - Async routes
  - Concurrent API calls
  - Middleware & dependency injection
- Add:
  - Exception handling
  - Pydantic response models
  - CORS, logging, config loading
- Optional: deploy using
  ```bash
  gunicorn -k uvicorn.workers.UvicornWorker --workers 4 app.main:app
  ```
