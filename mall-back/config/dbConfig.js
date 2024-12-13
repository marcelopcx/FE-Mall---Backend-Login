const { MongoClient, ServerApiVersion } = require("mongodb");
const uri =
  "mongodb+srv://davidpaz06:5YvFl0nyuZIYYDPy@cluster0.kecx8.mongodb.net/FE-Mall?retryWrites=true&w=majority&appName=Cluster0";

const client = new MongoClient(uri, {
  serverApi: {
    version: ServerApiVersion.v1,
    strict: true,
    deprecationErrors: true,
  },
});

module.exports = { client, uri, name: "FE-Mall" };
