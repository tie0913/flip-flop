📚 Scala + Akka Learning Syllabus (6 Weeks)
✅ Phase 1: Scala Fundamentals (Weeks 1–2)
📘 Week 1 – Core Scala Syntax & Functional Programming Basics
val vs var, expressions, string interpolation

case class, pattern matching with match

Immutable collections: List, Set, Map

Option, Either, Try: safe modeling & error handling

Functional combinators: map, flatMap, filter, fold

Mini project: Type-safe config loader

📘 Week 2 – Intermediate Scala & Project Setup
Higher-order functions and anonymous functions

for-comprehension and monadic chaining

trait, abstract class, sealed modeling

Implicits & given/using (preparing for Akka dependency injection)

Project structure with sbt/Gradle

Mini project: Type-safe config DSL with validations

✅ Phase 2: Akka Concurrency & System Design (Weeks 3–6)
🎭 Week 3 – Akka Typed Actor Basics
What is the Actor model? Isolation + message-passing

Creating ActorSystem

Defining Behavior, Receive, and message types

Spawning and messaging: context.spawn, !, ask pattern

Lifecycle management: PreStart, PostStop

Mini project: Concurrent task executor (simulating task queue)

🧠 Week 4 – Supervision, Routers, and Scheduling
Routing (PoolRouter, GroupRouter)

Supervision strategies: Restart, Resume, Stop

Scheduling with Timers and Scheduler

Mini project: Kafka-like controller logic using actors

🌊 Week 5 – Akka Streams for Reactive Data Flow
Building flows: Source → Flow → Sink

Backpressure and flow control

Materialization and runtime stream composition

Mini project: Batched log processor (Flink-style ingestion)

🧪 Week 6 – Integration & Real-world Simulation
Web layer integration (Play or Akka HTTP): WebSocket + REST

Coordinated actor networks, routing, supervision

Building a Loki-compatible log ingestion microservice

Capstone project: A mini Spark DAG executor or Kafka batch writer

