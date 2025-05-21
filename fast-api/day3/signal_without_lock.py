
import asyncio

# if we have multiple coroutines, which do not need to visit common resources and only need to wait for some event
# we may use event simply.

event = asyncio.Event()

async def wait_for_signal():

    print("Here is wait for signal, I am going to wait the event")
    await event.wait()
    print("Here is wait for signal, I am woken up by event")


async def send_a_signal():

    print("I am going to send a signal after sleep 10 seconds")
    await asyncio.sleep(10)
    print("I am back, I am going to send a signal")
    # be careful the set method of event is not an async method
    event.set()
    print("I have set the event")


async def main():
    await asyncio.gather(send_a_signal(), wait_for_signal())

if __name__ == "__main__":
    asyncio.run(main())
