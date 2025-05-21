import asyncio


#
# Class of Message Queue with capacity parameter
#
# 1. each method we want to support async should be decorated by async keyword in front of the def key word
# 2. when we invoke each method defined with async keyword, we should use await keyword.
# 3. the lock and condition are quiet similar with Lock and Condition in Java
# 4. async with self.condition is the lock command
# 5. use wait and nofity on condition object
# 6. asyncio.run is the enterance of coroutine system this one can only be invoked once in your project
# 7. asyncio.gather can start multiple coroutine method at the same time and gather the result


# asyncui has provided a queue so we do not need to do these when actually developing programs
class MessageQueue:

    def __init__(self, capacity:int):
        self.queue = []
        self.capacity = capacity
        self.lock = asyncio.Lock()
        self.condition = asyncio.Condition(self.lock)

    
    async def push(self, message:str):
        async with self.condition:
            while(len(self.queue) == self.capacity):
                await self.condition.wait()

            self.queue.append(message)
            self.condition.notify()


    async def get(self):
        res = None
        async with self.condition:
            while(len(self.queue) == 0):
                await self.condition.wait()
            res = self.queue[-1];
            self.queue.remove(res)
            self.condition.notify()

        return res


class Producer:

    def __init__(self, queue: MessageQueue):
        self.queue = queue

    async def put(self):
        count = 0
        while True:
            await self.queue.push(f"message {count}")
            count += 1
            await asyncio.sleep(1)


class Consumer:
    
    def __init__(self, queue: MessageQueue):
        self.queue = queue

    async def get(self):
        while True:
            message = await self.queue.get()
            print(message)
            await asyncio.sleep(1)

async def main():
    queue = MessageQueue(10)
    p = Producer(queue)
    c = Consumer(queue)
    await asyncio.gather(p.put(), c.get())

if __name__ == "__main__":
    asyncio.run(main())