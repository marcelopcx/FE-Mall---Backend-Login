const router = require("express").Router();
const billingController = require("../controllers/billingController");

router.post("/create", billingController.create);
router.get("/read", billingController.read);
router.put("/update", billingController.update);
router.delete("/delete", billingController.delete);

module.exports = router;
