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
    public class SellsController : Controller
    {
        private PharmacyDbContext db = new PharmacyDbContext();

        // GET: Sells
        public ActionResult Index()
        {
            var sells = db.Sells.Include(s => s.Counterparty);
            return View(sells.ToList());
        }

        // GET: Sells/Details/5
        public ActionResult Details(Guid? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Sell sell = db.Sells.Find(id);
            if (sell == null)
            {
                return HttpNotFound();
            }
            return View(sell);
        }

        // GET: Sells/Create
        public ActionResult Create()
        {
            ViewBag.CounterpartyId = new SelectList(db.Counterparties, "Id", "Address");
            return View();
        }

        // POST: Sells/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "Id,Number,DoneAt,CounterpartyId")] Sell sell)
        {
            if (ModelState.IsValid)
            {
                sell.Id = Guid.NewGuid();
                db.Sells.Add(sell);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            ViewBag.CounterpartyId = new SelectList(db.Counterparties, "Id", "Address", sell.CounterpartyId);
            return View(sell);
        }

        // GET: Sells/Edit/5
        public ActionResult Edit(Guid? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Sell sell = db.Sells.Find(id);
            if (sell == null)
            {
                return HttpNotFound();
            }
            ViewBag.CounterpartyId = new SelectList(db.Counterparties, "Id", "Address", sell.CounterpartyId);
            return View(sell);
        }

        // POST: Sells/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "Id,Number,DoneAt,CounterpartyId")] Sell sell)
        {
            if (ModelState.IsValid)
            {
                db.Entry(sell).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            ViewBag.CounterpartyId = new SelectList(db.Counterparties, "Id", "Address", sell.CounterpartyId);
            return View(sell);
        }

        // GET: Sells/Delete/5
        public ActionResult Delete(Guid? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Sell sell = db.Sells.Find(id);
            if (sell == null)
            {
                return HttpNotFound();
            }
            return View(sell);
        }

        // POST: Sells/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(Guid id)
        {
            Sell sell = db.Sells.Find(id);
            db.Sells.Remove(sell);
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
