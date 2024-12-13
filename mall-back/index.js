const express = require("express");
const cors = require("cors");

const app = express();
const corsConfig = require("./config/corsConfig");

global.db = new (require("./MongoDB"))();

// const billingRoutes = require("./routes/billingRoutes");
const productsRoutes = require("./routes/productsRoutes");

app.use(cors(corsConfig));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));

// app.use("/api/billing", billingRoutes);
app.use("/api/products", productsRoutes);

app.listen(3000, () => {
  console.log("Server is running on port 3000");
});
