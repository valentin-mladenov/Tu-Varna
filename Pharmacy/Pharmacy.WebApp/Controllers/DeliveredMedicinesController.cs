using System;
using System.Data;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Net;
using System.Web.Mvc;
using Pharmacy.WebApp.Exceptions;
using Pharmacy.WebApp.Models;
using Pharmacy.WebApp.Models.DataAccessLayer;

namespace Pharmacy.WebApp.Controllers
{
    public class DeliveredMedicinesController : Controller
    {
        private PharmacyDbContext db = new PharmacyDbContext();

        // GET: DeliveredMedicines
        public ActionResult Index(string searchString)
        {
            if (string.IsNullOrWhiteSpace(searchString))
            {
                searchString = "";
            }

            var deliveredMedicines = db.DeliveredMedicines
                .Include(d => d.Delivery)
                .Include(d => d.Delivery.Counterparty)
                .Include(d => d.Medicine)
                .Where(dm => dm.Delivery.Number.ToString().Contains(searchString)
                    || dm.Quantity.ToString().Contains(searchString)
                    || dm.Delivery.Counterparty.Address.Contains(searchString)
                    || dm.Price.ToString().Contains(searchString)
                    || dm.Medicine.ToString().Contains(searchString))
                .OrderByDescending(dm => dm.Delivery.DoneAt)
                .ThenBy(dm => dm.Delivery.Number)
                .ThenBy(dm => dm.Medicine.Name);

            return View(deliveredMedicines.ToList());
        }

        // GET: DeliveredMedicines/Details/5
        public ActionResult Details(Guid? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            DeliveredMedicine deliveredMedicine = db.DeliveredMedicines.Find(id);
            if (deliveredMedicine == null)
            {
                return HttpNotFound();
            }
            return View(deliveredMedicine);
        }

        // GET: DeliveredMedicines/Create
        public ActionResult Create()
        {
            ViewBag.DeliveryId = new SelectList(db.Deliveries, "Id", "Number");
            ViewBag.MedicineId = new SelectList(db.Medicines, "Id", "Name");
            return View();
        }

        // POST: DeliveredMedicines/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "Id,Price,Quantity,MedicineId,DeliveryId")] DeliveredMedicine deliveredMedicine)
        {
            var error = string.Empty;

            if (ModelState.IsValid)
            {
                try
                {
                    deliveredMedicine.Id = Guid.NewGuid();
                    db.DeliveredMedicines.Add(deliveredMedicine);
                    db.SaveChanges();
                    return RedirectToAction("Index");
                }
                catch (DbUpdateException ex)
                {
                    error = ex.GetDeepestMessage();
                }

            }

            ViewBag.DeliveryId = new SelectList(db.Deliveries, "Id", "Number", deliveredMedicine.DeliveryId);
            ViewBag.MedicineId = new SelectList(db.Medicines, "Id", "Name", deliveredMedicine.MedicineId);
            ViewBag.Error = error;

            return View(deliveredMedicine);
        }

        // GET: DeliveredMedicines/Edit/5
        public ActionResult Edit(Guid? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            DeliveredMedicine deliveredMedicine = db.DeliveredMedicines.Find(id);
            if (deliveredMedicine == null)
            {
                return HttpNotFound();
            }
            ViewBag.DeliveryId = new SelectList(db.Deliveries, "Id", "Number", deliveredMedicine.DeliveryId);
            ViewBag.MedicineId = new SelectList(db.Medicines, "Id", "Name", deliveredMedicine.MedicineId);
            return View(deliveredMedicine);
        }

        // POST: DeliveredMedicines/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "Id,Price,Quantity,MedicineId,DeliveryId")] DeliveredMedicine deliveredMedicine)
        {
            var error = string.Empty;

            if (ModelState.IsValid)
            {
                try
                {
                    db.Entry(deliveredMedicine).State = EntityState.Modified;
                    db.SaveChanges();
                    return RedirectToAction("Index");
                }
                catch (DbUpdateException ex)
                {
                    error = ex.GetDeepestMessage();
                }
            }

            ViewBag.DeliveryId = new SelectList(db.Deliveries, "Id", "Number", deliveredMedicine.DeliveryId);
            ViewBag.MedicineId = new SelectList(db.Medicines, "Id", "Name", deliveredMedicine.MedicineId);
            ViewBag.Error = error;

            return View(deliveredMedicine);
        }

        // GET: DeliveredMedicines/Delete/5
        public ActionResult Delete(Guid? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            DeliveredMedicine deliveredMedicine = db.DeliveredMedicines.Find(id);
            if (deliveredMedicine == null)
            {
                return HttpNotFound();
            }
            return View(deliveredMedicine);
        }

        // POST: DeliveredMedicines/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(Guid id)
        {
            DeliveredMedicine deliveredMedicine = db.DeliveredMedicines.Find(id);
            db.DeliveredMedicines.Remove(deliveredMedicine);
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
