BlockChain - Kotlin
===

A simple BlockChain implementation storing weight/date records.

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
    "timestamp" : 1535188264437,
    "record" : {
      "weight" : 0.0,
      "date" : 0
    },
    "previous_hash" : "",
    "hash" : "aa1da948d68d2c93bfb5179b519b1b73f53a96784316dc54b6c0f8aa276cee56"
  } ]
}%
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
    "timestamp" : 1535188264437,
    "record" : {
      "weight" : 0.0,
      "date" : 0
    },
    "previous_hash" : "",
    "hash" : "aa1da948d68d2c93bfb5179b519b1b73f53a96784316dc54b6c0f8aa276cee56"
  }, {
    "index" : 1,
    "timestamp" : 1535188334202,
    "record" : {
      "weight" : 190.0,
      "date" : 1535015447
    },
    "previous_hash" : "aa1da948d68d2c93bfb5179b519b1b73f53a96784316dc54b6c0f8aa276cee56",
    "hash" : "0bd53309cf67603be74b846caa7b7411828a7f2f0411884e623ac57d244227e2"
  }, {
    "index" : 2,
    "timestamp" : 1535188334225,
    "record" : {
      "weight" : 188.0,
      "date" : 1535101847
    },
    "previous_hash" : "0bd53309cf67603be74b846caa7b7411828a7f2f0411884e623ac57d244227e2",
    "hash" : "7b2c06c64850a5aeb68cae164e0b16129b480e819aea0941e5f229ea8027901b"
  }, {
    "index" : 3,
    "timestamp" : 1535188334247,
    "record" : {
      "weight" : 185.0,
      "date" : 1535188248
    },
    "previous_hash" : "7b2c06c64850a5aeb68cae164e0b16129b480e819aea0941e5f229ea8027901b",
    "hash" : "dc0dcdcd1be6a0c4e3f5c85e74e5cc8b958efeaadda238e70bd57899561d6764"
  } ]
}%
```