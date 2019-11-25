using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;
using Pharmacy.WebApp.Models;
using Pharmacy.WebApp.Models.DataAccessLayer;

namespace Pharmacy.WebApp.Controllers
{
    public class SoldMedicinesController : Controller
    {
        private PharmacyDbContext db = new PharmacyDbContext();

        // GET: SoldMedicines
        public ActionResult Index(string searchString)
        {
            if (string.IsNullOrWhiteSpace(searchString))
            {
                searchString = "";
            }

            var soldMedicines = db.SoldMedicines
                .Include(d => d.Sell)
                .Include(d => d.Sell.Counterparty)
                .Include(d => d.Medicine)
                .Where(dm => dm.Sell.Number.ToString().Contains(searchString)
                    || dm.Quantity.ToString().Contains(searchString)
                    || dm.Sell.Counterparty.Address.Contains(searchString)
                    || dm.Price.ToString().Contains(searchString)
                    || dm.Medicine.ToString().Contains(searchString))
                .OrderByDescending(dm => dm.Sell.DoneAt)
                .ThenBy(dm => dm.Sell.Number)
                .ThenBy(dm => dm.Medicine.Name);

            return View(soldMedicines.ToList());
        }

        // GET: SoldMedicines/Details/5
        public ActionResult Details(Guid? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            SoldMedicine soldMedicine = db.SoldMedicines.Find(id);
            if (soldMedicine == null)
            {
                return HttpNotFound();
            }
            return View(soldMedicine);
        }

        // GET: SoldMedicines/Create
        public ActionResult Create()
        {
            ViewBag.MedicineId = new SelectList(db.Medicines, "Id", "Name");
            ViewBag.SellId = new SelectList(db.Sells, "Id", "Id");
            return View();
        }

        // POST: SoldMedicines/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "Id,Price,Quantity,MedicineId,SellId")] SoldMedicine soldMedicine)
        {
            if (ModelState.IsValid)
            {
                soldMedicine.Id = Guid.NewGuid();
                db.SoldMedicines.Add(soldMedicine);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            ViewBag.MedicineId = new SelectList(db.Medicines, "Id", "Name", soldMedicine.MedicineId);
            ViewBag.SellId = new SelectList(db.Sells, "Id", "Id", soldMedicine.SellId);
            return View(soldMedicine);
        }

        // GET: SoldMedicines/Edit/5
        public ActionResult Edit(Guid? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            SoldMedicine soldMedicine = db.SoldMedicines.Find(id);
            if (soldMedicine == null)
            {
                return HttpNotFound();
            }
            ViewBag.MedicineId = new SelectList(db.Medicines, "Id", "Name", soldMedicine.MedicineId);
            ViewBag.SellId = new SelectList(db.Sells, "Id", "Id", soldMedicine.SellId);
            return View(soldMedicine);
        }

        // POST: SoldMedicines/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "Id,Price,Quantity,MedicineId,SellId")] SoldMedicine soldMedicine)
        {
            if (ModelState.IsValid)
            {
                db.Entry(soldMedicine).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            ViewBag.MedicineId = new SelectList(db.Medicines, "Id", "Name", soldMedicine.MedicineId);
            ViewBag.SellId = new SelectList(db.Sells, "Id", "Id", soldMedicine.SellId);
            return View(soldMedicine);
        }

        // GET: SoldMedicines/Delete/5
        public ActionResult Delete(Guid? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            SoldMedicine soldMedicine = db.SoldMedicines.Find(id);
            if (soldMedicine == null)
            {
                return HttpNotFound();
            }
            return View(soldMedicine);
        }

        // POST: SoldMedicines/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(Guid id)
        {
            SoldMedicine soldMedicine = db.SoldMedicines.Find(id);
            db.SoldMedicines.Remove(soldMedicine);
            db.SaveChanges();
            return RedirectToAction("Index");
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }
    }
}
