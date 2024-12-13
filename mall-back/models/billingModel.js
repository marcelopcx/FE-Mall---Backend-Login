const mongoose = require("mongoose");

const schemaOptions = {
  timestamps: true,
};

const BillingSchema = new mongoose.Schema(
  {
    rif: { type: String, default: "No RIF provided" },
    shop: { type: String, default: "No shop provided" },
    date: { type: Date, default: Date.now },
    products: { type: Object, default: {} },
    subtotal: { type: Number, default: 0 },
    tax: { type: Number, default: 0 },
  },
  { collection: "Billing" },
  schemaOptions
);

module.exports = mongoose.model("Billing", BillingSchema);
