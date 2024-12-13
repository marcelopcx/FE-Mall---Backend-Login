const mongoose = require("mongoose");

const schemaOptions = {
  timestamps: true,
};

const ProductsSchema = new mongoose.Schema(
  {
    name: { type: String, required: true },
    price: { type: Number, required: true },
    stock: { type: Number, required: true },
    description: { type: String, required: true },
    category: { type: String, required: true },
    image: { type: String, required: true },
  },
  { collection: "Products" },
  schemaOptions
);
