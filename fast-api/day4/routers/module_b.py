from fastapi import APIRouter


router = APIRouter(
    prefix="/module_b",
    tags=["tag_b"]
)

@router.get("/read_b")
async def read_module_b():
    return {"msg":"This is module_b"}