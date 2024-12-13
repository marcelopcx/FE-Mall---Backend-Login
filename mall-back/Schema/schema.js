const mongoose = require("mongoose");

const schemaOptions = {
  versionKey: false,
};

module.exports = {
  Comida: mongoose.Schema(
    {
      food_name: { type: String, default: "No name provided" },
      size: { type: String, default: "No size provided" },
      ingredients: { type: String, default: "No ingredients provided" },
      price: { type: Number, default: 0 },
      detail: { type: String, default: "No detail provided" },
    },
    { collection: "Comida" },
    schemaOptions
  ),
  Factura: mongoose.Schema(
    {
      rif: { type: String, default: "No RIF provided" },
      shop: { type: String, default: "No shop provided" },
      date: { type: Date, default: Date.now },
      products: { type: Object, default: {} },
      subtotal: { type: Number, default: 0 },
      tax: { type: Number, default: 0 },
    },
    { collection: "Factura" },
    schemaOptions
  ),
  Libreria: mongoose.Schema(
    {
      book_name: { type: String, default: "No book name provided" },
      editorial: { type: String, default: "No editorial provided" },
      author: { type: String, default: "No author provided" },
      genre: { type: String, default: "No genre provided" },
      price: { type: Number, default: 0 },
      detail: { type: String, default: "No detail provided" },
    },
    { collection: "Libreria" },
    schemaOptions
  ),
  Ropa: mongoose.Schema(
    {
      brand: { type: String, default: "No brand provided" },
      category: { type: String, default: "No category provided" },
      size: { type: mongoose.Schema.Types.Mixed, default: "No size provided" },
      genre: { type: String, default: "No genre provided" },
      price: { type: Number, default: 0 },
      detail: { type: String, default: "No detail provided" },
    },
    { collection: "Ropa" },
    schemaOptions
  ),
  Tecnologia: mongoose.Schema(
    {
      make: { type: String, default: "No make provided" },
      model: { type: String, default: "No model provided" },
      category: { type: String, default: "No category provided" },
      price: { type: Number, default: 0 },
      detail: { type: String, default: "No detail provided" },
    },
    { collection: "Tecnologia" },
    schemaOptions
  ),
};
