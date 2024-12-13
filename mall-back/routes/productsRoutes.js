const router = require("express").Router();
const productsController = require("../controllers/productsController");

router.post("/createComida", productsController.createComida);
router.post("/createLibreria", productsController.createLibreria);
router.post("/createTecnologia", productsController.createTecnologia);
router.post("/createRopa", productsController.createRopa);

router.get("/readComida", productsController.readComida);
router.get("/readLibreria", productsController.readLibreria);
router.get("/readTecnologia", productsController.readTecnologia);
router.get("/readRopa", productsController.readRopa);

module.exports = router;
