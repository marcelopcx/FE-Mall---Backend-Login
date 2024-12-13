const createComida = async (req, res) => {
  try {
    const result = await global.db.insert("Comida", {
      food_name: req.body.food_name,
      ingredients: req.body.ingredients,
      price: req.body.price,
      detail: req.body.detail,
      size: req.body.size,
    });
    res.json(result);
  } catch (err) {
    res.status(500).send("Error al crear el producto: " + err.message);
  }
};

const createRopa = async (req, res) => {
  try {
    const result = await global.db.insert("Ropa", {
      brand: req.body.brand,
      category: req.body.category,
      price: req.body.price,
      detail: req.body.detail,
      size: req.body.size,
      genre: req.body.genre,
    });
    res.json(result);
  } catch (err) {
    res.status(500).send("Error al crear el producto: " + err.message);
  }
};

const createTecnologia = async (req, res) => {
  try {
    const result = await global.db.insert("Tecnologia", {
      make: req.body.make,
      model: req.body.model,
      category: req.body.category,
      price: req.body.price,
      detail: req.body.detail,
    });
    res.json(result);
  } catch (err) {
    res.status(500).send("Error al crear el producto: " + err.message);
  }
};

const createLibreria = async (req, res) => {
  try {
    const result = await global.db.insert("Libreria", {
      book_name: req.body.title,
      author: req.body.author,
      genre: req.body.genre,
      editorial: req.body.editorial,
      price: req.body.price,
      detail: req.body.detail,
    });
    res.json(result);
  } catch (err) {
    res.status(500).send("Error al crear el producto: " + err.message);
  }
};

const readComida = async (req, res) => {
  try {
    const products = await global.db.find(
      "Comida",
      {},
      { _id: 1, food_name: 1, ingredientes: 1, price: 1, detail: 1, size: 1 }
    );
    res.json(products);
  } catch (err) {
    res.status(500).send("Error al obtener los productos: " + err.message);
  }
};

const readRopa = async (req, res) => {
  try {
    const products = await global.db.find(
      "Ropa",
      {},
      { _id: 1, brand: 1, category: 1, price: 1, detail: 1, size: 1, genre: 1 }
    );
    res.json(products);
  } catch (err) {
    res.status(500).send("Error al obtener los productos: " + err.message);
  }
};

const readTecnologia = async (req, res) => {
  try {
    const products = await global.db.find(
      "Tecnologia",
      {},
      { _id: 1, make: 1, model: 1, category: 1, price: 1, detail: 1 }
    );
    res.json(products);
  } catch (err) {
    res.status(500).send("Error al obtener los productos: " + err.message);
  }
};

const readLibreria = async (req, res) => {
  try {
    const products = await global.db.find(
      "Libreria",
      {},
      {
        _id: 1,
        book_name: 1,
        editorial: 1,
        author: 1,
        genre: 1,
        price: 1,
        detail: 1,
      }
    );
    res.json(products);
  } catch (err) {
    res.status(500).send("Error al obtener los productos: " + err.message);
  }
};

module.exports = {
  readComida,
  readLibreria,
  readTecnologia,
  readRopa,
  createComida,
  createLibreria,
  createTecnologia,
  createRopa,
};
