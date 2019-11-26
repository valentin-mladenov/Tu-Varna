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
    public class DeliveriesController : Controller
    {
        private PharmacyDbContext db = new PharmacyDbContext();

        // GET: Deliveries
        public ActionResult Index(string searchString)
        {
            if (string.IsNullOrWhiteSpace(searchString))
            {
                searchString = "";
            }

            var deliveries = db.Deliveries
                .Include(d => d.Counterparty)
                .ToList()
                .Where(d => d.Number.ToString().Contains(searchString)
                    || d.Counterparty.ToString().Contains(searchString))
                .OrderByDescending(d => d.DoneAt)
                .ThenBy(d => d.Number)
                .ToList();

            return View(deliveries);
        }

        // GET: Deliveries/Details/5
        public ActionResult Details(Guid? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Delivery delivery = db.Deliveries.Find(id);
            if (delivery == null)
            {
                return HttpNotFound();
            }
            return View(delivery);
        }

        // GET: Deliveries/Create
        public ActionResult Create()
        {
            ViewBag.CounterpartyId = new SelectList(db.Counterparties, "Id", "Stringed");
            return View();
        }

        // POST: Deliveries/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "Id,Number,DoneAt,CounterpartyId")] Delivery delivery)
        {
            var found = db.Deliveries.Where(s => s.Number == delivery.Number).FirstOrDefault();

            if (found != null)
            {
                ModelState.AddModelError("Number", "Number already exists in different delivery!!!");
            }

            if (ModelState.IsValid)
            {
                delivery.Id = Guid.NewGuid();
                db.Deliveries.Add(delivery);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            ViewBag.CounterpartyId = new SelectList(db.Counterparties, "Id", "Stringed", delivery.CounterpartyId);
            return View(delivery);
        }

        // GET: Deliveries/Edit/5
        public ActionResult Edit(Guid? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Delivery delivery = db.Deliveries.Find(id);
            if (delivery == null)
            {
                return HttpNotFound();
            }
            ViewBag.CounterpartyId = new SelectList(db.Counterparties, "Id", "Stringed", delivery.CounterpartyId);
            return View(delivery);
        }

        // POST: Deliveries/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "Id,Number,DoneAt,CounterpartyId")] Delivery delivery)
        {
            var found = db.Deliveries.Where(s => s.Number == delivery.Number && s.Id != delivery.Id).FirstOrDefault();

            if (found != null)
            {
                ModelState.AddModelError("Number", "Number already exists in different delivery!!!");
            }

            if (ModelState.IsValid)
            {
                db.Entry(delivery).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            ViewBag.CounterpartyId = new SelectList(db.Counterparties, "Id", "Stringed", delivery.CounterpartyId);
            return View(delivery);
        }

        // GET: Deliveries/Delete/5
        public ActionResult Delete(Guid? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Delivery delivery = db.Deliveries.Find(id);
            if (delivery == null)
            {
                return HttpNotFound();
            }
            return View(delivery);
        }

        // POST: Deliveries/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(Guid id)
        {
            Delivery delivery = db.Deliveries.Find(id);
            try
            {
                db.Deliveries.Remove(delivery);
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            catch (InvalidOperationException ex)
            {
                ViewBag.Error = "Delete cannot be Done! Remove all releted deliveries and sells!";

                return View(delivery);
            }
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
