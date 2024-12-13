const Bill = require("../models/billingModel");

exports.createBill = async (req, res) => {
  try {
    const bill = new Bill(req.body);
    await bill.save();
    res.status(201).send(bill);
  } catch (error) {
    res.status(400).send(error);
  }
};

exports.readBill = async (req, res) => {
  try {
    const bills = await Bill.find();
    res.status(200).send(bills);
  } catch (error) {
    res.status(400).send(error);
  }
};

exports.updateBill = async (req, res) => {
  try {
    const bill = await Bill.findByIdAndUpdate(req.params.id, req.body, {
      new: true,
    });
    res.status(200).send(bill);
  } catch (error) {
    res.status(400).send(error);
  }
};

exports.deleteBill = async (req, res) => {
  try {
    await Bill.findByIdAndDelete(req.params.id);
    res.status(200).send({ message: "Bill deleted successfully" });
  } catch (error) {
    res.status(400).send(error);
  }
};
