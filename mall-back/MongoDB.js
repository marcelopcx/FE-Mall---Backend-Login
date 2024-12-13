const { client, uri, name } = require("./config/dbConfig");
const schemas = require("./Schema/schema");
const mongoose = require("mongoose");
const fs = require("fs");
const csv = require("csv-parser");

class MongoDB {
  constructor() {
    this.client = client;
    this.dbName = name;
    this.db = this.client.db(this.dbName);
    this.mongoose = mongoose;
    this.models = new Map();
    this.connectToDatabase(uri);
    this.createModels(schemas);
  }

  // Background Operations

  async connectToDatabase(uri) {
    try {
      await this.mongoose.connect(uri);
    } catch (err) {
      console.error("Error al conectar a la base de datos", err);
    }
  }

  createModels(schemas) {
    for (const schemaName in schemas) {
      this.models.set(
        schemaName,
        this.mongoose.model(schemaName, schemas[schemaName])
      );
    }
  }

  async getCollection(collectionName) {
    try {
      const collection = this.models.get(collectionName);
      if (!collection) {
        throw new Error(
          `Modelo para la colección '${collectionName}' no encontrado`
        );
      }
      return collection;
    } catch (err) {
      console.error("Error al obtener colección", err);
    }
  }

  async exe(collectionName, operation) {
    try {
      const collection = await this.getCollection(collectionName);
      const res = await operation(collection);
      return res;
    } catch (err) {
      console.error("Error al ejecutar operación", err);
    } finally {
      await this.client.close();
    }
  }

  async parseCSV(filePath) {
    try {
      return await new Promise((resolve, reject) => {
        const documents = [];
        fs.createReadStream(filePath)
          .pipe(csv())
          .on("data", (data) => {
            documents.push(data);
          })
          .on("end", () => {
            console.log("Parsed data:", documents);
            resolve(documents);
          })
          .on("error", (error) => {
            console.error("Reading error:", error);
            reject(error);
          });
      });
    } catch (error) {
      console.error("Parsing error:", error);
      throw error;
    }
  }

  // CRUD Operations

  async insertCSV(collectionName, filePath) {
    const documents = await this.parseCSV(filePath);
    return this.insert(collectionName, documents);
  }

  async insert(collectionName, documents) {
    return this.exe(collectionName, async (collection) => {
      return Array.isArray(documents)
        ? await collection.insertMany(documents)
        : await collection.create(documents);
    });
  }

  async find(collectionName, filter, projection, findOne = false) {
    return this.exe(collectionName, async (collection) => {
      const query = findOne
        ? collection.findOne(filter, projection)
        : collection.find(filter, projection);
      const result = await query.exec();
      // console.log(
      //   `Resultado de la consulta en la colección '${collectionName}':`,
      //   result
      // );
      return result;
    });
  }

  async update(collectionName, filter, update, upsert = false) {
    return this.exe(collectionName, async (collection) => {
      const options = { upsert, setDefaultsOnInsert: true };
      return Array.isArray(filter)
        ? await collection.updateMany({ $or: filter }, update, options)
        : await collection.updateOne(filter, update, options);
    });
  }

  async delete(collectionName, filter) {
    return this.executeOperation(collectionName, async (collection) => {
      return Array.isArray(filter)
        ? await collection.deleteMany({ $or: filter })
        : await collection.deleteOne(filter);
    });
  }
}

module.exports = MongoDB;

const db = new MongoDB();

// db.find("Comida", {}, { _id: 0, name: 1, price: 1 });
// db.insert("Comida", { name: "Pizza", price: 10 });
