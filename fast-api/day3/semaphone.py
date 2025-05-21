
import asyncio

# asyncio has semaphore like Java
# here is how to use it

semaphore = asyncio.Semaphore(2)

async def limit_task(i):
    async with semaphore:
        print(f" Task {i} running")
        await asyncio.sleep(2)
        print(f"Task {i} done")


async def main():
    await asyncio.gather(*(limit_task(i) for i in range(5)))

if __name__ == "__main__":
    asyncio.run(main())