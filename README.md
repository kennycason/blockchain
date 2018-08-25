BlockChain - Kotlin
===

A simple BlockChain implementation storing weight/date records.


## BlockChain Structure

![BlockChain](https://github.com/kennycason/blockchain/blob/master/blockchain.png?raw=true)

## Start Server

Run `BlockChainServer.kt` (Ktor + Jetty)


## View Default BlockChain 

```bash
curl -XGET localhost:8080
```
```json
{
  "chain" : [ {
    "index" : 0,
    "timestamp" : 0,
    "record" : {
      "weight" : 0.0,
      "date" : 0
    },
    "previous_hash" : "",
    "hash" : "2ac9a6746aca543af8dff39894cfe8173afba21eb01c6fae33d52947222855ef"
  } ]
}
```

## Add Record to BlockChain

```bash
curl -XPOST --data '{"weight":190.0, "date":1535015447}' -H 'Content-Type: application/json' localhost:8080
curl -XPOST --data '{"weight":188.0, "date":1535101847}' -H 'Content-Type: application/json' localhost:8080
curl -XPOST --data '{"weight":185.0, "date":1535188248}' -H 'Content-Type: application/json' localhost:8080
```

Viewing the BlockChain again will now result in four entries. The genesis block + three entries.

```bash
curl -XGET localhost:8080
```
```json
{
  "chain" : [ {
    "index" : 0,
    "timestamp" : 0,
    "record" : {
      "weight" : 0.0,
      "date" : 0
    },
    "previous_hash" : "",
    "hash" : "2ac9a6746aca543af8dff39894cfe8173afba21eb01c6fae33d52947222855ef"
  }, {
    "index" : 1,
    "timestamp" : 1535232343698,
    "record" : {
      "weight" : 190.0,
      "date" : 1535015447
    },
    "previous_hash" : "2ac9a6746aca543af8dff39894cfe8173afba21eb01c6fae33d52947222855ef",
    "hash" : "ecd6d0758d83c595aef3c29ed9c76453537e2710f12ff960581d4fd775ff8ffd"
  }, {
    "index" : 2,
    "timestamp" : 1535232343723,
    "record" : {
      "weight" : 188.0,
      "date" : 1535101847
    },
    "previous_hash" : "ecd6d0758d83c595aef3c29ed9c76453537e2710f12ff960581d4fd775ff8ffd",
    "hash" : "0ee658c050e185a807781173492052a391cd7f659873b11d8ba360b2cd05c6dc"
  }, {
    "index" : 3,
    "timestamp" : 1535232343746,
    "record" : {
      "weight" : 185.0,
      "date" : 1535188248
    },
    "previous_hash" : "0ee658c050e185a807781173492052a391cd7f659873b11d8ba360b2cd05c6dc",
    "hash" : "3d29a963baa917af7367f28dc68fa030feacb28177cc69512ca97f0ac2ecae58"
  } ]
}
```
