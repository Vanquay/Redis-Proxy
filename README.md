# Description
This repo simulates a database for a soccer team and helps retrive Soccer players information. The user hits an API that checks a redis cache before going to an imaginary MYSQL database or returning the result from the stored cache results.

## Architecture Overview
![Architecture](images/Architecture.jpg)
## Algorithmic complexity of Cache
All get, put and evict operations have a time complexity of O(1)

## Build and Test
Navigate to the root directory and run
```
make test

```
## Requirements not implemented
Parallel Concurrent Processing - Allocated Time ran out.
Redis Client Protocol - Need more time to research it.
