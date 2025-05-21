
from fastapi import APIRouter

#prefix is used in url
#tags is used when swagger group methods
router = APIRouter(
    prefix="/module_a",
    tags=["tag_a"]
)

@router.get("/read_a")
async def read_module_a():
    return {"msg":"This is module_a"}